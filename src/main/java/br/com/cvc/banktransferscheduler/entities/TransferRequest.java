package br.com.cvc.banktransferscheduler.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Size(min=6, max = 6, message = "incorrectly formatted origin account")
    @Pattern(regexp = "[0-9]+")
    private String originAccount;
    @NotNull
    @Size(min=6, max = 6, message = "incorrectly formatted destination account")
    @Pattern(regexp = "[0-9]+")
    private String destinationAccount;
    @NotNull
    @FutureOrPresent
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate transferDate;

}
