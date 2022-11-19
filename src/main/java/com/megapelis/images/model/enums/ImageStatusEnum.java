package com.megapelis.images.model.enums;

import lombok.Getter;

/**
 * Clase {@link ImageStatusEnum}
 * @author yadir.garcia.
 */
@Getter
public enum ImageStatusEnum {
    SUCCESS("200", "La petición se ha procesado exitosamente!", "Super todo ha salido bien!"),
    ERROR("500", "No se ha procesado la petición.", "Lo sentimos hemos tenido una falla, vuelva a intentar mas tarde."),

    ERROR_FORMAT_REQUEST("400", "El formato de la petición no es el esperado.", "Lo sentimos hemos tenido una falla, vuelva a intentar mas tarde."),
    ERROR_SERVICE_OR_OPERATION("404", "No fue encontrado ningún servicio.", "Esperamos estar pronto para ti!"), ERROR_NOT_FOUND_DATA("405","No fue encontrada la informacion." ,"No se encuentra lo que buscabas" );

    private final String code;
    private final String messageBackend;
    private final String messageFrontend;

    ImageStatusEnum(String code, String messageBackend, String messageFrontend){
        this.code = code;
        this.messageBackend = messageBackend;
        this.messageFrontend = messageFrontend;
    }

}
