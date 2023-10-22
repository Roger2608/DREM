package com.drem.almaceningfiles.repository;

import org.springframework.core.io.Resource;
import reactor.core.publisher.Mono;

import java.io.IOException;

public interface BlobStorageRepository {
    Mono<String> saveBlob(Resource resource, String fileName) throws IOException;
}
