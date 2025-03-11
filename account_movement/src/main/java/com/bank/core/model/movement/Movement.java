package com.bank.core.model.movement;

import com.bank.core.model.account.Account;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movimientos")
public class Movement implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movimiento_id", unique = true)
    private Long id;

    @ManyToOne()
    @JoinColumn(name="cuenta_id", nullable=false)
    private Account account;

    @Column(name = "tipo_movimiento",length = 50, nullable = false)
    private String movementType;

    @Column(name = "cuenta_receptora", length = 50, nullable = false)
    private String receiverAccount;


    @Column(name = "tipo_cuenta_receptora", length = 50, nullable = false)
    private String receiverAccountType;

    @Column(name = "valor_transferencia")
    private Double transferAmount;

    @Column(name = "fecha_transferencia", length = 5, nullable = false)
    private Date transferDate;

    @Column(name = "fecha_creacion", nullable = true)
    private Date createdDate;

    @Column(name = "fecha_actualizada", nullable = true)
    private Date updatedDate;
}
