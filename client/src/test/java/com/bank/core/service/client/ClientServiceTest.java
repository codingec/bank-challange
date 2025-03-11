package com.bank.core.service.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static com.bank.core.data.DataUtil.buildClientData;
import static com.bank.core.data.DataUtil.buildClientDTOData;
import static com.bank.core.data.DataUtil.buildPersonData;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

import com.bank.core.model.client.Client;
import com.bank.core.model.client.ClientRepository;
import com.bank.core.model.person.Person;
import com.bank.core.model.person.PersonRepository;
import com.bank.core.services.client.impl.ClientServiceImpl;
import com.bank.core.services.dto.ClientDTO;
import com.bank.core.services.mappers.ClientMapper;
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
class ClientServiceTest {
    @InjectMocks
    ClientServiceImpl clientService;
    @Mock
    ClientRepository clientRepository;
    @Mock
    PersonRepository personRepository;
    @Mock
    ClientMapper clientMapper;

    @Test
    void givenValidDataToCreateAClient_thenSuccess_200_ok() {
        when(clientMapper.dtoToEntity(any(ClientDTO.class)))
                .thenReturn(buildClientData());
        when(clientMapper.entityToDTO(any(Client.class)))
                .thenReturn(buildClientDTOData());
        when(personRepository.save(any(Person.class)))
                .thenReturn(buildPersonData());
        when(clientRepository.save(any(Client.class)))
                .thenReturn(buildClientData());
        ClientDTO clientDTO = buildClientDTOData();
        ResponseEntity<ClientDTO> response = clientService.create(clientDTO);
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.OK)).isEqualTo(true);
        assertThat(response.getBody()).isNotNull();
        verify(clientMapper).dtoToEntity(any(ClientDTO.class));
        verify(clientMapper).entityToDTO(any(Client.class));
        verify(personRepository).save(any(Person.class));
        verify(clientRepository).save(any(Client.class));
    }

    @Test
    void givenValidDataToCreateAClient_thenSuccess_400_ok() {
        when(clientMapper.dtoToEntity(any(ClientDTO.class)))
                .thenReturn(buildClientData());

        ClientDTO clientDTO = buildClientDTOData();
        ResponseEntity<ClientDTO> response = clientService.create(clientDTO);
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)).isEqualTo(true);
        verify(clientMapper).dtoToEntity(any(ClientDTO.class));
    }

    @Test
    void givenValidDataToGetAllClients_thenSuccess_200_ok() {
        List<Client> client = new ArrayList<>();
        client.add(buildClientData());
        when(clientRepository.findAll())
                .thenReturn(client);

        ResponseEntity<List<ClientDTO>> response = clientService.getAll();
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.OK)).isEqualTo(true);
        assertThat(response.getBody()).isNotNull();
        verify(clientRepository).findAll();
    }

    @Test
    void givenValidDataToGetAllClients_thenSuccess_400_ok() {
        List<Client> client = new ArrayList<>();
        when(clientRepository.findAll())
                .thenReturn(client);

        ResponseEntity<List<ClientDTO>> response = clientService.getAll();
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)).isTrue();
        verify(clientRepository).findAll();
    }

    @Test
    void givenValidDataToGetClientById_thenSuccess_200_ok() {
         Client client = buildClientData();
        when(clientRepository.findById(anyLong()))
                .thenReturn(Optional.of(client));
        when(clientMapper.entityToDTO(any(Client.class)))
                .thenReturn(buildClientDTOData());
        ResponseEntity<ClientDTO> response = clientService.getClientById(1L);
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.OK)).isEqualTo(true);
        assertThat(response.getBody()).isNotNull();
        verify(clientRepository).findById(anyLong());
        verify(clientMapper).entityToDTO(any(Client.class));
    }

    @Test
    void givenValidDataToGetClientById_thenSuccess_400_ok() {
        when(clientRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        ResponseEntity<ClientDTO> response = clientService.getClientById(1L);
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)).isTrue();
        verify(clientRepository).findById(anyLong());
    }

    @Test
    void givenValidDataToUpdate_thenSuccess_200_ok() {
        Client client = buildClientData();
        ClientDTO clientDTO = buildClientDTOData();
        when(clientRepository.findById(anyLong()))
                .thenReturn(Optional.of(client));
        when(clientMapper.entityToDTO(any(Client.class)))
                .thenReturn(clientDTO);
        when(personRepository.save(any(Person.class)))
                .thenReturn(buildPersonData());
        when(clientRepository.save(any(Client.class)))
                .thenReturn(client);


        ResponseEntity<ClientDTO> response = clientService.update(1L, clientDTO);
        verify(clientRepository).findById(anyLong());
        verify(clientRepository).save(any(Client.class));
        verify(personRepository).save(any(Person.class));
        verify(clientMapper).entityToDTO(any(Client.class));
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.OK)).isEqualTo(true);
        assertThat(response.getBody()).isNotNull();

    }

    @Test
    void givenValidDataToUpdate_thenSuccess_400_ok() {
        when(clientRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        ClientDTO clientDTO = buildClientDTOData();
        ResponseEntity<ClientDTO> response = clientService.update(1L, clientDTO);
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)).isTrue() ;
    }

    @Test
    void givenValidDataToDelete_thenSuccess_ok() {
        Client client = buildClientData();
        when(clientRepository.findById(anyLong()))
                .thenReturn(Optional.of(client));

        clientService.delete(1L);
        verify(clientRepository).findById(anyLong());
        verify(clientRepository).delete(any(Client.class));
    }

    @Test
    void givenValidDataToDelete_thenSuccess_notDeletes() {
        when(clientRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        clientService.delete(1L);
        verify(clientRepository).findById(anyLong());
        verify(clientRepository, never()).delete(any(Client.class));
    }

    @Test
    void givenValidDataToGetClientNationalId_thenSuccess_200_ok() {
        Client client = buildClientData();
        when(clientRepository.findByClientNationalId(anyString()))
                .thenReturn(Optional.of(client));
        when(clientMapper.entityToDTO(any(Client.class)))
                .thenReturn(buildClientDTOData());
        ResponseEntity<ClientDTO> response = clientService.getClientByNationalId(111291234521L);
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.OK)).isEqualTo(true);
        assertThat(response.getBody()).isNotNull();
        verify(clientRepository).findByClientNationalId(anyString());
        verify(clientMapper).entityToDTO(any(Client.class));
    }

    @Test
    void givenValidDataToGetClientNationalId_thenSuccess_400_ok() {
        when(clientRepository.findByClientNationalId(anyString()))
                .thenReturn(Optional.empty());

        ResponseEntity<ClientDTO> response = clientService.getClientByNationalId(1L);
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)).isTrue();
        verify(clientRepository).findByClientNationalId(anyString());
    }
}
