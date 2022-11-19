package com.megapelis.images.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Clase {@link CreateImageRQ}
 *
 * @author yadir.garcia.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateImageRQ implements Serializable {
    private String image;
}
