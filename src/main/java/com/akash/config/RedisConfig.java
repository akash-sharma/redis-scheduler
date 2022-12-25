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
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class RedisConfig {

  // @Value("${redisHosts:redis://localhost:6379,redis://localhost:6380,redis://localhost:6381}")
  @Value("${redisHosts:redis://localhost:6379}")
  private String redisHosts;

  private final int maxConcurrency = 5;
  private final String threadPoolName = "scheduler-pool";
  private final String executorName = "Distributed-Scheduler";

  @Bean(destroyMethod = "shutdown")
  public RedissonClient redisson() {
    final String[] redisHostSplit = redisHosts.split(",");
    final Config config = new Config();
    config.useSingleServer().setAddress(redisHostSplit[0]);
    // config.useClusterServers().addNodeAddress(redisHostSplit);
    return Redisson.create(config);
  }

  @Bean(destroyMethod = "shutdown")
  public RScheduledExecutorService rScheduledExecutorService() {

    final ThreadPoolExecutor threadPoolExecutor =
        new ThreadPoolExecutor(
            maxConcurrency,
            maxConcurrency,
            5L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue(),
            new CustomizableThreadFactory(threadPoolName));

    final WorkerOptions workerOptions =
        WorkerOptions.defaults()
            .workers(2)
            .taskTimeout(10, TimeUnit.SECONDS)
            .executorService(threadPoolExecutor);
    final RScheduledExecutorService executorService =
        redisson().getExecutorService(executorName, ExecutorOptions.defaults());
    executorService.registerWorkers(workerOptions);
    return executorService;
  }
}
