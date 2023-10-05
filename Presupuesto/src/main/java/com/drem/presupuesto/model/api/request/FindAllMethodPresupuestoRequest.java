package com.drem.presupuesto.model.api.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Data
@Builder
public class FindAllMethodPresupuestoRequest {

    @NonNull
    private String identifierUser;
    @Nullable
    private LocalDateTime date;
    @Nullable
    private String namePresupuesto;

}
