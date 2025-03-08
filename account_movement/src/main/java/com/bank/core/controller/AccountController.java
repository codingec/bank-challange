package com.bank.core.controller;

import com.bank.core.controller.swagger.AccountApi;
import com.bank.core.services.account.AccountService;
import com.bank.core.services.dto.AccountDTO;
import com.bank.core.services.dto.response.ResponseDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cuentas")
@NoArgsConstructor()
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AccountController implements AccountApi {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDTO> create(@Valid @RequestBody AccountDTO accountDTO) {
        log.info("Processing Create request for accountNumber={}, endpointMethod={}",
                accountDTO.getAccountNumber(), "create");
        return this.accountService.create(accountDTO);
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAlls() {
        log.info("Processing Get all request for, endpointMethod={}", "getAlls");
        return this.accountService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        log.info("Processing get by Account id request for accountId={}, " +
                "endpointMethod={}", id,"getAccountById");
        return this.accountService.getAccountById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> update(@PathVariable Long id, @Valid @RequestBody AccountDTO accountDTO) {
        log.info("Processing update request for accountId={}, accountNumber={}," +
                "endpointMethod={}", id, accountDTO.getAccountNumber(),"update");
        return this.accountService.update(id, accountDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable Long id) {
        log.info("Processing delete request for accountId={}, " +
                "endpointMethod={}", id,"delete");
       return this.accountService.delete(id);
    }
}
