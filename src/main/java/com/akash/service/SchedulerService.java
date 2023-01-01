package com.akash.service;

import com.akash.task.MyOneTimeTask;
import com.akash.task.MyScheduledTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.redisson.api.RScheduledExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Service
public class SchedulerService {

  private static final Logger LOGGER = LogManager.getLogger(SchedulerService.class);

  @Autowired private RScheduledExecutorService executorService;

  @PostConstruct
  public void init() {
    LOGGER.info("Initiating Job scheduling...");
    scheduleWithFixedDelay();
  }

  public void scheduleWithFixedDelay() {
    final int taskCount = executorService.getTaskCount();
    LOGGER.info("taskCount in SchedulerService : {}", taskCount);
    if (taskCount == 0) {
      executorService.scheduleWithFixedDelay(new MyScheduledTask(), 0, 10, TimeUnit.SECONDS);
    }
  }

  public void submit() {
    executorService.submit(new MyOneTimeTask());
  }
}
