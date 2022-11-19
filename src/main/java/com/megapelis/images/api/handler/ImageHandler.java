package com.megapelis.images.api.handler;

import com.megapelis.images.model.dto.request.generic.Request;
import com.megapelis.images.model.dto.response.Response;
import com.megapelis.images.model.enums.ImageStatusEnum;
import com.megapelis.images.util.ImageCommon;
import com.megapelis.images.util.ImageException;

public abstract class ImageHandler extends ImageGenericCommon {
    /**
     * Metodo que permite ejecutar la logica del handler.
     * @param request
     * @return {@link Response}
     */
    public Response execute(Request request) {
        Response response;
        try {
            Object object = validatePayload(request);

            response = ImageCommon.buildResponse(request, ImageStatusEnum.SUCCESS);
        } catch (ImageException exception) {
            response = ImageCommon.buildResponse(request, exception.getStatus());
        }
        return response;
    }

    /**
     * Metodo que permite validar el payload.
     *
     * @param request
     * @return {@link Object}
     * @throws ImageException
     */
    public abstract Object validatePayload(Request request) throws ImageException;

}
