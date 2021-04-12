package br.com.cvc.banktransferscheduler.usecases.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InputError {

    private String field;
    private String error;

    public String getField() {
        return field;
    }

    public String getError() {
        return error;
    }
}
