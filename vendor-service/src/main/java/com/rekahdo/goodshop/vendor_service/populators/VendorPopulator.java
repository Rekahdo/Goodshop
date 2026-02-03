package com.rekahdo.goodshop.vendor_service.populators;//package com.rekahdo.goodshop.vendor_service.populators;
//
//import com.rekahdo.goodshop.vendor_service.dtos.request.BusinessRequest;
//import com.rekahdo.goodshop.vendor_service.repositories.VendorRepository;
//import com.rekahdo.goodshop.vendor_service.services.VendorService;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Component;
//
//@Component
//@Profile("!prod")
//@RequiredArgsConstructor
//public class VendorPopulator {
//
//    private final VendorRepository repository;
//    private final VendorService service;
//
//    @PostConstruct
//    private void insert() {
//        if (!repository.findAll().isEmpty())
//            return;
//
//        service.add(2L, new BusinessRequest("Rekahdo's Gadget Go",
//                null, "okaforrichard76@gmail.com"));
//    }
//
//}
