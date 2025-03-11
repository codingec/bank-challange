package com.bank.core.services.account;

import com.bank.core.services.consumer.client.response.ClientDTO;
import com.bank.core.services.dto.AccountDTO;
import com.bank.core.services.dto.response.ResponseDTO;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    ResponseEntity<AccountDTO> create(AccountDTO accountDTO);
    ResponseEntity<List<AccountDTO>> getAll();
    ResponseEntity<AccountDTO> getAccountById(Long id);
    ResponseEntity<AccountDTO> update(Long id, AccountDTO accountDTO);
    ResponseEntity<ResponseDTO> delete(Long id);
    Optional<ClientDTO> getClientDetails(Long clientNationalId);
}
