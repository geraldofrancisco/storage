package com.thor.storage.builder;

import com.thor.storage.document.FileDocument;
import com.thor.storage.dto.FileResponse;

public class FileBuilder {
    public static FileResponse toResponse(FileDocument document) {
        return FileResponse.builder()
                .id(document.getAzureId())
                .name(document.getOriginalName())
                .contentType(document.getContentType())
                .build();
    }
}
