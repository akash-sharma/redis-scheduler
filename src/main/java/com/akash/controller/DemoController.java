package com.akash.controller;

import com.akash.service.SchedulerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

  private static final Logger LOGGER = LogManager.getLogger(DemoController.class);

  @Autowired private SchedulerService schedulerService;

  @GetMapping(path = "/trigger", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> triggerScheduler() {

    LOGGER.info("Trigger api executed");
    schedulerService.submit();
    schedulerService.scheduleWithFixedDelay();
    return new ResponseEntity("Trigger api executed", HttpStatus.OK);
  }
}
