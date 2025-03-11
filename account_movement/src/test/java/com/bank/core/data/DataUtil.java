package com.bank.core.data;

import com.bank.core.model.account.Account;
import com.bank.core.model.movement.Movement;
import com.bank.core.services.consumer.client.response.ClientDTO;
import com.bank.core.services.consumer.client.response.PersonDTO;
import com.bank.core.services.dto.AccountDTO;
import com.bank.core.services.dto.MovementDTO;
import org.instancio.Instancio;

public class DataUtil {

    public static AccountDTO buildAccountDTOData(){
        AccountDTO accountDTO = Instancio.of(AccountDTO.class).create();
        accountDTO.setAccountNumber("19752115563");
        accountDTO.setInitialBalance(Double.valueOf("20000"));
        accountDTO.setStatus(true);
        return accountDTO;
    }

    public static Account buildAccountData(){
        Account account = Instancio.of(Account.class).create();
        account.setAccountNumber("19752115563");
        account.setInitialBalance(Double.valueOf("20000"));
        account.setStatus(true);
        return account;
    }

    public static MovementDTO buildMovementDTOData(){
        MovementDTO movementDTO = Instancio.of(MovementDTO.class).create();
        movementDTO.setAccount(buildAccountDTOData());
        movementDTO.setTransferAmount(Double.parseDouble("1000"));
        return movementDTO;
    }

    public static Movement buildMovementData(){
        Movement movement = Instancio.of(Movement.class).create();
        movement.setAccount(buildAccountData());
        movement.setTransferAmount(Double.parseDouble("1000"));
        return movement;
    }

    public static ClientDTO buildClientDTOData(){
        return Instancio.of(ClientDTO.class).create();
    }

    public static PersonDTO buildPersonDTOData(){
        return Instancio.of(PersonDTO.class).create();
    }
}
