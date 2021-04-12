package br.com.cvc.banktransferscheduler.usecases.impl;

import br.com.cvc.banktransferscheduler.entities.TransferInput;
import br.com.cvc.banktransferscheduler.gateway.database.entities.TransferEntity;
import br.com.cvc.banktransferscheduler.usecases.fee.enums.FeeTypeEnum;
import br.com.cvc.banktransferscheduler.usecases.fee.ICalculateFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransferImpl {

    @Autowired
    private ICalculateFee iCalculateFee;

    public TransferEntity buildTransfer(TransferInput transferInput) {

        FeeTypeEnum feeType = iCalculateFee.setFeeType(transferInput);

        TransferEntity transferEntity = TransferEntity.builder()
                .originAccount(transferInput.getOriginAccount())
                .destinationAccount(transferInput.getDestinationAccount())
                .transferValue(transferInput.getTransferValue())
                .schedulingDate(LocalDate.now())
                .transferDate(transferInput.getTransferDate())
                .feeValue(iCalculateFee.calculate(transferInput, feeType)) // FIX IMPLEMENTATION
                .build();

        return transferEntity;
    }
}
