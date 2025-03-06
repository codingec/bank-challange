package com.bank.core.data;

import com.bank.core.model.client.Client;
import com.bank.core.model.person.Person;
import com.bank.core.services.dto.ClientDTO;
import com.bank.core.services.dto.PersonDTO;
import org.instancio.Instancio;

public class DataUtil {

    public static ClientDTO buildClientDTOData(){
        return Instancio.of(ClientDTO.class).create();
    }

    public static PersonDTO buildPersonDTOData(){
        return Instancio.of(PersonDTO.class).create();
    }

    public static Client buildClientData(){
        return Instancio.of(Client.class).create();
    }

    public static Person buildPersonData(){
        return Instancio.of(Person.class).create();
    }
}
