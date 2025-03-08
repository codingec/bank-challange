package com.bank.core.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class GenderTypeSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {

        String result = "";
        if(value.isBlank() || value.isEmpty() || value.equals(null)){
            throw new RuntimeException("Gender be empty or null");
        }else {
            result =  switch(value) {
             case "Masculino" ->   "Masculino";
             case "Femenino" ->   "Femenino";
              default -> throw new RuntimeException("Unexpected value: " + value);
          };
        }
        jgen.writeString(result);
    }
}
