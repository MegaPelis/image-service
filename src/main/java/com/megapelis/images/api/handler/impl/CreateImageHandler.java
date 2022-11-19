package com.megapelis.images.api.handler.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.megapelis.images.api.handler.ImageHandler;
import com.megapelis.images.model.dto.request.CreateImageRQ;
import com.megapelis.images.model.dto.request.generic.Request;
import com.megapelis.images.model.dto.response.Response;
import com.megapelis.images.model.entity.Image;
import com.megapelis.images.model.enums.ImageStatusEnum;
import com.megapelis.images.util.ImageCommon;
import com.megapelis.images.util.ImageException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * Clase {@link CreateImageHandler}
 *
 * @author yadir.garcia.
 */
public class CreateImageHandler extends ImageHandler implements RequestHandler<Request, Response> {

    private AmazonDynamoDB db;
    private DynamoDBMapper mapper;

    @Override
    public Response execute(Request request) {
        Response response;
        try {
            Object object = validatePayload(request);
            response = handleRequest(request, null);
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
    @Override
    public Object validatePayload(Request request) throws ImageException {
        CreateImageRQ createImageRQ = convertPayload(request, CreateImageRQ.class);
        if (!ImageCommon.isValidString(createImageRQ.getImage()))
            throw new ImageException(ImageStatusEnum.ERROR_FORMAT_REQUEST);
        return createImageRQ;
    }

    @Override
    public Response handleRequest(Request request, Context context) {
        db = AmazonDynamoDBClientBuilder.defaultClient();
        mapper = new DynamoDBMapper(db);
        Image image = ImageCommon.convertObjectToClass(request.getData(), Image.class);
        image.setCreatedDate(ImageCommon.getDateTime());
        image.setLastModifiedDate(null);
        mapper.save(image);
        return ImageCommon.buildResponse(request, ImageStatusEnum.SUCCESS);
    }
}
