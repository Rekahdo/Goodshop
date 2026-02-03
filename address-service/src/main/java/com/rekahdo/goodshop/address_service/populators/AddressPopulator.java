package com.rekahdo.goodshop.address_service.populators;//package com.rekahdo.goodshop.address_service.populators;
//
//import com.rekahdo.goodshop.address_service.dtos.request.AddressRequest;
//import com.rekahdo.goodshop.address_service.enums.AddressPurpose;
//import com.rekahdo.goodshop.address_service.repositories.AddressRepository;
//import com.rekahdo.goodshop.address_service.services.AddressService;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Component;
//
//@Component
//@Profile("dev")
//@RequiredArgsConstructor
//public class AddressPopulator {
//
//    private final AddressRepository repository;
//    private final AddressService addressService;
//
//    private final String KILO = "kilo";
//    private final String CV = "Computer Village Under Bridge";
//    private final String ODUDUWA = "oduduwa";
//    private final String SHOTAYO = "shotayo hughes";
//    private final String OBAFEMI = "Obafemi Awolowo";
//    private final String SURULERE = "surulere";
//    private final String IKEJA = "ikeja";
//    private final String LAGOS = "lagos";
//    private final String NIGERIA = "nigeria";
//
//    @PostConstruct
//    private void insert() {
//        if (!repository.findAll().isEmpty())
//            return;
//
//        addressService.set(2L, AddressPurpose.PROFILE, new AddressRequest(
//                "Rekahdo", 2, 25, OBAFEMI, null, CV,
//                IKEJA, IKEJA, LAGOS, NIGERIA, 356738L));
//
//        addressService.set(3L, AddressPurpose.PROFILE, new AddressRequest(
//                "JB Gold", 2, 25, SHOTAYO, ODUDUWA, KILO,
//                SURULERE, SURULERE, LAGOS, NIGERIA, 235437L));
//
//        addressService.set(4L, AddressPurpose.PROFILE, new AddressRequest(
//                "Mary", 2, 25, SHOTAYO, null, null,
//                SURULERE, SURULERE, LAGOS, NIGERIA, 235437L));
//
//    }
//
//}
