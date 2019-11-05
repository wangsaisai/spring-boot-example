package com.bamboo.spring.boot.example.model;

public class DemoSearchInfo {

  private DemoType demoType;

  private Boolean enable;

  private String name;

  private String orderByName;

  private String orderByType;

  public DemoType getDemoType() {
    return demoType;
  }

  public void setDemoType(DemoType demoType) {
    this.demoType = demoType;
  }

  public Boolean getEnable() {
    return enable;
  }

  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOrderByName() {
    return orderByName;
  }

  public void setOrderByName(String orderByName) {
    this.orderByName = orderByName;
  }

  public String getOrderByType() {
    return orderByType;
  }

  public void setOrderByType(String orderByType) {
    this.orderByType = orderByType;
  }
}
