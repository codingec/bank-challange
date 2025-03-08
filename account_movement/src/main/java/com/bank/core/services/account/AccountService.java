package com.bank.core.services.account;

import com.bank.core.services.dto.AccountDTO;
import com.bank.core.services.dto.response.ResponseDTO;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface AccountService {
    ResponseEntity<AccountDTO> create(AccountDTO accountDTO);
    ResponseEntity<List<AccountDTO>> getAll();
    ResponseEntity<AccountDTO> getAccountById(Long id);
    ResponseEntity<AccountDTO> update(Long id, AccountDTO accountDTO);
    ResponseEntity<ResponseDTO> delete(Long id);
}
