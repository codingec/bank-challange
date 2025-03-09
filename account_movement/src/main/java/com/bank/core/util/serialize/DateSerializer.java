package com.bank.core.util.serialize;

import static com.bank.core.util.DateUtil.createAValidDate;
import static com.bank.core.util.DateUtil.dateToString;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Date;

@Slf4j
public class DateSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {
            jgen.writeString(dateToString(createAValidDate(value)));
    }
}
