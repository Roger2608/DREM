package com.drem.gasto.model.api.response;

import com.drem.gasto.model.entities.Gasto;
import com.drem.gasto.util.abstractClass.AbstractResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindByNameGastoResponse extends AbstractResponse {

    private Gasto gasto;

}
