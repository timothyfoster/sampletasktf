package com.arvoia.sampletask.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the list of available vehicles sorted in ascending order based on each vehicle's
 * {@code basePrice}.
 *
 * @author Tim Foster
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "available",
    "vehicles"
})
public class VehicleOffer {
  @JsonProperty("available")
  private int available = 0;

  @JsonProperty("vehicles")
  private List<Vehicle> vehicles = new ArrayList<>();

  /**
   * Will initialise an instance of this class with the vehicle list sorted according to {@code basePrice} and will
   * calculate the number of available vehicles.
   *
   * @param vehicles the list of available vehicles.
   */
  @JsonCreator
  public VehicleOffer(@JsonProperty("vehicles") List<Vehicle> vehicles) {
    super();
    setVehicles(vehicles);
  }

  /**
   * @return the list of available vehicles.
   */
  @JsonProperty("vehicles")
  public List<Vehicle> getVehicles() {
    return vehicles;
  }

  /**
   * Setter for initialising the list of vehicles. This method will sort the list of vehicles in ascending order based
   * on {@code basePrice} and then recalculate the number of available vehicles.
   *
   * @param vehicles the list of available vehicles.
   */
  @JsonProperty("vehicles")
  public void setVehicles(List<Vehicle> vehicles) {
    if (vehicles != null) {
      vehicles.sort(Vehicle::compareTo);
    }
    this.vehicles = vehicles;
    this.available = getTotalAvailableVehicles(vehicles);
  }

  /**
   * @return the number of available vehicles
   */
  @JsonProperty("available")
  public int getAvailable() {
    return available;
  }

  /**
   * This method will calculate the number of available vehicles in the provided list.
   *
   * @param vehicles the list of available vehicles.
   * @return the number of available vehicles.
   */
  private int getTotalAvailableVehicles(List<Vehicle> vehicles) {
    return (vehicles != null) ? vehicles.size() : 0;
  }
}
