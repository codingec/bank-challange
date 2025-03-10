package com.bank.core.controller;

import com.bank.core.model.account.AccountRepository;
import com.bank.core.services.account.impl.AccountServiceImpl;
import com.bank.core.services.consumer.client.impl.ClientRestConsumerServiceImpl;
import com.bank.core.services.mappers.AccountMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static com.bank.core.data.DataUtil.buildClientDTOData;
import static com.bank.core.data.DataUtil.buildClientDTOData;
import static com.bank.core.data.PayloadDataUtil.getDataStringFromJsonFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest()
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    private AccountServiceImpl accountService;
    static String CREATE_JSON_PAYLOAD = "json/account/create_payload.json";
    static String UPDATE_JSON_PAYLOAD = "json/account/update_payload.json";


    @Test
    @Order(1)
    void givenValidDataToCreateAccount_thenSuccess_ok() throws Exception {

        String accountPayload = getDataStringFromJsonFile(CREATE_JSON_PAYLOAD);
        MvcResult result = mockMvc.perform(
                        post("/api/cuentas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(accountPayload)
                )
                .andExpect(status().isOk())
                .andReturn();
        Thread.sleep(2000);
    }

    @Test
    @Order(2)
    void givenValidDataToGetAll_thenSuccess_ok() throws Exception {
        MvcResult result = mockMvc.perform(
                        get("/api/cuentas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(3)
    void givenValidDataToGetAccountById_thenSuccess_ok() throws Exception {
        MvcResult result = mockMvc.perform(
                        get("/api/cuentas/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(4)
    void givenValidDataToUpdateAccount_thenSuccess_ok() throws Exception {
        create();
        String clientPayload = getDataStringFromJsonFile(UPDATE_JSON_PAYLOAD);
        MvcResult result = mockMvc.perform(
                        put("/api/cuentas/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(clientPayload)
                )
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(5)
    void givenValidDataToDeleteAccount_thenSuccess_ok() throws Exception {
        MvcResult result = mockMvc.perform(
                        delete("/api/cuentas/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
    }

    private String create() throws Exception {

        when(accountService.getClientDetails(anyLong()))
                .thenReturn(Optional.of(buildClientDTOData()));
        String accountPayload = getDataStringFromJsonFile(CREATE_JSON_PAYLOAD);
        MvcResult result = mockMvc.perform(
                        post("/api/cuentas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(accountPayload)
                )
                .andExpect(status().isOk())
                .andReturn();
        Thread.sleep(2000);
        return result.getResponse().getContentAsString();
    }
}
