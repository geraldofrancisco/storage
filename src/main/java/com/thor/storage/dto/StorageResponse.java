package com.thor.storage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import static com.thor.storage.constant.DocumentationConstant.RESPONSE_CREATED_DATE;
import static com.thor.storage.constant.DocumentationConstant.RESPONSE_FILES;
import static com.thor.storage.constant.DocumentationConstant.RESPONSE_FINISH_UPLOAD;
import static com.thor.storage.constant.DocumentationConstant.RESPONSE_ID;
import static com.thor.storage.constant.DocumentationConstant.RESPONSE_QTD_FILES;

@Data
@Builder
@AllArgsConstructor
public class StorageResponse {
    @Schema(description = RESPONSE_ID)
    private String id;

    @Schema(description = RESPONSE_CREATED_DATE)
    private LocalDateTime createdDate;

    @Schema(description = RESPONSE_FINISH_UPLOAD)
    private LocalDateTime finalUploadDate;

    @Schema(description = RESPONSE_FILES)
    private List<FileResponse> files;

    @Schema(description = RESPONSE_QTD_FILES)
    public Integer getQtdFiles() {
        return this.files.size();
    }
}
