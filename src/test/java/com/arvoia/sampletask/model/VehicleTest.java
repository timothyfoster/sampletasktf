package com.arvoia.sampletask.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VehicleTest {

  private Vehicle v1 = new Vehicle("Audi", "Luxury", 700.0);
  private Vehicle v2 = new Vehicle("Volvo", "Economy", 500.0);
  private Vehicle v3 = new Vehicle("Toyota", "Economy", 500.0);
  private Vehicle v4 = new Vehicle("Volvo", "Luxury", 915.216419654914);

  @Test
  public void testVehicleConstructorWithArguments() {
    Vehicle v = new Vehicle("Audi", "Economy", 670.0);
    assertEquals("Audi", v.getBrand());
    assertEquals("Economy", v.getCategory());
    assertEquals((Double) 670.0, v.getBasePrice());
  }

  @Test
  public void testCompareToGreaterThan() {
    assertEquals(1, v1.compareTo(v2));
  }

  @Test
  public void testCompareToLessThan() {
    assertEquals(-1, v2.compareTo(v1));
  }

  @Test
  public void testCompareToEqual() {
    assertEquals(0, v2.compareTo(v3));
  }
}