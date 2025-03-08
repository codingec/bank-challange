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
@Table(name = "movement")
public class Movement implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movement_id", unique = true)
    private Long id;

    @ManyToOne()
    @JoinColumn(name="account_id", nullable=false)
    private Account account;

    @Column(name = "movement_type",length = 50, nullable = false)
    private String movementType;

    @Column(name = "receiver_account", length = 50, nullable = false)
    private String receiverAccount;


    @Column(name = "receiver_account_type", length = 50, nullable = false)
    private String receiverAccountType;

    @Column(name = "transfer_amount")
    private Double transferAmount;

    @Column(name = "transfer_date", length = 5, nullable = false)
    private Date transferDate;

    @Column(name = "created_date", nullable = true)
    private Date createdDate;

    @Column(name = "updated_date", nullable = true)
    private Date updatedDate;
}
