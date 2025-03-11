package com.bank.core.controller.swagger;

import com.bank.core.services.dto.AccountDTO;
import com.bank.core.services.dto.response.ResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Cuenta", description = "Cuenta CRUD")
public interface AccountApi {

    ResponseEntity<AccountDTO> create(@Valid @RequestBody AccountDTO accountDTO);

    ResponseEntity<List<AccountDTO>> getAlls();

    ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id);

    ResponseEntity<AccountDTO> update(@PathVariable Long id, @Valid @RequestBody AccountDTO accountDTO);

    ResponseEntity<ResponseDTO> delete(@PathVariable Long id);
}