package com.bamboo.spring.boot.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    LOGGER.info("spring boot example service start...");
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
  }
}
