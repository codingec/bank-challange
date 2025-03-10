package com.bank.core.util;

import com.bank.core.util.serialize.GenderTypeSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class GenderTypeSerializerTest {
    private GenderTypeSerializer genderTypeSerializer;

    @Mock
    private JsonGenerator jsonGenerator;

    @Mock
    private SerializerProvider serializerProvider;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        genderTypeSerializer = new GenderTypeSerializer();
    }

    @Test
    public void testSerialize_Masculino() throws IOException {
        String value = "Masculino";
        genderTypeSerializer.serialize(value, jsonGenerator, serializerProvider);

        Mockito.verify(jsonGenerator).writeString("Masculino");
        Mockito.verifyNoMoreInteractions(jsonGenerator, serializerProvider) ;
    }

    @Test
    public void testSerialize_Femenino() throws IOException {
        String value = "Femenino";
        genderTypeSerializer.serialize(value, jsonGenerator, serializerProvider);

        Mockito.verify(jsonGenerator).writeString("Femenino");
        Mockito.verifyNoMoreInteractions(jsonGenerator, serializerProvider);
    }

    @Test
    public void testSerialize_EmptyValue() {
        String value = "";
        Assertions.assertThrows(RuntimeException.class, () -> {
            genderTypeSerializer.serialize(value, jsonGenerator, serializerProvider);
        });
    }



}
