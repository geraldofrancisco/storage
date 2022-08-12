package com.thor.storage.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static com.thor.storage.constant.DocumentationConstant.RESPONSE_BYTES;
import static com.thor.storage.constant.DocumentationConstant.RESPONSE_MIME;
import static com.thor.storage.constant.DocumentationConstant.RESPONSE_NAME;

@Data
@Builder
@AllArgsConstructor
public class FileResponse {

    @JsonIgnore
    private String id;

    @Schema(description = RESPONSE_NAME)
    private String name;

    @Schema(description = RESPONSE_MIME)
    private String contentType;

    @Schema(description = RESPONSE_BYTES)
    private byte[] bytes;
}
