package com.thor.storage.configuration;

import com.azure.storage.blob.BlobClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureBlobConfiguration {

    @Value("${thor.azure.connection-string}")
    private String connectionString;

    @Value("${thor.azure.container}")
    private String container;

    @Bean
    public BlobClientBuilder getClient() {
        var client = new BlobClientBuilder();
        client.connectionString(connectionString);
        client.containerName(container);
        return client;
    }
}