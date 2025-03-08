package com.bank.core.services.movement.impl;

import static com.bank.core.util.DateUtil.createAValidDate;

import com.bank.core.controller.exception.BadRequest;
import com.bank.core.model.account.Account;
import com.bank.core.model.account.AccountRepository;
import com.bank.core.model.movement.Movement;
import com.bank.core.model.movement.MovementRepository;
import com.bank.core.services.dto.response.ResponseDTO;
import com.bank.core.services.movement.MovementService;
import com.bank.core.services.dto.MovementDTO;
import com.bank.core.services.mappers.MovementMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@NoArgsConstructor()
@AllArgsConstructor
@Service
public class MovementServiceImpl implements MovementService {
    @Autowired
    private MovementRepository movementRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private MovementMapper movementMapper;

    @Override
    public ResponseEntity<MovementDTO> create(MovementDTO movementDTO)  {
        Movement movement = this.movementMapper.dtoToEntity(movementDTO);
        log.info("Processing Service create for AccountNumber={}, serviceMethod={}",
                movementDTO.getAccount().getAccountNumber(), "create");
        Optional<Account> account= this.accountRepository.findById(movement.getAccount().getId());
        if(account.isPresent()) {
            Account accountToUpdate = account.get();
            if(accountToUpdate.getInitialBalance()
                    >= movementDTO.getTransferAmount()
            && accountToUpdate.getStatus().equals(true)){
                accountToUpdate.setInitialBalance(accountToUpdate.getInitialBalance() -
                movementDTO.getTransferAmount());
                this.accountRepository.save(accountToUpdate);
                movement.setAccount(accountToUpdate);
                movement.setCreatedDate(movement.getTransferDate());
                Optional<Movement> movement1= Optional.ofNullable(this.movementRepository.save(movement));
                log.info("Valid request for AccountNumber={}, serviceMethod={}",
                        movementDTO.getAccount().getAccountNumber(), "create");
                return new ResponseEntity<>( this.movementMapper.entityToDTO(
                        movement1.get()), HttpStatus.OK);
            } else if(accountToUpdate.getStatus().equals(false)){
                log.info("Not Valid request, Account disabled AccountNumber={}, serviceMethod={}",
                        movementDTO.getAccount().getAccountNumber(), "create");
                String message = "You cannot transfer to an enactive account:"+ accountToUpdate.getAccountNumber();
                throw new BadRequest(message, new Throwable("You cannot transfer to an enactive account"));
            }else {
                log.info("Not Valid request, Account insufficient funds AccountNumber={}, serviceMethod={}",
                        movementDTO.getAccount().getAccountNumber(), "create");
                String message = "You have insufficient funds, your Balance is :"+ accountToUpdate.getInitialBalance()
                        +"  and your transaction is: " +movementDTO.getTransferAmount();

                throw new BadRequest(message, new Throwable("You have insufficient funds"));
            }
        }
        log.info("Bad request, AccountNumber={}, serviceMethod={}",
                movementDTO.getAccount().getAccountNumber(), "create");
            return new ResponseEntity<>( new MovementDTO(), HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<List<MovementDTO>> getAll() {
        List<Movement> movementList = this.movementRepository.findAll();
        log.info("Processing Service get all for, serviceMethod={}",  "getAll");
        if(!movementList.isEmpty()){
            log.info("Valid request for get all, serviceMethod={}",  "getAll");
            return new ResponseEntity<>(movementList.stream()
                    .map(movementMapper::entityToDTO)
                    .toList(), HttpStatus.OK);
        }else {
            log.info("No contend for, serviceMethod={}",  "getAll");
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<MovementDTO> getMovementById(Long id) {
        Optional<Movement> movement = this.movementRepository.findById(id);
        log.info("Processing Service for get movement by Id={}, serviceMethod={}",  id,"getMovementById");
        if(movement.isPresent()){
            log.info("Valid Request for get movement by Id={}, serviceMethod={}",  id,"getMovementById");
            return new ResponseEntity<>(this.movementMapper
                    .entityToDTO(movement.get()), HttpStatus.OK);
        }else{
            log.info("No contend for, Id={}, serviceMethod={}",  id,"getMovementById");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<MovementDTO> update(Long id, MovementDTO movementDTO) {
        Optional<Movement> update  = this.movementRepository.findById(id);
        Optional<Account> account  = this.accountRepository.findById(movementDTO.getAccount().getId());
        log.info("Processing Service update for, Id={}, serviceMethod={}",  id,"update");
        if(update.isPresent() && account.isPresent()){
            Movement dataUpdated = buildMovement(movementDTO, update.get().getId(), account.get());
            Optional <Movement> updatedMovement = Optional.ofNullable(this.movementRepository.save(dataUpdated));
            log.info("Valid update Request for, id={id}, serviceMethod={}",  id,"update");
            return new ResponseEntity<>(this.movementMapper
                    .entityToDTO(updatedMovement.get()),HttpStatus.OK);

        }else {
            log.info("No contend for update, Id={}, serviceMethod={}",  id,"update");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    private static Movement buildMovement(MovementDTO movementDTO, Long movementId, Account account) {
        return Movement.builder()
                .id(movementId)
                .account(account)
                .movementType(movementDTO.getMovementType())
                .receiverAccount(movementDTO.getReceiverAccount())
                .receiverAccountType(movementDTO.getReceiverAccountType())
                .transferAmount(movementDTO.getTransferAmount())
                .transferDate(movementDTO.getTransferDate())
                .updatedDate(createAValidDate(new Date()))
                .build();
    }

    @Override
    public ResponseEntity<ResponseDTO> delete(Long id) {
        Optional<Movement> movement = this.movementRepository.findById(id);
        log.info("Processing Service delete for, Id={}, serviceMethod={}",  id,"delete");
        if(movement.isPresent()){
            String accountNumber = movement.get().getAccount().getAccountNumber();
            this.movementRepository.delete(movement.get());
            log.info("Valid delete request for, id={id}, serviceMethod={}",  id,"delete");
            return new ResponseEntity<>(ResponseDTO.builder()
                    .message("Movements for account:"+accountNumber+", transaction id "+id+" was deleted successfully")
                    .build(), HttpStatus.OK);
        }else {
            log.info("No contend for delete, Id={}, serviceMethod={}",  id,"update");
            return new ResponseEntity<>(ResponseDTO.builder()
                    .message("Movements does not exist")
                    .build(), HttpStatus.NO_CONTENT);
        }
    }
}
