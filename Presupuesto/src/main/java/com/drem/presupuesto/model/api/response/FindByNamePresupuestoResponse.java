package com.drem.presupuesto.model.api.response;

import com.drem.presupuesto.model.entities.Presupuesto;
import com.drem.presupuesto.util.abstractClass.AbstractResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindByNamePresupuestoResponse extends AbstractResponse {

    private Presupuesto presupuesto;

}
