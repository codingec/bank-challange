package com.bank.core.service.movement;

import com.bank.core.controller.exception.BadRequest;
import com.bank.core.model.account.Account;
import com.bank.core.model.account.AccountRepository;
import com.bank.core.model.movement.Movement;
import com.bank.core.model.movement.MovementRepository;
import com.bank.core.services.account.impl.AccountServiceImpl;
import com.bank.core.services.dto.AccountDTO;
import com.bank.core.services.dto.MovementDTO;
import com.bank.core.services.dto.response.ResponseDTO;
import com.bank.core.services.mappers.MovementMapper;
import com.bank.core.services.movement.impl.MovementServiceImpl;
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

import static com.bank.core.data.DataUtil.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovementServiceTest {
    @InjectMocks
    MovementServiceImpl movementService;
    @Mock
    MovementRepository movementRepository;
    @Mock
    AccountRepository accountRepository;
    @Mock
    MovementMapper movementMapper;
    @Mock
    AccountServiceImpl accountService;

    @Test
    void givenValidDataToCreateAnAccount_thenSuccess_200_ok() {
        MovementDTO movementDTO = buildMovementDTOData();
        when(accountService.getClientDetails(anyLong()))
                .thenReturn(Optional.of(buildClientDTOData()));
        Movement movement = buildMovementData();
        when(movementMapper.dtoToEntity(any(MovementDTO.class)))
                .thenReturn(movement);
        when(accountRepository.findById(anyLong()))
                .thenReturn(Optional.of(buildAccountData()));
        when(accountRepository.save(any(Account.class)))
                .thenReturn(new Account());
        when(movementRepository.save(any(Movement.class)))
                .thenReturn(movement);
        when(movementMapper.entityToDTO(any(Movement.class)))
                .thenReturn(movementDTO);
        ResponseEntity<MovementDTO> response = movementService.create(movementDTO);
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.OK)).isEqualTo(true);
        assertThat(response.getBody()).isNotNull();
        verify(movementMapper).dtoToEntity(any(MovementDTO.class));
        verify(accountRepository).save(any(Account.class));
        verify(movementRepository).save(any(Movement.class));
        verify(movementMapper).entityToDTO(any(Movement.class));
    }

    @Test
    void givenInValidDataToCreateAnAccountInactive_thenSuccess_400_ok() {
        MovementDTO movementDTO = buildMovementDTOData();
        AccountDTO account = buildAccountDTOData();
        account.setStatus(false);
        movementDTO.setAccount(account);
        Movement movement = buildMovementData();
        Account account1 = buildAccountData();
        account1.setStatus(false);
        movement.setAccount(account1);
        when(movementMapper.dtoToEntity(any(MovementDTO.class)))
                .thenReturn(movement);
        when(accountRepository.findById(anyLong()))
                .thenReturn(Optional.of(account1));
        assertThatThrownBy(() -> movementService.create(movementDTO))
                .isInstanceOf(BadRequest.class)  // Replace with actual exception type
                .hasMessage("No se puede transferir a una cuenta activa:19752115563") // Ensure the exact message matches
                .hasCauseInstanceOf(Throwable.class);
        verify(movementMapper).dtoToEntity(any(MovementDTO.class));
        verify(accountRepository).findById(anyLong());
    }

    @Test
    void givenInValidDataToCreateAnAccountInsufficientFunds_thenSuccess_400_ok() {
        MovementDTO movementDTO = buildMovementDTOData();
        AccountDTO account = buildAccountDTOData();
        account.setInitialBalance(Double.parseDouble("5"));
        movementDTO.setAccount(account);
        Movement movement = buildMovementData();
        Account account1 = buildAccountData();
        account1.setInitialBalance(Double.parseDouble("5"));
        movement.setAccount(account1);
        when(movementMapper.dtoToEntity(any(MovementDTO.class)))
                .thenReturn(movement);
        when(accountRepository.findById(anyLong()))
                .thenReturn(Optional.of(account1));
        assertThatThrownBy(() -> movementService.create(movementDTO))
                .isInstanceOf(BadRequest.class)  // Replace with actual exception type
                .hasMessage("No tiene fondos suficientes, su Saldo es :5.0  y su monto de transacción es: 1000.0") // Ensure the exact message matches
                .hasCauseInstanceOf(Throwable.class);
        verify(movementMapper).dtoToEntity(any(MovementDTO.class));
        verify(accountRepository).findById(anyLong());
    }

    @Test
    void givenInValidDataToCreateAnAccountNotFound_thenSuccess_404_ok() {
        MovementDTO movementDTO = buildMovementDTOData();
        AccountDTO account = buildAccountDTOData();
        movementDTO.setAccount(account);
        Movement movement = buildMovementData();
        Account account1 = buildAccountData();
        movement.setAccount(account1);
        when(movementMapper.dtoToEntity(any(MovementDTO.class)))
                .thenReturn(movement);
        when(accountRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        ResponseEntity<MovementDTO> response = movementService.create(movementDTO);
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)).isEqualTo(true);
        verify(movementMapper).dtoToEntity(any(MovementDTO.class));
        verify(accountRepository).findById(anyLong());
    }

    @Test
    void givenValidDataToGetAllAnAccount_thenSuccess_200_ok() {
        List<Movement> movements = new ArrayList<>();
        MovementDTO movementDTO = buildMovementDTOData();
        Movement movement = buildMovementData();
        movements.add(movement);
        when(accountService.getClientDetails(anyLong()))
                .thenReturn(Optional.of(buildClientDTOData()));
        when(movementRepository.findAll())
                .thenReturn(movements);
        when(movementMapper.entityToDTO(any(Movement.class)))
                .thenReturn(movementDTO);
        ResponseEntity<List<MovementDTO>> response = movementService.getAll();
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.OK)).isEqualTo(true);
        assertThat(response.getBody()).isNotNull();
        verify(movementRepository).findAll();
        verify(movementMapper).entityToDTO(any(Movement.class));
    }

    @Test
    void givenInValidDataToGetAllAnAccount_thenSuccess_400_not_found() {
        when(movementRepository.findAll())
                .thenReturn(new ArrayList<>());
        ResponseEntity<List<MovementDTO>> response = movementService.getAll();
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)).isEqualTo(true);
        verify(movementRepository).findAll();
    }

    @Test
    void givenValidDataToGetByIdAnAccount_thenSuccess_200_ok() {
        MovementDTO movementDTO = buildMovementDTOData();
        Movement movement = buildMovementData();
        when(accountService.getClientDetails(anyLong()))
                .thenReturn(Optional.of(buildClientDTOData()));
        when(movementRepository.findById(anyLong()))
                .thenReturn(Optional.of(movement));
        when(movementMapper.entityToDTO(any(Movement.class)))
                .thenReturn(movementDTO);
        ResponseEntity<MovementDTO> response = movementService.getMovementById(1L);
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.OK)).isEqualTo(true);
        assertThat(response.getBody()).isNotNull();
        verify(movementRepository).findById(anyLong());
        verify(movementMapper).entityToDTO(any(Movement.class));

    }

    @Test
    void givenInValidDataToGetByIdAnAccount_thenSuccess_400_not_found() {
        when(movementRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        ResponseEntity<MovementDTO> response = movementService.getMovementById(1L);
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)).isEqualTo(true);
        verify(movementRepository).findById(anyLong());

    }

    @Test
    void givenValidDataToUpdateAnAccount_thenSuccess_200_ok() {
        MovementDTO movementDTO = buildMovementDTOData();
        Movement movement = buildMovementData();
        when(accountService.getClientDetails(anyLong()))
                .thenReturn(Optional.of(buildClientDTOData()));
        when(movementRepository.findById(anyLong()))
                .thenReturn(Optional.of(movement));
        when(accountRepository.findById(anyLong()))
                .thenReturn(Optional.of(buildAccountData()));
        when(movementRepository.save(any(Movement.class)))
                .thenReturn(movement);
        when(movementMapper.entityToDTO(any(Movement.class)))
                .thenReturn(movementDTO);
        ResponseEntity<MovementDTO> response = movementService.update(1L, movementDTO);
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.OK)).isEqualTo(true);
        assertThat(response.getBody()).isNotNull();
        verify(movementRepository).findById(anyLong());
        verify(accountRepository).findById(anyLong());
        verify(movementRepository).save(any());
        verify(movementMapper).entityToDTO(any(Movement.class));

    }

    @Test
    void givenInValidDataToUpdateAnAccount_thenSuccess_400_not_found() {
        MovementDTO movementDTO = buildMovementDTOData();
        when(movementRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        ResponseEntity<MovementDTO> response = movementService.update(1L, movementDTO);
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)).isEqualTo(true);
        verify(movementRepository).findById(anyLong());

    }
//DELETE
    @Test
    void givenValidDataToDeleteAnAccount_thenSuccess_200_ok() {
        Movement movement = buildMovementData();
        when(movementRepository.findById(anyLong()))
                .thenReturn(Optional.of(movement));
        ResponseEntity<ResponseDTO> response = movementService.delete(1L);
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.OK)).isEqualTo(true);
        assertThat(response.getBody()).isNotNull();
        verify(movementRepository).delete(any());

    }

    @Test
    void givenInValidDataToDeleteAnAccount_thenSuccess_400_not_found() {
        when(movementRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        ResponseEntity<ResponseDTO> response = movementService.delete(1L);
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)).isEqualTo(true);
        verify(movementRepository).findById(anyLong());

    }

}
