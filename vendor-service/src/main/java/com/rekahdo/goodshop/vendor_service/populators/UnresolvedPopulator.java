package com.rekahdo.goodshop.vendor_service.populators;//package com.rekahdo.goodshop.vendor_service.populators;
//
//import com.rekahdo.goodshop.vendor_service.enums.UnresolvedReason;
//import com.rekahdo.goodshop.vendor_service.repositories.UnresolvedRepository;
//import com.rekahdo.goodshop.vendor_service.services.UnresolvedService;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Component;
//
//@Component
//@Profile("!prod")
//@DependsOn("vendorPopulator")
//@RequiredArgsConstructor
//public class UnresolvedPopulator {
//
//    private final UnresolvedRepository repository;
//    private final UnresolvedService service;
//
//    @PostConstruct
//    private void insert() {
//        if (!repository.findAll().isEmpty())
//            return;
//
//        service.add(2L, UnresolvedReason.ACCOUNT_INCOMPLETE); // 1
//        service.add(2L, UnresolvedReason.CONTACT_PERSON_PHONE_NOT_PROVIDED); // 2
//        service.add(2L, UnresolvedReason.CONTACT_PERSON_EMERGENCY_PHONE_NOT_PROVIDED); // 3
//        service.add(2L, UnresolvedReason.BUSINESS_EMAIL_NOT_VERIFIED); // 4
//        service.add(2L, UnresolvedReason.BUSINESS_PHONE_NOT_PROVIDED); // 5
//        service.add(2L, UnresolvedReason.BANK_DETAILS_NOT_ADDED); // 6
//    }
//
//}
