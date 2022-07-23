package com.thor.storage.rest;

import com.thor.storage.dto.ExceptionResponse;
import com.thor.storage.dto.StorageResponse;
import com.thor.storage.service.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.thor.storage.constant.DocumentationConstant.STATUS_CREATED;
import static com.thor.storage.constant.DocumentationConstant.STATUS_CREATED_DESCRIPTION;
import static com.thor.storage.constant.DocumentationConstant.STATUS_NOT_FOUND;
import static com.thor.storage.constant.DocumentationConstant.STATUS_NOT_FOUND_DESCRIPTION;
import static com.thor.storage.constant.DocumentationConstant.STATUS_NO_CONTENT;
import static com.thor.storage.constant.DocumentationConstant.STATUS_NO_CONTENT_DESCRIPTION;
import static com.thor.storage.constant.DocumentationConstant.STATUS_OK;
import static com.thor.storage.constant.DocumentationConstant.STATUS_OK_DESCRIPTION;
import static com.thor.storage.constant.DocumentationConstant.STORAGE_DELETE_BY_ID_DESCRIPTION;
import static com.thor.storage.constant.DocumentationConstant.STORAGE_DELETE_BY_ID_SUMMARY;
import static com.thor.storage.constant.DocumentationConstant.STORAGE_GET_BY_ID_DESCRIPTION;
import static com.thor.storage.constant.DocumentationConstant.STORAGE_GET_BY_ID_SUMMARY;
import static com.thor.storage.constant.DocumentationConstant.STORAGE_UPLOAD_DESCRIPTION;
import static com.thor.storage.constant.DocumentationConstant.STORAGE_UPLOAD_ID;
import static com.thor.storage.constant.DocumentationConstant.STORAGE_UPLOAD_SUMMARY;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.MULTIPART_FORM_DATA;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@Tag(name = "Storage")
@RequestMapping("/v1/storage")
public class StorageController {

    private final StorageService service;

    @PostMapping(consumes = MULTIPART_FORM_DATA)
    @ResponseStatus(CREATED)
    @Operation(
            summary = STORAGE_UPLOAD_SUMMARY,
            description = STORAGE_UPLOAD_DESCRIPTION,
            responses = {
                    @ApiResponse(
                            responseCode = STATUS_CREATED,
                            description = STATUS_CREATED_DESCRIPTION,
                            content = @Content(
                                    mediaType = TEXT_PLAIN,
                                    schema = @Schema(
                                            implementation = String.class,
                                            description = STORAGE_UPLOAD_ID
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = STATUS_NOT_FOUND,
                            description = STATUS_NOT_FOUND_DESCRIPTION,
                            content = @Content(
                                    mediaType = APPLICATION_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    )
            }
    )
    public String upload(@RequestParam MultipartFile file) throws IOException {
        return this.service.insert(file);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    @Operation(
            summary = STORAGE_GET_BY_ID_SUMMARY,
            description = STORAGE_GET_BY_ID_DESCRIPTION,
            responses = {
                    @ApiResponse(
                            responseCode = STATUS_OK,
                            description = STATUS_OK_DESCRIPTION,
                            content = @Content(
                                    mediaType = APPLICATION_JSON,
                                    schema = @Schema(implementation = StorageResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = STATUS_NOT_FOUND,
                            description = STATUS_NOT_FOUND_DESCRIPTION,
                            content = @Content(
                                    mediaType = APPLICATION_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    )
            }
    )
    public StorageResponse getById(@PathVariable String id) {
        return this.service.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(
            summary = STORAGE_DELETE_BY_ID_SUMMARY,
            description = STORAGE_DELETE_BY_ID_DESCRIPTION,
            responses = {
                    @ApiResponse(
                            responseCode = STATUS_NO_CONTENT,
                            description = STATUS_NO_CONTENT_DESCRIPTION,
                            content = @Content(
                                    mediaType = APPLICATION_JSON,
                                    schema = @Schema(implementation = StorageResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = STATUS_NOT_FOUND,
                            description = STATUS_NOT_FOUND_DESCRIPTION,
                            content = @Content(
                                    mediaType = APPLICATION_JSON,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    )
            }
    )
    public void delete(@PathVariable String id) {
        this.service.delete(id);
    }
}
