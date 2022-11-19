package com.megapelis.images.api.handler.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.megapelis.images.api.handler.ImageHandler;
import com.megapelis.images.model.dto.request.DeleteImageByIdRQ;
import com.megapelis.images.model.dto.request.generic.Request;
import com.megapelis.images.model.dto.response.Response;
import com.megapelis.images.model.entity.Image;
import com.megapelis.images.model.enums.ImageStatusEnum;
import com.megapelis.images.util.ImageCommon;
import com.megapelis.images.util.ImageException;

public class DeleteImageHandler extends ImageHandler implements RequestHandler<Request, Response> {

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
    @Override
    public Object validatePayload(Request request) throws ImageException {
        DeleteImageByIdRQ deleteImageByIdRQ = convertPayload(request, DeleteImageByIdRQ.class);
        if (!ImageCommon.isValidString(deleteImageByIdRQ.getId()))
            throw new ImageException(ImageStatusEnum.ERROR_FORMAT_REQUEST);
        return deleteImageByIdRQ;
    }

    @Override
    public Response handleRequest(Request request, Context context) {
        db = AmazonDynamoDBClientBuilder.defaultClient();
        mapper = new DynamoDBMapper(db);
        Image image = ImageCommon.convertObjectToClass(request.getData(), Image.class);
        System.out.println("Image to delete: " + image.getId());
        Image imageDB = mapper.load(Image.class, image.getId());
        if(imageDB!=null){
            imageDB.setLastModifiedDate(ImageCommon.getDateTime());
            mapper.delete(imageDB);
            return ImageCommon.buildResponse(request, ImageStatusEnum.SUCCESS, imageDB);
        }

        return ImageCommon.buildResponse(request, ImageStatusEnum.ERROR_NOT_FOUND_DATA);
    }



}
