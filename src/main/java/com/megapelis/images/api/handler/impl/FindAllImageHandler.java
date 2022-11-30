package com.megapelis.images.api.handler.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.megapelis.images.api.handler.ImageHandler;
import com.megapelis.images.model.dto.request.FindAllImageRQ;
import com.megapelis.images.model.dto.request.generic.Request;
import com.megapelis.images.model.dto.response.Response;
import com.megapelis.images.model.dto.response.imp.FindAllImageRS;
import com.megapelis.images.model.dto.response.imp.FindByIdImageRS;
import com.megapelis.images.model.entity.Image;
import com.megapelis.images.model.enums.ImageStatusEnum;
import com.megapelis.images.util.ImageCommon;
import com.megapelis.images.util.ImageConstant;
import com.megapelis.images.util.ImageException;

import java.util.List;

/**
 * Clase {@link FindAllImageHandler}
 *
 * @author yadir.garcia.
 */

public class FindAllImageHandler extends ImageHandler implements RequestHandler<Request, Response> {

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
        FindAllImageRQ findAllImageRQ = convertPayload(request, FindAllImageRQ.class);
        if(ImageConstant.INTEGER_ONE > findAllImageRQ.getPage())
            findAllImageRQ.setPage(ImageConstant.INTEGER_ONE);
        return findAllImageRQ;
    }

    @Override
    public Response handleRequest(Request request, Context context) {
        db = AmazonDynamoDBClientBuilder.defaultClient();
        mapper = new DynamoDBMapper(db);
        FindAllImageRS findAllImageRS = new FindAllImageRS();
        findAllImageRS.setFindAll(mapper.scan(FindByIdImageRS.class, new DynamoDBScanExpression()));
        return ImageCommon.buildResponse(request, ImageStatusEnum.SUCCESS, findAllImageRS);
    }
}
