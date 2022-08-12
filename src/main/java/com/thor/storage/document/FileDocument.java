package com.thor.storage.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@AllArgsConstructor
public class FileDocument {
    private String azureId;

    @Field("nomeOriginal")
    private String originalName;

    @Field("tipo")
    private String contentType;

}
