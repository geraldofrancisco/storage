package com.thor.storage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static com.thor.storage.constant.DocumentationConstant.RESPONSE_BYTES;
import static com.thor.storage.constant.DocumentationConstant.RESPONSE_EXTENSION;
import static com.thor.storage.constant.DocumentationConstant.RESPONSE_ID;
import static com.thor.storage.constant.DocumentationConstant.RESPONSE_MIME;
import static com.thor.storage.constant.DocumentationConstant.RESPONSE_NAME;

@Data
@Builder
@AllArgsConstructor
public class StorageResponse {
    @Schema(description = RESPONSE_ID)
    private String id;

    @Schema(description = RESPONSE_NAME)
    private String name;

    @Schema(description = RESPONSE_EXTENSION)
    private String extension;

    @Schema(description = RESPONSE_MIME)
    private String mime;

    @Schema(description = RESPONSE_BYTES)
    private byte[] bytes;
}
