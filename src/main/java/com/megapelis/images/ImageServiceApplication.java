package com.megapelis.images;


import com.megapelis.images.api.factory.ImageFactory;
import com.megapelis.images.model.dto.request.generic.Request;
import com.megapelis.images.model.dto.response.Response;

/**
 * Clase {@link ImageServiceApplication}
 * @author yadir.garcia.
 */
public class ImageServiceApplication {

    /**
     * Metodo que permite realizar el llamado de los servicios.
     * @param request
     * @return {@link Response}
     */

    public Response handler(Request request){
        return ImageFactory.handler(request);
    }


}
