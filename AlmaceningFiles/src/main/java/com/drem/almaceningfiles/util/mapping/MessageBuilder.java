package com.drem.almaceningfiles.util.mapping;

import com.drem.almaceningfiles.model.api.response.SaveBlobResponse;
import com.drem.almaceningfiles.util.constants.ResponseConstants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageBuilder {

    public static SaveBlobResponse buildCreateGastoResponse(String nameFile){
        SaveBlobResponse response = SaveBlobResponse.builder()
                .nameBlob(nameFile)
                .build();
        response.setMessage(ResponseConstants.FILE_SAVE_SUCCESS_MESSAGE);
        return response;
    }

}
