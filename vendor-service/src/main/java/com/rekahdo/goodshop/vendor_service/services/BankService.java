package com.rekahdo.goodshop.vendor_service.services;

import com.rekahdo.goodshop.vendor_service.dtos.request.BankRequest;
import com.rekahdo.goodshop.vendor_service.entities.Bank;
import com.rekahdo.goodshop.vendor_service.entities.Vendor;
import com.rekahdo.goodshop.vendor_service.enums.UnresolvedReason;
import com.rekahdo.goodshop.vendor_service.mappers.BankMapper;
import com.rekahdo.goodshop.vendor_service.repositories.BankRepository;
import com.rekahdo.goodshop.vendor_service.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankService {

    private final BankRepository repository;
    private final BankMapper mapper;

    private final VendorService vendorService;
    private final VendorRepository vendorRepository;
    private final UnresolvedService unresolvedService;

    public Long set(Long userId, BankRequest request) {
        Bank bank = retrieve(userId);
        mapper.updateEntity(request, bank);
        repository.save(bank);

        Vendor vendor = vendorService.findAndThrow(userId);
        vendor.setBank(new Bank(bank.getId()));
        vendorRepository.save(vendor);

        // Add unresolved if user has not added bank address
        if(!bank.isAddressAdded())
            unresolvedService.add(userId, UnresolvedReason.BANK_ADDRESS_NOT_PROVIDED);
        
        unresolvedService.delete(userId, UnresolvedReason.BANK_INFO_NOT_PROVIDED);

        return bank.getId();
    }

    public void setAddressAdded(Long userId, boolean added){
        repository.setAddressAdded(userId, added);
    }

    public Bank retrieve(Long userId){
        return repository.findByUserId(userId).orElseGet(Bank::new);
    }

}
