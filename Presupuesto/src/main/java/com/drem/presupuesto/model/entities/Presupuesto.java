package com.drem.presupuesto.model.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@Table("presupuestos")
public class Presupuesto {

    @Id
    private Integer id;
    private String nombre;
    private String descripcion;
    private LocalDateTime fecha_creacion;
    private Integer usuario_id;

}
