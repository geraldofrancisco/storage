package com.thor.storage.builder;

import com.thor.storage.document.StorageFileDocument;
import com.thor.storage.dto.StorageResponse;

public class StorageBuilder {
    public static StorageResponse.StorageResponseBuilder toResponse(StorageFileDocument document) {
        return StorageResponse.builder()
                .id(document.getId())
                .createdDate(document.getCreatedDate())
                .finalUploadDate(document.getFinalUploadDate());
    }

}
