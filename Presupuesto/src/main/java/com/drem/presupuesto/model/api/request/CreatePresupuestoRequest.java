package com.drem.presupuesto.model.api.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

@Data
@Builder
public class CreatePresupuestoRequest {
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private LocalDateTime date;
    @NonNull
    private String userIdentifier;
}
