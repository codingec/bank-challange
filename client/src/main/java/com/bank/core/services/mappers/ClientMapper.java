package com.bank.core.services.mappers;

import com.bank.core.model.client.Client;
import com.bank.core.services.dto.ClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring",
        uses = { PersonMapper.class})
@Component
public interface ClientMapper {

    @Mapping(target = "password", ignore = true)
    @Mapping(source = "person", target = "persona")
    ClientDTO entityToDTO(Client client);

    @Mapping(source = "persona", target = "person")
    Client dtoToEntity(ClientDTO clientDTO);
}
