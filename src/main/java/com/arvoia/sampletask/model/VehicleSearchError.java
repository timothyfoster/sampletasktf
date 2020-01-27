package com.arvoia.sampletask.model;

import com.fasterxml.jackson.annotation.*;

/**
 * Represents the error state for the car rental company's search interface.
 *
 * <p>This class always returns a constant value for {@code code} and {@code description}. In order to allow this class
 * to be easily extended, the raw values of {@code actualCode} and {@code actualDescription} are maintained. This could
 * be useful if a form of logging needed to be implemented in the future.</p>
 *
 * @author Tim Foster
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "code",
    "description"
})
public class VehicleSearchError {
  @JsonProperty("code")
  private int code = 1;

  @JsonIgnore
  private int actualCode;

  @JsonProperty("description")
  private String description = "vendor service not available";

  @JsonIgnore
  private String actualDescription;

  /**
   * Will initialise an instance of this class with the provided {@code code} and {@code description}.
   *
   * @param code        the error code received from the search interface.
   * @param description the error description received from the search interface.
   */
  @JsonCreator
  public VehicleSearchError(@JsonProperty("code") int code, @JsonProperty("description") String description) {
    super();
    this.actualCode = code;
    this.actualDescription = description;
  }

  /**
   * @return the error code.
   */
  @JsonProperty("code")
  public int getCode() {
    return code;
  }

  /**
   * @param code the actual error code received from the search interface.
   */
  @JsonProperty("code")
  public void setActualCode(int code) {
    this.actualCode = code;
  }

  /**
   * @return the description of the error.
   */
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  /**
   * @param description the actual description of the error received from the search interface.
   */
  @JsonProperty("description")
  public void setActualDescription(String description) {
    this.actualDescription = description;
  }
}