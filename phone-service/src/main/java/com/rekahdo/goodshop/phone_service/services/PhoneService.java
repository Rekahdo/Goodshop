package com.rekahdo.goodshop.phone_service.services;

import com.rekahdo.goodshop.phone_service.dtos.assemblers.PhoneAssembler;
import com.rekahdo.goodshop.phone_service.dtos.entities.PhoneDto;
import com.rekahdo.goodshop.phone_service.dtos.request.PhoneRequest;
import com.rekahdo.goodshop.phone_service.entities.Code;
import com.rekahdo.goodshop.phone_service.entities.Phone;
import com.rekahdo.goodshop.phone_service.enums.PhonePurpose;
import com.rekahdo.goodshop.phone_service.exceptions.classes.PhoneNotFoundException;
import com.rekahdo.goodshop.phone_service.feign.clients.OtpServiceClient;
import com.rekahdo.goodshop.phone_service.feign.clients.VendorServiceClient;
import com.rekahdo.goodshop.phone_service.feign.enums.UnresolvedReason;
import com.rekahdo.goodshop.phone_service.mappers.PhoneMapper;
import com.rekahdo.goodshop.phone_service.repositories.PhoneRepository;
import com.rekahdo.goodshop.phone_service.utilities.ApiKey;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PhoneService {

    private final PhoneRepository repository;
    private final PhoneMapper mapper;
    private final PhoneAssembler assembler;

    private final CodeService codeService;
    private final OtpServiceClient otpService;
    private final VendorServiceClient vendorService;

    private Phone find(Long userId, PhonePurpose purpose){
        return repository.findByUserIdAndPurpose(userId, purpose.index)
                .orElse(new Phone(userId, purpose));
    }

    private Phone findOrThrow(Long userId, PhonePurpose purpose){
        return repository.findByUserIdAndPurpose(userId, purpose.index)
                .orElseThrow(() -> new PhoneNotFoundException(userId, purpose));
    }

    public void set(Long userId, PhonePurpose purpose, PhoneRequest request) {
        Long codeId = codeService.add(request.countryCode());
        Phone phone = find(userId, purpose);
        phone.setCode(phone.getCode() == null ? new Code(codeId) : phone.getCode());

        // If new number not equal to old number, set new number to unverified
        if(!Objects.equals(request.phone(), phone.phone())) {
            phone.setVerified(false);

            // Delete number not provided, add number not verified, set number added
            if(Objects.equals(PhonePurpose.BUSINESS, purpose)) {
                vendorService.deleteUnresolved(userId, UnresolvedReason.BUSINESS_PHONE_NOT_PROVIDED, ApiKey.VENDOR_SERVICE);
                vendorService.addUnresolved(userId, UnresolvedReason.BUSINESS_PHONE_NOT_VERIFIED, ApiKey.VENDOR_SERVICE);
                vendorService.businessPhoneAdded(userId, ApiKey.VENDOR_SERVICE);
            }

            // Delete number not provided, add number not verified, set number added
            if(Objects.equals(PhonePurpose.CONTACT_PERSON, purpose)) {
                vendorService.deleteUnresolved(userId, UnresolvedReason.CONTACT_PERSON_PHONE_NOT_PROVIDED, ApiKey.VENDOR_SERVICE);
                vendorService.addUnresolved(userId, UnresolvedReason.CONTACT_PERSON_PHONE_NOT_VERIFIED, ApiKey.VENDOR_SERVICE);
                vendorService.contactPhoneAdded(userId, ApiKey.VENDOR_SERVICE);
            }

            // Delete number not provided, add number not verified, set number added
            if(Objects.equals(PhonePurpose.CONTACT_PERSON_EMERGENCY, purpose)) {
                vendorService.deleteUnresolved(userId, UnresolvedReason.CONTACT_PERSON_EMERGENCY_PHONE_NOT_PROVIDED, ApiKey.VENDOR_SERVICE);
                vendorService.addUnresolved(userId, UnresolvedReason.CONTACT_PERSON_EMERGENCY_PHONE_NOT_VERIFIED, ApiKey.VENDOR_SERVICE);
                vendorService.contactEmergencyPhoneAdded(userId, ApiKey.VENDOR_SERVICE);
            }
        }

        mapper.updateEntity(request, phone);
        repository.save(phone);
    }

    public void verify(Long userId, PhonePurpose purpose) {
        Phone phone = findOrThrow(userId, purpose);
        String sentTo = String.format("%s%s", phone.getCode().getNumber(), phone.getNumber());

        String apiKey = ApiKey.OTP_SERVICE;
        boolean validated = Objects.equals(PhonePurpose.PROFILE, purpose) ?
                otpService.validateProfileNumberVerificationOtp(userId, sentTo, apiKey) :
                Objects.equals(PhonePurpose.BUSINESS, purpose) ?
                otpService.validateBusinessNumberVerificationOtp(userId, sentTo, apiKey) :
                Objects.equals(PhonePurpose.CONTACT_PERSON, purpose) ?
                otpService.validateContactPersonNumberVerificationOtp(userId, sentTo, apiKey) :
                otpService.validateContactPersonEmergencyNumberVerificationOtp(userId, sentTo, apiKey);

        if(validated){
            repository.verifyPhone(userId, purpose.index);
            otpService.deleteByUserId(userId, apiKey);

            if(Objects.equals(PhonePurpose.BUSINESS, purpose))
                vendorService.deleteUnresolved(userId, UnresolvedReason.BUSINESS_PHONE_NOT_VERIFIED, ApiKey.VENDOR_SERVICE);

            if(Objects.equals(PhonePurpose.CONTACT_PERSON, purpose))
                vendorService.deleteUnresolved(userId, UnresolvedReason.CONTACT_PERSON_PHONE_NOT_VERIFIED, ApiKey.VENDOR_SERVICE);

            if(Objects.equals(PhonePurpose.CONTACT_PERSON_EMERGENCY, purpose))
                vendorService.deleteUnresolved(userId, UnresolvedReason.CONTACT_PERSON_EMERGENCY_PHONE_NOT_VERIFIED, ApiKey.VENDOR_SERVICE);
        }
    }

    public EntityModel<PhoneDto> retrieve(Long userId, PhonePurpose purpose) {
        Phone phone = findOrThrow(userId, purpose);
        return assembler.toModel(mapper.toDto(phone));
    }

    public CollectionModel<EntityModel<PhoneDto>> retrieveAll(Long userId) {
        List<Phone> phones = repository.findByUserId(userId);

        if(phones.isEmpty())
            throw new PhoneNotFoundException(userId);

        return assembler.toCollectionModel(mapper.toDtoList(phones));
    }
}
