package com.drem.gasto.controller;

import com.drem.gasto.model.api.request.CreateGastoRequest;
import com.drem.gasto.model.api.request.FindAllMethodGastoRequest;
import com.drem.gasto.model.api.response.CreateGastoResponse;
import com.drem.gasto.model.api.response.FindAllGastoResponse;
import com.drem.gasto.model.api.response.FindByNameGastoResponse;
import com.drem.gasto.service.GastoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/gasto")
public class GastoController {

    @Autowired
    GastoService gastoService;

    @PostMapping(path = "create", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<CreateGastoResponse> creargasto(@RequestBody CreateGastoRequest gastoRequest) {
        return gastoService.create(gastoRequest)
                .doOnSuccess(a -> a.setCode(HttpStatus.OK.value()));
    }

        @GetMapping(path = "all/{identifier}/{date}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<FindAllGastoResponse> buscargastoPorRangoFechas(@PathVariable String identifier,
                                                                @PathVariable LocalDateTime date) {
        return gastoService.findAllRangeDate(
                        FindAllMethodGastoRequest.builder()
                                .identifierUser(identifier)
                                .date(date)
                                .build()
                )
                .doOnSuccess(a -> a.setCode(HttpStatus.OK.value()));
    }

    @GetMapping(path = "byName/{identifier}/{presupuestoId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<FindAllGastoResponse> buscargastoPorNombre(@PathVariable String identifier,
                                                              @PathVariable Integer presupuestoId) {
        return gastoService.findAllByPresupuesto(
                        FindAllMethodGastoRequest.builder()
                                .identifierUser(identifier)
                                .presupuestoId(presupuestoId)
                                .build()
                )
                    .doOnSuccess(a -> a.setCode(HttpStatus.OK.value()));
    }
}
