# redis-scheduler

## Links

https://github.com/redisson/redisson/wiki/9.-distributed-services

https://github.com/redisson/redisson/wiki/14.-Integration-with-frameworks

https://www.linkedin.com/pulse/springboot-redissons-distributed-task-engine-zhifu-jin

https://github.com/redisson/redisson/issues/3441

https://github.com/redisson/redisson/issues/2560

## Output :

- [INFO ] 2022-12-26T00:15:00.003Z [scheduler-pool2] MyOneTimeTask - MyOneTimeTask executed
- [INFO ] 2022-12-26T00:15:00.004Z [scheduler-pool3] MyScheduledTask - MyScheduledTask executed at 2022-12-26T00:15:00.004213
- [INFO ] 2022-12-26T00:15:10.072Z [scheduler-pool4] MyScheduledTask - MyScheduledTask executed at 2022-12-26T00:15:10.071951
- [INFO ] 2022-12-26T00:15:20.169Z [scheduler-pool5] MyScheduledTask - MyScheduledTask executed at 2022-12-26T00:15:20.169199
- [INFO ] 2022-12-26T00:15:30.270Z [scheduler-pool1] MyScheduledTask - MyScheduledTask executed at 2022-12-26T00:15:30.270089




## Redis keys and their data

### 127.0.0.1:6379> keys *
1) "{Distributed-Scheduler:org.redisson.executor.RemoteExecutorService}:counter"
2) "{Distributed-Scheduler:org.redisson.executor.RemoteExecutorService}:scheduler"
3) "{Distributed-Scheduler:org.redisson.executor.RemoteExecutorService}:retry-interval"
4) "{Distributed-Scheduler:org.redisson.executor.RemoteExecutorService}:tasks"



#### counter --> count for number of jobs running,
get key
#### scheduler -- sorted set
zrange key 0 -1
#### retry-interval -- simple key
get key
#### tasks -- Storing hash for sorted set key. serialised form of Runnable/Callable class
hgetall key




#### 127.0.0.1:6379> get {Distributed-Scheduler:org.redisson.executor.RemoteExecutorService}:counter
"1"


#### 127.0.0.1:6379> zrange {Distributed-Scheduler:org.redisson.executor.RemoteExecutorService}:scheduler 0 -1
1) "f17cfc06732ea3ab16b21a130e2287ce"
2) "ff:f17cfc06732ea3ab16b21a130e2287ce"


#### 127.0.0.1:6379> get {Distributed-Scheduler:org.redisson.executor.RemoteExecutorService}:retry-interval
"300000"


#### 127.0.0.1:6379> hgetall {Distributed-Scheduler:org.redisson.executor.RemoteExecutorService}:tasks
1) "f17cfc06732ea3ab16b21a130e2287ce"
2) "\x01\x00org.redisson.remote.RemoteServiceReques\xf4\x01\x01[Ljava.lang.Object\xbb\x02\x01\x02org.redisson.executor.params.ScheduledWithFixedDelayParameter\xf3\xb1\a\xca\xfe\xba\xbe\x00\x00\x004\x003\n\x00\b\x00\x1d\t\x00\x06\x00\x1e\b\x00\x1f\n\x00 \x00!\x0b\x00\"\x00#\a\x00$\n\x00%\x00&\a\x00'\a\x00(\a\x00)\x01\x00\x10serialVersionUID\x01\x00\x01J\x01\x00\rConstantValue\x05\x00\x00\x00\x02!\xd6\x91j\x01\x00\x06LOGGER\x01\x00!Lorg/apache/logging/log4j/Logger;\x01\x00\x06<init>\x01\x00\x03()V\x01\x00\x04Code\x01\x00\x0fLineNumberTable\x01\x00\x12LocalVariableTable\x01\x00\x04this\x01\x00 Lcom/akash/task/MyScheduledTask;\x01\x00\x03run\x01\x00\b<clinit>\x01\x00\nSourceFile\x01\x00\x14MyScheduledTask.java\x0c\x00\x12\x00\x13\x0c\x00\x10\x00\x11\x01\x00\x1eMyScheduledTask executed at {}\a\x00*\x0c\x00+\x00,\a\x00-\x0c\x00.\x00/\x01\x00\x1ecom/akash/task/MyScheduledTask\a\x000\x0c\x001\x002\x01\x00\x10java/lang/Object\x01\x00\x12java/lang/Runnable\x01\x00\x14java/io/Serializable\x01\x00\x17java/time/LocalDateTime\x01\x00\x03now\x01\x00\x1b()Ljava/time/LocalDateTime;\x01\x00\x1forg/apache/logging/log4j/Logger\x01\x00\x04info\x01\x00'(Ljava/lang/String;Ljava/lang/Object;)V\x01\x00#org/apache/logging/log4j/LogManager\x01\x00\tgetLogger\x01\x004(Ljava/lang/Class;)Lorg/apache/logging/log4j/Logger;\x00!\x00\x06\x00\b\x00\x02\x00\t\x00\n\x00\x02\x00\x1a\x00\x0b\x00\x0c\x00\x01\x00\r\x00\x00\x00\x02\x00\x0e\x00\x1a\x00\x10\x00\x11\x00\x00\x00\x03\x00\x01\x00\x12\x00\x13\x00\x01\x00\x14\x00\x00\x00/\x00\x01\x00\x01\x00\x00\x00\x05*\xb7\x00\x01\xb1\x00\x00\x00\x02\x00\x15\x00\x00\x00\x06\x00\x01\x00\x00\x00\t\x00\x16\x00\x00\x00\x0c\x00\x01\x00\x00\x00\x05\x00\x17\x00\x18\x00\x00\x00\x01\x00\x19\x00\x13\x00\x01\x00\x14\x00\x00\x00<\x00\x03\x00\x01\x00\x00\x00\x0e\xb2\x00\x02\x12\x03\xb8\x00\x04\xb9\x00\x05\x03\x00\xb1\x00\x00\x00\x02\x00\x15\x00\x00\x00\n\x00\x02\x00\x00\x00\x11\x00\r\x00\x12\x00\x16\x00\x00\x00\x0c\x00\x01\x00\x00\x00\x0e\x00\x17\x00\x18\x00\x00\x00\b\x00\x1a\x00\x13\x00\x01\x00\x14\x00\x00\x00!\x00\x01\x00\x00\x00\x00\x00\t\x12\x06\xb8\x00\a\xb3\x00\x02\xb1\x00\x00\x00\x01\x00\x15\x00\x00\x00\x06\x00\x01\x00\x00\x00\r\x00\x01\x00\x1b\x00\x00\x00\x02\x00\x1ccom.akash.task.MyScheduledTas\xeb\xa0\x9c\x01\xa56065090a-cad6-4b6e-bcef-0bbfdae6f1a1\x00f17cfc06732ea3ab16b21a130e2287c\xe5\xb4\xb6\x8b\xa8\xa9a!\x01\x00com.akash.task.MyScheduledTas\xeb\x00\x94\x9a\x8a\xa8\xa9a\xa56065090a-cad6-4b6e-bcef-0bbfdae6f1a1f17cfc06732ea3ab16b21a130e2287c\xe5scheduleWithFixedDela\xf9\x01\x00\x00\x03\xd7\xa9\xaa\xb6\x8d\x9f\xeb\xcd\xe8\xa7\xe6\x86\xe8\xbe\xc9\xf6\xa8\xaf"

