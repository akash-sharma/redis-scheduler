package com.akash.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

public class MyScheduledTask implements Runnable {

  private static final Logger LOGGER = LogManager.getLogger(MyScheduledTask.class);

  @Override
  public void run() {
    LOGGER.info("MyScheduledTask executed at {}", LocalDateTime.now());
  }
}
