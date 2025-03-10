package com.bank.core.model.person;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serial;
import java.io.Serializable;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "persona")
public class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persona_id", unique = true)
    private Long id;

    @Column(name = "nombre", length = 50, nullable = false)
    private String name;

    @Column(name = "genero", nullable = false)
    private String gender;

    @Column(name = "edad", nullable = false)
    private Integer age;

    @Column(name = "identificacion", length = 15, nullable = false,  unique = true)
    private String nationalId;

    @Column(name = "direccion", length = 100, nullable = false)
    private String address;

    @Column(name = "telefono", length = 20, nullable = false)
    private String telephone;
}
