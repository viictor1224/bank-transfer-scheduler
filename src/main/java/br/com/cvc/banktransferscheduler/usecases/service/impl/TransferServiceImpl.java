package br.com.cvc.banktransferscheduler.usecases.service.impl;

import br.com.cvc.banktransferscheduler.entities.TransferRequest;
import br.com.cvc.banktransferscheduler.entities.database.ITransferRepository;
import br.com.cvc.banktransferscheduler.entities.database.TransferEntity;
import br.com.cvc.banktransferscheduler.usecases.fee.IFeeCalculator;
import br.com.cvc.banktransferscheduler.usecases.fee.IFeeType;
import br.com.cvc.banktransferscheduler.usecases.fee.enums.Fee;
import br.com.cvc.banktransferscheduler.usecases.service.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransferServiceImpl implements ITransferService {

    @Autowired
    private ITransferRepository iTransferRepository;
    @Autowired
    private IFeeType iFeeType;
    @Autowired
    private IFeeCalculator iFeeCalculator;

    public TransferEntity createTransfer(TransferRequest transferRequest) {
        Fee feeType = iFeeType.setFeeType(transferRequest);

        TransferEntity transferEntity = TransferEntity.builder()
                .originAccount(transferRequest.getOriginAccount())
                .destinationAccount(transferRequest.getDestinationAccount())
                .transferValue(transferRequest.getTransferValue())
                .schedulingDate(LocalDate.now())
                .transferDate(transferRequest.getTransferDate())
                .feeValue(iFeeCalculator.calculate(transferRequest, feeType))
                .feeType(feeType)
                .build();
        return iTransferRepository.save(transferEntity);
    }

    public Optional<TransferEntity> getTransfer(Long id) {
        Optional<TransferEntity> optional = iTransferRepository.findById(id);
        return optional;
    }

    public List<TransferEntity> getAll() {
        List<TransferEntity> transferEntities = iTransferRepository.findAll();

        return transferEntities;
    }

    public TransferEntity updateTransfer(Long id, TransferRequest transferRequest) {
        Fee feeType = iFeeType.setFeeType(transferRequest);

        TransferEntity transferEntity = iTransferRepository.getOne(id);
        transferEntity.setOriginAccount(transferRequest.getOriginAccount());
        transferEntity.setDestinationAccount(transferRequest.getDestinationAccount());
        transferEntity.setTransferValue(transferRequest.getTransferValue());
        transferEntity.setSchedulingDate(LocalDate.now());
        transferEntity.setTransferDate(transferRequest.getTransferDate());
        transferEntity.setFeeValue(iFeeCalculator.calculate(transferRequest, feeType));
        transferEntity.setFeeType(feeType);

        return iTransferRepository.save(transferEntity);
    }

    public boolean deleteTransfer(Long id) {
        Optional<TransferEntity> optional = iTransferRepository.findById(id);
        if (!optional.isPresent()) {
            return false;
        }
        iTransferRepository.delete(optional.get());
        return true;
    }


}
