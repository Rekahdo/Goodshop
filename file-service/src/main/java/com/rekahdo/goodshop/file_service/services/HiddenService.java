package com.rekahdo.goodshop.file_service.services;

import com.rekahdo.goodshop.file_service.controllers.BusinessCertificateController;
import com.rekahdo.goodshop.file_service.controllers.BusinessRegistrationDocumentsController;
import com.rekahdo.goodshop.file_service.controllers.ContactPersonIdentityProofController;
import com.rekahdo.goodshop.file_service.controllers.DisplayPictureController;
import com.rekahdo.goodshop.file_service.dtos.clients.FileClient;
import com.rekahdo.goodshop.file_service.enums.Purpose;
import com.rekahdo.goodshop.file_service.mappers.FileMapper;
import com.rekahdo.goodshop.file_service.repositories.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class HiddenService {

    private final FileMapper mapper;
    private final FileRepository repository;

    public Map<String, URI> retrieveURIs(Long userId) throws IOException {
        return Map.of(
                "business-certificate", linkTo(methodOn(BusinessCertificateController.class).retrieve(userId)).toUri(),
                "business-registration-documents", linkTo(methodOn(BusinessRegistrationDocumentsController.class).retrieve(userId)).toUri(),
                "contact-person-identity-proof", linkTo(methodOn(ContactPersonIdentityProofController.class).retrieve(userId)).toUri(),
                "display-picture", linkTo(methodOn(DisplayPictureController.class).retrieve(userId)).toUri()
        );
    }

    public FileClient find(Long userId, Purpose purpose) {
        return repository.findByUserIdAndPurpose(userId, purpose.index)
                .map(mapper::toClient).orElse(null);
    }

    public List<FileClient> findAll(Long userId, Purpose purpose) {
        return repository.findAllByUserIdAndPurpose(userId, purpose.index).stream()
                .findAny().map(mapper::toClient).stream().toList();
    }


}
