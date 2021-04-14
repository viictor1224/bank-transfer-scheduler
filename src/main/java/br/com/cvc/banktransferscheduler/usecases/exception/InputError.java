package br.com.cvc.banktransferscheduler.usecases.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InputError {

    private String field;
    private String error;

}
