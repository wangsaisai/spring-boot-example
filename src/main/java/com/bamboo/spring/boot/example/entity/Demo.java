package com.bamboo.spring.boot.example.entity;

import com.bamboo.spring.boot.example.converter.IdListConverter;
import com.bamboo.spring.boot.example.model.DemoType;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "demo")
public class Demo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(name = "demo_type")
  private DemoType type;

  @Column(columnDefinition = "default true")
  private Boolean enable;

  @Column(columnDefinition = "TEXT")
  private String content;

  @Convert(converter = IdListConverter.class)
  @Column(name = "id_list")
  private List<Long> idList;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public DemoType getType() {
    return type;
  }

  public void setType(DemoType type) {
    this.type = type;
  }

  public Boolean getEnable() {
    return enable;
  }

  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public List<Long> getIdList() {
    return idList;
  }

  public void setIdList(List<Long> idList) {
    this.idList = idList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Demo demo = (Demo) o;
    return Objects.equals(id, demo.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
