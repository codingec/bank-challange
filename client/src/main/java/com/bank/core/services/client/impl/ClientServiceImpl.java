package com.bank.core.services.client.impl;

import com.bank.core.model.client.Client;
import com.bank.core.model.client.ClientRepository;
import com.bank.core.model.person.Person;
import com.bank.core.model.person.PersonRepository;
import com.bank.core.services.client.ClientService;
import com.bank.core.services.dto.ClientDTO;
import com.bank.core.services.dto.response.ResponseDTO;
import com.bank.core.services.mappers.ClientMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        log.info("Processing Service for clientIdentification={}, serviceMethod={}",
                client.getPerson().getNationalId(), "create");
        Optional<Person> person = Optional.ofNullable(this.personRepository.save(client.getPerson()));
        if (person.isPresent()) {
            client.setPerson(person.get());
            log.info("Valid request for clientIdentification={}, serviceMethod={}",
                    client.getPerson().getNationalId(), "create");
            return new ResponseEntity<>(this.clientMapper.entityToDTO(
                    Optional.of(this.clientRepository.save(client))
                            .get()), HttpStatus.OK);
        } else {
            log.info("Not found for clientIdentification={}, serviceMethod={}",
                    clientDTO.getPersona().getNationalId(), "create");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<ClientDTO>> getAll() {
        List<Client> client = this.clientRepository.findAll();
        log.info("Processing Service for all clients, serviceMethod={}", "getAll");
        if (!client.isEmpty()) {
            log.info("Valid request for for all clients, serviceMethod={}", "getAll");
            return new ResponseEntity<>(client
                    .stream()
                    .map(clientMapper::entityToDTO)
                    .toList(), HttpStatus.OK);
        } else {
            log.info("Not found, serviceMethod={}", "getAll");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ClientDTO> getClientById(Long id) {
        Optional<Client> client = this.clientRepository.findById(id);
        log.info("Processing Service to get client by id,  clientId={},  " +
                "serviceMethod={}", id, "getClientById");
        if (client.isPresent()) {
            log.info("Valid get client by id,  clientId={}, " +
                    " serviceMethod={}", id, "getClientById");
            return new ResponseEntity<>(this.clientMapper.entityToDTO(client.get()),
                    HttpStatus.OK);
        } else {
            log.info("Not found,  clientId={}, " +
                    " serviceMethod={}", id, "getClientById");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @Transactional
    @Override
    public ResponseEntity<ClientDTO> update(Long id, ClientDTO clientDTO) {
        Optional<Client> clientData = this.clientRepository.findById(id);
        log.info("Processing Service to update,  clientId={},  " +
                "serviceMethod={}", id, "update");
        if (clientData.isPresent()) {
            Person personUpdated = buildPerson(clientDTO, clientData.get().getPerson().getId());
            Optional<Person> person = Optional.ofNullable(this.personRepository.save(personUpdated));
            Client clientUpdated = buildClient(clientData.get().getId(), person.get(), clientDTO);
            Optional<Client> client = Optional.ofNullable(this.clientRepository.save(clientUpdated));
            log.info("Valid update, clientId={},  " +
                    "serviceMethod={}", id, "update");
            return new ResponseEntity<>(this.clientMapper
                    .entityToDTO(client.get()), HttpStatus.OK);

        } else {
            log.info("Not found, clientId={},  " +
                    "serviceMethod={}", id, "update");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<ResponseDTO> delete(Long id) {
        Optional<Client> client = this.clientRepository.findById(id);
        log.info("Processing Service to delete,  clientId={},  " +
                "serviceMethod={}", id, "delete");
        if (client.isPresent()) {
            String name = client.get().getPerson().getName();
            this.clientRepository.delete(client.get());
            this.personRepository.delete(client.get().getPerson());
            log.info("Valid delete,  clientId={},  " +
                    "serviceMethod={}", id, "delete");
            return new ResponseEntity<>(ResponseDTO.builder()
                    .mensaje("Cliente " + name + " fue eliminado exitosamente.")
                    .build(), HttpStatus.OK);
        } else {
            log.info("Not found, client Id={},  " +
                    "serviceMethod={}", id, "delete");
            return new ResponseEntity<>(ResponseDTO.builder()
                    .mensaje("Cliente no existe.")
                    .build(), HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<ClientDTO> getClientByNationalId(Long nationalIdentity) {
        String nationalId = Long.toString(nationalIdentity);
        Optional<Client> client=  this.clientRepository.findByClientNationalId(nationalId);
        log.info("Processing Service to get client by nationalId,  nationalId={},  " +
                "serviceMethod={}", nationalIdentity, "getClientByNationalId");
        if (client.isPresent()) {
            log.info("Valid get client by nationalId,  nationalId={}, " +
                    " serviceMethod={}", nationalIdentity, "getClientByNationalId");
            return new ResponseEntity<>(this.clientMapper.entityToDTO(client.get()),
                    HttpStatus.OK);
        } else {
            log.info("Not found,  nationalId={}, " +
                    " serviceMethod={}", nationalIdentity, "getClientByNationalId");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private Client buildClient(Long clientId, Person person, ClientDTO clientDTO) {
        return Client.builder()
                .id(clientId)
                .person(person)
                .password(clientDTO.getPassword())
                .status(clientDTO.getStatus())
                .build();
    }

    private Person buildPerson(ClientDTO clientDTO, Long personId) {
        return Person.builder()
                .id(personId)
                .name(clientDTO.getPersona().getName())
                .gender(clientDTO.getPersona().getGender())
                .age(clientDTO.getPersona().getAge())
                .nationalId(clientDTO.getPersona().getNationalId())
                .address(clientDTO.getPersona().getAddress())
                .telephone(clientDTO.getPersona().getTelephone())
                .build();
    }
}
