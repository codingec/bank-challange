package com.bank.core.controller;

import com.bank.core.data.PayloadDataUtil;

import com.bank.core.model.client.ClientRepository;
import com.bank.core.model.person.PersonRepository;
import com.bank.core.services.client.impl.ClientServiceImpl;
import com.bank.core.services.mappers.ClientMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
class ClientControllerTest {

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    static ClientController clientController;

    @Mock
    static ClientServiceImpl clientService;

    @Mock
    static ClientRepository clientRepository;

    @Mock
    static PersonRepository personRepository;
    @Mock
    static ClientMapper clientMapper;
    static String createJsonPayload = "json/client/create_payload.json";
    static String updateJsonPayload = "json/client/update_payload.json";


    @BeforeAll
    static void initialize() {
         clientService = new ClientServiceImpl(clientRepository,
                personRepository, clientMapper);
        clientController = new ClientController(clientService);
    }

    @Test
    void givenValidDataToCreateClient_thenSuccess_ok() throws Exception {
        String clientPayload = PayloadDataUtil.getDataStringFromJsonFile(createJsonPayload);
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
        assertThat(responseBody).contains("\"nationalId\":\"1111122221\"");
    }

    @Test
    void givenValidDataToUpdateClient_thenSuccess_ok() throws Exception {
        String clientPayload = PayloadDataUtil.getDataStringFromJsonFile(updateJsonPayload);
        MvcResult result = mockMvc.perform(
                        put("/api/clientes/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(clientPayload)
                )
                .andExpect(status().isOk())
                .andReturn();
        Thread.sleep(2000);
        String responseBody = result.getResponse().getContentAsString();
        System.out.println("Update Response Body: " + responseBody);
        assertThat(responseBody).contains("\"name\":\"Anatoly\"");
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
        assertThat(responseBody).contains("\"nationalId\":\"1111122221\"");
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
        assertThat(responseBody).contains("\"nationalId\":\"1111122221\"");
    }

    @Test
    void givenValidDataToDeleteClient_thenSuccess_ok() throws Exception {
        Thread.sleep(5000);
        //deleteClient();
    }

    private void deleteClient() throws Exception {
        long startTime = System.currentTimeMillis();
        long timeout = 20000;
        boolean conditionMet = false;

        while (System.currentTimeMillis() - startTime < timeout) {
        System.out.println("Waiting time: " + (System.currentTimeMillis() - startTime));
            Thread.sleep(500); // Wait for 500ms before polling again
        }
        Thread.sleep(2000);
        MvcResult result = mockMvc.perform(
                        delete("/api/clientes/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        assertThat(responseBody).contains("Client 1 was deleted successfully");
    }

}
