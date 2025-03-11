package com.bank.core.util;

import com.bank.core.util.serialize.BooleanTypeSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class BooleanTypeSerializerTest {
    @Mock
    private JsonGenerator jsonGenerator;

    @Mock
    private SerializerProvider serializerProvider;

    private BooleanTypeSerializer booleanTypeSerializer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        booleanTypeSerializer = new BooleanTypeSerializer();
    }

    @Test
    public void testSerializeTrue() throws IOException {
        booleanTypeSerializer.serialize(true, jsonGenerator, serializerProvider);
        Mockito.verify(jsonGenerator).writeString("true");
    }

    @Test
    public void testSerializeFalse() throws IOException {
        booleanTypeSerializer.serialize(false, jsonGenerator, serializerProvider);
        Mockito.verify(jsonGenerator).writeString("false");
    }
}
