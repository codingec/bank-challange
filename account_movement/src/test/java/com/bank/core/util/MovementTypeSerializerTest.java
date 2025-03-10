package com.bank.core.util;

import com.bank.core.controller.exception.BadRequest;
import com.bank.core.util.serialize.MovementTypeSerializer;
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
class MovementTypeSerializerTest {

    private MovementTypeSerializer serializer;

    @Mock
    private JsonGenerator jsonGenerator;

    @Mock
    private SerializerProvider serializerProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        serializer = new MovementTypeSerializer();
    }

    @Test
    void testSerialize_Transferencia() throws IOException {
        String value = "Transferencia";
        serializer.serialize(value, jsonGenerator, serializerProvider);
        Mockito.verify(jsonGenerator).writeString("Transferencia");
    }

    @Test
    void testSerialize_Deposito() throws IOException {
        String value = "Deposito";
        serializer.serialize(value, jsonGenerator, serializerProvider);
        Mockito.verify(jsonGenerator).writeString("Deposito");
    }

    @Test
    void testSerialize_EmptyValue() {
        String value = "";
        Assertions.assertThrows(BadRequest.class, () -> {
            serializer.serialize(value, jsonGenerator, serializerProvider);
        }) ;
    }

    @Test
    void testSerialize_InvalidValue() {
        String value = "InvalidValue";
        Assertions.assertThrows(BadRequest.class, () -> {
            serializer.serialize(value, jsonGenerator, serializerProvider);
        }) ;
    }
}
