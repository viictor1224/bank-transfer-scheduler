package br.com.cvc.banktransferscheduler.usecases.service;

import br.com.cvc.banktransferscheduler.entities.database.TransferEntity;
import br.com.cvc.banktransferscheduler.entities.TransferRequest;
import br.com.cvc.banktransferscheduler.entities.TransferResponse;

import java.util.List;
import java.util.Optional;

public interface ITransferService {

    TransferEntity createTransfer(TransferRequest transferRequest);
    Optional<TransferEntity> getTransfer(Long id);
    List<TransferEntity> getAll();
    TransferEntity updateTransfer(Long id, TransferRequest transferRequest);
    boolean deleteTransfer(Long id);
}
