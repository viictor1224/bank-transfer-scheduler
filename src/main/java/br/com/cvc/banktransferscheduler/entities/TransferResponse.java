package br.com.cvc.banktransferscheduler.entities;

import br.com.cvc.banktransferscheduler.entities.database.TransferEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class TransferResponse {

    private Long id;

    private String originAccount;

    private String destinationAccount;

    private BigDecimal transferValue;

    private BigDecimal feeValue;

    private Enum feeType;

    private LocalDate transferDate;

    private LocalDate schedulingDate;

}
