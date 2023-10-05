package com.drem.presupuesto.util.mapping;

import com.drem.presupuesto.model.api.request.CreatePresupuestoRequest;
import com.drem.presupuesto.model.entities.Presupuesto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PresupuestoBuilder {

    public static Presupuesto builderPresupuestoOfCreate(CreatePresupuestoRequest request){
        log.info("mapeando la creación del presupuesto");
        return Presupuesto
                .builder()
                .descripcion(request.getDescription())
                .fecha_creacion(request.getDate())
                .nombre(request.getName())
                .build();
    }

}
