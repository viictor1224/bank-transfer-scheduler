package br.com.cvc.banktransferscheduler.usecases.fee.enums;

import br.com.cvc.banktransferscheduler.entities.TransferRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public enum Fee {
    A,
    B,
    C;

    public static BigDecimal calculateFeeValue(TransferRequest transferRequest, Fee fee) {

        Long differenceInDays = -1 * ChronoUnit.DAYS.between(transferRequest.getTransferDate(), LocalDate.now());
        BigDecimal value = transferRequest.getTransferValue();

        switch (fee) {
            case A:
                return BigDecimal.valueOf(3).add(value.multiply(BigDecimal.valueOf(0.03)));
            case B:
                return BigDecimal.valueOf(12).multiply(BigDecimal.valueOf(differenceInDays));
            case C:
                if (10L < differenceInDays && differenceInDays <= 20L) return BigDecimal.valueOf(0.08).multiply(value);
                else if (20L < differenceInDays && differenceInDays <= 30L) return BigDecimal.valueOf(0.06).multiply(value);
                else if (30L < differenceInDays && differenceInDays <= 40L) return BigDecimal.valueOf(0.04).multiply(value);
                else if (40L < differenceInDays && transferRequest.getTransferValue().compareTo(BigDecimal.valueOf(100000)) == 1)
                    return BigDecimal.valueOf(0.02).multiply(value);
        }
        return null;
    }


}
