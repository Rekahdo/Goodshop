package com.rekahdo.goodshop.phone_service.services;

import com.rekahdo.goodshop.phone_service.entities.Code;
import com.rekahdo.goodshop.phone_service.repositories.CodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CodeService {

    private final CodeRepository repository;

    public Long add(String code) {
        Optional<Code> optional = repository.findByNumber(code);
        Code countryCode = optional.orElse(new Code(code));

        if(optional.isEmpty())
            repository.save(countryCode);

        return countryCode.getId();
    }

}
