package com.bank.core.util.serialize;

import com.bank.core.controller.exception.BadRequest;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;


public class AccountTypeSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator jgen, SerializerProvider provider) throws IOException {

            String result = "";
            if (value.isBlank()) {
                throw new BadRequest("Account Type cannot be empty or null",
                        new Throwable("Unexpected value: "));
            } else {
                result = switch (value) {
                    case "Ahorro" -> "Ahorro";
                    case "Corriente" -> "Corriente";
                    default -> throw new BadRequest("Unexpected value: " + value,
                            new Throwable("Unexpected value: "));
                };
            }

            jgen.writeString(result);

    }
}
