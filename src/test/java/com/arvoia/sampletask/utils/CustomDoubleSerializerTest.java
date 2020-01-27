package com.arvoia.sampletask.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import static org.junit.Assert.assertEquals;

public class CustomDoubleSerializerTest {

  @Test
  public void testSerializeTo1DecimalPlace() throws IOException {
    Writer jsonWriter = new StringWriter();
    JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
    SerializerProvider serializerProvider = new ObjectMapper().getSerializerProvider();

    new CustomDoubleSerializer().serialize(150.159565, jsonGenerator, serializerProvider);
    new CustomDoubleSerializer().serialize(150.999999, jsonGenerator, serializerProvider);
    new CustomDoubleSerializer().serialize(150.02345, jsonGenerator, serializerProvider);
    jsonGenerator.flush();

    assertEquals("150.2 151.0 150.0", jsonWriter.toString());
  }
}