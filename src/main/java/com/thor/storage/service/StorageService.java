package com.thor.storage.service;

import com.thor.storage.dto.StorageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StorageService {
    String insert(List<MultipartFile> files);

    StorageResponse getById(String id);

    void delete(String id);
}
