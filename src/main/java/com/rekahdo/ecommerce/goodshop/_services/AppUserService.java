package com.rekahdo.ecommerce.goodshop._services;

import com.rekahdo.ecommerce.goodshop._controllers.AppUserController;
import com.rekahdo.ecommerce.goodshop._dtos.entities.AppUserDto;
import com.rekahdo.ecommerce.goodshop._dtos.paginations.AppUserPageRequestDto;
import com.rekahdo.ecommerce.goodshop._entities.AppUser;
import com.rekahdo.ecommerce.goodshop._mappers.AppUserMapper;
import com.rekahdo.ecommerce.goodshop._repository.AppUserRepository;
import com.rekahdo.ecommerce.goodshop.exceptions.classes.EmptyListException;
import com.rekahdo.ecommerce.goodshop.exceptions.classes.UserIdNotFoundException;
import com.rekahdo.ecommerce.goodshop.exceptions.classes.UsernameExistException;
import com.rekahdo.ecommerce.goodshop.mappingJacksonValue.AppUserMJV;
import com.rekahdo.ecommerce.goodshop.security.jjwt.JwtSymmetricService;
import com.rekahdo.ecommerce.goodshop.utilities.AuthUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
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
	private AppUserMJV mjv;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtSymmetricService jwtService;

	public ResponseEntity<?> createUser(AppUserDto dto) {
		if (repo.existsByUsername(dto.getUsername()))
			throw new UsernameExistException(dto.getUsername());

		dto.setId(null);
		if(dto.getPassword() != null)
			dto.setPassword(passwordEncoder.encode(dto.getPassword()));

		dto.setCreatedAt(LocalDate.now());
		repo.save(mapper.toEntity(dto));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	public ResponseEntity<?> loginUser(AppUserDto dto) {
		Authentication usernamePassword = new UsernamePasswordAuthenticationToken(
				dto.getUsername(), dto.getPassword());

		Authentication authentication = authenticationManager.authenticate(usernamePassword);
		return ResponseEntity.ok(jwtService.createToken(authentication));
	}

	public ResponseEntity<?> editUser(Long userId, AppUserDto dto) {
		Optional<AppUser> optional = repo.findById(userId);
		if(optional.isEmpty()) throw new UserIdNotFoundException();

		if(dto.getPassword() != null)
			dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		dto.setUpdatedAt(LocalDate.now());

		AppUser user = optional.get();
		mapper.updateEntity(dto, user);
		mapper.toDto(repo.save(user));
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<?> getUsers(AppUserPageRequestDto dto) {
		Page<AppUser> users = repo.findAll(dto.getPageable());
		if (users.isEmpty()) throw new EmptyListException();

		Page<AppUserDto> userDtos = users.map(appUser -> {
			AppUserDto appUserDto = mapper.toDto(appUser);
			appUserDto.add(linkTo(methodOn(AppUserController.class).getUser(appUser.getId())).withSelfRel());
			return appUserDto;
		});
		PagedModel<AppUserDto> pagedModel = dto.getPagedModel(userDtos, methodOn(AppUserController.class).getUsers(dto));
		return ResponseEntity.ok(mjv.listFilter(pagedModel));
	}

	public ResponseEntity<?> getUser(Long id) {
		AppUser user = repo.findById(id).orElseThrow(UserIdNotFoundException::new);
		AppUserDto dto = mapper.toDto(user);

		if(AuthUser.IS_AN_ADMIN() || AuthUser.IS_A_MODERATOR())
			dto.add(linkTo(methodOn(AppUserController.class).getUsers(new AppUserPageRequestDto())).withRel("users"));

		return ResponseEntity.ok(mjv.selfFilter(dto));
	}

	public ResponseEntity<?> deleteUser(Long id) {
		repo.deleteById(id);
		return ResponseEntity.ok("USER WITH ID '" + id + "' HAS BEEN SUCCESSFULLY DELETED");
	}

}