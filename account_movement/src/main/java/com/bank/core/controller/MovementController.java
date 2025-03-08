package com.bank.core.controller;

import com.bank.core.controller.swagger.MovementApi;
import com.bank.core.services.dto.response.ResponseDTO;
import com.bank.core.services.movement.MovementService;
import com.bank.core.services.dto.MovementDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/movimientos")
@NoArgsConstructor()
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class MovementController implements MovementApi {

    @Autowired
    private MovementService movementService;

    @PostMapping
    public ResponseEntity<MovementDTO> create(@Valid @RequestBody MovementDTO movementDto) {
        log.info("Processing Create request for accountNumber={}, endpointMethod={}",
                movementDto.getAccount().getAccountNumber(), "create");
        return movementService.create(movementDto);
    }

    @GetMapping
    public ResponseEntity<List<MovementDTO>> getAlls() {
        log.info("Processing Get all request for, endpointMethod={}", "getAlls");
        return movementService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovementDTO> getMovementById(@PathVariable Long id) {
        log.info("Processing get by Account id request for accountId={}, " +
                "endpointMethod={}", id,"getMovementById");
        return movementService.getMovementById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovementDTO> update(@PathVariable Long id, @Valid @RequestBody MovementDTO movementDto) {
        log.info("Processing update request for accountId={}, accountNumber={}," +
                "endpointMethod={}", id, movementDto.getAccount().getAccountNumber(),"update");
        return movementService.update(id, movementDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO>  delete(@PathVariable Long id) {
        log.info("Processing delete request for accountId={}, " +
                "endpointMethod={}", id,"delete");
        return movementService.delete(id);
    }
}
