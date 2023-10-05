package com.drem.presupuesto.util.mapping;

import com.drem.presupuesto.model.api.response.CreatePresupuestoResponse;
import com.drem.presupuesto.model.api.response.FindAllPresupuestoResponse;
import com.drem.presupuesto.model.api.response.FindByNamePresupuestoResponse;
import com.drem.presupuesto.model.entities.Presupuesto;
import com.drem.presupuesto.util.constants.ResponseConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class MessageBuilder {

    public static CreatePresupuestoResponse buildCreatePresupuestoResponse(Presupuesto presupuesto){
        CreatePresupuestoResponse response = CreatePresupuestoResponse.builder()
                .presupuestoName(presupuesto.getNombre())
                .presupuestoDate(presupuesto.getFecha_creacion())
                .build();
        response.setMessage(ResponseConstants.PRESUPUESTO_CREATE_SUCCESS_MESSAGE);
        return response;
    }

    public static FindAllPresupuestoResponse buildFindAllPresupuestoResponse(List<Presupuesto> presupuestos){
        FindAllPresupuestoResponse response = FindAllPresupuestoResponse.builder()
                .presupuestos(presupuestos)
                .build();
        response.setMessage(ResponseConstants.PRESUPUESTO_CREATE_SUCCESS_MESSAGE);
        return response;
    }

    public static FindByNamePresupuestoResponse buildFindByNamePresupuestoResponse(Presupuesto presupuesto){
        FindByNamePresupuestoResponse response = FindByNamePresupuestoResponse.builder()
                .presupuesto(presupuesto)
                .build();
        response.setMessage(ResponseConstants.PRESUPUESTO_CREATE_SUCCESS_MESSAGE);
        return response;
    }

}
