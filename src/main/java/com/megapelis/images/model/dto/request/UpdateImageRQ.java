package com.megapelis.images.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase {@link UpdateImageRQ}
 *
 * @author yadir.garcia.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateImageRQ {
    private String id;
    private String image;
}
