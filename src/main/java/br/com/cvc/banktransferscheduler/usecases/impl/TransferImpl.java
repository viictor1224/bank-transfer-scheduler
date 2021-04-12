package br.com.cvc.banktransferscheduler.usecases.impl;

import br.com.cvc.banktransferscheduler.entities.TransferInput;
import br.com.cvc.banktransferscheduler.gateway.database.entities.TransferEntity;
import br.com.cvc.banktransferscheduler.usecases.fee.FeeTypeEnum;
import br.com.cvc.banktransferscheduler.usecases.fee.ICalculateFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransferImpl {

    @Autowired
    private ICalculateFee iCalculateFee;

    public TransferEntity converter(TransferInput transferInput) {

        TransferEntity transferEntity = new TransferEntity.Builder(transferInput.getOriginAccount())
                .withDestination(transferInput.getDestinationAccount())
                .atValue(transferInput.getTransferValue())
                .atSchedulingDate(LocalDate.now())
                .atTransferDate(transferInput.getTransferDate())
                .atFeeValue(iCalculateFee.calculate(transferInput, FeeTypeEnum.A))
                .build();

        return transferEntity;
    }
}
