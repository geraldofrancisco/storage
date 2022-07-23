package com.thor.storage.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class DocumentationConstant {
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
    public static final String STORAGE_UPLOAD_FILE_DESCRIPTION = "File to be saved";

    public static final String STORAGE_GET_BY_ID_SUMMARY = "Get file";
    public static final String STORAGE_GET_BY_ID_DESCRIPTION = "Get saved file by id";
    public static final String STORAGE_DELETE_BY_ID_SUMMARY = "Delete file";
    public static final String STORAGE_DELETE_BY_ID_DESCRIPTION = "Delete saved file by id";

}
