#### Redis Repositories
- @RedisHash annotation on its type and a property named id that is annotated with org.springframework.data.annotation.Id. Those two items   are responsible for creating the actual key used to persist the hash.
-


#### REDIS LETTUCE INTEGRATION WITH JAVA SPRING BOOT

LETTUCE VS JEDIS
- While Jedis is easy to use and supports a vast number of Redis features, it is not thread safe and needs connection pooling to work in a   multi-threaded environment. Connection pooling comes at the cost of a physical connection per Jedis instance which increases the number     of Redis connections.

- Lettuce, on the other hand, is built on netty (https://netty.io/) and connection instances can be shared across multiple threads. So a     multi-threaded application can use a single connection regardless the number of concurrent threads that interact with Lettuce.

SYNC VS ASYNC
- One other reason we opted to go with Lettuce was that it facilitates asynchronicity from building the client on top of netty that is a     multithreaded, event-driven I/O framework. Asynchronous methodologies allow you to utilize better system resources, instead of wasting     threads waiting for network or disk I/O. Threads can be fully utilised to perform other work instead.

- For the purpose of having a concurrently processing system, it’s convenient, in this scenario, to have all communication handled           asynchronously. There are scenarios where this might not be the case, where you have quick running tasks and try to access data that has   just been invalidated by a different task.

CREATE THE BEAN FOR CONFIGURATION

