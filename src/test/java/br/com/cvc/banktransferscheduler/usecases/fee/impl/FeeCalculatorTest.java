package br.com.cvc.banktransferscheduler.usecases.fee.impl;

import br.com.cvc.banktransferscheduler.entities.TransferRequest;
import br.com.cvc.banktransferscheduler.usecases.fee.enums.Fee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
public class FeeCalculatorTest {

    @InjectMocks
    private FeeCalculator feeCalculator;

    @Before
    public void setUp() {
    }

    @Test
    public void calculateA() {
        Assert.assertEquals(feeCalculator.calculate(buildTransferARequest(), Fee.A), BigDecimal.valueOf(303).setScale(2, RoundingMode.DOWN));
    }

    @Test
    public void calculateB() {
        Assert.assertEquals(feeCalculator.calculate(buildTransferBRequest(), Fee.B), BigDecimal.valueOf(60).setScale(2, RoundingMode.DOWN));
    }

    @Test
    public void calculateC1() {
        Assert.assertEquals(feeCalculator.calculate(buildTransferC1Request(), Fee.C), BigDecimal.valueOf(800).setScale(2, RoundingMode.DOWN));
    }

    @Test
    public void calculateC2() {
        Assert.assertEquals(feeCalculator.calculate(buildTransferC2Request(), Fee.C), BigDecimal.valueOf(600).setScale(2, RoundingMode.DOWN));
    }

    @Test
    public void calculateC3() {
        Assert.assertEquals(feeCalculator.calculate(buildTransferC3Request(), Fee.C), BigDecimal.valueOf(400).setScale(2, RoundingMode.DOWN));
    }

    @Test
    public void calculateC4() {
        Assert.assertEquals(feeCalculator.calculate(buildTransferC4Request(), Fee.C), BigDecimal.valueOf(2200).setScale(2, RoundingMode.DOWN));
    }
    @Test
    public void calculateToReturnNull() {
        Assert.assertNull(feeCalculator.calculate(buildTransferToReturnNull(), Fee.C));
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
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setOriginAccount("123456");
        transferRequest.setDestinationAccount("654321");
        transferRequest.setTransferDate(LocalDate.now().plusDays(5));
        transferRequest.setTransferValue(BigDecimal.valueOf(10000));
        return transferRequest;
    }

    private TransferRequest buildTransferC1Request() {
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setOriginAccount("123456");
        transferRequest.setDestinationAccount("654321");
        transferRequest.setTransferDate(LocalDate.now().plusDays(11));
        transferRequest.setTransferValue(BigDecimal.valueOf(10000));
        return transferRequest;
    }

    private TransferRequest buildTransferC2Request() {
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setOriginAccount("123456");
        transferRequest.setDestinationAccount("654321");
        transferRequest.setTransferDate(LocalDate.now().plusDays(21));
        transferRequest.setTransferValue(BigDecimal.valueOf(10000));
        return transferRequest;
    }

    private TransferRequest buildTransferC3Request() {
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setOriginAccount("123456");
        transferRequest.setDestinationAccount("654321");
        transferRequest.setTransferDate(LocalDate.now().plusDays(31));
        transferRequest.setTransferValue(BigDecimal.valueOf(10000));
        return transferRequest;
    }

    private TransferRequest buildTransferC4Request() {
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setOriginAccount("123456");
        transferRequest.setDestinationAccount("654321");
        transferRequest.setTransferDate(LocalDate.now().plusDays(41));
        transferRequest.setTransferValue(BigDecimal.valueOf(110000));
        return transferRequest;
    }

    private TransferRequest buildTransferToReturnNull() {
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setOriginAccount("123456");
        transferRequest.setDestinationAccount("654321");
        transferRequest.setTransferDate(LocalDate.now().plusDays(41));
        transferRequest.setTransferValue(BigDecimal.valueOf(11000));
        return transferRequest;
    }

}
