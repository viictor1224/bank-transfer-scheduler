package br.com.cvc.banktransferscheduler.gateway.controller;

import br.com.cvc.banktransferscheduler.entities.TransferRequest;
import br.com.cvc.banktransferscheduler.entities.TransferResponse;
import br.com.cvc.banktransferscheduler.entities.database.TransferEntity;
import br.com.cvc.banktransferscheduler.usecases.service.ITransferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/schedules")
public class TransferController {

    @Autowired
    private ITransferService iTransferService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<TransferResponse> schedule(@RequestBody @Valid TransferRequest transferRequest, UriComponentsBuilder uriBuilder) {

        TransferEntity transferEntity = iTransferService.createTransfer(transferRequest);

        URI uri = uriBuilder.path("/schedules/{id}").buildAndExpand(toResponse(transferEntity).getId()).toUri();
        return ResponseEntity.created(uri).body(toResponse(transferEntity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferResponse> get(@PathVariable Long id) {
        Optional<TransferEntity> optional = iTransferService.getTransfer(id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toResponse(optional.get()));
    }


    @GetMapping
    public List<TransferResponse> list() {
        List<TransferEntity> transferEntities = iTransferService.getAll();
        List<TransferResponse> responses = new ArrayList<>();
        transferEntities.forEach(ent -> responses.add(toResponse(ent)));
        return responses;
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TransferResponse> update(@PathVariable Long id, @RequestBody @Valid TransferRequest transferRequest) {
        Optional<TransferEntity> optional = iTransferService.getTransfer(id);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        TransferEntity transferEntity = iTransferService.updateTransfer(id, transferRequest);
        return ResponseEntity.ok(toResponse(transferEntity));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remove(@PathVariable Long id) {
        if (iTransferService.deleteTransfer(id))
            return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    private TransferResponse toResponse(TransferEntity entity) {
        return modelMapper.map(entity, TransferResponse.class);
    }

}


