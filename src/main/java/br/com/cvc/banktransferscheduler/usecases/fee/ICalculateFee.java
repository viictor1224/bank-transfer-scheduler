package br.com.cvc.banktransferscheduler.usecases.fee;

import br.com.cvc.banktransferscheduler.entities.TransferRequest;
import br.com.cvc.banktransferscheduler.usecases.fee.enums.FeeTypeEnum;

import java.math.BigDecimal;

public interface ICalculateFee {

    BigDecimal calculate(TransferRequest transferRequest, FeeTypeEnum feeType);
    FeeTypeEnum setFeeType (TransferRequest transferRequest);

}
