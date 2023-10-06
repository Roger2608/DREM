package com.drem.gasto.model.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@Table("gastos_diarios")
public class Gasto {

    @Id
    private Integer id;
    private String descripcion;
    private Double monto;
    private LocalDateTime fecha;
    private Integer usuario_id;
    private Integer presupuesto_id;

}
