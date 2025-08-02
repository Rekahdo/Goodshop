package com.rekahdo.ecommerce.goodshop._entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
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

    private String name;

    @Column(name = "houseNumber")
    private Integer houseNo;

    private String street;

    private String city;

    private String zipCode;

    private String state;

    @Pattern(regexp = "^\\+\\d{1,3}-[1-9]\\d{9}$",
            message = "Specify a valid country code and a valid 10 digit phone number")
    private String phoneNumber;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    public Address(String name, Integer houseNo, String street, String city, String state, String phoneNumber, AppUser appUser) {
        this.name = name;
        this.houseNo = houseNo;
        this.street = street;
        this.city = city;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.appUser = appUser;
    }

}