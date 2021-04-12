package br.com.cvc.banktransferscheduler.gateway.database.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @NotEmpty// use length? notempty?
    private String originAccount;
    @NotNull @NotEmpty
    private String destinationAccount;
    @NotNull @NotEmpty
    private BigDecimal transferValue;
    private BigDecimal feeValue;
    @NotNull @NotEmpty
    private LocalDate transferDate;
    private LocalDate schedulingDate;

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

}

