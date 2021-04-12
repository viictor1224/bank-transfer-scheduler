package br.com.cvc.banktransferscheduler.usecases.exception;

import br.com.cvc.banktransferscheduler.usecases.exception.InputError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationHandler {

    @Autowired
    private MessageSource messageSource;  // class that helps to get error message according to message's origin

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<InputError> handle(MethodArgumentNotValidException exception) {
        List<InputError> errors = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            InputError error = new InputError(e.getField(), message);
            errors.add(error);
        });

        return errors;

    }


}