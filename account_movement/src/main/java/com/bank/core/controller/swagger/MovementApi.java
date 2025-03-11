package com.bank.core.controller.swagger;

import com.bank.core.services.dto.MovementDTO;
import com.bank.core.services.dto.response.ResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Movimientos", description = "Movimientos CRUD")
public interface MovementApi {

    ResponseEntity<MovementDTO> create(@Valid @RequestBody MovementDTO movementDto) ;

    ResponseEntity<List<MovementDTO>> getAlls();

    ResponseEntity<MovementDTO> getMovementById(@PathVariable Long id);

    ResponseEntity<MovementDTO> update(@PathVariable Long id, @Valid @RequestBody MovementDTO movementDto);

    ResponseEntity<ResponseDTO> delete(@PathVariable Long id);

    ResponseEntity<List<MovementDTO>> report(@RequestParam(name = "fechaInicio") String startDate,
                                             @RequestParam(name = "fechaFin") String endDate);
}
