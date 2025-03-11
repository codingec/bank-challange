package com.bank.core.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.bank.core.data.PayloadDataUtil.getDataStringFromJsonFile;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


@SpringBootTest()
@AutoConfigureMockMvc
class ClientControllerTest {

    @Autowired
    MockMvc mockMvc;
    static String CREATE_JSON_PAYLOAD = "json/client/create_payload.json";
    static String UPDATE_JSON_PAYLOAD = "json/client/update_payload.json";

    @Test
    void givenValidDataToCreateClient_thenSuccess_ok() throws Exception {
        String clientPayload = getDataStringFromJsonFile(CREATE_JSON_PAYLOAD);
        MvcResult result = mockMvc.perform(
                        post("/api/clientes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(clientPayload)
                )
                .andExpect(status().isOk())
                .andReturn();
        Thread.sleep(2000);
        String responseBody = result.getResponse().getContentAsString();
        System.out.println("Create Response Body: " + responseBody);
        assertThat(responseBody).contains("\"identificacion\":\"111291234521\"");
    }


    @Test
    void givenValidDataToGetAll_thenSuccess_ok() throws Exception {
        MvcResult result = mockMvc.perform(
                        get("/api/clientes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        System.out.println("GetAll Response Body: " + responseBody);
        assertThat(responseBody).contains("\"identificacion\":\"111291234521\"");
    }

    @Test
    void givenValidDataToGetClientById_thenSuccess_ok() throws Exception {
        MvcResult result = mockMvc.perform(
                        get("/api/clientes/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        System.out.println("Get by Client id Response Body: " + responseBody);
        assertThat(responseBody).contains("\"identificacion\":\"111291234521\"");
    }

    @Test
    void givenValidDataToUpdateClient_thenSuccess_ok() throws Exception {
        String clientPayload = getDataStringFromJsonFile(UPDATE_JSON_PAYLOAD);
        MvcResult result = mockMvc.perform(
                        put("/api/clientes/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(clientPayload)
                )
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        System.out.println("Update Response Body: " + responseBody);
        assertThat(responseBody).contains("\"nombre\":\"Anatoly\"");
    }

    @Test
    void givenValidDataToDeleteClient_thenSuccess_ok() throws Exception {
        MvcResult result = mockMvc.perform(
                        delete("/api/clientes/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        assertThat(responseBody).contains(" fue eliminado exitosamente.");
    }
}
