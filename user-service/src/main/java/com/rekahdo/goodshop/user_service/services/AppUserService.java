package com.rekahdo.goodshop.user_service.services;

import com.rekahdo.goodshop.user_service.dtos.entities.AppUserDto;
import com.rekahdo.goodshop.user_service.dtos.paginations.AccountPageRequest;
import com.rekahdo.goodshop.user_service.dtos.assemblers.AppUserAssembler;
import com.rekahdo.goodshop.user_service.dtos.request.AccountRequest;
import com.rekahdo.goodshop.user_service.entities.AppUser;
import com.rekahdo.goodshop.user_service.exceptions.classes.*;
import com.rekahdo.goodshop.user_service.feign.clients.OtpServiceClient;
import com.rekahdo.goodshop.user_service.mappers.AppUserMapper;
import com.rekahdo.goodshop.user_service.repositories.AppUserRepository;
import com.rekahdo.goodshop.user_service.utilities.ApiKey;
import com.rekahdo.goodshop.user_service.utilities.UserIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository repository;
    private final AppUserMapper mapper;
    private final AppUserAssembler assembler;

    private final OtpServiceClient otpService;
    private final PasswordEncoder passwordEncoder;

    public Long add(AccountRequest request) {
        Optional<AppUser> optional = repository.findByUsernameOrEmail(request.username(), request.email());
        if(optional.isPresent()) throw new AccountExistsException(request.email());

        AppUser entity = mapper.toEntity(request);
        entity.setUid(UserIdGenerator.generateId());
        entity.setPassword(passwordEncoder.encode(request.password()));
        entity.setCreatedAt(LocalDateTime.now());
        repository.save(entity);

        return entity.getId();
    }

    public void verify(String email) {
        AppUser entity = repository.findByEmail(email)
                .orElseThrow(AccountNotFoundException::new);

        if(otpService.validateAcctVerificationOtp(entity.getId(), email, ApiKey.OTP_SERVICE)){
            entity.setVerified(true);
            repository.save(entity);
            otpService.deleteByEmail(email, ApiKey.OTP_SERVICE);
        }
    }

    public ResponseEntity<EntityModel<AppUserDto>> retrieveById(Long id) {
        AppUser entity = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                .body(assembler.toModel(mapper.toDto(entity)));
    }

    public PagedModel<EntityModel<AppUserDto>> retrieveAll(AccountPageRequest request) {
        Page<AppUser> userPage = repository.findAll(request.getPageable());
        Page<AppUserDto> dtoPage = userPage.map(mapper::toDto);
        return assembler.toPagedModel(dtoPage, request);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void editUsername(Long id, String username, String accountPassword) {
        AppUser entity = repository.findById(id)
                .orElseThrow(AccountNotFoundException::new);

        verifyPassword(accountPassword, entity.getPassword());

        entity.setUsername(username);
        repository.save(entity);
    }

    public void editEmail(Long id, String email, String accountPassword) {
        AppUser entity = repository.findById(id)
                .orElseThrow(AccountNotFoundException::new);

        verifyPassword(accountPassword, entity.getPassword());

        entity.setEmail(email);
        entity.setVerified(false);
        repository.save(entity);
    }

    public void editPassword(Long id, String password, String accountPassword) {
        AppUser entity = repository.findById(id)
                .orElseThrow(AccountNotFoundException::new);

        verifyPassword(accountPassword, entity.getPassword());

        entity.setPassword(passwordEncoder.encode(password));
        repository.save(entity);
    }

    private void verifyPassword(String rawPassword, String encodedPassword){
        boolean isRightUser = passwordEncoder.matches(rawPassword, encodedPassword);
        if(!isRightUser) throw new IncorrectPasswordException(rawPassword);
    }

}
