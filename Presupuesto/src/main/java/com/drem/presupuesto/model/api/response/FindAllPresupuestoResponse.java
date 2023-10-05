package com.drem.presupuesto.model.api.response;

import com.drem.presupuesto.model.entities.Presupuesto;
import com.drem.presupuesto.util.abstractClass.AbstractResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FindAllPresupuestoResponse extends AbstractResponse {

    private List<Presupuesto> presupuestos;

}
