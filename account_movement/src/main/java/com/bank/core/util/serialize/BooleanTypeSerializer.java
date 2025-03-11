package com.bank.core.util.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class BooleanTypeSerializer extends JsonSerializer<Boolean> {

    @Override
    public void serialize(Boolean value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {

        Boolean result = false;
        if(value.equals(true)){
            result = true;
        }else if(value.equals(false)) {
            result = false;
        }
        jgen.writeString(String.valueOf(result));
    }
}
