package br.com.cvc.banktransferscheduler.usecases.fee;

import br.com.cvc.banktransferscheduler.entities.TransferRequest;
import br.com.cvc.banktransferscheduler.usecases.fee.enums.Fee;

import java.math.BigDecimal;

public interface IFeeCalculator {
    BigDecimal calculate(TransferRequest transferRequest, Fee feeType);
    BigDecimal applyFee(BigDecimal value, double fee);
}
