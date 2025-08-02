package com.rekahdo.ecommerce.goodshop._entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String middleName;
    private String lastName;
    private boolean isMale;
    private String bio;

    @Pattern(regexp = "^\\+\\d{1,3}-[1-9]\\d{9}$",
            message = "Specify a valid country code and a valid 10 digit phone number")
    private String phoneNumber;

    @Past(message = "Birth date must be in the past")
    private LocalDate dateOfBirth;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    public Profile(String firstName, String middleName, String lastName, String bio, boolean isMale, String phoneNumber, LocalDate dateOfBirth, AppUser appUser) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.bio = bio;
        this.isMale = isMale;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.appUser = appUser;
    }

    public Profile(AppUser appUser) {
        this.appUser = appUser;
    }
}