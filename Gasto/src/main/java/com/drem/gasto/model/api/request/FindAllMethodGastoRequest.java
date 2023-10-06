package com.drem.gasto.model.api.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Data
@Builder
public class FindAllMethodGastoRequest {

    @NonNull
    private String identifierUser;
    @Nullable
    private LocalDateTime date;
    @Nullable
    private Integer presupuestoId;

}
