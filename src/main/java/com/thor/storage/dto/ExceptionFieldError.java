package com.thor.storage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static com.thor.storage.constant.DocumentationConstant.ERROR_FIELD;
import static com.thor.storage.constant.DocumentationConstant.ERROR_MESSAGE;

@AllArgsConstructor
@Builder
@Data
public class ExceptionFieldError {
    @Schema(description = ERROR_FIELD)
    private String field;

    @Schema(description = ERROR_MESSAGE)
    private String error;
}
