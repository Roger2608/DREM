package com.drem.almaceningfiles.controller;

import com.drem.almaceningfiles.model.api.response.SaveBlobResponse;
import com.drem.almaceningfiles.service.AlmaceningFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@RequestMapping("almacening/file")
@Slf4j
public class AlmaceningController {

    @Autowired
    AlmaceningFileService fileService;

    @PostMapping(path = "save/{fileName}",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<SaveBlobResponse> saveFile(@RequestBody Resource resource,
                                           @PathVariable(name = "fileName") String fileName) throws IOException {
        return fileService.saveFile(resource, fileName);
    }

}
