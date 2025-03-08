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
        Optional<Account> optionalAccount = Optional.ofNullable(this.accountRepository.save(account));
        if(optionalAccount.isPresent()){
            return new ResponseEntity<>( this.accountMapper.entityToDTO(optionalAccount.get()),
                    HttpStatus.OK);
        }else{
            return new ResponseEntity<>( new AccountDTO(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<AccountDTO>> getAll() {
        List<Account> accountDTOList = this.accountRepository.findAll();
        if(!accountDTOList.isEmpty()){
            return new ResponseEntity<>(accountDTOList.stream()
                    .map(accountMapper::entityToDTO)
                    .toList(), HttpStatus.OK);
        } else {
          return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<AccountDTO> getAccountById(Long id) {
        Optional<Account> client = this.accountRepository.findById(id);
        if(client.isPresent()){
            return new ResponseEntity<>( this.accountMapper.entityToDTO(client.get()),
                    HttpStatus.OK);
        }else {
            return new ResponseEntity<>( new AccountDTO(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<AccountDTO> update(Long id, AccountDTO accountDTO) {
        Optional<Account> update  = this.accountRepository.findById(id);
        if(update.isPresent()){
            Account account = buildAccount(accountDTO, update.get().getId());
            Optional<Account> accountUpdated =
            Optional.ofNullable(this.accountRepository.save(account));
            return new ResponseEntity<>(this.accountMapper.entityToDTO(accountUpdated.get()),
                    HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>( new AccountDTO(), HttpStatus.BAD_REQUEST);
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

    @Override
    public ResponseEntity<ResponseDTO> delete(Long id) {
        Optional<Account> account = this.accountRepository.findById(id);
        if(account.isPresent()) {
            String accountNumber = account.get().getAccountNumber();
            this.accountRepository.delete(account.get());
            return new ResponseEntity<>(ResponseDTO.builder()
                    .message("Account "+accountNumber+" was deleted successfully")
                    .build(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(ResponseDTO.builder()
                    .message("Account does not exist")
                    .build(), HttpStatus.NO_CONTENT);
        }
    }
}
