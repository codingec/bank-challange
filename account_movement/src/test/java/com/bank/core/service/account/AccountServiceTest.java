package com.bank.core.service.account;

import static com.bank.core.data.DataUtil.buildAccountData;
import static com.bank.core.data.DataUtil.buildAccountDTOData;
import static com.bank.core.data.DataUtil.buildClientDTOData;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bank.core.controller.exception.BadRequest;
import com.bank.core.model.account.Account;
import com.bank.core.model.account.AccountRepository;
import com.bank.core.services.account.impl.AccountServiceImpl;
import com.bank.core.services.consumer.client.ClientRestConsumerService;
import com.bank.core.services.consumer.client.response.ClientDTO;
import com.bank.core.services.dto.AccountDTO;
import com.bank.core.services.dto.response.ResponseDTO;
import com.bank.core.services.mappers.AccountMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks
    AccountServiceImpl accountService;
    @Mock
    AccountRepository accountRepository;
    @Mock
    ClientRestConsumerService restConsumerService;
    @Mock
    AccountMapper accountMapper;


    @Test
    void givenValidDataToCreateAnAccount_thenSuccess_200_ok() {
        Account account =buildAccountData();
        AccountDTO accountDTO =buildAccountDTOData();
        when(restConsumerService.restConsumer(anyString()))
                .thenReturn(Optional.of(buildClientDTOData()));
        when(accountMapper.dtoToEntity(any(AccountDTO.class)))
                .thenReturn(account);
        when(accountRepository.save(any(Account.class)))
                .thenReturn(account);
        when(accountMapper.entityToDTO(any(Account.class)))
                .thenReturn(accountDTO);
        ResponseEntity<AccountDTO> response = accountService.create(accountDTO);
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.OK)).isEqualTo(true);
        assertThat(response.getBody()).isNotNull();
        verify(accountMapper).dtoToEntity(any(AccountDTO.class));
        verify(accountRepository).save(any(Account.class));
        verify(accountMapper).entityToDTO(any(Account.class));
    }

    @Test
    void givenInValidDataToCreateAnAccount_thenSuccess_400_ok() {
        AccountDTO accountDTO =buildAccountDTOData();
        when(restConsumerService.restConsumer(anyString()))
                .thenReturn(Optional.of(buildClientDTOData()));
        ResponseEntity<AccountDTO> response = accountService.create(accountDTO);
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)).isEqualTo(true);
        verify(accountMapper).dtoToEntity(any(AccountDTO.class));
    }

    @Test
    void givenValidDataToGetAllAccounts_thenSuccess_200_ok() {
        List<Account> accountList = new ArrayList<>();
        when(restConsumerService.restConsumer(anyString()))
                .thenReturn(Optional.of(buildClientDTOData()));
        accountList.add(buildAccountData());
        when(accountMapper.entityToDTO(any(Account.class)))
                .thenReturn(buildAccountDTOData());
        when(accountRepository.findAll())
                .thenReturn(accountList);
        ResponseEntity<List<AccountDTO>> response = accountService.getAll();
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.OK)).isEqualTo(true);
        assertThat(response.getBody()).isNotNull();
        verify(accountRepository).findAll();
    }

    @Test
    void givenInValidDataToGetAllAccounts_thenSuccess_g400_ok() {
        List<Account> accountList = new ArrayList<>();
        when(accountRepository.findAll())
                .thenReturn(accountList);
        ResponseEntity<List<AccountDTO>> response = accountService.getAll();
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)).isEqualTo(true);
        verify(accountRepository).findAll();
    }

    @Test
    void givenValidDataToGetByIdAccounts_thenSuccess_200_ok() {
        when(accountMapper.entityToDTO(any(Account.class)))
                .thenReturn(buildAccountDTOData());
        when(restConsumerService.restConsumer(anyString()))
                .thenReturn(Optional.of(buildClientDTOData()));
        when(accountRepository.findById(anyLong()))
                .thenReturn(Optional.of(buildAccountData()));
        ResponseEntity<AccountDTO> response = accountService.getAccountById(1L);
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.OK)).isEqualTo(true);
        assertThat(response.getBody()).isNotNull();
        verify(accountRepository).findById(anyLong());
    }

    @Test
    void givenInValidDataToGetByIdAccounts_thenSuccess_g400_ok() {
        when(accountRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        ResponseEntity<AccountDTO> response = accountService.getAccountById(1L);
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)).isEqualTo(true);
        verify(accountRepository).findById(anyLong());
    }

    @Test
    void givenValidDataToUpdateAccount_thenSuccess_200_ok() {
        AccountDTO accountDTO =buildAccountDTOData();
        when(restConsumerService.restConsumer(anyString()))
                .thenReturn(Optional.of(buildClientDTOData()));
        when(accountMapper.entityToDTO(any(Account.class)))
                .thenReturn(accountDTO);
        when(accountRepository.findById(anyLong()))
                .thenReturn(Optional.of(buildAccountData()));
        when(accountRepository.save(any(Account.class)))
                .thenReturn(buildAccountData());
        ResponseEntity<AccountDTO> response = accountService.update(1L, accountDTO);
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.OK)).isEqualTo(true);
        assertThat(response.getBody()).isNotNull();
        verify(accountRepository).findById(any());
        verify(accountRepository).save(any());
    }

    @Test
    void givenInValidDataToUpdateAccount_thenSuccess_g400_ok() {
        when(accountRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        ResponseEntity<AccountDTO> response = accountService.getAccountById(1L);
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)).isEqualTo(true);
        verify(accountRepository).findById(any());
    }

    @Test
    void givenValidDataToDeleteAccount_thenSuccess_200_ok() {
        when(accountRepository.findById(anyLong()))
                .thenReturn(Optional.of(buildAccountData()));
        ResponseEntity<ResponseDTO> response = accountService.delete(1L);
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.OK)).isEqualTo(true);
        assertThat(response.getBody()).isNotNull();
        verify(accountRepository).findById(any());
        verify(accountRepository).delete(any());
    }

    @Test
    void givenInValidDataToDeleteAccount_thenSuccess_g400_ok() {
        when(accountRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        ResponseEntity<ResponseDTO> response = accountService.delete(1L);
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)).isEqualTo(true);
        verify(accountRepository).findById(any());
    }

    @Test
    void givenValidDataToClient_thenSuccess_200_ok() {
        when(restConsumerService.restConsumer(anyString()))
                .thenReturn(Optional.of(buildClientDTOData()));
        Optional<ClientDTO> response = accountService.getClientDetails(1L);
        assertThat(response.isPresent()).isEqualTo(true);
        verify(restConsumerService).restConsumer(anyString());
    }

    @Test
    void givenInValidDataToClientDoesNotExist_thenSuccess_400_ok() {
        AccountDTO accountDTO =buildAccountDTOData();
        accountDTO.setClientNationalId(1234L);
        when(restConsumerService.restConsumer(anyString()))
                .thenThrow(new BadRequest("Cliente no existe: 1234" , new Throwable("Cliente no existe")));

        assertThatThrownBy(() -> accountService.getClientDetails(1l))
                .isInstanceOf(BadRequest.class) // Verify the exception type
                .hasMessage("Cliente no existe: 1234");

    }



    @Test
    void givenInValidDataToBadRequestClientDoesNotExist_thenSuccess_400_ok() {
        AccountDTO accountDTO =buildAccountDTOData();
        accountDTO.setClientNationalId(1234L);
        when(restConsumerService.restConsumer(anyString()))
                .thenThrow(new BadRequest("Cliente no existe: 1234" , new Throwable("Cliente no existe")));

        assertThatThrownBy(() -> accountService.create(accountDTO))
                .isInstanceOf(BadRequest.class) // Verify the exception type
                .hasMessage("Cliente no existe: 1234");

    }
}