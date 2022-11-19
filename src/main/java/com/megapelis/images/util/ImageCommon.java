package com.megapelis.images.util;

import com.google.gson.Gson;
import com.megapelis.images.model.dto.request.generic.Request;
import com.megapelis.images.model.dto.response.Response;
import com.megapelis.images.model.dto.response.ResponseStatus;
import com.megapelis.images.model.entity.Image;
import com.megapelis.images.model.enums.ImageStatusEnum;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ImageCommon {

    /**
     * Metodo que permite validar una cadena no este vacia.
     *
     * @param value
     * @return {@link boolean}
     */
    public static boolean isValidString(String value) {
        return null != value && !value.trim().isEmpty();
    }

    /**
     * Metodo que permite validar varias cadenas.
     *
     * @param values
     * @return {@link boolean}
     */
    public static boolean isValidString(String... values) {
        for (String value : values) {
            if (!isValidString(value)) {
                return ImageConstant.BOOLEAN_FALSE;
            }
        }
        return ImageConstant.BOOLEAN_TRUE;
    }

    /**
     * Metodo que permite registrar la salida para cloud watch.
     *
     * @param object
     */
    public static void output(Object object) {
        if (object instanceof Response || object instanceof Request || object instanceof Image)
            System.out.println(getStringJSON(object));
        else
            System.out.println(object);
    }

    /**
     * Metodo que permite obtener el objeto en cadena.
     *
     * @param object
     * @return {@link  String}
     */
    public static String getStringJSON(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    /**
     * Metodo que valida la petición.
     *
     * @param request
     * @throws ImageException
     */
    public static void isValidRequest(Request request) throws ImageException {
        boolean isError = ImageConstant.BOOLEAN_FALSE;
        if (null == request || !isValidString(request.getTraceId(), request.getDateTime(), request.getService(), request.getOperation()))
            isError = ImageConstant.BOOLEAN_TRUE;
        if (!request.getService().equalsIgnoreCase(ImageConstant.STRING_SERVICE_NAME))
            isError = ImageConstant.BOOLEAN_TRUE;
        if (request.getService().equalsIgnoreCase(request.getOperation()))
            isError = ImageConstant.BOOLEAN_TRUE;
        if (isError)
            throw new ImageException(ImageStatusEnum.ERROR_FORMAT_REQUEST);
    }

    /**
     * Metodo que permite construir la respuesta del servicio.
     *
     * @param request
     * @return {@link Response}
     */
    public static Response buildResponse(Request request) {
        return buildResponse(request, null, null, null, null);
    }

    /**
     * Metodo que permite construir la respuesta del servicio.
     *
     * @param request
     * @param statusEnum
     * @return {@link Response}
     */
    public static Response buildResponse(Request request, ImageStatusEnum statusEnum) {
        return buildResponse(request, statusEnum, null);
    }

    /**
     * Metodo que permite construir la respuesta del servicio.
     *
     * @param request
     * @param statusEnum
     * @param data
     * @return {@link Response}
     */
    public static Response buildResponse(Request request, ImageStatusEnum statusEnum, Object data) {
        return buildResponse(request, statusEnum.getCode(), statusEnum.getMessageBackend(), statusEnum.getMessageFrontend(), data);
    }

    /**
     * Metodo que permite construir la respuesta del servicio.
     *
     * @param request
     * @param code
     * @param messageBackend
     * @param messageFrontend
     * @param data
     * @return {@link Response}
     */
    private static Response buildResponse(Request request, String code, String messageBackend, String messageFrontend, Object data) {
        if (null == request)
            return null;
        if (!isValidString(code, messageBackend, messageFrontend)) {
            code = ImageStatusEnum.ERROR.getCode();
            messageBackend = ImageStatusEnum.ERROR.getMessageBackend();
            messageFrontend = ImageStatusEnum.ERROR.getMessageFrontend();
        }
        if (null != data) {
            Gson gson = new Gson();
            data = gson.toJson(data);
        }
        String dateTime = ImageCommon.getDateTime();
        return Response
                .builder()
                .traceId(request.getTraceId())
                .dateTime(dateTime)
                .service(request.getService())
                .operation(request.getOperation())
                .status(new ResponseStatus(code, messageBackend, messageFrontend))
                .data(data)
                .build();
    }

    /**
     * Metodo que permite obtener la fecha y hora actual.
     *
     * @return {@link String}
     */
    public static String getDateTime() {
        return ZonedDateTime.now(ZoneId.of(ImageConstant.STRING_DATE_ZONE))
                .format(DateTimeFormatter.ofPattern(ImageConstant.STRING_DATE_TIME_FORMAT));
    }

    public static <T> T convertObjectToClass(Object object, Class<T> clazz) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return gson.fromJson(json, clazz);
    }
}
