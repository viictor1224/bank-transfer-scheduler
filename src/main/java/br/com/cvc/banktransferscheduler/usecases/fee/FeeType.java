package br.com.cvc.banktransferscheduler.usecases.fee;

import br.com.cvc.banktransferscheduler.entities.TransferRequest;
import br.com.cvc.banktransferscheduler.usecases.exception.FeeTypeException;
import br.com.cvc.banktransferscheduler.usecases.fee.enums.Fee;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class FeeType implements IFeeType {

    public Fee setFeeType(TransferRequest transferRequest) {

        Fee feeType = null;
        Long differenceInDays = -1 * ChronoUnit.DAYS.between(transferRequest.getTransferDate(), LocalDate.now());

        if (transferRequest.getTransferDate().equals(LocalDate.now())) feeType = Fee.A;
        else if (differenceInDays > 0 && differenceInDays <= 10L) feeType = Fee.B;
        else if ((10L < differenceInDays && differenceInDays <= 40L) ||
                (40L < differenceInDays && transferRequest.getTransferValue().compareTo(BigDecimal.valueOf(100000)) == 1))
            feeType = Fee.C;
        else
            throw new FeeTypeException(transferRequest.getTransferDate(), transferRequest.getTransferValue());

        return feeType;
    }


}
