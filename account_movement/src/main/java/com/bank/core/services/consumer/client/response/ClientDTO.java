package com.bank.core.services.consumer.client.response;

import com.bank.core.util.serialize.BooleanTypeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;



@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    private PersonDTO persona;


    private String name;

    private Integer age;

    private String nationalId;


    @JsonSerialize(using = BooleanTypeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Boolean status;


    @JsonProperty("persona")
    public void setPersona(PersonDTO persona) {
        //this.persona = persona;
        this.name = persona.getName();
        this.age = persona.getAge();
        this.nationalId = persona.getNationalId();
    }

    @JsonProperty("estado")
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @JsonProperty("nombre")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("edad")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @JsonProperty("identificacion")
    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }
}
