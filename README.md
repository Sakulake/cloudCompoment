# cloudCompoment
spring cloud 组件实践
 
##consul 配置中心
通过页面设置config，无效，需要通过命令完成

热部署的原理  是初始化一个定时线程池。 按照delay 的间隔，请求consul 等待或者阻塞 wait-time，如果config版本与本地版本不一致，证明有更行，则发布refresh事件。让RefreshEventListener ，更新context中的配置。