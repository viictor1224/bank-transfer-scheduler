package br.com.cvc.banktransferscheduler.gateway.controller;

import br.com.cvc.banktransferscheduler.entities.TransferInput;
import br.com.cvc.banktransferscheduler.gateway.database.ITransferRepository;
import br.com.cvc.banktransferscheduler.gateway.database.entities.TransferEntity;
import br.com.cvc.banktransferscheduler.usecases.impl.TransferImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;


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
    public ResponseEntity<TransferEntity> schedule(@RequestBody @Valid TransferInput transferInput, UriComponentsBuilder uriBuilder) {

        TransferEntity transferEntity = transferImpl.buildTransfer(transferInput);
        iTransferRepository.save(transferEntity);

        URI uri = uriBuilder.path("/schedules/{id}").buildAndExpand(transferEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(transferEntity);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TransferEntity> get(@PathVariable Long id) {
        Optional<TransferEntity> optional = iTransferRepository.findById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }
        return ResponseEntity.notFound().build();
    }

//    @PutMapping("/{id}")
//    @Transactional
//    public ResponseEntity<TransferEntity> update(@PathVariable Long id, @RequestBody @Valid TransferInput transferInput) {
//        Optional<TransferEntity> optional = iTransferRepository.findById(id);
//        if (!optional.isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//        op = transferImpl.buildTransfer(transferInput);
//        return ResponseEntity.ok(new TopicoDto(topico));
//
//    }
//
//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity<?> remover(@PathVariable Long id) {
//        Optional<Topico> optional = iTopicoRepository.findById(id);
//        if (optional.isPresent()) {
//            iTopicoRepository.deleteById(id);
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.notFound().build();
//    }

}
