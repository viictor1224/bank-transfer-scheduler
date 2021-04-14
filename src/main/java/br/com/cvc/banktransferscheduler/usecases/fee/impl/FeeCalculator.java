package br.com.cvc.banktransferscheduler.usecases.fee.impl;

import br.com.cvc.banktransferscheduler.entities.TransferRequest;
import br.com.cvc.banktransferscheduler.usecases.fee.IFeeCalculator;
import br.com.cvc.banktransferscheduler.usecases.fee.enums.Fee;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class FeeCalculator implements IFeeCalculator {

    public BigDecimal calculate(TransferRequest transferRequest, Fee feeType) {

        Long differenceInDays = -1 * ChronoUnit.DAYS.between(transferRequest.getTransferDate(), LocalDate.now());
        BigDecimal value = transferRequest.getTransferValue();

        switch (feeType) {
            case A:
                return BigDecimal.valueOf(3).add(applyFee(value, 0.03));
            case B:
                return applyFee(BigDecimal.valueOf(12), differenceInDays);
            case C:
                if (10L < differenceInDays && differenceInDays <= 20L) return applyFee(value, 0.08);
                else if (20L < differenceInDays && differenceInDays <= 30L) return applyFee(value, 0.06);
                else if (30L < differenceInDays && differenceInDays <= 40L) return applyFee(value, 0.04);
                else if (40L < differenceInDays && transferRequest.getTransferValue().compareTo(BigDecimal.valueOf(100000)) == 1)
                    return applyFee(value, 0.02);
        }
        return null;
    }

    public BigDecimal applyFee(BigDecimal value, double fee) {
        return value.multiply(BigDecimal.valueOf(fee));
    }
}
