package com.thor.storage.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ErrorConstant {
    public static final String UPLOAD_NOT_FINISHED = "Upload não finalizado";
    public static final String FILE_NOT_FOUND = "Arquivo não encontrado";
    public static final String AZURE_ERROR = "When trying to save the %s file it gave the following error: %s";
}
