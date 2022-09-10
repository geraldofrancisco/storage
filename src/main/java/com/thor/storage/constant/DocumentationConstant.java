package com.thor.storage.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class DocumentationConstant {
    public static final String STATUS_PROCESSING = "102";
    public static final String STATUS_PROCESSING_DESCRIPTION = "Processing";
    public static final String STATUS_OK = "200";
    public static final String STATUS_OK_DESCRIPTION = "Successful operation";
    public static final String STATUS_CREATED = "201";
    public static final String STATUS_CREATED_DESCRIPTION = "Successful created operation";
    public static final String STATUS_NO_CONTENT = "204";
    public static final String STATUS_NO_CONTENT_DESCRIPTION = "No content";
    public static final String STATUS_NOT_FOUND = "404";
    public static final String STATUS_NOT_FOUND_DESCRIPTION = "Entity not found";
    public static final String STORAGE_UPLOAD_SUMMARY = "System file upload";
    public static final String STORAGE_UPLOAD_DESCRIPTION = "Endpoint that uploads files from the thor system";
    public static final String STORAGE_UPLOAD_ID = "Saved file id";

    public static final String RESPONSE_ID = "File list id";

    public static final String RESPONSE_CREATED_DATE = "Date time of upload creation";
    public static final String RESPONSE_FINISH_UPLOAD = "Date time of when the update of all files finished";
    public static final String RESPONSE_FILES = "List of files to be saved";

    public static final String RESPONSE_QTD_FILES = "Number of saved files";
    public static final String RESPONSE_NAME = "File name";
    public static final String RESPONSE_MIME = "File mime";
    public static final String RESPONSE_BYTES = "File bytes array";

    public static final String STORAGE_GET_BY_ID_SUMMARY = "Get file";
    public static final String STORAGE_GET_BY_ID_DESCRIPTION = "Get saved file by id";
    public static final String STORAGE_DELETE_BY_ID_SUMMARY = "Delete file";
    public static final String STORAGE_DELETE_BY_ID_DESCRIPTION = "Delete saved file by id";

    public static final String ERROR_HTTP_STATUS = "HTTP status error";
    public static final String ERROR_TIMESTAMP = "Timestamp of error";
    public static final String ERROR_LIST = "Error list";
    public static final String ERROR_FIELD = "Field that belongs to the error when it exists";
    public static final String ERROR_MESSAGE = "Error message";

    public static final String ERROR_415_NOT_SUPPORTED  = "Conteúdo não suportado";
    public static final String ERROR_424_NOT_SUPPORTED  = "Conteúdo não identificado";
}
