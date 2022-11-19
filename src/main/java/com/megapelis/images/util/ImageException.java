package com.megapelis.images.util;

import com.megapelis.images.model.enums.ImageStatusEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase {@link ImageException}
 * @author yadir.garcia.
 */
@Getter
@Setter
public class ImageException extends Exception {
    private ImageStatusEnum status;

    public ImageException(ImageStatusEnum status) {
        super(status.getMessageBackend());
        this.status = status;
    }
}

