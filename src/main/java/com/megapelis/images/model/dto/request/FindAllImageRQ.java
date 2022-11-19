package com.megapelis.images.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Clase {@link FindAllImageRQ}
 *
 * @author yadir.garcia.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FindAllImageRQ implements Serializable {
   private int page;
}
