package com.akash.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

public class MyOneTimeTask implements Runnable, Serializable {

  private static final long serialVersionUID = 4357644690L;

  private static final Logger LOGGER = LogManager.getLogger(MyOneTimeTask.class);

  @Override
  public void run() {
    LOGGER.info("MyOneTimeTask executed");
  }
}
