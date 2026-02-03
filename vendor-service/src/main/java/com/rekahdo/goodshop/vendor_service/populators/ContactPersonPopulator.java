package com.rekahdo.goodshop.vendor_service.populators;//package com.rekahdo.goodshop.vendor_service.populators;
//
//import com.rekahdo.goodshop.vendor_service.dtos.request.ContactRequest;
//import com.rekahdo.goodshop.vendor_service.enums.Role;
//import com.rekahdo.goodshop.vendor_service.enums.Title;
//import com.rekahdo.goodshop.vendor_service.repositories.ContactRepository;
//import com.rekahdo.goodshop.vendor_service.services.ContactService;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Component;
//
//@Component
//@Profile("!prod")
//@RequiredArgsConstructor
//@DependsOn("vendorPopulator")
//public class ContactPersonPopulator {
//
//    private final ContactRepository repository;
//    private final ContactService service;
//
//    @PostConstruct
//    private void insert() {
//        if (!repository.findAll().isEmpty())
//            return;
//
//        service.set(2L, new ContactRequest(Title.MR, "Richard", Role.OWNER));
//    }
//
//}