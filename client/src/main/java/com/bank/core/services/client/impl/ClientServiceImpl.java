package com.bank.core.services.client.impl;

import com.bank.core.model.client.Client;
import com.bank.core.model.client.ClientRepository;
import com.bank.core.model.person.Person;
import com.bank.core.model.person.PersonRepository;
import com.bank.core.services.client.ClientService;
import com.bank.core.services.dto.ClientDTO;
import com.bank.core.services.mappers.ClientMapper;
import jakarta.transaction.Transactional;
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
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public ResponseEntity<ClientDTO> create(ClientDTO clientDTO) {
        Client client = this.clientMapper.dtoToEntity(clientDTO);
        Optional<Person> person = Optional.ofNullable(this.personRepository.saveAndFlush(client.getPerson()));
        if(person.isPresent()){
            client.setPerson(person.get());
            return new ResponseEntity<>(this.clientMapper.entityToDTO(
                    Optional.of(this.clientRepository.saveAndFlush(client))
                            .get()), HttpStatus.OK);
        }else {
            return new ResponseEntity<>( new ClientDTO(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<ClientDTO>> getAll() {
        List<Client> client = this.clientRepository.findAll();
        if(!client.isEmpty()){
            return new ResponseEntity<>(client
                    .stream()
                    .map(clientMapper::entityToDTO)
                    .toList(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<ClientDTO> getClientById(Long id) {
        Optional<Client> client = this.clientRepository.findById(id);
        ClientDTO response;
        if(client.isPresent()){
            response = this.clientMapper.entityToDTO(client.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ClientDTO(), HttpStatus.NO_CONTENT);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<ClientDTO> update(Long id, ClientDTO clientDTO) {
        Optional<Client> clientData  = this.clientRepository.findById(id);
        ClientDTO response;
        if(clientData.isPresent()){
            Person personUpdated = buildPerson(clientDTO, clientData.get().getPerson().getId());
            Optional<Person> person = Optional.ofNullable(this.personRepository.saveAndFlush(personUpdated));
            Client clientUpdated = buildClient(clientData.get().getId(), person.get(), clientDTO);
            Optional<Client> client = Optional.ofNullable(this.clientRepository.saveAndFlush(clientUpdated));
            response = this.clientMapper.entityToDTO(client.get());
            return new ResponseEntity<>(response, HttpStatus.OK);

        }else {
            return new ResponseEntity<>(new ClientDTO(), HttpStatus.NO_CONTENT);
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
      Optional<Client> client =  this.clientRepository.findById(id);
      if(client.isPresent()){
          this.personRepository.delete(client.get().getPerson());
          this.clientRepository.delete(client.get());
      }

    }

    private Client buildClient(Long clientId, Person person, ClientDTO clientDTO){
        return Client.builder()
                .id(clientId )
                .person(person)
                .password(clientDTO.getPassword())
                .status(clientDTO.getStatus())
                .build();
    }

    private Person buildPerson(ClientDTO clientDTO, Long personId) {
        return Person.builder()
                .id(personId)
                .name(clientDTO.getPerson().getName())
                .gender(clientDTO.getPerson().getGender())
                .age(clientDTO.getPerson().getAge())
                .nationalId(clientDTO.getPerson().getNationalId())
                .address(clientDTO.getPerson().getAddress())
                .telephone(clientDTO.getPerson().getTelephone())
                .build();
    }
}
