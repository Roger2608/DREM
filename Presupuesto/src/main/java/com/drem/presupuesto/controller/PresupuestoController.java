package com.drem.presupuesto.controller;

import com.drem.presupuesto.model.api.request.CreatePresupuestoRequest;
import com.drem.presupuesto.model.api.request.FindAllMethodPresupuestoRequest;
import com.drem.presupuesto.model.api.response.CreatePresupuestoResponse;
import com.drem.presupuesto.model.api.response.FindAllPresupuestoResponse;
import com.drem.presupuesto.model.api.response.FindByNamePresupuestoResponse;
import com.drem.presupuesto.service.PresupuestoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/presupuesto")
public class PresupuestoController {

    @Autowired
    PresupuestoService presupuestoService;

    @PostMapping(path = "create", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<CreatePresupuestoResponse> crearPresupuesto(@RequestBody CreatePresupuestoRequest presupuestoRequest) {
        return presupuestoService.create(presupuestoRequest)
                .doOnSuccess(a -> a.setCode(HttpStatus.OK.value()));
    }

    @GetMapping(path = "all/{identifier}/{date}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<FindAllPresupuestoResponse> buscarPresupuestoPorRangoFechas(@PathVariable String identifier,
                                                                            @PathVariable LocalDateTime date,
                                                                            @RequestParam(required = false) String namePresupuesto) {
        return presupuestoService.findAllRangeDate(
                        FindAllMethodPresupuestoRequest.builder()
                                .identifierUser(identifier)
                                .namePresupuesto(namePresupuesto)
                                .date(date)
                                .build()
                )
                .doOnSuccess(a -> a.setCode(HttpStatus.OK.value()));
    }

    @GetMapping(path = "byName/{identifier}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<FindByNamePresupuestoResponse> buscarPresupuestoPorNombre(@PathVariable String identifier,
                                                                          @RequestParam String namePresupuesto) {
        return presupuestoService.findAllByName(
                        FindAllMethodPresupuestoRequest.builder()
                                .identifierUser(identifier)
                                .namePresupuesto(namePresupuesto)
                                .build()
                )
                    .doOnSuccess(a -> a.setCode(HttpStatus.OK.value()));
    }

}
