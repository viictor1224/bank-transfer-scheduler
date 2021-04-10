package br.com.cvc.banktransferscheduler.entities;

import br.com.cvc.banktransferscheduler.gateway.model.TransferInput;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originAccount;
    private String destinationAccount;
    private BigDecimal transferValue;
    private BigDecimal feeValue;
    private LocalDate transferDate;
    private LocalDate schedulingDate;

    private Transfer (TransferInput transferInput) {
        this.id = transferInput.getId();
        this.originAccount = transferInput.getOriginAccount();
        this.destinationAccount = transferInput.getDestinationAccount();
        this.transferValue = transferInput.getTransferValue();
//        this.feeValue =
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(String originAccount) {
        this.originAccount = originAccount;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public BigDecimal getTransferValue() {
        return transferValue;
    }

    public void setTransferValue(BigDecimal transferValue) {
        this.transferValue = transferValue;
    }

    public BigDecimal getFeeValue() {
        return feeValue;
    }

    public void setTaxValue(BigDecimal feeValue) {
        this.feeValue = feeValue;
    }

    public LocalDate getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDate transferDate) {
        this.transferDate = transferDate;
    }

    public LocalDate getSchedulingDate() {
        return schedulingDate;
    }

    public void setSchedulingDate(LocalDate schedulingDate) {
        this.schedulingDate = schedulingDate;
    }

    public static List<Transfer> converter (List<TransferInput> transferInputs) {
        return transferInputs.stream().map(Transfer::new).collect(Collectors.toList());
    }

}
