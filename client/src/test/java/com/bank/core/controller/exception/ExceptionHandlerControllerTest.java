package com.bank.core.controller.exception;

import static com.bank.core.data.PayloadDataUtil.getDataStringFromJsonFile;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ExceptionHandlerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

    static String CREATE_JSON_PAYLOAD = "json/client/create_payload.json";

    @Test
    void givenValidDataToCreateClient_thenSuccess_500() throws Exception {
        String clientPayload = getDataStringFromJsonFile(CREATE_JSON_PAYLOAD);
        mockMvc.perform(
                        post("/banco/api/clientes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(clientPayload)
                )
                .andExpect(status().is5xxServerError());

    }
}
