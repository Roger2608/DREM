package com.drem.gasto.util.mapping;


import com.drem.gasto.model.api.request.CreateGastoRequest;
import com.drem.gasto.model.entities.Gasto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GastoBuilder {

    public static Gasto builderGastoOfCreate(CreateGastoRequest request){
        log.info("mapeando la creación del gasto");
        return Gasto
                .builder()
                .descripcion(request.getDescription())
                .fecha(request.getDate())
                .monto(request.getMonto())
                .presupuesto_id(request.getPresupuesto())
                .build();
    }

}
