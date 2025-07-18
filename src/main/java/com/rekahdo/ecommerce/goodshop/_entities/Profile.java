package com.rekahdo.ecommerce.goodshop._entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

    private String bio;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

}