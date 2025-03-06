package com.bank.core.model.client;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import static com.bank.core.data.DataUtil.buildClientData;
import static com.bank.core.data.DataUtil.buildPersonData;
import static org.assertj.core.api.Assertions.assertThat;

import com.bank.core.model.person.Person;
import com.bank.core.model.person.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ClientRepositoryTest {
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private PersonRepository personRepository;

    @Test
    void givenValidDataToSaveAClient_thenSuccess_ok() {
        Client client = buildClientData();
        Person person = buildPersonData();
        when(this.personRepository.save(any(Person.class)))
                .thenReturn(person);
        when(this.clientRepository.save(any(Client.class)))
                .thenReturn(client);
        Person personSaved = this.personRepository.save(person);
        client.setPerson(personSaved);
        Client clientSaved = this.clientRepository.save(client);
        assertThat(clientSaved.getId()).isNotNull();
        assertThat(clientSaved.getPerson()).isNotNull();
        assertThat(clientSaved.getPassword()).isEqualTo(client.getPassword());
        verify(personRepository).save(any(Person.class));
        verify(clientRepository).save(any(Client.class));
    }

    @Test
    void givenValidDataToGetAllClients_thenSuccess_ok() {
        List<Client> listClients = new ArrayList<>();
        Client client = buildClientData();
        listClients.add(client);
        when(this.clientRepository.findAll())
                .thenReturn(listClients);

        List<Client> findAll = this.clientRepository.findAll();
        assertThat(findAll.get(0).getId()).isNotNull();
        assertThat(findAll.get(0).getPerson()).isNotNull();
        assertThat(findAll.get(0).getPassword()).isEqualTo(client.getPassword());
        verify(clientRepository).findAll();
    }

    @Test
    void givenValidDataToFindByIdClient_thenSuccess_ok() {
        Client client = buildClientData();
        when(this.clientRepository.findById(anyLong()))
                .thenReturn(Optional.of(client));
        Optional<Client> clientSaved = this.clientRepository.findById(1L);
        assertThat(clientSaved.get().getId()).isNotNull();
        assertThat(clientSaved.get().getPerson()).isNotNull();
        assertThat(clientSaved.get().getPassword()).isEqualTo(client.getPassword());
        verify(clientRepository).findById(anyLong());
    }


}
