package com.rekahdo.goodshop.profile_service.dtos.clients;

import com.rekahdo.goodshop.profile_service.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProfileClient implements ApiClient {

    private Long id;
    private Long userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Integer age;
    private String gender;

}