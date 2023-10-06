package com.drem.gasto.model.api.response;

import com.drem.gasto.util.abstractClass.AbstractResponse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CreateGastoResponse extends AbstractResponse {

    private String gastoName;
    private String monto;
    private LocalDateTime gastoDate;

}
