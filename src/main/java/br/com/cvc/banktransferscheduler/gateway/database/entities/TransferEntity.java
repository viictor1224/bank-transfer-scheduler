package br.com.cvc.banktransferscheduler.gateway.database.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class TransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originAccount;
    private String destinationAccount;
    private BigDecimal transferValue;
    private BigDecimal feeValue;
    private LocalDate transferDate;
    private LocalDate schedulingDate;

    //Builderpattern
    public static class Builder {

        private String originAccount;
        private String destinationAccount;
        private BigDecimal transferValue;
        private LocalDate transferDate;
        private LocalDate schedulingDate;//=LocalDate.now();
        private BigDecimal feeValue;//calculate

        public Builder(String originAccount) {
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

        public Builder atSchedulingDate(LocalDate schedulingDate) {
            this.schedulingDate = schedulingDate;
            return this;
        }

        public Builder atTransferDate(LocalDate transferDate) {
            this.transferDate = transferDate;
            return this;
        }

        public Builder atFeeValue(BigDecimal feeValue) {
            this.feeValue = feeValue;
            return this;
        }

        public TransferEntity build() {
            TransferEntity transferEntity = new TransferEntity();
            transferEntity.originAccount = this.originAccount;
            transferEntity.destinationAccount = this.destinationAccount;
            transferEntity.transferValue = this.transferValue;
            transferEntity.transferDate = this.transferDate;
            transferEntity.schedulingDate = this.schedulingDate;
            transferEntity.feeValue = this.feeValue;//calculate
            return transferEntity;
        }
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


//    public static List<Transfer> converter (List<TransferInput> transferInputs) {
//        return transferInputs.stream().map(Transfer::new).collect(Collectors.toList());
//    }

    }
