package com.drem.presupuesto.util.abstractClass;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
public abstract class AbstractResponse {
    private int code;
    private String message;
}
