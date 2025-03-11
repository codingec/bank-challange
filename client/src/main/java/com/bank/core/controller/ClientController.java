package com.bank.core.controller;

import com.bank.core.controller.swagger.ClientApi;
import com.bank.core.services.client.ClientService;
import com.bank.core.services.dto.ClientDTO;
import com.bank.core.services.dto.response.ResponseDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/clientes")
@NoArgsConstructor()
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class ClientController implements ClientApi {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO clientDto) {
        log.info("Processing Create request for clientIdentification={}, endpointMethod={}",
                clientDto.getPersona().getNationalId(), "create");
        return this.clientService.create(clientDto);
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAll() {
        log.info("Processing get all request for client={}, endpointMethod={}",
                "allClients", "getAll");
        return this.clientService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        log.info("Processing get by client id request for clientId={}, endpointMethod={}",
                id, "getClientById");
        return this.clientService.getClientById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDto) {
        log.info("Processing update request for clientId={}, clientIdentification={}, endpointMethod={}",
                id, clientDto.getPersona().getNationalId(), "update");
        return this.clientService.update(id, clientDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable Long id) {
        log.info("Processing delete request for clientId={},  endpointMethod={}",
                id,  "delete");
        return this.clientService.delete(id);

    }

    @GetMapping("/")
    public ResponseEntity<ClientDTO> getClientByNationalId(@RequestParam(name = "identificacion")
                                                               Long nationalIdentity) {
        log.info("Processing get by client National Id request for nationalId={}, endpointMethod={}",
                nationalIdentity, "getClientByNationalId");
        return this.clientService.getClientByNationalId(nationalIdentity);
    }
}
