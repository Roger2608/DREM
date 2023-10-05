package com.drem.presupuesto.service;

import com.drem.presupuesto.model.api.request.CreatePresupuestoRequest;
import com.drem.presupuesto.model.api.request.FindAllMethodPresupuestoRequest;
import com.drem.presupuesto.model.api.response.CreatePresupuestoResponse;
import com.drem.presupuesto.model.api.response.FindAllPresupuestoResponse;
import com.drem.presupuesto.model.api.response.FindByNamePresupuestoResponse;
import com.drem.presupuesto.model.entities.Presupuesto;
import reactor.core.publisher.Mono;

public interface PresupuestoService {

    Mono<CreatePresupuestoResponse> create(CreatePresupuestoRequest presupuestoRequest);

    Mono<FindAllPresupuestoResponse> findAllRangeDate(FindAllMethodPresupuestoRequest presupuestoRequest);

    Mono<FindByNamePresupuestoResponse> findAllByName(FindAllMethodPresupuestoRequest presupuestoRequest);


}
