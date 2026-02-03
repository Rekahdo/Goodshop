package com.rekahdo.goodshop.vendor_service.populators;//package com.rekahdo.goodshop.vendor_service.populators;
//
//import com.rekahdo.goodshop.vendor_service.dtos.request.BankRequest;
//import com.rekahdo.goodshop.vendor_service.enums.AccountType;
//import com.rekahdo.goodshop.vendor_service.repositories.BankRepository;
//import com.rekahdo.goodshop.vendor_service.services.BankService;
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
//public class BankPopulator {
//
//    private final BankRepository repository;
//    private final BankService service;
//
//    @PostConstruct
//    private void insert() {
//        if (!repository.findAll().isEmpty())
//            return;
//
//        service.set(2L, new BankRequest("Okafor Richard Tochukwu",
//                "Guaranty Trust Bank", "0243467243", AccountType.SAVINGS));
//    }
//
//}
