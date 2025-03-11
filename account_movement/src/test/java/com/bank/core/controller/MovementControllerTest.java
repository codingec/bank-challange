package com.bank.core.controller;

import com.bank.core.services.account.impl.AccountServiceImpl;
import com.bank.core.services.movement.impl.MovementServiceImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static com.bank.core.data.DataUtil.buildClientDTOData;
import static com.bank.core.data.PayloadDataUtil.getDataStringFromJsonFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest()
@AutoConfigureMockMvc
class MovementControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    private MovementServiceImpl movementService;
    @MockitoBean
    private AccountServiceImpl accountService;
    static String ACCOUNT_CREATE_JSON_PAYLOAD = "json/movement/new_account/create_payload.json";
    static String MOVEMENT_CREATE_JSON_PAYLOAD = "json/movement/create_payload.json";
    static String UPDATE_JSON_PAYLOAD = "json/movement/update_payload.json";


    void AccountNeedsToBeCreated_ok() throws Exception {
        when(accountService.getClientDetails(anyLong()))
                .thenReturn(Optional.of(buildClientDTOData()));
        String accountPayload = getDataStringFromJsonFile(ACCOUNT_CREATE_JSON_PAYLOAD);
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
    @Order(1)
    void givenValidDataToCreateMovement_thenSuccess_ok() throws Exception {
        AccountNeedsToBeCreated_ok();
        Thread.sleep(2000);
        String accountPayload = getDataStringFromJsonFile(MOVEMENT_CREATE_JSON_PAYLOAD);
        MvcResult result = mockMvc.perform(
                        post("/api/movimientos")
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
        Thread.sleep(2000);
        MvcResult result = mockMvc.perform(
                        get("/api/movimientos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(3)
    void givenValidDataToGetMovementById_thenSuccess_ok() throws Exception {
        Thread.sleep(2000);
        MvcResult result = mockMvc.perform(
                        get("/api/movimientos/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(4)
    void givenValidDataToUpdateMovement_thenSuccess_ok() throws Exception {
        Thread.sleep(2000);
        String clientPayload = getDataStringFromJsonFile(UPDATE_JSON_PAYLOAD);
        MvcResult result = mockMvc.perform(
                        put("/api/movimientos/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(clientPayload)
                )
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    @Order(5)
    void givenValidDataToDeleteMovement_thenSuccess_ok() throws Exception {
        Thread.sleep(2000);
        MvcResult result = mockMvc.perform(
                        delete("/api/movimientos/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
    }
}
