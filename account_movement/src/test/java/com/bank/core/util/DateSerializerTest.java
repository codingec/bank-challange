package com.bank.core.util;

import com.bank.core.controller.exception.BadRequest;
import com.bank.core.util.serialize.DateSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
class DateSerializerTest {

    private DateSerializer dateSerializer;

    @Mock
    private JsonGenerator jsonGenerator;

    @Mock
    private SerializerProvider serializerProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dateSerializer = new DateSerializer();
    }
    @Test
    void testSerializeWithValidDate() throws IOException, ParseException {
        String dateString = "2023-10-01";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateString);

        dateSerializer.serialize(date, jsonGenerator, serializerProvider);

        Mockito.verify(jsonGenerator, Mockito.times(1)).writeString(ArgumentMatchers.anyString());
    }


    @Test
    void testSerializeWithNullDate() {
        Date nullDate = null;

        Assertions.assertThrows(NullPointerException.class, () -> {
            dateSerializer.serialize(nullDate, jsonGenerator, serializerProvider);
        });
    }
}
