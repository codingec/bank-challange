package com.bank.core.model.client;

import com.bank.core.model.person.Person;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
@JsonIdentityInfo(generator =
        ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Client implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", unique = true)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="person_id", nullable=false)
    private Person person;

    @Column(name = "password", length = 30, nullable = false)
    private String password;

    @Column(name = "status")
    private Boolean status;
}
