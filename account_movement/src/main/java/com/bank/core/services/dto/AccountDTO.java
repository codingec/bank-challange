package com.bank.core.services.dto;

import com.bank.core.util.serialize.AccountTypeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    private Long clientNationalId;

    @NotEmpty
    @Size(min = 2, max= 20, message = "Por favor insertar una cuenta correcto.")
    private String accountNumber;

    @NotEmpty
    @JsonSerialize(using = AccountTypeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Size(min = 2, max= 20, message = "Por favor insertar una cuenta correcto.")
    private String accountType;

    private Double initialBalance;

    private Boolean status;
}
