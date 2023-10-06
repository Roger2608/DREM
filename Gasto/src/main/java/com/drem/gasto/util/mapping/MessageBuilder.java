package com.drem.gasto.util.mapping;

import com.drem.gasto.model.api.response.CreateGastoResponse;
import com.drem.gasto.model.api.response.FindAllGastoResponse;
import com.drem.gasto.model.api.response.FindByNameGastoResponse;
import com.drem.gasto.model.entities.Gasto;
import com.drem.gasto.util.constants.ResponseConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class MessageBuilder {

    public static CreateGastoResponse buildCreateGastoResponse(Gasto gasto){
        CreateGastoResponse response = CreateGastoResponse.builder()
                .gastoName(gasto.getDescripcion())
                .gastoDate(gasto.getFecha())
                .build();
        response.setMessage(ResponseConstants.GASTO_CREATE_SUCCESS_MESSAGE);
        return response;
    }

    public static FindAllGastoResponse buildFindAllGastoResponse(List<Gasto> gastos){
        FindAllGastoResponse response = FindAllGastoResponse.builder()
                .gastos(gastos)
                .build();
        response.setMessage(ResponseConstants.GASTO_FIND_ALL_RANGE_DATE);
        return response;
    }

    public static FindAllGastoResponse buildFindByPresupuestoGastoResponse(List<Gasto> gastos){
        FindAllGastoResponse response = FindAllGastoResponse.builder()
                .gastos(gastos)
                .build();
        response.setMessage(ResponseConstants.GASTO_FIND_ALL_BY_PRESUPUESTO);
        return response;
    }

}
