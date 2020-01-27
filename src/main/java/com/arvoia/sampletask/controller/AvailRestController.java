package com.arvoia.sampletask.controller;

import com.arvoia.sampletask.model.AvailResponse;
import com.arvoia.sampletask.model.VehicleSearchResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Configuration
@RestController
public class AvailRestController {

  @Autowired
  private RestTemplate restTemplate;

  @Value("${vendor.url}")
  private String vendorUrl;

  @GetMapping(value = "/avail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody
  AvailResponse getAvailabilities() throws IOException {
    AvailResponse ret = new AvailResponse();
    String searchResult = getSearchResults();
    ObjectMapper objectMapper = new ObjectMapper();
    VehicleSearchResult jsonSearchResult = objectMapper.readValue(searchResult, VehicleSearchResult.class);
    String resultAsString = objectMapper.writeValueAsString(jsonSearchResult);
    ret.setResult(resultAsString);
    return ret;
  }

  private String getSearchResults() {
    return restTemplate.getForEntity(vendorUrl, String.class).getBody();
  }
}
