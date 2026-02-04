package com.rekahdo.goodshop.profile_service.services;

import com.rekahdo.goodshop.profile_service.dtos.assemblers.ProfileAssembler;
import com.rekahdo.goodshop.profile_service.dtos.entities.ProfileDto;
import com.rekahdo.goodshop.profile_service.dtos.request.DOBRequest;
import com.rekahdo.goodshop.profile_service.dtos.request.ProfileRequest;
import com.rekahdo.goodshop.profile_service.entities.Profile;
import com.rekahdo.goodshop.profile_service.enums.Gender;
import com.rekahdo.goodshop.profile_service.mappers.ProfileMapper;
import com.rekahdo.goodshop.profile_service.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository repository;
    private final ProfileMapper mapper;
    private final ProfileAssembler assembler;

    private Profile find(Long userId){
        return repository.findByUserId(userId).orElse(new Profile(userId));
    }

    public EntityModel<ProfileDto> retrieve(Long userId) {
        Profile profile = find(userId);
        return assembler.toModel(mapper.toDto(profile));
    }

    public void setNames(Long userId, ProfileRequest request) {
        Profile profile = find(userId);
        mapper.updateEntity(request, profile);
        repository.save(profile);
    }

    public void setDob(Long userId, DOBRequest request) {
        Profile profile = find(userId);

        if(profile.getDateOfBirth() != null)
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Date of Birth can only be modified once");

        LocalDate dob = LocalDate.of(Integer.parseInt(request.year()),
                Integer.parseInt(request.month()), Integer.parseInt(request.day()));

        LocalDate now = LocalDate.now();
        int age = Period.between(dob, now).getYears();

        if(age < 13) throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                "Age can not be below 13 years");

        profile.setDateOfBirth(dob);
        repository.save(profile);
    }

    public void setGender(Long userId, Gender gender) {
        Profile profile = find(userId);
        profile.setGender(gender.getIndex());
        repository.save(profile);
    }

}
