package com.rekahdo.ecommerce.goodshop._entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer houseNumber;

    private String street;

    private String city;

    private String zip;

    private String state;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

}