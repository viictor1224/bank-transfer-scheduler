package br.com.cvc.banktransferscheduler.entities;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TransferInput {

    @NotNull
    private String originAccount;
    @NotNull
    private String destinationAccount;
    @NotNull
    private BigDecimal transferValue;
    @NotNull
    private LocalDate transferDate;

    private TransferInput() {
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

    public LocalDate getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDate transferDate) {
        this.transferDate = transferDate;
    }

}
