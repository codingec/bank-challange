package com.bank.core.util.serialize;

import com.bank.core.controller.exception.BadRequest;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class MovementTypeSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {
        String result = "";
        if(value.isBlank()){
            throw new BadRequest("Movement Type cannot be empty or null",
                    new Throwable("Movement Type cannot be empty or null "));
        }else {
            result =  switch(value) {
             case "Transferencia" ->   "Transferencia";
             case "Deposito" ->   "Deposito";
              default -> throw new BadRequest("Unexpected value: " + value,
                      new Throwable("Unexpected value: "));
          };
        }
            jgen.writeString(result);
    }
}
