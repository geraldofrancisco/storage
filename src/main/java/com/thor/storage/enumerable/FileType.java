package com.thor.storage.enumerable;

import com.thor.storage.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

import static com.thor.storage.constant.ErrorConstant.FILE_TYPE_INVALID;

@AllArgsConstructor
@Getter
public enum FileType {
    AVI(".avi", "video/x-msvideo"),
    BM(".bm", "image/bmp"),
    BMP(".bmp", "image/bmp"),
    CSV(".csv", "text/csv"),
    DOC(".doc", "application/msword"),
    DOCX(".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    DOT(".dot", "application/msword"),
    DOTX(".dotx", "application/vnd.openxmlformats-officedocument.wordprocessingml.template"),
    GIF("", "image/gif"),
    JPEG(".jpeg", "image/jpeg"),
    JPG(".jpg", "image/jpeg"),
    PDF(".pdf", "application/pdf"),
    PNG(".png", "image/png"),
    PPT(".ppt", "application/mspowerpoint"),
    PPTX(".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"),
    TEXT(".txt", "text/plain"),
    XLS(".xls", "application/vnd.ms-excel"),
    XLSX(".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");


    private String extension;
    private String mime;

    public static FileType getByMime(String mime) {
        var type = Arrays.stream(FileType.values())
                .filter(f -> f.mime.equals(mime))
                .findFirst();
        if(!type.isPresent())
            throw new BusinessException(FILE_TYPE_INVALID);

        return type.get();
    }
}
