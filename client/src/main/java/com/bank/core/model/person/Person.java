package com.bank.core.model.person;

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
@Table(name = "person")
@JsonIdentityInfo(generator =
        ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", unique = true)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "gender", length = 15, nullable = false)
    private String gender;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "nationalId", length = 15, nullable = false,  unique = true)
    private String nationalId;

    @Column(name = "address", length = 100, nullable = false)
    private String address;

    @Column(name = "telephone", length = 20, nullable = false)
    private String telephone;
}
