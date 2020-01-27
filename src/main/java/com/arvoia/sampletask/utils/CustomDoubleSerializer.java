package com.arvoia.sampletask.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Provides a custom Double serializer for Jackson to use to format any Double's to 1 decimal place.
 *
 * @author Tim Foster
 */
public class CustomDoubleSerializer extends JsonSerializer<Double> {
  /**
   * Implement custom serialize method which will round Double's to 1 decimal place.
   */
  @Override
  public void serialize(Double value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(1, RoundingMode.HALF_UP);
    jgen.writeNumber(bd.doubleValue());
  }
}