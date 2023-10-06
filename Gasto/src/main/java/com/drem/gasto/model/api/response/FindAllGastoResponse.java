package com.drem.gasto.model.api.response;

import com.drem.gasto.model.entities.Gasto;
import com.drem.gasto.util.abstractClass.AbstractResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FindAllGastoResponse extends AbstractResponse {

    private List<Gasto> gastos;

}
