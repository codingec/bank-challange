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

    @JsonProperty("estado")
    @JsonSerialize(using = BooleanTypeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Boolean status;
}
