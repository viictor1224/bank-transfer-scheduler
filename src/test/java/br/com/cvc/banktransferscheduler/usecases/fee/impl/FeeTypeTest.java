package br.com.cvc.banktransferscheduler.usecases.fee.impl;

import br.com.cvc.banktransferscheduler.entities.TransferRequest;
import br.com.cvc.banktransferscheduler.usecases.exception.FeeTypeException;
import br.com.cvc.banktransferscheduler.usecases.fee.enums.Fee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertThrows;

@RunWith(SpringRunner.class)
public class FeeTypeTest {

    @InjectMocks
    private FeeType feeType;

    @Test
    public void setFeeTypeATest() {
        Assert.assertEquals(Fee.A, feeType.setFeeType(buildTransferARequest()));
    }

    @Test
    public void setFeeTypeBTest() {
        Assert.assertEquals(Fee.B, feeType.setFeeType(buildTransferBRequest()));
    }

    @Test
    public void setFeeTypeC1Test() {
        Assert.assertEquals(Fee.C, feeType.setFeeType(buildTransferC1Request()));
    }

    @Test
    public void setFeeTypeC2Test() {
        Assert.assertEquals(Fee.C, feeType.setFeeType(buildTransferC2Request()));
    }

    @Test
    public void FeeTypeExceptionTest() {
        assertThrows(FeeTypeException.class, () -> {
            feeType.setFeeType(buildTransferToReturnException());
        });
    }

    private TransferRequest buildTransferARequest() {
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setOriginAccount("123456");
        transferRequest.setDestinationAccount("654321");
        transferRequest.setTransferDate(LocalDate.now());
        transferRequest.setTransferValue(BigDecimal.valueOf(10000));
        return transferRequest;
    }

    private TransferRequest buildTransferBRequest() {
        TransferRequest transferRequest = buildTransferARequest();
        transferRequest.setTransferDate(LocalDate.now().plusDays(5));
        return transferRequest;
    }

    private TransferRequest buildTransferC1Request() {
        TransferRequest transferRequest = buildTransferARequest();
        transferRequest.setTransferDate(LocalDate.now().plusDays(11));
        return transferRequest;
    }

    private TransferRequest buildTransferC2Request() {
        TransferRequest transferRequest = buildTransferARequest();
        transferRequest.setTransferDate(LocalDate.now().plusDays(41));
        transferRequest.setTransferValue(BigDecimal.valueOf(110000));
        return transferRequest;
    }

    private TransferRequest buildTransferToReturnException() {
        TransferRequest transferRequest = buildTransferARequest();
        transferRequest.setTransferDate(LocalDate.now().plusDays(41));
        transferRequest.setTransferValue(BigDecimal.valueOf(10000));
        return transferRequest;
    }
}
