package com.rekahdo.goodshop.vendor_service.services;

import com.rekahdo.goodshop.vendor_service.dtos.assemblers.VendorAssembler;
import com.rekahdo.goodshop.vendor_service.dtos.entities.VendorDto;
import com.rekahdo.goodshop.vendor_service.dtos.paginations.VendorPageRequest;
import com.rekahdo.goodshop.vendor_service.dtos.request.BusinessRequest;
import com.rekahdo.goodshop.vendor_service.entities.Vendor;
import com.rekahdo.goodshop.vendor_service.enums.ApprovalStatus;
import com.rekahdo.goodshop.vendor_service.enums.UnresolvedReason;
import com.rekahdo.goodshop.vendor_service.exceptions.classes.VendorApplicationStatusException;
import com.rekahdo.goodshop.vendor_service.exceptions.classes.VendorApprovedException;
import com.rekahdo.goodshop.vendor_service.exceptions.classes.VendorNotFoundException;
import com.rekahdo.goodshop.vendor_service.feign.clients.AddressServiceClient;
import com.rekahdo.goodshop.vendor_service.feign.clients.AdminServiceClient;
import com.rekahdo.goodshop.vendor_service.feign.clients.FileServiceClient;
import com.rekahdo.goodshop.vendor_service.feign.clients.PhoneServiceClient;
import com.rekahdo.goodshop.vendor_service.mappers.VendorMapper;
import com.rekahdo.goodshop.vendor_service.repositories.VendorRepository;
import com.rekahdo.goodshop.vendor_service.utilities.ApiKey;
import com.rekahdo.goodshop.vendor_service.utilities.VendorIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VendorService {

    private final VendorRepository repository;
    private final VendorMapper mapper;
    private final VendorAssembler assembler;

    private final UnresolvedService unresolvedService;
    private final BusinessService businessService;
    private final AdminServiceClient adminService;

    private final AddressServiceClient addressService;
    private final PhoneServiceClient phoneService;
    private final FileServiceClient fileService;

    public void add(Long userId, BusinessRequest request) {
        Optional<Vendor> optional = repository.findByUserId(userId);

        if(optional.isPresent()) {
            if (Objects.equals(ApprovalStatus.APPROVED.index, optional.get().getApprovalStatus()))
                throw new VendorApprovedException(userId);
            else
                throw new VendorApplicationStatusException(userId, optional.get().getApprovalStatus());
        }

        Vendor vendor = new Vendor(userId);
        vendor.setUid(VendorIdGenerator.generateId());
        vendor.setApprovalStatus(ApprovalStatus.PENDING.index);
        vendor.setRegisteredAt(LocalDateTime.now());
        repository.save(vendor);

        businessService.set(userId, request);

        // Add initial unresolved for user when first applied to become a vendor
        unresolvedService.add(userId, List.of(
            UnresolvedReason.CONTACT_PERSON_INFO_NOT_PROVIDED,
            UnresolvedReason.BANK_INFO_NOT_PROVIDED
        ));

    }

    public void approvalStatus(Long userId, ApprovalStatus status) {
        Vendor vendor = findAndThrow(userId);

        // If new and old status are not the same, enter
        if(!Objects.equals(status.index, vendor.getApprovalStatus())){
            if (Objects.equals(ApprovalStatus.APPROVED, status))
                adminService.assignVendor(userId, ApiKey.ADMIN_SERVICE);
            else
                adminService.delete(userId, ApiKey.ADMIN_SERVICE);
        }

        vendor.setApprovalStatus(status.index);
        repository.save(vendor);
    }

    public void query(Long userId, List<UnresolvedReason> list) {
        unresolvedService.query(userId, list);
    }

    public EntityModel<VendorDto> retrieve(Long userId) {
        Vendor vendor = repository.findByUserId(userId)
                .orElseThrow(() -> new VendorNotFoundException(userId));

        VendorDto dto = mapper.toSecond(vendor);
        dto.update(addressService, phoneService, fileService);
        return assembler.toModel(dto);
    }

    public PagedModel<EntityModel<VendorDto>> retrieveAll(VendorPageRequest request) {
        Page<Vendor> page = repository.findAll(request.getPageable());
        Page<VendorDto> dtoPage = page.map(mapper::toSecond);
        return assembler.toPagedModel(dtoPage, request);
    }

    public void delete(Long userId) {
        repository.deleteByUserId(userId);
    }

    public Vendor findAndThrow(Long userId) {
        return repository.findByUserId(userId)
                .orElseThrow(() -> new VendorNotFoundException(userId));
    }

    public Vendor find(Long userId) {
        return repository.findByUserId(userId).orElse(null);
    }
}
