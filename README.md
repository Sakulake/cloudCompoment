# cloudCompoment
spring cloud 组件实践
 
## consul 配置中心
通过页面设置config，无效，需要通过命令完成
consul kv put config/consul-service/data

热部署的原理  是初始化一个定时线程池。 按照delay 的间隔，请求consul 等待或者阻塞 wait-time，如果config版本与本地版本不一致，证明有更新，则发布refresh事件。让RefreshEventListener ，更新context中的配置。

**consul kv的导入导出**
consul kv export --http-addr=http://10.12.142.216:8500 -token=b3a9bca3-6e8e-9678-ea35-ccb8fb272d42 '' > consul_kv_$ts.json
echo 'eyJ2ZXJzaW9uX3RpbWVzdGFtcCI6IC0xfQ==' | base64 -d
consul kv import --http-addr=http://10.12.142.216:8500 -token=b3a9bca3-6e8e-9678-ea35-ccb8fb272d42 @consul_kv_20180521150322.json

## Feignclient 调用
### Feign设置超时时间
使用Feign调用接口分两层，ribbon的调用和hystrix的调用，所以ribbon的超时时间和Hystrix的超时时间的结合就是Feign的超时时间

一般情况下 都是 ribbon 的超时时间（<）hystrix的超时时间（因为涉及到ribbon的重试机制）

因为ribbon的重试机制和Feign的重试机制有冲突，所以源码中默认关闭Feign的重试机制

MaxAutoRetries+MaxAutoRetriesNextServer+(MaxAutoRetries *MaxAutoRetriesNextServer) 即重试3次 则一共产生4次调用

如果在重试期间，时间超过了hystrix的超时时间，便会立即执行熔断，fallback。所以要根据上面配置的参数计算hystrix的超时时间，使得在重试期间不能达到hystrix的超时时间，不然重试机制就会没有意义

hystrix超时时间的计算： (1（首次调用） + MaxAutoRetries + MaxAutoRetriesNextServer) * ReadTimeout 即按照以上的配置 hystrix的超时时间应该配置为 （1+1+1）*3=9秒


当ribbon超时后且hystrix没有超时，便会采取重试机制。当OkToRetryOnAllOperations设置为false时，只会对get请求进行重试。如果设置为true，便会对所有的请求进行重试，如果是put或post等写操作，如果服务器接口没做幂等性，会产生不好的结果，所以OkToRetryOnAllOperations慎用。

如果不配置ribbon的重试次数，默认会重试一次。


## Ribbon负载均衡
ribbon实现的关键点是为ribbon定制的RestTemplate，ribbon利用了RestTemplate的拦截器（ribbonInterceptor）机制，在拦截器中实现ribbon的负载均衡。

### @LoadBalanced注解
这个注解是spring cloud实现的，不是ribbon中的，它的作用是在依赖注入时，只注入实例化时被@LoadBalanced修饰的实例。

### 负载策略
- 可用者优先
- 选择请求最少的节点 
    - 跳过断路器“跳闸”的服务器并选择并发请求最少的服务器的规则。 此规则通常应与ServerListSubsetFilter一起使用，后者对规则可见的服务器设置限制。这确保了它只需要在少量服务器中找到最小的并发请求。此外，每个客户机都将获得一个随机的服务器列表，这避免了大量客户机选择一个并发请求最少的服务器并立即被淹没的问题。
- 自定义规则，需要继承 PredicateBasedRule
- 随机
- 轮询


## Hystrix 
熔断

> 雪崩效应: 下游服务不可用，导致中游服务占据大量资源没有释放，时间长了导致中游、上游服务不可用。

**三种状态**
- 开启
  - 如果错误请求达到一定的阈值，就会变成开启状态,就会让所有请求短路，直接返回失败的响应
- 关闭
  - 所有请求都可以通过
- 半开
  - 一段时间后，断路器会变成半开状态，如果下一个请求成功了，就关闭断路器，反之就开启断路器

1. hystrix.command.default.circuitBreaker.requestVolumeThreshold（当在配置时间窗口内达到此数量的失败后，进行短路。默认20个）简言之，10s内请求失败数量达到20个，断路器就会变成打开状态。
2. hystrix.command.default.circuitBreaker.sleepWindowInMilliseconds（短路多久以后开始尝试是否恢复，默认5s）
3. hystrix.command.default.circuitBreaker.errorThresholdPercentage（出错百分比阈值，当达到此阈值后，开始短路。默认50%）

![](https://img-blog.csdnimg.cn/20190621144810332.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dhbmdjaGVuZ21pbmcx,size_16,color_FFFFFF,t_70)
```
@HystrixCommand(threadPoolKey = "user-hello")
String getUserHello();
```

**Hystrix整个工作流如下:**

1. 构造一个 HystrixCommand或HystrixObservableCommand对象，用于封装请求，并在构造方法 配置请求被执行需要的参数; 
2. 执行命令，Hystrix提供了4种执行命令的方法，后面详述; 
3. 判断是否使用缓存响应请求，若启用了缓存，且缓存可用，直接使用缓存响应请求。Hystrix支持 请求缓存，但需要用户自定义启动; 
4. 判断熔断器是否打开，如果打开，跳到第8步; 
5. 判断线程池/队列/信号量是否已满，已满则跳到第8步; 
6. 执行HystrixObservableCommand.construct()或HystrixCommand.run()，如果执行失败或者超时，跳到第8步;否则，跳到第9步; 
7. 统计熔断器监控指标; 
8. 走Fallback备用逻辑 
9. 返回请求响应
从流程图上可知道，第5步线程池/队列/信号量已满时，还会执行第7步逻辑，更新熔断器统计信息，而 第6步无论成功与否，都会更新熔断器统计信息。


Hystrix使用了一个ConcurrentHashMap来保存线程池

资源隔离(线程池和信号量实现)。应用程序会被完全保护起来，即使依赖的一个服务出问题了，也不会影响到应用程序的其他部分。使用多个线程池就是一种资源隔离方式，也是默认的隔离方式。而且Hystrix底层是使用的RxJava，使用线程池可以让你很方便地实现异步操作。


### fallback
降级
- 执行超时
- 执行报错
- 断路器开启
- 线程池拒绝
- 信号量拒绝


# Sentinel