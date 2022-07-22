package com.thor.storage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StorageResponse {
    private String id;
    private String name;
    private String extension;
    private String mime;
    private byte[] bytes;
}
