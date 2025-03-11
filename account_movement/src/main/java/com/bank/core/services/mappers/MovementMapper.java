package com.bank.core.services.mappers;

import com.bank.core.model.movement.Movement;
import com.bank.core.services.dto.MovementDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring",
        uses = { AccountMapper.class})
@Component
public interface MovementMapper {
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    MovementDTO entityToDTO(Movement movement);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    Movement dtoToEntity(MovementDTO movementDTO);
}