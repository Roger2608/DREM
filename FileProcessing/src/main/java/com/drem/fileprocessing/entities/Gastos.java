package com.drem.fileprocessing.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "gastos_diarios")
@AllArgsConstructor
@NoArgsConstructor
public class Gastos {

    @Id
    private Integer id;
    private String descripcion;
    private Double monto;
    private LocalDateTime fecha;
    private Integer usuario_id;
    private Integer presupuesto_id;

}
