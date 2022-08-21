package com.thor.storage.service.impl;

import com.azure.storage.blob.BlobClientBuilder;
import com.thor.storage.builder.FileBuilder;
import com.thor.storage.builder.StorageBuilder;
import com.thor.storage.document.FileDocument;
import com.thor.storage.document.StorageFileDocument;
import com.thor.storage.dto.FileResponse;
import com.thor.storage.dto.StorageResponse;
import com.thor.storage.exception.BusinessException;
import com.thor.storage.repository.StorageRepository;
import com.thor.storage.service.StorageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.thor.storage.constant.ErrorConstant.AZURE_ERROR;
import static com.thor.storage.constant.ErrorConstant.FILE_NOT_FOUND;
import static com.thor.storage.constant.ErrorConstant.UPLOAD_NOT_FINISHED;
import static java.lang.String.format;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.PROCESSING;

@Service
@AllArgsConstructor
@Slf4j
public class StorageServiceImpl implements StorageService {

    private StorageRepository repository;

    private BlobClientBuilder client;

    @Override
    public String insert(List<MultipartFile> files) {
        var document = this.repository.save(StorageFileDocument.builder().build());
        this.uploadFile(files, document);
        return document.getId();
    }

    @Override
    public StorageResponse getById(String id) {
        var storage = findById(id);
        if (storage.getFinalUploadDate() == null)
            throw new BusinessException(UPLOAD_NOT_FINISHED, PROCESSING);

        var response = StorageBuilder.toResponse(storage);
        if (storage.getFile() != null)
            return response.files(getFileResponses(storage)).build();

        return response.file(getSingleFileResponse(storage)).build();
    }

    private FileResponse getSingleFileResponse(StorageFileDocument storage) {
        var file = FileBuilder.toResponse(storage.getFile());
        file.setBytes(getBytes(file.getId()));
        return file;
    }

    private List<FileResponse> getFileResponses(StorageFileDocument storage) {
        var files = storage.getFiles().stream()
                .map(FileBuilder::toResponse)
                .collect(Collectors.toList());

        files.stream().forEach(r -> r.setBytes(getBytes(r.getId())));
        return files;
    }

    private byte[] getBytes(String file) {
        return this.client.blobName(file).buildClient().downloadContent().toBytes();
    }

    @Override
    public void delete(String id) {
        var storage = findById(id);
        if (storage.getFile() != null)
            storage.getFiles().stream().forEach(file -> deleteAzure(file.getAzureId()));
        else
            deleteAzure(storage.getFile().getAzureId());
        this.repository.delete(storage);
    }

    private void deleteAzure(String azureId) {
        this.client.blobName(azureId).buildClient().delete();
    }

    @Async
    private void uploadFile(List<MultipartFile> files, StorageFileDocument document) {

        if (!files.isEmpty() && files.size() == 1) {
            var file = files.get(0);
            FileDocument fileDocument = saveFileAzure(file);
            document.setFile(fileDocument);
        } else {
            files.stream().forEach(file -> {
                FileDocument fileDocument = saveFileAzure(file);
                document.getFiles().add(fileDocument);
            });
        }
        document.setFinalUploadDate(now());
        this.repository.save(document);
    }

    private FileDocument saveFileAzure(MultipartFile file)  {
        try {
        var fileDocument = FileDocument.builder()
                .azureId(UUID.randomUUID().toString())
                .originalName(file.getOriginalFilename())
                .contentType(file.getContentType())
                .build();


            this.client.blobName(fileDocument.getAzureId())
                    .buildClient()
                    .upload(file.getInputStream(), file.getSize());
            return fileDocument;
        } catch (IOException e) {
            log.error(format(AZURE_ERROR, file.getOriginalFilename(), e.getMessage()));
        }
        return null;
    }

    private StorageFileDocument findById(String id) {
        var optional = this.repository.findById(id);
        if (!optional.isPresent())
            throw new BusinessException(FILE_NOT_FOUND, NOT_FOUND);
        return optional.get();
    }
}
