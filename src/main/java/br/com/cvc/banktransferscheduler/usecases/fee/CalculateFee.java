package br.com.cvc.banktransferscheduler.usecases.fee;

import br.com.cvc.banktransferscheduler.entities.TransferRequest;
import br.com.cvc.banktransferscheduler.usecases.exception.FeeTypeException;
import br.com.cvc.banktransferscheduler.usecases.fee.enums.FeeTypeEnum;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class CalculateFee implements ICalculateFee {

    public BigDecimal calculate(TransferRequest transferRequest, FeeTypeEnum feeType) {

        Long differenceInDays = -1 * ChronoUnit.DAYS.between(transferRequest.getTransferDate(), LocalDate.now());
        BigDecimal value = transferRequest.getTransferValue();

        switch (feeType) {
            case A:
                return BigDecimal.valueOf(3).add(value.multiply(BigDecimal.valueOf(0.03)));
            case B:
                return BigDecimal.valueOf(12).multiply(BigDecimal.valueOf(differenceInDays));
            case C1:
                return BigDecimal.valueOf(0.08).multiply(value);
            case C2:
                return BigDecimal.valueOf(0.06).multiply(value);
            case C3:
                return BigDecimal.valueOf(0.04).multiply(value);
            case C4:
                return BigDecimal.valueOf(0.02).multiply(value);
        }
        return null; // thrown exception?
    }

    public FeeTypeEnum setFeeType(TransferRequest transferRequest) {

        FeeTypeEnum feeType = null;
        Long differenceInDays = -1 * ChronoUnit.DAYS.between(transferRequest.getTransferDate(), LocalDate.now());

        if (transferRequest.getTransferDate().equals(LocalDate.now())) feeType = FeeTypeEnum.A;
        else if (differenceInDays > 0 && differenceInDays <= 10L) feeType = FeeTypeEnum.B;
        else if (10L < differenceInDays && differenceInDays <= 20L) feeType = FeeTypeEnum.C1;
        else if (20L < differenceInDays && differenceInDays <= 30L) feeType = FeeTypeEnum.C2;
        else if (30L < differenceInDays && differenceInDays <= 40L) feeType = FeeTypeEnum.C3;
        else if (40L < differenceInDays && transferRequest.getTransferValue().compareTo(BigDecimal.valueOf(100000)) == 1)
            feeType = FeeTypeEnum.C4;
        else
            throw new FeeTypeException(transferRequest.getTransferDate(), transferRequest.getTransferValue());

        return feeType;
    }


}
