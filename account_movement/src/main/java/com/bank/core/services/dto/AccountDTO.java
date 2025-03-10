package com.bank.core.services.dto;

import com.bank.core.services.consumer.client.response.ClientDTO;
import com.bank.core.util.serialize.AccountTypeSerializer;
import com.bank.core.util.serialize.BooleanTypeSerializer;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonProperty("identificacion_cliente")
    private Long clientNationalId;

    private ClientDTO cliente;

    @NotEmpty
    @JsonProperty("numero_cuenta")
    @Size(min = 2, max= 20, message = "Por favor insertar una cuenta correcto.")
    private String accountNumber;

    @NotEmpty
    @JsonProperty("tipo_cuenta")
    @JsonSerialize(using = AccountTypeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Size(min = 2, max= 20, message = "Por favor insertar una cuenta correcto.")
    private String accountType;

    @JsonProperty("saldo_inicial")
    private Double initialBalance;

    @JsonProperty("estado")
    @JsonSerialize(using = BooleanTypeSerializer.class)
    private Boolean status;
}
