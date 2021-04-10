package br.com.cvc.banktransferscheduler.gateway.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TransferInput {

    // id annotations
    private Long id;
    private String originAccount;
    private String destinationAccount;
    private BigDecimal transferValue;
    private LocalDate transferDate;
    private LocalDate schedulingDate;


    // Builder pattern
    public static class Builder {

        private Long id;

        private String originAccount;
        private String destinationAccount;
        private BigDecimal transferValue;
        private LocalDate transferDate;
        private LocalDate schedulingDate;

        public Builder (String originAccount) {
            this.originAccount = originAccount;
        }

        public Builder withDestination(String destinationAccount) {
            this.destinationAccount = destinationAccount;
            return this;
        }

        public Builder atValue(BigDecimal transferValue) {
            this.transferValue = transferValue;
            return this;
        }

        public Builder atTransferDate(LocalDate transferDate) {
            this.transferDate = transferDate;
            return this;
        }

        public Builder atSchedulingDate(LocalDate schedulingDate) {
            this.schedulingDate = schedulingDate;
            return this;
        }

         public TransferInput build() {
            TransferInput transferInput = new TransferInput();
            transferInput.originAccount = this.originAccount;
            transferInput.destinationAccount = this.destinationAccount;
            transferInput.transferValue = this.transferValue;
            transferInput.transferDate = this.transferDate;
            transferInput.schedulingDate = this.schedulingDate;

            return transferInput;
         }
    }

    private TransferInput() {
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
