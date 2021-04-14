package br.com.cvc.banktransferscheduler.usecases.service;

import br.com.cvc.banktransferscheduler.entities.TransferRequest;
import br.com.cvc.banktransferscheduler.entities.database.ITransferRepository;
import br.com.cvc.banktransferscheduler.entities.database.TransferEntity;
import br.com.cvc.banktransferscheduler.usecases.fee.IFeeCalculator;
import br.com.cvc.banktransferscheduler.usecases.fee.IFeeType;
import br.com.cvc.banktransferscheduler.usecases.fee.enums.Fee;
import br.com.cvc.banktransferscheduler.usecases.service.impl.TransferServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TransferServiceImplTest {

    @InjectMocks
    private TransferServiceImpl transferService;

    @Mock
    private ITransferRepository iTransferRepository;

    @Mock
    private IFeeType iFeeType;

    @Mock
    private IFeeCalculator iFeeCalculator;

    @Test
    public void createTransferTest() {
        when(iFeeType.setFeeType(any(TransferRequest.class))).thenReturn(Fee.A);
        when(iFeeCalculator.calculate(any(TransferRequest.class), any(Fee.class))).thenReturn(BigDecimal.valueOf(303));

        doReturn(buildTransferEntity()).when(iTransferRepository).save(any(TransferEntity.class));
        TransferEntity entity = transferService.createTransfer(this.buildTransferRequest());

        Assert.assertEquals(buildTransferEntity(), entity);
    }

    @Test
    public void readTransferTest() {
        doReturn(Optional.of(buildTransferEntity())).when(iTransferRepository).findById(any(Long.class));
        Optional<TransferEntity> entity = transferService.getTransfer(1L);

        Assert.assertEquals(Optional.of(buildTransferEntity()), entity);
    }

    @Test
    public void getAllTest() {
        doReturn(List.of(buildTransferEntity())).when(iTransferRepository).findAll();
        List<TransferEntity> entity = transferService.getAll();

        Assert.assertEquals(List.of(buildTransferEntity()), entity);
    }

    @Test
    public void updateTransferTest() {
        when(iFeeType.setFeeType(any(TransferRequest.class))).thenReturn(Fee.A);
        when(iFeeCalculator.calculate(any(TransferRequest.class), any(Fee.class))).thenReturn(BigDecimal.valueOf(303));
        doReturn(buildTransferEntity()).when(iTransferRepository).getOne(any(Long.class));
        doReturn(buildTransferEntity()).when(iTransferRepository).save(any(TransferEntity.class));

        TransferEntity entity = transferService.updateTransfer(1L, this.buildTransferRequest());

        Assert.assertEquals(buildTransferEntity(), entity);
    }

    @Test
    public void deleteTransferTest() {
        doReturn(Optional.of(buildTransferEntity())).when(iTransferRepository).findById(any(Long.class));
        Assert.assertTrue(transferService.deleteTransfer(1L));
    }

    @Test
    public void deleteTransfer404Test() {
        Optional<TransferEntity> entityEmpty = Optional.empty();
        doReturn(entityEmpty).when(iTransferRepository).findById(any(Long.class));
        Assert.assertFalse(transferService.deleteTransfer(1L));

    }

    private TransferRequest buildTransferRequest() {
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setOriginAccount("123456");
        transferRequest.setDestinationAccount("654321");
        transferRequest.setTransferDate(LocalDate.now());
        transferRequest.setTransferValue(BigDecimal.valueOf(10000));
        return transferRequest;
    }

    private TransferEntity buildTransferEntity() {
        TransferEntity transferEntity = new TransferEntity();
        transferEntity.setId(1L);
        transferEntity.setOriginAccount(buildTransferRequest().getOriginAccount());
        transferEntity.setDestinationAccount(buildTransferRequest().getDestinationAccount());
        transferEntity.setTransferValue(buildTransferRequest().getTransferValue());
        transferEntity.setFeeValue(BigDecimal.valueOf(3).add(buildTransferRequest().getTransferValue().multiply(BigDecimal.valueOf(0.03))));
        transferEntity.setFeeType(iFeeType.setFeeType(buildTransferRequest()));
        transferEntity.setTransferDate(buildTransferRequest().getTransferDate());
        transferEntity.setSchedulingDate(LocalDate.now());
        return transferEntity;
    }

}
