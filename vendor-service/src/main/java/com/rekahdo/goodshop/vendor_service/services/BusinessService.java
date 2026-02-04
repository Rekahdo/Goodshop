package com.rekahdo.goodshop.vendor_service.services;

import com.rekahdo.goodshop.vendor_service.dtos.request.BusinessRequest;
import com.rekahdo.goodshop.vendor_service.entities.Business;
import com.rekahdo.goodshop.vendor_service.entities.Vendor;
import com.rekahdo.goodshop.vendor_service.enums.UnresolvedReason;
import com.rekahdo.goodshop.vendor_service.feign.clients.OtpServiceClient;
import com.rekahdo.goodshop.vendor_service.mappers.BusinessMapper;
import com.rekahdo.goodshop.vendor_service.repositories.BusinessRepository;
import com.rekahdo.goodshop.vendor_service.repositories.VendorRepository;
import com.rekahdo.goodshop.vendor_service.utilities.ApiKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BusinessService {

    private final BusinessRepository repository;
    private final BusinessMapper mapper;

    @Autowired @Lazy
    private VendorService vendorService;
    private final VendorRepository vendorRepository;

    private final UnresolvedService unresolvedService;
    private final OtpServiceClient otpService;

    public Long set(Long userId, BusinessRequest request) {
        Business business = find(userId);

        // If user sets a business email, add email not verified unresolved
        if(!Objects.equals(business.getEmail(), request.email()))
            unresolvedService.add(userId, UnresolvedReason.BUSINESS_EMAIL_NOT_VERIFIED);

        mapper.updateEntity(request, business);
        repository.save(business);

        Vendor vendor = vendorService.findOrThrow(userId);
        vendor.setBusiness(new Business(business.getId()));
        vendorRepository.save(vendor);

        List<UnresolvedReason> unresolvedReasons = new ArrayList<>();

        // Add unresolved if user has not added business phone
        if(!business.isPhoneAdded())
            unresolvedReasons.add(UnresolvedReason.BUSINESS_PHONE_NOT_PROVIDED);

        // Add unresolved if user has not added business address
        if(!business.isAddressAdded())
            unresolvedReasons.add(UnresolvedReason.BUSINESS_ADDRESS_NOT_PROVIDED);

        // Add unresolved if user has not added business address
        if(!business.isCertificateAdded())
            unresolvedReasons.add(UnresolvedReason.BUSINESS_CERTIFICATE_NOT_PROVIDED);

        unresolvedService.add(userId, unresolvedReasons);

        return business.getId();
    }

    public void verify(Long userId) {
        Business entity = find(userId);

        if(otpService.validateBusinessEmailVerificationOtp(userId, entity.getEmail(), ApiKey.OTP_SERVICE)){
            entity.setEmailVerified(true);
            repository.save(entity);
            otpService.deleteByEmail(entity.getEmail(), ApiKey.OTP_SERVICE);

            // When user verifies business email, remove email not verified unresolved
            unresolvedService.delete(userId, UnresolvedReason.BUSINESS_EMAIL_NOT_VERIFIED);
        }
    }

    public void setPhoneAdded(Long userId, boolean added){
        Business business = find(userId);
        business.setPhoneAdded(added);
        repository.save(business);
    }

    public void setAddressAdded(Long userId, boolean added){
        Business business = find(userId);
        business.setAddressAdded(added);
        repository.setAddressAdded(userId, added);
    }

    public void setCertificateAdded(Long userId, boolean added){
        Business business = find(userId);
        business.setCertificateAdded(added);
        repository.setCertificateAdded(userId, added);
    }

    public Business find(Long userId){
        return repository.findByUserId(userId).orElse(new Business());
    }

}
