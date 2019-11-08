package com.bamboo.spring.boot.example.schedule;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleJob {

  private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleJob.class);

  // scheduled job
  // first job start while wait 1 minute (60000ms)
  // next job start while wait 10 minutes after last job completed
  @Scheduled(initialDelay = 60000, fixedDelay = 60000)
  public void doYourJob() {
    LOGGER.info("scheduled job start");

    try {
      // doing your job
      TimeUnit.SECONDS.sleep(30L);
    } catch (Exception e) {
      LOGGER.error("scheduled job error", e);
    }

    LOGGER.info("scheduled job completed");
  }


}
