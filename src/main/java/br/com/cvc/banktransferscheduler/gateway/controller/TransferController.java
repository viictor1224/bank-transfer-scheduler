package br.com.cvc.banktransferscheduler.gateway.controller;

import br.com.cvc.banktransferscheduler.entities.TransferInput;
import br.com.cvc.banktransferscheduler.gateway.database.ITransferRepository;
import br.com.cvc.banktransferscheduler.gateway.database.entities.TransferEntity;
import br.com.cvc.banktransferscheduler.usecases.impl.TransferImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/schedules")
public class TransferController {

    @Autowired
    private ITransferRepository iTransferRepository;

    @Autowired
    private TransferImpl transferImpl;

    @GetMapping
    public List<TransferEntity> list() {
        List<TransferEntity> transferEntities = iTransferRepository.findAll();
        return transferEntities;
    }

    @PostMapping
    public ResponseEntity<TransferEntity> schedule(@RequestBody TransferInput transferInput, UriComponentsBuilder uriBuilder) {

        TransferEntity transferEntity = transferImpl.converter(transferInput);
        iTransferRepository.save(transferEntity);

        URI uri = uriBuilder.path("/schedules/{id}").buildAndExpand(transferEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(new TransferEntity());
    }

}
