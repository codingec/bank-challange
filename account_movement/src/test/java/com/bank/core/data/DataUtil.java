package com.bank.core.data;

import com.bank.core.model.account.Account;
import com.bank.core.model.movement.Movement;
import com.bank.core.services.dto.AccountDTO;
import com.bank.core.services.dto.MovementDTO;
import org.instancio.Instancio;

public class DataUtil {

    public static AccountDTO buildAccountDTOData(){
        return Instancio.of(AccountDTO.class).create();
    }

    public static Account buildAccountData(){
        return Instancio.of(Account.class).create();
    }

    public static MovementDTO buildMovementDTOData(){
        return Instancio.of(MovementDTO.class).create();
    }

    public static Movement buildMovementData(){
        return Instancio.of(Movement.class).create();
    }
}
