package br.com.cvc.banktransferscheduler.usecases.fee;

import br.com.cvc.banktransferscheduler.entities.TransferInput;
import br.com.cvc.banktransferscheduler.usecases.fee.enums.FeeTypeEnum;

import java.math.BigDecimal;

public interface ICalculateFee {

    BigDecimal calculate(TransferInput transferInput, FeeTypeEnum feeType);
    FeeTypeEnum setFeeType (TransferInput transferInput);

}
