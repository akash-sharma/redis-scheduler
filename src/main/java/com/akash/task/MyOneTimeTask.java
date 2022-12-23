package com.akash.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyOneTimeTask implements Runnable {

  private static final Logger LOGGER = LogManager.getLogger(MyOneTimeTask.class);

  @Override
  public void run() {
    LOGGER.info("MyOneTimeTask executed");
  }
}
