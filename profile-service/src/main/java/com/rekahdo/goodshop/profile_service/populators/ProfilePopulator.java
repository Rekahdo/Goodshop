//package com.rekahdo.goodshop.profile_service.populators;
//
//import com.rekahdo.goodshop.profile_service.dtos.request.DOBRequest;
//import com.rekahdo.goodshop.profile_service.dtos.request.ProfileRequest;
//import com.rekahdo.goodshop.profile_service.enums.Gender;
//import com.rekahdo.goodshop.profile_service.repositories.ProfileRepository;
//import com.rekahdo.goodshop.profile_service.services.ProfileService;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Component;
//
//@Component
//@Profile("!prod")
//@RequiredArgsConstructor
//public class ProfilePopulator {
//
//    private final ProfileRepository repository;
//    private final ProfileService service;
//
//    @PostConstruct
//    private void insert() {
//        if (!repository.findAll().isEmpty())
//            return;
//
//        service.setNames(2L, new ProfileRequest("Richard", "Tochukwu", "Okafor"));
//        service.setNames(3L, new ProfileRequest("John", "Chukwuemeka", "Okafor"));
//
//        service.setGender(2L, Gender.MALE);
//        service.setGender(3L, Gender.MALE);
//        service.setGender(4L, Gender.FEMALE);
//
//        service.setDob(2L, new DOBRequest("6", "5", "1999"));
//        service.setDob(3L, new DOBRequest("7", "6", "1997"));
//
//
//    }
//
//}
