package br.com.cvc.banktransferscheduler.entities;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

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
    private BigDecimal transferValue;
    @NotNull
    private LocalDate transferDate;

    private TransferRequest() {
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
