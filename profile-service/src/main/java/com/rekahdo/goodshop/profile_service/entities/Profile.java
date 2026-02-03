package com.rekahdo.goodshop.profile_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "profiles")
@NoArgsConstructor
@AllArgsConstructor
public class Profile implements ApiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(name = "fn")
    private String firstName;

    @Column(name = "mn")
    private String middleName;

    @Column(name = "ln")
    private String lastName;

    @Column(name = "dob")
    private LocalDate dateOfBirth;

    private int gender;

    public Profile(Long userId) {
        this.userId = userId;
    }

}
