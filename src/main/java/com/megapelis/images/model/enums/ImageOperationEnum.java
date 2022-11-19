package com.megapelis.images.model.enums;

import com.megapelis.images.util.ImageException;
import lombok.Getter;

import java.util.Arrays;

/**
 * Clase {@link ImageOperationEnum}
 *
 * @author yadir.garcia.
 */
@Getter
public enum ImageOperationEnum {

    CREATE("create"),
    FIND_ALL("findAll"),
    DELETE("delete"),
    UPDATE("update");

    private String name;

    ImageOperationEnum(String name) {
        this.name = name;
    }

    /**
     * Metodo que permite validar si existe esa operación
     * @param name
     * @return {@link ImageOperationEnum}
     * @throws ImageException
     */
    public static ImageOperationEnum isValid(String name) throws ImageException {
        return Arrays.stream(ImageOperationEnum.values())
                .filter(operationEnum -> operationEnum.getName().equalsIgnoreCase(name))
                .findFirst().orElseThrow(() -> new ImageException(ImageStatusEnum.ERROR_SERVICE_OR_OPERATION));
    }

}
