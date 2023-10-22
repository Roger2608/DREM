package com.drem.almaceningfiles.util.abstractClass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractResponse {
    private int code;
    private String message;
}
