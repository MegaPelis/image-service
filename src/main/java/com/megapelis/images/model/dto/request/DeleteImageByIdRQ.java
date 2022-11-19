package com.megapelis.images.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
/**
 * Clase {@link DeleteImageByIdRQ}
 *
 * @author yadir.garcia.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteImageByIdRQ implements Serializable {
    private String id;
}
