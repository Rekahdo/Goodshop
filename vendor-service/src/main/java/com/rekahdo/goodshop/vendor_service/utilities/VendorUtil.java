package com.rekahdo.goodshop.vendor_service.utilities;

import com.rekahdo.goodshop.vendor_service.entities.Vendor;
import com.rekahdo.goodshop.vendor_service.enums.ApprovalStatus;
import com.rekahdo.goodshop.vendor_service.exceptions.classes.VendorApplicationStatusException;
import com.rekahdo.goodshop.vendor_service.exceptions.classes.VendorApprovedException;
import com.rekahdo.goodshop.vendor_service.exceptions.classes.VendorDeniedException;
import com.rekahdo.goodshop.vendor_service.exceptions.classes.VendorNotFoundException;
import com.rekahdo.goodshop.vendor_service.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class VendorUtil {

    @Autowired
    private static VendorRepository repository;

    public static Vendor findOrNew(Long userId){
        return repository.findByUserId(userId).orElse(new Vendor());
    }

    public static Vendor findOrNull(Long userId){
        return repository.findByUserId(userId).orElse(null);
    }

    public static Vendor findOrThrow(Long userId){
        return repository.findByUserId(userId)
                .orElseThrow(() -> new VendorNotFoundException(userId));
    }

    public static boolean exists(Long userId){
        return repository.existsByUserId(userId);
    }

    public static void validate(Long userId){
        Vendor vendor = findOrThrow(userId);

        if(Objects.equals(vendor.getApprovalStatus(), ApprovalStatus.DENIED.index))
            throw new VendorDeniedException(userId);

        else if(Objects.equals(vendor.getApprovalStatus(), ApprovalStatus.APPROVED.index))
            throw new VendorApprovedException(userId);

        else
            throw new VendorApplicationStatusException(userId, vendor.getApprovalStatus());
    }

}
