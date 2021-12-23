# 基于 Spring Cloud 的微服务架构

本项目是一个基于 Spring Boot、Spring Cloud、Spring Oauth2 和 Spring Cloud Netflix 等框架构建的微服务基础组件学习项目。
#### springcloud常用组件
> 项目中使用的技术如下：
>
* Spring boot - 微服务的入门级微框架，用来简化 Spring 应用的初始搭建以及开发过程。

* Eureka - 云端服务发现，一个基于 REST 的服务，用于定位服务，以实现云端中间层服务发现和故障转移。

* Spring Cloud Config - 配置管理工具包，让你可以把配置放到远程服务器，集中化管理集群配置，目前支持本地存储、Git 以及 Subversion。

* Hystrix - 熔断器，容错管理工具，旨在通过熔断机制控制服务和第三方库的节点,从而对延迟和故障提供更强大的容错能力。

* Zuul - Zuul 是在云平台上提供动态路由，监控，弹性，安全等边缘服务的框架。Zuul 相当于是设备和 Netflix 流应用的 Web 网站后端所有请求的前门。

* Spring Cloud Bus - 事件、消息总线，用于在集群（例如，配置变化事件）中传播状态变化，可与 Spring Cloud Config 联合实现热部署。

* Spring Cloud Sleuth - 日志收集工具包，封装了 Dapper 和 log-based 追踪以及 Zipkin 和 HTrace 操作，为 SpringCloud 应用实现了一种分布式追踪解决方案。

* Ribbon - 提供云端负载均衡，有多种负载均衡策略可供选择，可配合服务发现和断路器使用。

* Turbine - Turbine 是聚合服务器发送事件流数据的一个工具，用来监控集群下 hystrix 的 metrics 情况。

* Spring Cloud Stream - Spring 数据流操作开发包，封装了与 Redis、Rabbit、Kafka 等发送接收消息。

* Feign - Feign 是一种声明式、模板化的 HTTP 客户端。

* Spring Cloud OAuth2 - 基于 Spring Security 和 OAuth2 的安全工具包，为你的应用程序添加安全控制。

#### 项目启动 
##### 1、前期准备
- 首先在本地电脑的host文件中，配置好虚拟域名；
  我本地host文件中的配置如下（注意中间空格）：
```
127.0.0.1 server.eureka.node1.com server.eureka.node2.com
```
- 安装rabbitmq
```
rabbitmq目前只用在配置中心，实现动态刷新spring bean，建议安装。
```
- 安装kafka、zookeeper
```
用来做stream服务支持。
```

##### 2、开发环境（以下是我本机环境）

- jdk1.8
- idea 2020.1

##### 3、服务启动
1、启动eureka注册中心 
```
（建议运行至少2个eureka服务器节点，方可看到高可用集群效果）
```
<br/>

2、启动config配置中心
```
（建议运行至少2个config配置中心节点，方可看到高可用集群效果）
```
<br/>

3、启动zuul服务网关
```
（建议运行至少2个zuul服务网关节点，方可看到高可用集群效果）
```
<br/>

4、启动hystrix dashboard仪表盘服务

<br/>

5、启动user服务
```
（建议运行至少2个user服务节点，方可看到高可用集群效果）
```
<br/>

6、启动rainze-spring-cloud-consumption应用 测试服务 当然自己可以在按需要集成自己的模块
```
（建议运行至少2个shop应用节点，方可看到高可用集群效果）
```
<br/>

7、启动 rainze-spring-cloud-zipkin 作为链路追踪服务中心，负责存储链路数据;运用了Sleuth;Sleuth 用来追踪记录日志;其中兼容支持了zipkin,用来汇总并展示数据

#### 打开eureka控制台，查看各服务的集群信息
 截图略


### 项目后续加入模块
 完善的日志
 集成alibaba微服务组件
