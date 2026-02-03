package com.rekahdo.goodshop.file_service.services;

import com.rekahdo.goodshop.file_service.dtos.entities.FileDto;
import com.rekahdo.goodshop.file_service.entities.File;
import com.rekahdo.goodshop.file_service.enums.Purpose;
import com.rekahdo.goodshop.file_service.exceptions.classes.FileNotFoundException;
import com.rekahdo.goodshop.file_service.feign.clients.VendorServiceClient;
import com.rekahdo.goodshop.file_service.feign.enums.UnresolvedReason;
import com.rekahdo.goodshop.file_service.mappers.FileMapper;
import com.rekahdo.goodshop.file_service.repositories.FileRepository;
import com.rekahdo.goodshop.file_service.utilities.ApiKey;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileMapper mapper;
    private final FileRepository repository;

    private final VendorServiceClient vendorService;

    // UPLOAD SINGLE
    public void upload(Long userId, MultipartFile multipartFile, Purpose purpose) {
        File file = repository.findByUserIdAndPurpose(userId, purpose.index)
                .orElse(new File(userId, purpose, multipartFile));

        if(file.getId() == null){
            if(Objects.equals(Purpose.BUSINESS_CERTIFICATE, purpose)) {
                vendorService.businessCertificateAdded(userId, ApiKey.VENDOR_SERVICE);
                vendorService.deleteUnresolved(userId, UnresolvedReason.BUSINESS_CERTIFICATE_NOT_PROVIDED, ApiKey.VENDOR_SERVICE);
            }
            else if (Objects.equals(Purpose.CONTACT_PERSON_ID_PROOF, purpose)) {
                vendorService.contactIdProofAdded(userId, ApiKey.VENDOR_SERVICE);
                vendorService.deleteUnresolved(userId, UnresolvedReason.CONTACT_PERSON_ID_PROOF_NOT_PROVIDED, ApiKey.VENDOR_SERVICE);
            }
        }
        else
            file.update(multipartFile);

        repository.save(file);
    }

    // UPLOAD LIST
    public void upload(Long userId, List<MultipartFile> multipartFiles, Purpose purpose) {
        List<File> files = repository.findAllByUserIdAndPurpose(userId, purpose.index);

        if(files.isEmpty())
            repository.saveAll(createFiles(userId, purpose, multipartFiles));
        else
            repository.saveAll(modifyFiles(userId, purpose, files, multipartFiles));
    }

    private List<File> createFiles(Long userId, Purpose purpose, List<MultipartFile> multipartFiles){
        return multipartFiles.stream()
                .map(multipartFile -> new File(userId, purpose, multipartFile)).toList();
    }

    private List<File> modifyFiles(Long userId, Purpose purpose, List<File> files, List<MultipartFile> multipartFiles){
        return multipartFiles.stream().map(multipartFile ->
                files.stream().filter(file -> Objects.equals(file.getName(), multipartFile.getOriginalFilename()))
                        .findFirst().map(file -> file.update(multipartFile))
                        .orElse(new File(userId, purpose, multipartFile))
        ).toList();
    }

    public MappingJacksonValue retrieve(Long userId, Purpose purpose) {
        File file = repository.findByUserIdAndPurpose(userId, purpose.index)
                .orElseThrow(() -> new FileNotFoundException(userId, purpose));

        return FileDto.showAll(mapper.toDto(file));
    }

    public MappingJacksonValue retrieveList(Long userId, Purpose purpose) {
        List<File> files = repository.findAllByUserIdAndPurpose(userId, purpose.index);
        if(files.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);

        return FileDto.showAll(mapper.toDtoList(files));
    }

    public MappingJacksonValue retrieveAll(Long userId) {
        List<File> files = repository.findByUserId(userId);
        if(files.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);

        return FileDto.showFew(mapper.toDtoList(files));
    }

    public void deleteFile(Long userId, Purpose purpose) {
        repository.deleteByUserIdAndPurpose(userId, purpose.getIndex());
    }

}
