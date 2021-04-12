package br.com.cvc.banktransferscheduler.usecases.fee;

import br.com.cvc.banktransferscheduler.entities.TransferInput;
import br.com.cvc.banktransferscheduler.usecases.fee.enums.FeeTypeEnum;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class CalculateFee implements ICalculateFee {

    public BigDecimal calculate(TransferInput transferInput, FeeTypeEnum feeType) {

        Long differenceInDays = -1 * ChronoUnit.DAYS.between(transferInput.getTransferDate(), LocalDate.now());
        BigDecimal value = transferInput.getTransferValue();

        switch (feeType) {
            case A:
                return BigDecimal.valueOf(3).add(value.multiply(BigDecimal.valueOf(0.03)));
            case B:
                return BigDecimal.valueOf(12).multiply(BigDecimal.valueOf(differenceInDays));
            case C:
                if (10 < differenceInDays && differenceInDays <= 20) {
                    return BigDecimal.valueOf(0.08).multiply(value);
                } else if (differenceInDays <= 30) {
                    return BigDecimal.valueOf(0.06).multiply(value);
                } else if (differenceInDays <= 40) {
                    return BigDecimal.valueOf(0.04).multiply(value);
                } else if (differenceInDays > 40) {
                    return BigDecimal.valueOf(0.02).multiply(value);
                }
        }
        return null; // thrown exception?
    }

    public FeeTypeEnum setFeeType(TransferInput transferInput) {

        FeeTypeEnum feeType = null;
        Long bFeeTypeMaxRange = 10L, cFeeTypeMaxRange = 40L;
        Long differenceInDays = -1 * ChronoUnit.DAYS.between(transferInput.getTransferDate(), LocalDate.now());

        if (transferInput.getTransferDate().equals(LocalDate.now())) feeType = FeeTypeEnum.A;
        else if (differenceInDays > 0 && differenceInDays <= bFeeTypeMaxRange) feeType = FeeTypeEnum.B;
        else if (bFeeTypeMaxRange < differenceInDays || differenceInDays <= cFeeTypeMaxRange || cFeeTypeMaxRange < differenceInDays &&
                transferInput.getTransferValue().compareTo(BigDecimal.valueOf(100000)) == 1) feeType = FeeTypeEnum.C;

//        else throw business exception

        return feeType;
    }


}
