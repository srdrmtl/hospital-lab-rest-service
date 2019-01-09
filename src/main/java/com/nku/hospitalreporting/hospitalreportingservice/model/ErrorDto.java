package com.nku.hospitalreporting.hospitalreportingservice.model;

import java.io.Serializable;

/**
 * Bu sınıfta genelde hata mesajlarını json olarak basıyoruz. Ama token'i body'de
 * göndermek içinde bu sınıfı kullandım. status = true message = token içeriği
 * şeklinde isminden ötürü biraz saçma oldu ama neyse düzeltiriz bi ara.
 * FIXME:...
 * @author serdar
 */

public class ErrorDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean status;
    private String message;

    public ErrorDto(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
