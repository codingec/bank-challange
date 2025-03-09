package com.bank.core.services.account.impl;

import com.bank.core.model.account.Account;
import com.bank.core.model.account.AccountRepository;
import com.bank.core.services.account.AccountService;
import com.bank.core.services.dto.AccountDTO;
import com.bank.core.services.dto.response.ResponseDTO;
import com.bank.core.services.mappers.AccountMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@NoArgsConstructor()
@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public ResponseEntity<AccountDTO> create(AccountDTO accountDTO) {
        Account account = this.accountMapper.dtoToEntity(accountDTO);
        log.info("Processing Service create for, AccountNumber={}, serviceMethod={}",
                accountDTO.getAccountNumber(), "create");
        Optional<Account> optionalAccount = Optional.ofNullable(this.accountRepository.save(account));
        if (optionalAccount.isPresent()) {
            log.info("Valid create request for, AccountNumber={id}, serviceMethod={}",
                    accountDTO.getAccountNumber(), "create");
            return new ResponseEntity<>(this.accountMapper.entityToDTO(optionalAccount.get()),
                    HttpStatus.OK);
        } else {
            log.info("Not found for create, AccountNumber={}, serviceMethod={}",
                    accountDTO.getAccountNumber(), "update");
            return new ResponseEntity<>(new AccountDTO(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<AccountDTO>> getAll() {
        List<Account> accountDTOList = this.accountRepository.findAll();
        log.info("Processing Service get all for, serviceMethod={}", "getAll");
        if (!accountDTOList.isEmpty()) {
            log.info("Valid update request for, serviceMethod={}", "getAll");
            return new ResponseEntity<>(accountDTOList.stream()
                    .map(accountMapper::entityToDTO)
                    .toList(), HttpStatus.OK);
        } else {
            log.info("Not found for update, serviceMethod={}",  "getAll");
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<AccountDTO> getAccountById(Long id) {
        Optional<Account> client = this.accountRepository.findById(id);
        log.info("Processing Service get by id, id={},  serviceMethod={}", id,"getAccountById");
        if (client.isPresent()) {
            log.info("Valid get by id request for, id={}, serviceMethod={}", id,"getAll");
            return new ResponseEntity<>(this.accountMapper.entityToDTO(client.get()),
                    HttpStatus.OK);
        } else {
            log.info("Not found for get by id, id={}, serviceMethod={}", id, "getAll");
            return new ResponseEntity<>(new AccountDTO(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<AccountDTO> update(Long id, AccountDTO accountDTO) {
        Optional<Account> update = this.accountRepository.findById(id);
        log.info("Processing Service update for, id={},  serviceMethod={}", id,"update");
        if (update.isPresent()) {
            Account account = buildAccount(accountDTO, update.get().getId());
            Optional<Account> accountUpdated =
                    Optional.ofNullable(this.accountRepository.save(account));
            log.info("Valid update request for, id={}, serviceMethod={}", id,"update");
            return new ResponseEntity<>(this.accountMapper.entityToDTO(accountUpdated.get()),
                    HttpStatus.OK);
        } else {
            log.info("Not found for update, id={}, serviceMethod={}", id, "update");
            return new ResponseEntity<>(new AccountDTO(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> delete(Long id) {
        Optional<Account> account = this.accountRepository.findById(id);
        log.info("Processing Service delete for, id={},  serviceMethod={}", id, "delete");
        if (account.isPresent()) {
            String accountNumber = account.get().getAccountNumber();
            this.accountRepository.delete(account.get());
            log.info("Valid delete request for, id={}, serviceMethod={}", id, "delete");
            return new ResponseEntity<>(ResponseDTO.builder()
                    .message("Account " + accountNumber + " was deleted successfully.")
                    .build(), HttpStatus.OK);
        } else {
            log.info("Not found for delete, id={}, serviceMethod={}", id, "delete");
            return new ResponseEntity<>(ResponseDTO.builder()
                    .message("Account does not exist")
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    private static Account buildAccount(AccountDTO accountDTO, Long accountId) {
        return Account.builder()
                .id(accountId)
                .clientNationalId(accountDTO.getClientNationalId())
                .accountNumber(accountDTO.getAccountNumber())
                .accountType(accountDTO.getAccountType())
                .initialBalance(accountDTO.getInitialBalance())
                .status(accountDTO.getStatus())
                .build();

    }
}
