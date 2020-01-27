package com.arvoia.sampletask.model;

import com.arvoia.sampletask.utils.CustomDoubleSerializer;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * This class represents a vehicle.
 *
 * @author Tim Foster
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "brand",
    "category",
    "basePrice",
})
@JsonIgnoreProperties(value = {"color", "doors"})
public class Vehicle implements Comparable<Vehicle> {
  @JsonProperty("brand")
  private String brand;

  @JsonProperty("category")
  private String category;

  @JsonProperty("basePrice")
  private Double basePrice;

  /**
   * Creates a vehicle instance with the {@code brand}, {@code category} and {@code basePrice}.
   *
   * @param brand     the vehicle's brand or manufacturer.
   * @param category  the vehicle's class (for example "Luxury" or "Economy").
   * @param basePrice the vehicle's base price.
   */
  @JsonCreator
  public Vehicle(@JsonProperty("brand") String brand, @JsonProperty("category") String category, @JsonProperty("basePrice") Double basePrice) {
    super();
    this.brand = brand;
    this.category = category;
    this.basePrice = basePrice;
  }

  /**
   * @return the vehicle's brand.
   */
  @JsonProperty("brand")
  public String getBrand() {
    return brand;
  }

  /**
   * @param brand brand or manufacturer to assign to this vehicle.
   */
  @JsonProperty("brand")
  public void setBrand(String brand) {
    this.brand = brand;
  }

  /**
   * @return the vehicle's category.
   */
  @JsonProperty("category")
  public String getCategory() {
    return category;
  }

  /**
   * @param category the category to assign to this vehicle (for example "Luxury" or "Economy").
   */
  @JsonProperty("category")
  public void setCategory(String category) {
    this.category = category;
  }

  /**
   * @return the vehicle's base price.
   */
  @JsonProperty("basePrice")
  @JsonSerialize(using = CustomDoubleSerializer.class)
  public Double getBasePrice() {
    return basePrice;
  }

  /**
   * @param basePrice the base price to assign to this vehicle.
   */
  @JsonProperty("basePrice")
  public void setBasePrice(Double basePrice) {
    this.basePrice = basePrice;
  }

  /**
   * Compares the {@code basePrice} of two vehicles.
   *
   * @param vehicle the vehicle to compare this vehicle's {@code basePrice} against.
   * @return an integer {@code 0} if {@code vehicle}'s {@code basePrice} is equal to this vehicle's {@code basePrice}.
   * {@code -1} if this vehicle's {@code basePrice} is less than {@code vehicle}'s {@code basePrice}, and
   * {@code 1} if this vehicle's {@code basePrice} is greater than {@code vehicle}'s {@code basePrice}.
   */
  @Override
  public int compareTo(Vehicle vehicle) {
    return this.basePrice.compareTo(vehicle.basePrice);
  }
}