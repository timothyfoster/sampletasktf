package com.arvoia.sampletask.model;

import com.fasterxml.jackson.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Transforms the data received from the car rental company's search interface into an acceptable format.
 *
 * @author Tim Foster
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "result",
    "error",
    "offer",
    "timestamp"
})
public class VehicleSearchResult {
  @JsonProperty("result")
  private String result;

  @JsonProperty("error")
  private VehicleSearchError error;

  @JsonProperty("offer")
  private VehicleOffer offer;

  @JsonProperty("timestamp")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss.SSS")
  private Date timestamp;

  /**
   * Will initialise an instance of this class with the provided {@code error} and {@code vehicles} list.
   *
   * <p>If the error value is present, the result is set to "error" and the timestamp nullified in order to match the
   * provided specification.</p>
   *
   * @param error    an instance of {@link VehicleSearchError} containing the error code and description if it exists.
   * @param vehicles the list of available vehicles returned from the search interface.
   */
  @JsonCreator
  public VehicleSearchResult(@JsonProperty("error") VehicleSearchError error, @JsonProperty("offer") List<Vehicle> vehicles) {
    super();
    offer = new VehicleOffer(vehicles);
    setError(error);
  }

  /**
   * @return an instance of {@link VehicleSearchError} containing the error code and description or null if there were
   * no errors.
   */
  @JsonProperty("error")
  public VehicleSearchError getError() {
    return error;
  }

  /**
   * Will set result is to "error" and nullify the timestamp if an error is present.
   *
   * @param error an instance of {@link VehicleSearchError} containing the error code and description.
   */
  @JsonProperty("error")
  public void setError(VehicleSearchError error) {
    this.error = error;
    if (this.error != null) {
      result = "error";
      timestamp = null;
    } else {
      timestamp = new Date();
      result = "success";
    }
  }

  /**
   * @return the search status, can be either "success" or "error".
   */
  @JsonProperty("result")
  public String getResult() {
    return result;
  }

  /**
   * @return the datetime of when the search results were received.
   */
  @JsonProperty("timestamp")
  public Date getTimestamp() {
    return timestamp;
  }

  /**
   * @return the list of available vehicles or null if an error occurred.
   */
  @JsonProperty("offer")
  public VehicleOffer getOffer() {
    return (this.error == null) ? offer : null;
  }

  /**
   * @param vehicles the list of vehicles received from the search interface.
   */
  @JsonProperty("vehicles")
  public void setOffer(List<Vehicle> vehicles) {
    this.offer = new VehicleOffer(vehicles);
  }
}