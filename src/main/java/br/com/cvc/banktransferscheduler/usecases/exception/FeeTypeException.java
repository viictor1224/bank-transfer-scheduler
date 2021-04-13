package br.com.cvc.banktransferscheduler.usecases.exception;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class FeeTypeException extends RuntimeException {

    private LocalDate date;
    private BigDecimal value;
    private String message;

    public FeeTypeException(LocalDate date, BigDecimal value) {
        this.date = date;
        this.value = value;
        this.message = "Fee not found for date: " + date + " and value: " + value;
    }

}
