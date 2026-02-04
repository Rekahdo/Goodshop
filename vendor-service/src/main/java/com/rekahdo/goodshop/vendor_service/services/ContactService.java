package com.rekahdo.goodshop.vendor_service.services;

import com.rekahdo.goodshop.vendor_service.dtos.request.ContactRequest;
import com.rekahdo.goodshop.vendor_service.entities.Contact;
import com.rekahdo.goodshop.vendor_service.entities.Vendor;
import com.rekahdo.goodshop.vendor_service.enums.UnresolvedReason;
import com.rekahdo.goodshop.vendor_service.mappers.ContactMapper;
import com.rekahdo.goodshop.vendor_service.repositories.ContactRepository;
import com.rekahdo.goodshop.vendor_service.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository repository;
    private final ContactMapper mapper;

    private final VendorService vendorService;
    private final VendorRepository vendorRepository;
    private final UnresolvedService unresolvedService;

    public Long set(Long userId, ContactRequest request) {
        Contact contact = find(userId);
        mapper.updateEntity(request, contact);
        repository.save(contact);

        Vendor vendor = vendorService.findOrThrow(userId);
        vendor.setContact(new Contact(contact.getId()));
        vendorRepository.save(vendor);

        List<UnresolvedReason> unresolvedReasons = new ArrayList<>();

        // Add unresolved if user has not added both contact person phone and emergency phone
        if(!contact.isPhoneAdded())
            unresolvedReasons.add(UnresolvedReason.CONTACT_PERSON_PHONE_NOT_PROVIDED);

        if(!contact.isEmergencyPhoneAdded())
            unresolvedReasons.add(UnresolvedReason.CONTACT_PERSON_EMERGENCY_PHONE_NOT_PROVIDED);

        if(!contact.isIdProofAdded())
            unresolvedReasons.add(UnresolvedReason.CONTACT_PERSON_ID_PROOF_NOT_PROVIDED);

        unresolvedService.add(userId, unresolvedReasons);
        unresolvedService.delete(userId, UnresolvedReason.CONTACT_PERSON_INFO_NOT_PROVIDED);

        return contact.getId();
    }

    public void setPhoneAdded(Long userId, boolean added){
        Contact contact = find(userId);
        contact.setPhoneAdded(added);
        repository.save(contact);
    }

    public void setEmergencyPhoneAdded(Long userId, boolean added){
        Contact contact = find(userId);
        contact.setEmergencyPhoneAdded(added);
        repository.save(contact);
    }

    public void setIdProofAdded(Long userId, boolean added){
        Contact contact = find(userId);
        contact.setIdProofAdded(added);
        repository.save(contact);
    }

    public Contact find(Long userId){
        return repository.findByUserId(userId).orElse(new Contact());
    }

}
