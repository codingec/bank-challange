package com.bank.core.util;

import com.bank.core.controller.exception.BadRequest;
import com.bank.core.util.serialize.AccountTypeSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class AccountTypeSerializerTest {
    @Mock
    private JsonGenerator jsonGenerator;

    @Mock
    private SerializerProvider serializerProvider;

    private AccountTypeSerializer accountTypeSerializer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        accountTypeSerializer = new AccountTypeSerializer();
    }

    @Test
    public void testSerialize_Ahorro() throws IOException {
        String value = "Ahorro";
        accountTypeSerializer.serialize(value, jsonGenerator, serializerProvider);

        verify(jsonGenerator).writeString("Ahorro");
        verifyNoMoreInteractions(jsonGenerator, serializerProvider);
    }

    @Test
    public void testSerialize_Corriente() throws IOException {
        String value = "Corriente";
        accountTypeSerializer.serialize(value, jsonGenerator, serializerProvider);

        verify(jsonGenerator).writeString("Corriente");
        verifyNoMoreInteractions(jsonGenerator, serializerProvider);
    }

    @Test
    public void testSerialize_EmptyValue() {
        String value = "";
        Assertions.assertThrows(BadRequest.class, () -> {
            accountTypeSerializer.serialize(value, jsonGenerator, serializerProvider);
        });
    }

    @Test
    public void testSerialize_InvalidValue() {
        String value = "Invalid";
        Assertions.assertThrows(BadRequest.class, () -> {
            accountTypeSerializer.serialize(value, jsonGenerator, serializerProvider);
        });
    }
}
