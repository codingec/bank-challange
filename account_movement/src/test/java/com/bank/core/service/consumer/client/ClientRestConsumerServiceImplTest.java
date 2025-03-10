package com.bank.core.service.consumer.client;

import com.bank.core.controller.exception.BadRequest;
import com.bank.core.data.DataUtil;
import com.bank.core.services.consumer.client.impl.ClientRestConsumerServiceImpl;
import com.bank.core.services.consumer.client.response.ClientDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.AssertionErrors;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ClientRestConsumerServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ClientRestConsumerServiceImpl clientRestConsumerService;

    private String clientNationalId;
    private String url;

    @BeforeEach
    void setUp() {
        clientNationalId = "123456789";
        url = "http://localhost:80/banco/api/clientes/?identificacion=" + clientNationalId;
    }

    @Test
    void testRestConsumer_Success() {
        // Arrange
        ClientDTO mockClientDTO =  DataUtil.buildClientDTOData();
        ResponseEntity<ClientDTO> responseEntity = new ResponseEntity<>(mockClientDTO, HttpStatus.OK);

        Mockito.when(restTemplate.getForEntity(url, ClientDTO.class)).thenReturn(responseEntity);

        // Act
        Optional<ClientDTO> result = clientRestConsumerService.restConsumer(clientNationalId);

        // Assertv
        Mockito.verify(restTemplate, Mockito.times(1)).getForEntity(url, ClientDTO.class);
    }

    @Test
    void testRestConsumer_Failure() {
        // Arrange
        ResponseEntity<ClientDTO> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Mockito.when(restTemplate.getForEntity(url, ClientDTO.class)).thenReturn(responseEntity);

        // Act & Assert
        Exception exception = Assertions.assertThrows(BadRequest.class, () -> {
            clientRestConsumerService.restConsumer(clientNationalId);
        });

        Mockito.verify(restTemplate, Mockito.times(1)).getForEntity(url, ClientDTO.class);
    }
}
