package com.bank.core.controller.swagger;

import com.bank.core.services.dto.ClientDTO;
import com.bank.core.services.dto.response.ResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Client", description = "Client CRUD")
public interface ClientApi {

    ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO clientDto) ;

    ResponseEntity<List<ClientDTO>> getAll();

    ResponseEntity<ClientDTO> getClientById(@PathVariable Long id);

    ResponseEntity<ClientDTO> update(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDto);

    ResponseEntity<ResponseDTO> delete(@PathVariable Long id);

    ResponseEntity<ClientDTO> getClientByNationalId(@RequestParam(name = "identificacion")
                                                    Long nationalIdentity);
}
