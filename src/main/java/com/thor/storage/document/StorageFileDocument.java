package com.thor.storage.document;

import com.thor.storage.enumerable.FileType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "arquivo")
public class StorageFileDocument {
    @Id
    private String id;

    @Field("dataUpload")
    @Builder.Default
    private LocalDateTime uploadDate = LocalDateTime.now();

    @Field("nome")
    private String name;

    @Field("tipo")
    private FileType type;

}
