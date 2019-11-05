package com.bamboo.spring.boot.example.converter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.AttributeConverter;

/**
 * convert string in database to java.util.List
 */
public class IdListConverter implements AttributeConverter<List<Long>, String> {

  private static final String SEPARATOR = ",";

  @Override
  public String convertToDatabaseColumn(List<Long> attribute) {
    if (attribute == null || attribute.isEmpty()) {
      return "";
    }

    StringBuilder sb = new StringBuilder();
    attribute.forEach(v -> sb.append(v).append(SEPARATOR));
    return sb.substring(0, sb.length() - 2);
  }

  @Override
  public List<Long> convertToEntityAttribute(String dbData) {
    if (dbData == null || dbData.trim().isEmpty()) {
      return Collections.emptyList();
    }
    return Arrays.stream(dbData.trim().split(SEPARATOR)).map(Long::valueOf).collect(
        Collectors.toList());
  }
}
