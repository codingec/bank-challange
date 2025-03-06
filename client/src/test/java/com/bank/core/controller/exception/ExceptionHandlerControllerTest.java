package com.bank.core.controller.exception;

import com.bank.core.controller.ClientController;
import com.bank.core.data.PayloadDataUtil;
import com.bank.core.model.client.ClientRepository;
import com.bank.core.model.person.PersonRepository;
import com.bank.core.services.client.impl.ClientServiceImpl;
import com.bank.core.services.mappers.ClientMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ExceptionHandlerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    ClientController clientController;

    @Mock
    ClientServiceImpl clientService;

    @Mock
    ClientRepository clientRepository;

    @Mock
    PersonRepository personRepository;
    @Mock
    ClientMapper clientMapper;

    @LocalServerPort
    private int port;


    @Test
    void givenValidDataToCreateClient_thenSuccess_500() throws Exception {

        mockMvc.perform(
                        post("/banco/api/clientes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(PayloadDataUtil.buildClientDTODataRequest())
                )
                .andExpect(status().is5xxServerError());

    }

    @Test
    void givenValidDataToCreateClient_thenSuccess_400() throws Exception {

        mockMvc.perform(
                        post("/banco/api/clientes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is5xxServerError());

    }

}
