package com.bank.core.services.dto;

import com.bank.core.util.GenderTypeSerializer;
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
public class PersonDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;

    @NotEmpty
    @Size(min = 2, max= 16, message = "Por favor insertar un nombre correcto.")
    private String name;

    @NotEmpty
    @JsonSerialize(using = GenderTypeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Size(min = 2, max= 16, message = "Por favor insertar un genero correcto.")
    private String gender;

    @NotEmpty
    private Integer age;

    @NotEmpty
    @Size(min = 2, max= 15, message = "Por favor insertar una identificación correcta.")
    private String nationalId;

    @NotEmpty
    @Size(min = 2, max= 100, message = "Por favor insertar una dirección correcta.")
    private String address;

    @NotEmpty
    @Size(min = 2, max= 16, message = "Por favor insertar un teléfono correcto.")
    private String telephone;
}
