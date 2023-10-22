package com.drem.almaceningfiles.service.impl;

import com.drem.almaceningfiles.model.api.response.SaveBlobResponse;
import com.drem.almaceningfiles.repository.BlobStorageRepository;
import com.drem.almaceningfiles.service.AlmaceningFileService;
import com.drem.almaceningfiles.util.mapping.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class AlmaceningFileServiceImpl implements AlmaceningFileService {

    @Autowired
    BlobStorageRepository repository;

    @Override
    public Mono<SaveBlobResponse> saveFile(Resource resource, String fileName) throws IOException {
        return repository.saveBlob(resource, fileName)
                .map(MessageBuilder::buildCreateGastoResponse);
    }
}
