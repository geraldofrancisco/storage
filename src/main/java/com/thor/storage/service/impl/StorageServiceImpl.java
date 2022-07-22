package com.thor.storage.service.impl;

import com.azure.storage.blob.BlobClientBuilder;
import com.thor.storage.document.StorageFileDocument;
import com.thor.storage.dto.StorageResponse;
import com.thor.storage.enumerable.FileType;
import com.thor.storage.repository.StorageRepository;
import com.thor.storage.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

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
        return null;
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
