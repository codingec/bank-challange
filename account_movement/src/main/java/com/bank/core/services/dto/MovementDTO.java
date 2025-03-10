package com.bank.core.services.dto;

import com.bank.core.util.serialize.AccountTypeSerializer;
import com.bank.core.util.serialize.DateSerializer;
import com.bank.core.util.serialize.MovementTypeSerializer;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovementDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;

    private AccountDTO account;

    @NotEmpty
    @JsonSerialize(using = MovementTypeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Size(min = 2, max= 100, message = "Por favor insertar un movimiento correcto.")
    private String movementType;

    @NotEmpty
    @Size(min = 2, max= 50, message = "Por favor insertar una cuenta correcta a la que se envia.")
    private String receiverAccount;

    @NotEmpty
    @JsonSerialize(using = AccountTypeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Size(min = 2, max= 50, message = "Por favor insertar un tipo de cuenta correcta a la que se envia.")
    private String receiverAccountType;

    private Double transferAmount;


    @JsonSerialize(using = DateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date transferDate;
    private Date createdDate;
    private Date updatedDate;
}
