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
        return this.clientService.create(clientDto);
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAll() {
        return this.clientService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        return this.clientService.getClientById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDto) {
        return this.clientService.update(id, clientDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable Long id) {
        this.clientService.delete(id);
        return ResponseEntity.ok(ResponseDTO.builder()
                .message("Client "+id+" was deleted successfully")
                .build());
    }
}
