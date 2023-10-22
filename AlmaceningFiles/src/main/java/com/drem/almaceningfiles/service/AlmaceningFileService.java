package com.drem.almaceningfiles.service;

import com.drem.almaceningfiles.model.api.response.SaveBlobResponse;
import org.springframework.core.io.Resource;
import reactor.core.publisher.Mono;

import java.io.IOException;

public interface AlmaceningFileService {

    Mono<SaveBlobResponse> saveFile(Resource resource, String fileName) throws IOException;

}
