package br.com.cvc.banktransferscheduler.gateway.controller;

import br.com.cvc.banktransferscheduler.entities.TransferRequest;
import br.com.cvc.banktransferscheduler.entities.database.TransferEntity;
import br.com.cvc.banktransferscheduler.usecases.fee.IFeeType;
import br.com.cvc.banktransferscheduler.usecases.service.ITransferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransferControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private IFeeType iFeeType;

    @MockBean
    private ITransferService iTransferService;

    @Test
    public void getTest() throws Exception {
        doReturn(Optional.of(new TransferEntity())).when(iTransferService).getTransfer(any(Long.class));
        mvc.perform(MockMvcRequestBuilders
                .get("/schedules/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getTestNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/schedules/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void listTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/schedules")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void scheduleTest() throws Exception {
        doReturn(new TransferEntity()).when(iTransferService).createTransfer(any(TransferRequest.class));
        mvc.perform(MockMvcRequestBuilders
                .post("/schedules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(buildTransferRequest()))
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void updateTest() throws Exception {
        doReturn(Optional.of(buildTransferEntity())).when(iTransferService).getTransfer(any(Long.class));
        doReturn(buildTransferEntity()).when(iTransferService).updateTransfer(any(Long.class), any(TransferRequest.class));
        mvc.perform(MockMvcRequestBuilders
                .put("/schedules/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(buildTransferRequest()))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void removeTest() throws Exception {
        doReturn(true).when(iTransferService).deleteTransfer(any(Long.class));
        mvc.perform(MockMvcRequestBuilders
                .delete("/schedules/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(buildTransferRequest()))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void removeTestNotFound() throws Exception {
        doReturn(false).when(iTransferService).deleteTransfer(any(Long.class));
        mvc.perform(MockMvcRequestBuilders
                .delete("/schedules/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(buildTransferRequest()))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
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

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper()
                    .findAndRegisterModules()
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
