package com.drem.gasto.service;

import com.drem.gasto.model.api.request.CreateGastoRequest;
import com.drem.gasto.model.api.request.FindAllMethodGastoRequest;
import com.drem.gasto.model.api.response.CreateGastoResponse;
import com.drem.gasto.model.api.response.FindAllGastoResponse;
import reactor.core.publisher.Mono;

public interface GastoService {

    Mono<CreateGastoResponse> create(CreateGastoRequest GastoRequest);

    Mono<FindAllGastoResponse> findAllRangeDate(FindAllMethodGastoRequest GastoRequest);

    Mono<FindAllGastoResponse> findAllByPresupuesto(FindAllMethodGastoRequest GastoRequest);


}
