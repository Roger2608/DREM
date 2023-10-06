package com.drem.gasto.model.api.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

@Data
@Builder
public class CreateGastoRequest {
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private Double monto;
    @NonNull
    private LocalDateTime date;
    @NonNull
    private String userIdentifier;
    @NonNull
    private Integer presupuesto;

}
