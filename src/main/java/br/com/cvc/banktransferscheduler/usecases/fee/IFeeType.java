package br.com.cvc.banktransferscheduler.usecases.fee;

import br.com.cvc.banktransferscheduler.entities.TransferRequest;
import br.com.cvc.banktransferscheduler.usecases.fee.enums.Fee;

public interface IFeeType {
    Fee setFeeType(TransferRequest transferRequest);
}
