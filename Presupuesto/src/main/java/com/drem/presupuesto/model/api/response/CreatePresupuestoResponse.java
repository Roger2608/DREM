package com.drem.presupuesto.model.api.response;

import com.drem.presupuesto.model.entities.Presupuesto;
import com.drem.presupuesto.util.abstractClass.AbstractResponse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CreatePresupuestoResponse extends AbstractResponse {

    private String presupuestoName;
    private LocalDateTime presupuestoDate;

}
