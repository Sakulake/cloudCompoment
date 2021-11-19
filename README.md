# cloudCompoment
spring cloud 组件实践
 
##consul 配置中心
通过页面设置config，无效，需要通过命令完成

热部署的原理  是初始化一个定时线程池。 按照delay 的间隔，请求consul 等待或者阻塞 wait-time，如果config版本与本地版本不一致，证明有更行，则发布refresh事件。让RefreshEventListener ，更新context中的配置。


## Feignclient 调用
### Feign设置超时时间
使用Feign调用接口分两层，ribbon的调用和hystrix的调用，所以ribbon的超时时间和Hystrix的超时时间的结合就是Feign的超时时间

一般情况下 都是 ribbon 的超时时间（<）hystrix的超时时间（因为涉及到ribbon的重试机制）

因为ribbon的重试机制和Feign的重试机制有冲突，所以源码中默认关闭Feign的重试机制

MaxAutoRetries+MaxAutoRetriesNextServer+(MaxAutoRetries *MaxAutoRetriesNextServer) 即重试3次 则一共产生4次调用

如果在重试期间，时间超过了hystrix的超时时间，便会立即执行熔断，fallback。所以要根据上面配置的参数计算hystrix的超时时间，使得在重试期间不能达到hystrix的超时时间，不然重试机制就会没有意义

hystrix超时时间的计算： (1（首次调用） + MaxAutoRetries + MaxAutoRetriesNextServer) * ReadTimeout 即按照以上的配置 hystrix的超时时间应该配置为 （1+1+1）*3=9秒


当ribbon超时后且hystrix没有超时，便会采取重试机制。当OkToRetryOnAllOperations设置为false时，只会对get请求进行重试。如果设置为true，便会对所有的请求进行重试，如果是put或post等写操作，如果服务器接口没做幂等性，会产生不好的结果，所以OkToRetryOnAllOperations慎用。

如果不配置ribbon的重试次数，默认会重试一次