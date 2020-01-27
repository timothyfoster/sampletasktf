package com.arvoia.sampletask.model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class VehicleSearchResultTest {

  private VehicleSearchError error = new VehicleSearchError(2, "Test Error");

  @Test
  public void testResultIsErrorIfErrorIsPresent() {
    VehicleSearchResult vehicleSearchResult = new VehicleSearchResult(error, null);
    assertEquals("error", vehicleSearchResult.getResult());
  }

  @Test
  public void testResultIsSuccessIfErrorNotPresent() {
    VehicleSearchResult vehicleSearchResult = new VehicleSearchResult(null, null);
    assertEquals("success", vehicleSearchResult.getResult());
  }

  @Test
  public void testTimestampIsNullIfErrorPresent() {
    VehicleSearchResult vehicleSearchResult = new VehicleSearchResult(error, null);
    assertNull(vehicleSearchResult.getTimestamp());
  }

  @Test
  public void testTimestampExistsIfErrorNotPresent() {
    VehicleSearchResult vehicleSearchResult = new VehicleSearchResult(null, null);
    assertEquals(Date.class, vehicleSearchResult.getTimestamp().getClass());
  }
}