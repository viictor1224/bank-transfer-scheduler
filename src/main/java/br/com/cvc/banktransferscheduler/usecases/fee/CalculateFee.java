package br.com.cvc.banktransferscheduler.usecases.fee;

import br.com.cvc.banktransferscheduler.entities.TransferInput;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class CalculateFee implements ICalculateFee {

    public BigDecimal calculate(TransferInput transferInput, Enum feeType) {
        // transferDate - schedulingDate
        LocalDate transferDate = transferInput.getTransferDate();
        BigDecimal value = transferInput.getTransferValue();
        Long differenceInDays = ChronoUnit.DAYS.between(transferDate, LocalDate.now());
//        switch (differenceInDays) {
//            case 1: return 1;  //Monthly interest rate is annual rate / 12 months.
//            case 2: return 12*differenceInDays;
////            case 3: Acima de 10 até 20 dias da data de agendamento 8%
////Acima de 20 até 30 dias da data de agendamento 6%
////Acima de 30 até 40 dias da data de agendamento 4%
////Acima de 40 dias da data de agendamento e valor superior a 100.000 2%
//            default:
        return new BigDecimal(BigInteger.ZERO);
//        }
    }

    public Enum setFeeType(TransferInput transferInput) {
        return FeeTypeEnum.A;
    }


}
