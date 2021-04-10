package br.com.cvc.banktransferscheduler.gateway.controller;

import br.com.cvc.banktransferscheduler.entities.Transfer;
import br.com.cvc.banktransferscheduler.gateway.database.ITransferRepository;
import br.com.cvc.banktransferscheduler.gateway.model.TransferInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RestController
@RequestMapping(value = "/schedules")
public class TransferController {

    @Autowired
    private ITransferRepository iTransferRepository;

    @GetMapping
    public List<Transfer> list() {
        List<Transfer> transfers = iTransferRepository.findAll();
        return transfers;
    }


//    TransferInput transferInput = new TransferInput.Builder("123123")
//            .withDestination("321321")
//            .atValue(new BigDecimal(321.1))
//            .atSchedulingDate(LocalDate.now())
//            .atTransferDate(LocalDate.now())
//            .build();



}
