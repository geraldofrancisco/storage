package com.thor.storage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import static com.thor.storage.constant.DocumentationConstant.ERROR_HTTP_STATUS;
import static com.thor.storage.constant.DocumentationConstant.ERROR_LIST;
import static com.thor.storage.constant.DocumentationConstant.ERROR_TIMESTAMP;

@Builder
@Data
public class ExceptionResponse {
    @Schema(description = ERROR_HTTP_STATUS)
    private Integer status;

    @Schema(description = ERROR_TIMESTAMP)
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    @Schema(description = ERROR_LIST)
    private List<ExceptionFieldError> messages;
}
