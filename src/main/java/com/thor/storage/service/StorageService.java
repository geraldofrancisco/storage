package com.thor.storage.service;

import com.thor.storage.dto.StorageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    String insert(MultipartFile file) throws IOException;

    StorageResponse getById(String id);

    void delete(String id);
}
