package com.akash.config;

import org.redisson.Redisson;
import org.redisson.api.ExecutorOptions;
import org.redisson.api.RScheduledExecutorService;
import org.redisson.api.RedissonClient;
import org.redisson.api.WorkerOptions;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Configuration
public class RedisConfig {

  @Value("${redisHosts:redis://localhost:6379,redis://localhost:6380,redis://localhost:6381}")
  private String redisHosts;

  @Bean(destroyMethod = "shutdown")
  public RedissonClient redisson() {
    final String[] redisHostSplit = redisHosts.split(",");
    final Config config = new Config();
    config.useSingleServer().setAddress(redisHostSplit[0]);
    // config.useClusterServers().addNodeAddress(redisHostSplit);
    config.setExecutor(Executors.newFixedThreadPool(5)); // default is 16
    return Redisson.create(config);
  }

  @Bean(destroyMethod = "shutdown")
  public RScheduledExecutorService rScheduledExecutorService() {

    final WorkerOptions workerOptions =
        WorkerOptions.defaults()
            .workers(2)
            .taskTimeout(10, TimeUnit.SECONDS)
            .executorService(Executors.newFixedThreadPool(10));
    final RScheduledExecutorService executorService =
        redisson().getExecutorService("Redisson-Job", ExecutorOptions.defaults());
    executorService.registerWorkers(workerOptions);
    return executorService;
  }
}
