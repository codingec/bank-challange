package com.bank.core.model.movement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

}
