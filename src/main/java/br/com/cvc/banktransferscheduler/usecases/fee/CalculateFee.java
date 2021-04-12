package br.com.cvc.banktransferscheduler.usecases.fee;

import br.com.cvc.banktransferscheduler.entities.TransferInput;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class CalculateFee implements ICalculateFee {

    public BigDecimal calculate(TransferInput transferInput, FeeTypeEnum feeType) {

        switch (feeType) {
            case A:
            case B:
            case C:
        }

        return new BigDecimal(BigInteger.ZERO);

    }

    public FeeTypeEnum setFeeType(TransferInput transferInput) {

        FeeTypeEnum feeType = null;
        Long bFeeTypeMaxRange = 10L, cFeeTypeMaxRange = 40L;
        Long differenceInDays = -1*ChronoUnit.DAYS.between(transferInput.getTransferDate(), LocalDate.now());

        if (transferInput.getTransferDate().equals(LocalDate.now()))  feeType = FeeTypeEnum.A;
        else if (differenceInDays > 0 && differenceInDays<=bFeeTypeMaxRange) feeType = FeeTypeEnum.B;
        else if (bFeeTypeMaxRange < differenceInDays || differenceInDays <= cFeeTypeMaxRange || cFeeTypeMaxRange < differenceInDays &&
                transferInput.getTransferValue().compareTo(BigDecimal.valueOf(100000)) == 1) feeType = FeeTypeEnum.C;

        return feeType;
    }


}
