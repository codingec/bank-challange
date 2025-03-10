package com.bank.core.model.account;

import com.bank.core.util.serialize.AccountTypeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
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
@Table(name = "cuenta")
public class Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuenta_id", unique = true)
    private Long id;

    @Column(name = "identificacion_cliente", nullable = false)
    private Long clientNationalId;

    @Column(name = "numero_cuenta", length = 75, nullable = false)
    private String accountNumber;

    @JsonSerialize(using = AccountTypeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(name = "tipo_cuenta", length = 50, nullable = false)
    private String accountType;

    @Column(name = "saldo_inicial")
    private Double initialBalance;

    @Column(name = "estado")
    private Boolean status;
}
