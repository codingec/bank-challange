package com.bank.core.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

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
