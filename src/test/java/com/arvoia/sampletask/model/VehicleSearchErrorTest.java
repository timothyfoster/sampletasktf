package com.arvoia.sampletask.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VehicleSearchErrorTest {

  VehicleSearchError vehicleSearchError = new VehicleSearchError(500, "system failure");

  @Test
  public void testGetCodeIsAlways1() {
    assertEquals(1, vehicleSearchError.getCode());
  }

  @Test
  public void testGetDescriptionIsAlwaysTheSame() {
    assertEquals("vendor service not available", vehicleSearchError.getDescription());
  }
}