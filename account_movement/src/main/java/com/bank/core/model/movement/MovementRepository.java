package com.bank.core.model.movement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

    @Query("SELECT mv FROM Movement mv WHERE mv.transferDate BETWEEN :startDate AND :endDate")
    List<Movement> findByStartDateAndEndDate(@Param("startDate") Date startDate,
                                             @Param("endDate") Date endDate);
}
