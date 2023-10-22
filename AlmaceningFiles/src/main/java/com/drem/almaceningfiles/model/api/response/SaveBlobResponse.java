package com.drem.almaceningfiles.model.api.response;

import com.drem.almaceningfiles.util.abstractClass.AbstractResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaveBlobResponse extends AbstractResponse {

    private String nameBlob;

}
