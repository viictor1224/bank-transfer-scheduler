package br.com.cvc.banktransferscheduler.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class TransferRequest {

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal transferValue;
    @NotNull
    @Size(max = 6, message = "incorrectly formatted origin account")
    @Pattern(regexp = "[0-9]+")
    private String originAccount;
    @NotNull
    @Size(max = 6, message = "incorrectly formatted destination account")
    @Pattern(regexp = "[0-9]+")
    private String destinationAccount;
    @NotNull
    @FutureOrPresent
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate transferDate;

}
