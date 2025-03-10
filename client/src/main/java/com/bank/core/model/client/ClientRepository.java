package com.bank.core.model.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT client FROM Client client WHERE client.person.nationalId=:identificacion")
    Optional<Client> findByClientNationalId(@Param("identificacion")  String identificacion);
}
