package com.drem.fileprocessing.recipient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GastosRecipient {
    private String description;
    private Double monto;
    private String fecha;
    private Integer usuario;
    private Integer presupuesto;
}
