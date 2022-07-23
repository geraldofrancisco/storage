package com.thor.storage.service.impl;

import com.azure.storage.blob.BlobClientBuilder;
import com.thor.storage.document.StorageFileDocument;
import com.thor.storage.dto.StorageResponse;
import com.thor.storage.enumerable.FileType;
import com.thor.storage.exception.BusinessException;
import com.thor.storage.repository.StorageRepository;
import com.thor.storage.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.thor.storage.constant.ErrorConstant.FILE_NOT_FOUND;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
public class StorageServiceImpl implements StorageService {

    private StorageRepository repository;

    private BlobClientBuilder client;

    @Override
    public String insert(MultipartFile file) throws IOException {
        var document = StorageFileDocument.builder()
                .name(file.getOriginalFilename())
                .type(FileType.getByMime(file.getContentType()))
                .build();
        document = this.repository.save(document);
        this.uploadFile(file.getInputStream(), file.getSize(), document.getId());
        return document.getId();
    }

    @Override
    public StorageResponse getById(String id) {
        var optional = this.repository.findById(id);
        if (!optional.isPresent())
            throw new BusinessException(FILE_NOT_FOUND, NOT_FOUND);
        var file = optional.get();
        return StorageResponse.builder()
                .bytes(this.client.blobName(id).buildClient().downloadContent().toBytes())
                .name(file.getName())
                .id(file.getId())
                .extension(file.getType().getExtension())
                .mime(file.getType().getMime())
                .build();
    }

    @Override
    public void delete(String id) {

    }

    @Async
    private void uploadFile(InputStream input, long size, String name) {
        this.client.blobName(name)
                .buildClient()
                .upload(input, size);
    }
}
