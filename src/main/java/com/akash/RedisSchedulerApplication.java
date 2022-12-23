package com.akash;

import com.akash.service.SchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@ComponentScan(basePackages = {"com.akash"})
@SpringBootApplication
@EnableAutoConfiguration(
    exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class RedisSchedulerApplication {

  private static final Logger LOGGER = LoggerFactory.getLogger(RedisSchedulerApplication.class);

  @Autowired private SchedulerService schedulerService;

  public static void main(String[] args) {

    SpringApplication.run(RedisSchedulerApplication.class, args);
  }

  @PostConstruct
  public void init() {
    schedulerService.submit();
    schedulerService.scheduleWithFixedDelay();
  }
}
