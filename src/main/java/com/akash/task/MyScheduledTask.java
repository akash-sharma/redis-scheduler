package com.akash.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MyScheduledTask implements Runnable, Serializable {

  private static final long serialVersionUID = 9157644650L;

  private static final Logger LOGGER = LogManager.getLogger(MyScheduledTask.class);

  @Override
  public void run() {
    LOGGER.info("MyScheduledTask executed at {}", LocalDateTime.now());
  }
}
