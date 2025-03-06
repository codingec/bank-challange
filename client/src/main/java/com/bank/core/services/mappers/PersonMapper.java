package com.bank.core.services.mappers;

import com.bank.core.model.person.Person;
import com.bank.core.services.dto.PersonDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface PersonMapper {


    PersonDTO entityToDTO(Person person);

    Person dtoToEntity(PersonDTO personDTO);
}
