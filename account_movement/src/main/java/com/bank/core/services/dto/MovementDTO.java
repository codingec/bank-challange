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

    @JsonProperty("cuenta")
    private AccountDTO account;

    @NotEmpty
    @JsonProperty("tipo_movimiento")
    @JsonSerialize(using = MovementTypeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Size(min = 2, max= 100, message = "Por favor insertar un movimiento correcto.")
    private String movementType;

    @NotEmpty
    @JsonProperty("cuenta_receptora")
    @Size(min = 2, max= 50, message = "Por favor insertar una cuenta correcta a la que se envia.")
    private String receiverAccount;

    @NotEmpty
    @JsonProperty("tipo_cuenta_receptora")
    @JsonSerialize(using = AccountTypeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Size(min = 2, max= 50, message = "Por favor insertar un tipo de cuenta correcta a la que se envia.")
    private String receiverAccountType;

    @JsonProperty("valor")
    private Double transferAmount;


    @JsonSerialize(using = DateSerializer.class)
    @JsonProperty("fecha_transferencia")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date transferDate;
    private Date createdDate;
    private Date updatedDate;
}
