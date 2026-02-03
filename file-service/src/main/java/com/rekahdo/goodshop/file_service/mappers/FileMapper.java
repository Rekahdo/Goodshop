package com.rekahdo.goodshop.file_service.mappers;

import com.rekahdo.goodshop.file_service.controllers.BusinessCertificateController;
import com.rekahdo.goodshop.file_service.controllers.BusinessRegistrationDocumentsController;
import com.rekahdo.goodshop.file_service.controllers.ContactPersonIdentityProofController;
import com.rekahdo.goodshop.file_service.controllers.DisplayPictureController;
import com.rekahdo.goodshop.file_service.dtos.clients.FileClient;
import com.rekahdo.goodshop.file_service.dtos.entities.FileDto;
import com.rekahdo.goodshop.file_service.entities.File;
import com.rekahdo.goodshop.file_service.enums.Purpose;
import com.rekahdo.goodshop.file_service.utilities.FileUtils;
import org.mapstruct.*;

import java.net.URI;
import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface FileMapper extends TripleMapper<File, FileDto, FileClient> {

    @Override
    @Mappings({
        @Mapping(target = "purpose", ignore = true),
        @Mapping(target = "fileData", ignore = true),
    })
    FileDto toDto(File entity);

    @Override
    @Mappings({
            @Mapping(target = "purpose", ignore = true)
    })
    FileClient toClient(File entity);

    @Override
    @AfterMapping
    default void afterMappingToDto(@MappingTarget FileDto target, File source) {
        target.setPurpose(Purpose.findByIndex(source.getPurpose()));
        target.setFileData(FileUtils.decompressImage(source.getFileData()));
    }

    @Override
    @AfterMapping
    default void afterMappingToClient(@MappingTarget FileClient target, File source) {
        target.setPurpose(Purpose.findByIndex(source.getPurpose()).name());

        if(Objects.equals(Purpose.DISPLAY_PICTURE.name(), target.getPurpose()))
            target.setSelf(linkTo(methodOn(DisplayPictureController.class).retrieve(source.getUserId())).toUri());

        if(Objects.equals(Purpose.BUSINESS_CERTIFICATE.name(), target.getPurpose()))
            target.setSelf(linkTo(methodOn(BusinessCertificateController.class).retrieve(source.getUserId())).toUri());

        if(Objects.equals(Purpose.BUSINESS_REGISTRATION_DOCUMENTS.name(), target.getPurpose()))
            target.setSelf(linkTo(methodOn(BusinessRegistrationDocumentsController.class).retrieve(source.getUserId())).toUri());

        if(Objects.equals(Purpose.CONTACT_PERSON_ID_PROOF.name(), target.getPurpose()))
            target.setSelf(linkTo(methodOn(ContactPersonIdentityProofController.class).retrieve(source.getUserId())).toUri());
    }

}