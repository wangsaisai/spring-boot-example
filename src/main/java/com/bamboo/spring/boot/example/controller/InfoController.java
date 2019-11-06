package com.bamboo.spring.boot.example.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.InfoProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class InfoController {

  @Autowired
  private BuildProperties buildProperties;

  @RequestMapping(value = "/health", method = RequestMethod.GET)
  public int health() {
    return 1;
  }

  @RequestMapping(value = "/version", method = RequestMethod.GET)
  public Map<String, String> version() {
    return Collections.singletonMap("version", buildProperties.getVersion());
  }


  @RequestMapping(value = "/build", method = RequestMethod.GET)
  public Map<String, String> build() {
    Map<String, String> buildMap = new HashMap<>();
    for (InfoProperties.Entry entry : buildProperties) {
      buildMap.put(entry.getKey(), entry.getValue());
    }
    return buildMap;
  }

}
