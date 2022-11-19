package com.megapelis.images.api.factory;

import com.megapelis.images.api.handler.ImageHandler;
import com.megapelis.images.api.handler.impl.CreateImageHandler;
import com.megapelis.images.api.handler.impl.DeleteImageHandler;
import com.megapelis.images.api.handler.impl.FindAllImageHandler;
import com.megapelis.images.api.handler.impl.UpdateImageHandler;
import com.megapelis.images.model.dto.request.generic.Request;
import com.megapelis.images.model.dto.response.Response;
import com.megapelis.images.model.enums.ImageOperationEnum;
import com.megapelis.images.model.enums.ImageStatusEnum;
import com.megapelis.images.util.ImageCommon;
import com.megapelis.images.util.ImageException;

/**
 * Clase {@link ImageFactory}
 *
 * @author yadir.garcia.
 */
public class ImageFactory {
    private ImageFactory() {
    }
    /**
     * Fabrica que permite ejecutar mediante la operacion.
     * @param request
     * @return {@link Response}
     */

    public static Response handler(Request request) {
        ImageCommon.output(request);
        Response response = null;
        ImageHandler handler = null;
        try {
            ImageCommon.isValidRequest(request);
            switch (ImageOperationEnum.isValid(request.getOperation())){
                case CREATE:
                    handler = new CreateImageHandler();
                    break;
                case FIND_ALL:
                    handler = new FindAllImageHandler();
                    break;
                case DELETE:
                    handler = new DeleteImageHandler();
                    break;
                case UPDATE:
                    handler = new UpdateImageHandler();
                    break;
                default:
                    response =  ImageCommon.buildResponse(request, ImageStatusEnum.ERROR);
                    break;
            }
        } catch (ImageException e) {
            response =  ImageCommon.buildResponse(request, e.getStatus());
        }
        if(null == response && null != handler)
            response = handler.execute(request);
        ImageCommon.output(response);
        return response;
    }


}
