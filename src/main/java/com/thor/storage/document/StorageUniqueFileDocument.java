package com.thor.storage.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "arquivo")
public class StorageUniqueFileDocument {
    @Id
    private String id;

    @Field("dataCriacao")
    @Builder.Default
    private LocalDateTime createdDate = now();

    @Field("dataFinalUpload")
    private LocalDateTime finalUploadDate;

    @Field("arquivo")
    private FileDocument file;

}
