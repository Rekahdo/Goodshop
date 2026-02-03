package com.rekahdo.goodshop.address_service.entities;

import com.rekahdo.goodshop.address_service.enums.AddressPurpose;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "addresses")
public class Address implements ApiEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer purpose;
	private String name;

	@Column(name = "flat_no")
	private String flatNo;

	@Column(name = "house_no")
	private Integer houseNo;

	@ManyToOne
	@JoinColumn(name = "street_id")
	private Street street;

	@ManyToOne
	@JoinColumn(name = "off_street_id")
	private Street offStreet;

	@ManyToOne
	@JoinColumn(name = "closest_bus_stop_id")
	private BusStop busStop;

	@ManyToOne
	@JoinColumn(name = "lga_id")
	private LGA lga;

	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;

	@ManyToOne
	@JoinColumn(name = "state_id")
	private State state;

	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;

	@ManyToOne
	@JoinColumn(name = "zipcode_id")
	private Zipcode zipcode;

	@Column(name = "user_id")
	private Long userId;

	public Address(Long userId, AddressPurpose purpose) {
		this.userId = userId;
		this.purpose = purpose.index;
	}
}
