package com.bank.core.services.mappers;

import com.bank.core.model.account.Account;
import com.bank.core.services.dto.AccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface AccountMapper {

    @Mapping(target = "cliente", ignore = true)
    AccountDTO entityToDTO(Account account);


    Account dtoToEntity(AccountDTO accountDTO);
}
