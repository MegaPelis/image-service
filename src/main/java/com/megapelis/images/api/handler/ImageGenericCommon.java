package com.megapelis.images.api.handler;

import com.megapelis.images.model.dto.request.generic.Request;
import com.megapelis.images.model.enums.ImageStatusEnum;
import com.megapelis.images.util.ImageCommon;
import com.megapelis.images.util.ImageException;

/**
 * Clase {@link ImageGenericCommon}
 * @author yadir.garcia.
 */
public class ImageGenericCommon {

    public ImageGenericCommon(){
    }

    /**
     * Metodo que permite parsear el payload.
     * @param request
     * @param clazz
     * @return {@link T}
     * @param <T>
     * @throws ImageException
     */
    protected <T> T convertPayload(Request request, Class<T> clazz) throws ImageException {
        if(null == request || null == request.getData())
            throw new ImageException(ImageStatusEnum.ERROR_FORMAT_REQUEST);
        return ImageCommon.convertObjectToClass(request.getData(), clazz);
    }
}
