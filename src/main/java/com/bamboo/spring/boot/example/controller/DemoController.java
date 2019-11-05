package com.bamboo.spring.boot.example.controller;

import com.bamboo.spring.boot.example.entity.Demo;
import com.bamboo.spring.boot.example.model.DemoSearchInfo;
import com.bamboo.spring.boot.example.model.DemoType;
import com.bamboo.spring.boot.example.service.DemoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/spring-boot-example/demo")
public class DemoController {

  @Autowired
  private DemoService demoService;


  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Demo getById(@PathVariable Long id) {
    return demoService.findById(id);
  }

  @RequestMapping(value = "/id/batch", method = RequestMethod.POST)
  public List<Demo> getByIds(@RequestBody List<Long> ids) {
    return demoService.findByIds(ids);
  }

  @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
  public Demo getByName(@PathVariable String name) {
    return demoService.findByName(name);
  }

  @RequestMapping(value = "/type/{demoType}", method = RequestMethod.GET)
  public List<Demo> getByType(@PathVariable(name = "demoType") String type) {
    try {
      DemoType demoType = DemoType.valueOf(type);
      return demoService.findByType(demoType);
    } catch (IllegalArgumentException e) {
      throw new RuntimeException("unSupport demo type", e);
    }
  }

  @RequestMapping(value = "/search", method = RequestMethod.POST)
  public List<Demo> search(@RequestBody DemoSearchInfo demoSearchInfo) {
    return demoService.search(demoSearchInfo);
  }


}
