package com.drem.gasto.util.abstractClass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractResponse {
    private int code;
    private String message;
}
