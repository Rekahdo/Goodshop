package com.rekahdo.ecommerce.goodshop._services;

import com.rekahdo.ecommerce.goodshop._controllers.AppUserController;
import com.rekahdo.ecommerce.goodshop._dtos.entities.AppUserDto;
import com.rekahdo.ecommerce.goodshop._dtos.paginations.AppUserPageRequestDto;
import com.rekahdo.ecommerce.goodshop._entities.AppUser;
import com.rekahdo.ecommerce.goodshop._mappers.AppUserMapper;
import com.rekahdo.ecommerce.goodshop._repository.AppUserRepository;
import com.rekahdo.ecommerce.goodshop._repository.AuthorityRepository;
import com.rekahdo.ecommerce.goodshop.enums.ErrorMessage;
import com.rekahdo.ecommerce.goodshop.exceptions.classes.EmptyListException;
import com.rekahdo.ecommerce.goodshop.exceptions.classes.UserIdNotFoundException;
import com.rekahdo.ecommerce.goodshop.exceptions.classes.UsernameExistException;
import com.rekahdo.ecommerce.goodshop.mappingJacksonValue.AppUserMJV;
import com.rekahdo.ecommerce.goodshop.security.jjwt.JwtSymmetricService;
import com.rekahdo.ecommerce.goodshop.utilities.AuthUser;
import com.rekahdo.ecommerce.goodshop.utilities.DBAdmin;
import com.rekahdo.ecommerce.goodshop.utilities.PageRequestUriBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@Transactional
public class AppUserService {

	@Autowired
	private AppUserRepository repo;

	@Autowired
	private AppUserMapper mapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtSymmetricService jwtService;

	@Autowired
	private PageRequestUriBuilder<AppUserDto, AppUserPageRequestDto> pageLinkBuilder;

	@Autowired
	private DBAdmin dbAdmin;

	@Value("${adminKey}")
	private String adminKey;

	public ResponseEntity<?> createUser(AppUserDto dto) {
		if (repo.existsByUsername(dto.getUsername()))
			throw new UsernameExistException(dto.getUsername());

		dto.setId(null);
		if(dto.getPassword() != null)
			dto.setPassword(passwordEncoder.encode(dto.getPassword()));

		if(dto.hasRoles()){
			if(dto.adminKeyIsNotValid(adminKey))
				throw new AccessDeniedException(ErrorMessage.ADMIN_KEY);
		}

		dto.setCreatedAt(Instant.now()); dto.setUpdatedAt(Instant.now());
		dto = mapper.toDto(repo.save(mapper.toEntity(dto)));
		MappingJacksonValue mappingJacksonValue = AppUserMJV.publicFilter(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(mappingJacksonValue);
	}

	public ResponseEntity<?> loginUser(AppUserDto dto) {
		Authentication usernamePassword = new UsernamePasswordAuthenticationToken(
				dto.getUsername(), dto.getPassword());

		Authentication authentication = authenticationManager.authenticate(usernamePassword);
		return ResponseEntity.ok(jwtService.createToken(authentication));
	}

	public ResponseEntity<?> editUser(Long userId, AppUserDto dto) {
		Optional<AppUser> optional = repo.findById(userId);
		if(optional.isEmpty())
			throw new UserIdNotFoundException(userId);

		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		dto.setUpdatedAt(Instant.now());

		if(dto.hasRoles()){
			if(AuthUser.IS_NOT_AN_ADMIN() && dto.adminKeyIsNotValid(adminKey))
				throw new AccessDeniedException(ErrorMessage.ADMIN_KEY);

			authorityRepository.deleteByAppUserId(userId);
		}

		AppUser user = optional.get();
		mapper.updateEntity(dto, user);
		dto = mapper.toDto(repo.save(user));
		return ResponseEntity.ok(AppUserMJV.privateFilter(dto,true));
	}

	public ResponseEntity<?> patchUser(Long userId, AppUserDto dto) {
		Optional<AppUser> optional = repo.findById(userId);
		if(optional.isEmpty())
			throw new UserIdNotFoundException(userId);

		if(dto.getPassword() != null)
			dto.setPassword(passwordEncoder.encode(dto.getPassword()));

		if(dto.hasRoles()){
			if(AuthUser.IS_NOT_AN_ADMIN() && dto.adminKeyIsNotValid(adminKey))
				throw new AccessDeniedException(ErrorMessage.ADMIN_KEY);

			authorityRepository.deleteByAppUserId(userId);
		}

		dto.setUpdatedAt(Instant.now());
		AppUser user = optional.get();
		mapper.updateEntity(dto, user);
		dto = mapper.toDto(repo.save(user));
		return ResponseEntity.ok(AppUserMJV.privateFilter(dto));
	}

	public ResponseEntity<?> getUsers(AppUserPageRequestDto dto) {
		Page<AppUser> users = repo.findAll(dto.getPageable(dto));
		if (users.isEmpty()) throw new EmptyListException();

		Page<AppUserDto> userDtos = users.map(mapper::toDto);
		PagedModel<AppUserDto> pagedModel = pageLinkBuilder.getPagedModel(dto, userDtos, methodOn(AppUserController.class).getUsers(dto));
		return ResponseEntity.ok(AppUserMJV.publicFilter(pagedModel));
	}

	public ResponseEntity<?> getUser(Long id) {
		AppUser user = repo.findById(id).orElseThrow(() -> new UserIdNotFoundException(id));
		AppUserDto dto = mapper.toDto(user);

		if(AuthUser.IS_AN_ADMIN() || AuthUser.IS_A_MODERATOR())
			dto.add(linkTo(methodOn(AppUserController.class).getUsers(new AppUserPageRequestDto())).withRel("users"));

		return ResponseEntity.ok(AppUserMJV.privateFilter(dto));
	}

	public ResponseEntity<?> deleteUser(Long id) {
		repo.deleteById(id);
		return ResponseEntity.ok("USER WITH ID '" + id + "' HAS BEEN SUCCESSFULLY DELETED");
	}
}