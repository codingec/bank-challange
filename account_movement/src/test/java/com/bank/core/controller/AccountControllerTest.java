package com.bank.core.controller;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.bank.core.data.PayloadDataUtil.getDataStringFromJsonFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest()
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;
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
        String responseBody =  result.getResponse().getContentAsString();
        System.out.println("Create Response Body: " + responseBody);
       assertThat(responseBody).contains("\"accountNumber\":\"72322664\"");
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
        String responseBody = result.getResponse().getContentAsString();
        System.out.println("GetAll Response Body: " + responseBody);
        assertThat(responseBody).isEqualTo("[{\"id\":1,\"accountNumber\":\"72322664\",\"accountType\":\"Corriente\",\"initialBalance\":80000.0,\"status\":true}]");
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
        String responseBody = result.getResponse().getContentAsString();
        System.out.println("Get by Client id Response Body: " + responseBody);
        assertThat(responseBody).isEqualTo("{\"id\":1,\"accountNumber\":\"72322664\",\"accountType\":\"Corriente\",\"initialBalance\":80000.0,\"status\":true}");
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
        String responseBody = result.getResponse().getContentAsString();
        System.out.println("Update Response Body: " + responseBody);
        assertThat(responseBody).isEqualTo("{\"id\":1,\"accountNumber\":\"72322664\",\"accountType\":\"Corriente\",\"initialBalance\":80000.0,\"status\":true}");
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
        String responseBody = result.getResponse().getContentAsString();
        assertThat(responseBody).contains(" was deleted successfully");
    }

    private String create() throws Exception {
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
