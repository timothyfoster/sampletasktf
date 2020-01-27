package com.arvoia.sampletask.model;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonValue;

public class AvailResponse {
  private String result;

  /**
   * Standard getter method for retrieving the response body. This method Will format the output to produce nicely
   * structured JSON.
   *
   * @return nicely structured JSON as a String.
   */
  @JsonValue
  @JsonRawValue
  public String getResult() {
    return result;
  }

  /**
   * Standard setter method for setting the response body.
   *
   * @param result is the JSON string to save to the response body.
   */
  public void setResult(String result) {
    this.result = result;
  }
}
