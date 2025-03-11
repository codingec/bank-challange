package com.bank.core.services.consumer.client.impl;

import com.bank.core.config.properties.Properties;
import com.bank.core.controller.exception.BadRequest;
import com.bank.core.services.consumer.client.ClientRestConsumerService;
import com.bank.core.services.consumer.client.response.ClientDTO;
import com.bank.core.services.dto.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@Slf4j
@NoArgsConstructor()
@AllArgsConstructor
@Service
public class ClientRestConsumerServiceImpl implements ClientRestConsumerService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Properties properties;

    @Override
    public Optional<ClientDTO> restConsumer(String clientNationalId) {
        log.info("Processing Service restConsumer for clientNationalId={}, serviceMethod={}",
                clientNationalId, "restConsumer");
        ResponseEntity<ClientDTO> response = restTemplate
                .getForEntity(properties.getClientUrl()+clientNationalId, ClientDTO.class);
       if(response.getStatusCode().is2xxSuccessful()){
           log.info("Valid request, Account disabled clientNationalId={}, serviceMethod={}",
                   clientNationalId, "restConsumer");
           return Optional.ofNullable(response.getBody());
       }else{
           log.info("Not Valid request, Client does not exist clientNationalId={}, serviceMethod={}",
                   clientNationalId, "restConsumer");
           String message = "Cliente no existe, identificacion cliente:"+ clientNationalId;
           throw new BadRequest(message, new Throwable("Cliente no existe"));
       }
    }
}
