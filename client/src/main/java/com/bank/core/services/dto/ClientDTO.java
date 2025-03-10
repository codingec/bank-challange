package com.bank.core.services.dto;

import com.bank.core.util.GenderTypeSerializer;
import com.bank.core.util.serialize.BooleanTypeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

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
public class ClientDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;

    private PersonDTO persona;

    @NotEmpty
    @JsonProperty("contraseña")
    @Size(min = 2, max= 16, message = "Por favor insertar una contraseña correcta.")
    private String password;

    @JsonProperty("estado")
    @JsonSerialize(using = BooleanTypeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Boolean status;
}
