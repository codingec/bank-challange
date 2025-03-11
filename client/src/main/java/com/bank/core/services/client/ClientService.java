package com.bank.core.services.client;

import com.bank.core.services.dto.ClientDTO;
import com.bank.core.services.dto.response.ResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {
    ResponseEntity<ClientDTO> create(ClientDTO clientDTO);
    ResponseEntity<List<ClientDTO>> getAll();
    ResponseEntity<ClientDTO> getClientById(Long id);
    ResponseEntity<ClientDTO> update(Long id, ClientDTO clientDTO);
    ResponseEntity<ResponseDTO> delete(Long id);
    ResponseEntity<ClientDTO> getClientByNationalId(Long nationalIdentity);
}
