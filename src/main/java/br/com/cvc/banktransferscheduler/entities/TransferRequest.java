package br.com.cvc.banktransferscheduler.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class TransferRequest {

    @NotNull
    @Size(max = 6, message = "incorrectly formatted origin account")
    @Pattern(regexp = "[0-9]+")
    private String originAccount;
    @NotNull
    @Size(max = 6, message = "incorrectly formatted destination account")
    @Pattern(regexp = "[0-9]+")
    private String destinationAccount;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal transferValue;
    @NotNull
    @FutureOrPresent
    private LocalDate transferDate;

}
