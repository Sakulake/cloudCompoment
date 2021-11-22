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


# Zipkin(利用拦截器拦截器)
链路的追踪原理：跟踪器位于应用程序中，记录发生的操作的时间和元数据，收集的跟踪数据称为Span，将数据发送到Zipkin的仪器化应用程序中的组件称为Reporter，Reporter通过几种传输方式（http，kafka）之一将追踪数据发送到Zipkin收集器(collector)，然后将跟踪数据进行存储(storage)，由API查询存储以向UI提供数据。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL21tYml6X3BuZy95amNuaWEwM2ljb1gxYWtHaWFJUktmcmc4SVdpYmN5U3JlVEloa2RsTXJ4VzFCWVdiWnlHMG9vdTZpY0R2STJCWkdXR2N0U2czTWljMTdSV3p4NE5SWDJ3YUl5US82NDA_d3hfZm10PXBuZw?x-oss-process=image/format,png)

Brave 是用来装备 Java 程序的类库，提供了面向 Standard Servlet、Spring MVC、Http Client、JAX RS、Jersey、Resteasy 和 MySQL 等接口的装备能力，可以通过编写简单的配置和代码，让基于这些框架构建的应用可以向 Zipkin报告数据。同时 Brave 也提供了非常简单且标准化的接口，在以上封装无法满足要求的时候可以方便扩展与定制。

Brave 主要是利用拦截器在请求前和请求后分别埋点。例如 Spingmvc 监控使用 Interceptors，Mysql 监控使用 statementInterceptors。同理 Dubbo 的监控是利用 com.alibaba.dubbo.rpc.Filter 来过滤生产者和消费者的请求。

和log4j2 结合[%X{X-B3-TraceId},%X{X-B3-SpanId}]

# log4j2
由于spring架构使用的是logback作为日志组件。当然该日志组件还是挺好用的，很方便的进行了日志分割等操作。但是该组件是同步的，所以高访问的时候可能会影响效率，这里就换成log4j2支持异步的日志组件。

disruptor是一个基于无锁化环形队列的高性能并发框架，log4j2就是借助它进行高性能日志异步输出的。

- 日志接口(slf4j)
  
  slf4j是对所有日志框架制定的一种规范、标准、接口，并不是一个框架的具体的实现，因为接口并不能独立使用，需要和具体的日志框架实现配合使用（如log4j、logback）
- 日志实现(log4j、logback、log4j2)
  - log4j是apache实现的一个开源日志组件
  - logback同样是由log4j的作者设计完成的，拥有更好的特性，用来取代log4j的一个日志框架，是slf4j的原生实现
  - Log4j2是log4j 1.x和logback的改进版，据说采用了一些新技术（无锁异步、等等），使得日志的吞吐量、性能比log4j 1.x提高10倍，并解决了一些死锁的bug，而且配置更加简单灵活
  


# Spring Cloud Gateway

Spring Cloud Gateway 底层使用了高性能的通信框架Netty。
1. 基于 Spring Framework 5，Project Reactor 和 Spring Boot 2.0
2. 集成 Hystrix 断路器
3. 集成 Spring Cloud DiscoveryClient
4. Predicates 和 Filters 作用于特定路由，易于编写的 Predicates 和 Filters
5. 具备一些网关的高级功能：动态路由、限流、路径重写


Filter（过滤器）: 和Zuul的过滤器在概念上类似，可以使用它拦截和修改请求，并且对上游的响应，进行二次处理。过滤器为org.springframework.cloud.gateway.filter.GatewayFilter类的实例。

Route（路由）: 网关配置的基本组成模块，和Zuul的路由配置模块类似。一个Route模块由一个 ID，一个目标 URI，一组断言和一组过滤器定义。如果断言为真，则路由匹配，目标URI会被访问。

Predicate（断言）: 这是一个 Java 8 的 Predicate，可以使用它来匹配来自 HTTP 请求的任何内容，例如 headers 或参数。断言的输入类型是一个 ServerWebExchange。

### 流程
1. 客户端向 Spring Cloud Gateway 发出请求。
2. 然后在 Gateway Handler Mapping 中找到与请求相匹配的路由，将其发送到 Gateway Web Handler。
3. Handler 再通过指定的过滤器链来将请求发送到我们实际的服务执行业务逻辑，然后返回。过滤器之间用虚线分开是因为过滤器可能会在发送代理请求之前（“pre”）或之后（“post”）执行业务逻辑。
![](https://upload-images.jianshu.io/upload_images/19816137-eeedbd49be096c05?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

gateWay的主要功能之一是转发请求
，转发规则的定义主要包含三个部分
1. Route（路由）

   路由是网关的基本单元，由ID、URI、一组Predicate、一组Filter组成，根据Predicate进行匹配转发。
2. Predicate（谓语、断言）

   路由转发的判断条件，目前SpringCloud Gateway支持多种方式，常见如：Path、Query、Method、Header等，写法必须遵循 key=vlue的形式
3. Filter（过滤器）

   过滤器是路由转发请求时所经过的过滤逻辑，可用于修改请求、响应内容

其中Route和Predicate必须同时申明