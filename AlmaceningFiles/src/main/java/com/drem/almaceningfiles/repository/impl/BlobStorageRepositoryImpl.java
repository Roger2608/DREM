package com.drem.almaceningfiles.repository.impl;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobClientBuilder;
import com.drem.almaceningfiles.repository.BlobStorageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Repository
public class BlobStorageRepositoryImpl implements BlobStorageRepository {
    @Value("${spring.cloud.azure.storage.blob.container-name}")
    private String containerName;

    @Value("${spring.cloud.azure.storage.blob.connection-string}")
    private String connectionString;

    @Override
    public Mono<String> saveBlob(Resource resource, String fileName) throws IOException {
        return new BlobClientBuilder()
                .containerName(containerName)
                .blobName(fileName)
                .connectionString(connectionString)
                .buildAsyncClient()
                .upload(
                        BinaryData.fromStream(
                                resource.getInputStream()
                        ),
                        false)
                .map(a -> a.getETag());

    }
}
