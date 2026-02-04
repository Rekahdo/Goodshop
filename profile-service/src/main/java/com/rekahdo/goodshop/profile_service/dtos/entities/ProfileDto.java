package com.rekahdo.goodshop.profile_service.dtos.entities;

import com.rekahdo.goodshop.profile_service.enums.Gender;
import com.rekahdo.goodshop.profile_service.feign.dtos.PhoneClient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.net.URI;
import java.time.LocalDate;

@Getter
@Setter
@Relation(collectionRelation = "profiles", itemRelation = "profile")
public class ProfileDto implements ApiDto{

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Integer age;
    private Gender gender;
    private Long userId;

}