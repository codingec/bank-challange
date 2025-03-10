package com.bank.core.services.consumer.client;

import com.bank.core.services.consumer.client.response.ClientDTO;
import com.bank.core.services.dto.AccountDTO;

import java.util.Optional;

public interface ClientRestConsumerService {
    Optional<ClientDTO> restConsumer(String clientNationalId);
}
