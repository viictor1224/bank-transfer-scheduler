package br.com.cvc.banktransferscheduler.entities;

import br.com.cvc.banktransferscheduler.entities.database.TransferEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TransferResponse {

    private Long id;

    private String originAccount;

    private String destinationAccount;

    private BigDecimal transferValue;

    private BigDecimal feeValue;

    private LocalDate transferDate;

    private LocalDate schedulingDate;

    public TransferResponse(TransferEntity transferEntity) {
        this.id = transferEntity.getId();
        this.originAccount = transferEntity.getOriginAccount();
        this.destinationAccount = transferEntity.getDestinationAccount();
        this.transferValue = transferEntity.getTransferValue();
        this.feeValue = transferEntity.getFeeValue();
        this.transferDate = transferEntity.getTransferDate();
        this.schedulingDate = transferEntity.getSchedulingDate();
    }

}
