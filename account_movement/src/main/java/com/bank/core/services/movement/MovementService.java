package com.bank.core.services.movement;

import com.bank.core.services.dto.MovementDTO;
import com.bank.core.services.dto.response.ResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovementService {
    ResponseEntity<MovementDTO> create(MovementDTO movementDTO);
    ResponseEntity<List<MovementDTO>> getAll();
    ResponseEntity<MovementDTO> getMovementById(Long id);
    ResponseEntity<MovementDTO> update(Long id, MovementDTO movementDTO);
    ResponseEntity<ResponseDTO>  delete(Long id);
    ResponseEntity<List<MovementDTO>> report(String startDate, String endDate);
}
