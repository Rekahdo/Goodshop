package com.rekahdo.goodshop.vendor_service.services;

import com.rekahdo.goodshop.vendor_service.dtos.entities.UnresolvedDto;
import com.rekahdo.goodshop.vendor_service.entities.Unresolved;
import com.rekahdo.goodshop.vendor_service.entities.Vendor;
import com.rekahdo.goodshop.vendor_service.enums.UnresolvedReason;
import com.rekahdo.goodshop.vendor_service.exceptions.classes.EmptyListException;
import com.rekahdo.goodshop.vendor_service.mappers.UnresolvedMapper;
import com.rekahdo.goodshop.vendor_service.repositories.UnresolvedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UnresolvedService {

    private final UnresolvedRepository repository;
    private final UnresolvedMapper mapper;

    @Autowired @Lazy
    private VendorService vendorService;

    public void add(Long userId, List<UnresolvedReason> reasons) {
        if(!reasons.isEmpty()) {
            List<Unresolved> list = repository.findByUserId(userId);
            Vendor vendor = vendorService.findAndThrow(userId);

            if(list.isEmpty())
                repository.saveAll(reasons.stream()
                        .map(reason -> new Unresolved(reason, vendor)).toList());
            else{
                List<String> listIndex = list.stream().map(Unresolved::getReason).toList();
                List<Unresolved> save = reasons.stream()
                        .filter(reason -> !listIndex.contains(reason.index))
                        .map(reason -> new Unresolved(reason, vendor)).toList();

                repository.saveAll(save);
            }
        }
    }

    public void add(Long userId, UnresolvedReason reason) {
        Unresolved unresolved = find(userId, reason);

        if(unresolved == null) {
            Vendor vendor = vendorService.findAndThrow(userId);
            repository.save(new Unresolved(reason, vendor));
        }
    }

    private Unresolved find(Long userId, UnresolvedReason reason){
        return repository.findByUserIdAndReason(userId, reason.index).orElse(null);
    }

    public void query(Long userId, List<UnresolvedReason> list) {
        repository.query(userId, list.stream().map(UnresolvedReason::getIndex).toList());
    }

    public List<UnresolvedDto> get(Long userId) {
        List<Unresolved> list = repository.findByUserId(userId);

        if(list.isEmpty())
            throw new EmptyListException();

        return mapper.toSecondList(list);
    }

    public void delete(Long userId, UnresolvedReason reason) {
        repository.deleteByUserIdAndReason(userId, reason.index);
    }
}
