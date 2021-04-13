package br.com.cvc.banktransferscheduler.usecases.fee.enums;

import br.com.cvc.banktransferscheduler.entities.TransferRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public enum Fee {
    A {
        @Override
        public BigDecimal calculateFeeValue(TransferRequest transferRequest) {
            return BigDecimal.valueOf(3).add(transferRequest.getTransferValue().multiply(BigDecimal.valueOf(0.03)));
        }
    },
    B {
        @Override
        public BigDecimal calculateFeeValue(TransferRequest transferRequest) {
            Long differenceInDays = -1 * ChronoUnit.DAYS.between(transferRequest.getTransferDate(), LocalDate.now());
            return BigDecimal.valueOf(12).multiply(BigDecimal.valueOf(differenceInDays));
        }
    },
    C {
        @Override
        public BigDecimal calculateFeeValue(TransferRequest transferRequest) {
        Long differenceInDays = -1 * ChronoUnit.DAYS.between(transferRequest.getTransferDate(), LocalDate.now());
        BigDecimal value = transferRequest.getTransferValue();
            if (10L < differenceInDays && differenceInDays <= 20L) return BigDecimal.valueOf(0.08).multiply(value);
                else if (20L < differenceInDays && differenceInDays <= 30L)
                    return BigDecimal.valueOf(0.06).multiply(value);
                else if (30L < differenceInDays && differenceInDays <= 40L)
                    return BigDecimal.valueOf(0.04).multiply(value);
                else if (40L < differenceInDays && transferRequest.getTransferValue().compareTo(BigDecimal.valueOf(100000)) == 1)
                    return BigDecimal.valueOf(0.02).multiply(value);
            return null;
        }
    };

    public abstract BigDecimal calculateFeeValue(TransferRequest transferRequest);
    
}
