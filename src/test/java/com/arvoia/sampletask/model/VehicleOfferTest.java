package com.arvoia.sampletask.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class VehicleOfferTest {

  private Vehicle v1 = new Vehicle("Audi", "Luxury", 700.0);
  private Vehicle v2 = new Vehicle("Volvo", "Economy", 500.0);
  private Vehicle v3 = new Vehicle("Toyota", "Economy", 300.0);
  private List<Vehicle> unsortedVehicleList = Arrays.asList(v1, v2, v3);
  private List<Vehicle> sortedVehicleList = Arrays.asList(v3, v2, v1);

  @Test
  public void testGetVehiclesAreSorted() {
    VehicleOffer vehicleOffer = new VehicleOffer(unsortedVehicleList);
    assertEquals(sortedVehicleList, vehicleOffer.getVehicles());
  }

  @Test
  public void testSetVehiclesSortsList() {
    VehicleOffer vehicleOffer = new VehicleOffer(new ArrayList<>());
    vehicleOffer.setVehicles(unsortedVehicleList);
    assertEquals(sortedVehicleList, vehicleOffer.getVehicles());
  }

  @Test
  public void testGetAvailableVehiclesIs0() {
    VehicleOffer vehicleOffer = new VehicleOffer(new ArrayList<>());
    assertEquals(0, vehicleOffer.getAvailable());
  }

  @Test
  public void testGetAvailableVehiclesIs3() {
    VehicleOffer vehicleOffer = new VehicleOffer(sortedVehicleList);
    assertEquals(3, vehicleOffer.getAvailable());
  }
}