package br.com.cvc.banktransferscheduler.entities.database;

import br.com.cvc.banktransferscheduler.usecases.fee.enums.Fee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originAccount;

    private String destinationAccount;

    private BigDecimal transferValue;

    private BigDecimal feeValue;

    @Enumerated(EnumType.STRING)
    private Fee feeType;

    private LocalDate transferDate;

    private LocalDate schedulingDate;

}

