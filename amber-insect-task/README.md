# amber-insect-task

- 整合并学习市面上所有的定时任务框架。
### Java中的定时任务

* Timer和TimerTask
* ScheduledExecutorService
* 三方框架 Quartz 、elastic-job 、xxl-job等。

## Quartz
Quartz是OpenSymphony开源组织在Job scheduling领域又一个开源项目，完全由Java开发，可以用来执行定时任务，类似于java.util.Timer。但是相较于Timer， Quartz增加了很多功能：
持久性作业 - 就是保持调度定时的状态;
作业管理 - 对调度作业进行有效的管理;

## Quartz的基本组成部分：
- 调度器：Scheduler
- 任务：JobDetail
- 触发器：Trigger，包括SimpleTrigger和CronTrigger

## Quartz核心详解
- 下面就程序中出现的几个参数，看一下Quartz框架中的几个重要参数：
- Job和JobDetail
- JobExecutionContext
- JobDataMap
- Trigger、SimpleTrigger、CronTrigger
（1）Job和JobDetail
- Job是Quartz中的一个接口，接口下只有execute方法，在这个方法中编写业务逻辑。
- JobDetail用来绑定Job，为Job实例提供许多属性：
- name
- group
- jobClass
- jobDataMap
- JobDetail绑定指定的Job，每次Scheduler调度执行一个Job的时候，首先会拿到对应的Job，然后创建该Job实例，再去执行Job中的execute()的内容，任务执行结束后，关联的Job对象实例会被释放，且会被JVM GC清除。
```
JobDetail定义的是任务数据，而真正的执行逻辑是在Job中。
这是因为任务是有可能并发执行，如果Scheduler直接使用Job，就会存在对同一个Job实例并发访问的问题。而JobDetail & Job 方式，
Sheduler每次执行，都会根据JobDetail创建一个新的Job实例，这样就可以规避并发访问的问题。
```
为什么设计成JobDetail + Job，不直接使用Job

（2）JobExecutionContext
JobExecutionContext中包含了Quartz运行时的环境以及Job本身的详细数据信息。
当Schedule调度执行一个Job的时候，就会将JobExecutionContext传递给该Job的execute()中，Job就可以通过JobExecutionContext对象获取信息。

（3）JobExecutionContext
JobDataMap实现了JDK的Map接口，可以以Key-Value的形式存储数据。
JobDetail、Trigger都可以使用JobDataMap来设置一些参数或信息，
Job执行execute()方法的时候，JobExecutionContext可以获取到JobExecutionContext中的信息：


- (4)Trigger、SimpleTrigger、CronTrigger
- 
- Trigger
- Trigger是Quartz的触发器，会去通知Scheduler何时去执行对应Job。
- new Trigger().startAt():表示触发器首次被触发的时间;
- new Trigger().endAt():表示触发器结束触发的时间;
- SimpleTrigger
- SimpleTrigger可以实现在一个指定时间段内执行一次作业任务或一个时间段内多次执行作业任务。
- 下面的程序就实现了程序运行5s后开始执行Job，执行Job 5s后结束执行：

（5）CronTrigger
- CronTrigger功能非常强大，是基于日历的作业调度，而SimpleTrigger是精准指定间隔，所以相比SimpleTrigger，CroTrigger更加常用。CroTrigger是基于Cron表达式的，先了解下Cron表达式：
- 由7个子表达式组成字符串的，格式如下：
- [秒] [分] [小时] [日] [月] [周] [年]
- Cron表达式的语法比较复杂，
- 如：* 30 10 ? * 1/5 *
- 表示（从后往前看）
- [指定年份] 的[ 周一到周五][指定月][不指定日][上午10时][30分][指定秒]
- 
- 又如：00 00 00 ？ * 10,11,12 1#5 2018
- 表示2018年10、11、12月的第一周的星期五这一天的0时0分0秒去执行任务。
- 可通过在线生成Cron表达式的工具：http://cron.qqe2.com/ 来生成自己想要的表达式。

## 启动说明
- 项目使用的数据库为MySql，选择resources/sql中的tables_mysql_innodb.sql文件初始化数据库信息。
- 在resources/application.properties 以及quartz.properties文件中替换为自己的数据源。
- 运行Application main方法启动项目，项目启动会自动创建一个测试任务 见：com.itstyle.quartz.config.TaskRunner.java。
- 项目访问地址：http://localhost:8080/quartz


## 版本区别(spring-boot 1.x and 2.x)

这里只是针对这两个项目异同做比较，当然spring-boot 2.x版本升级还有不少需要注意的地方。

项目名称配置：
```
# spring boot 1.x
server.context-path=/quartz
# spring boot 2.x
server.servlet.context-path=/quartz
```
thymeleaf配置：
```
#spring boot 1.x
spring.thymeleaf.mode=LEGACYHTML5
#spring boot 2.x
spring.thymeleaf.mode=HTML
```
Hibernate配置：
```
# spring boot 2.x JPA 依赖  Hibernate 5
# Hibernate 4 naming strategy fully qualified name. Not supported with Hibernate 5.
spring.jpa.hibernate.naming.strategy = org.hibernate.cfg.ImprovedNamingStrategy
# stripped before adding them to the entity manager)
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
# Hibernate 5
spring.jpa.hibernate.naming.implicit-strategy=org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
```
quartz配置：
```
# spring boot 2.x 已集成Quartz，无需自己配置
spring.quartz.job-store-type=jdbc
spring.quartz.properties.org.quartz.scheduler.instanceName=clusteredScheduler
spring.quartz.properties.org.quartz.scheduler.instanceId=AUTO
spring.quartz.properties.org.quartz.jobStore.class=org.quartz.impl.jdbcjobstore.JobStoreTX
spring.quartz.properties.org.quartz.jobStore.driverDelegateClass=org.quartz.impl.jdbcjobstore.StdJDBCDelegate
spring.quartz.properties.org.quartz.jobStore.tablePrefix=QRTZ_
spring.quartz.properties.org.quartz.jobStore.isClustered=true
spring.quartz.properties.org.quartz.jobStore.clusterCheckinInterval=10000
spring.quartz.properties.org.quartz.jobStore.useProperties=false
spring.quartz.properties.org.quartz.threadPool.class=org.quartz.simpl.SimpleThreadPool
spring.quartz.properties.org.quartz.threadPool.threadCount=10
spring.quartz.properties.org.quartz.threadPool.threadPriority=5
spring.quartz.properties.org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread=true
```

## 集群测试

打开quartz集群配置：
```
# 打开集群配置
spring.quartz.properties.org.quartz.jobStore.isClustered = true
# 设置集群检查间隔20s
spring.quartz.properties.org.quartz.jobStore.clusterCheckinInterval = 2000  
```
本地跑两个项目，分别设置不同的端口8081和8082，启动成功以后，会发现只有一个任务在跑，然后杀掉在跑的任务，你会发现另一个项目会检测到集群中的一个任务挂了，然后接管任务。
```
2018-04-12 09:00:01.792  INFO 7488 --- [_ClusterManager] o.s.s.quartz.LocalDataSourceJobStore     : ClusterManager: detected 1 failed or restarted instances.
2018-04-12 09:00:01.793  INFO 7488 --- [_ClusterManager] o.s.s.quartz.LocalDataSourceJobStore     : ClusterManager: Scanning for instance "itstyle-PC1523496348539"'s failed in-progress jobs.
2018-04-12 09:00:01.839  INFO 7488 --- [_ClusterManager] o.s.s.quartz.LocalDataSourceJobStore     : ClusterManager: ......Freed 1 acquired trigger(s).

```
## 扩展知识

一、Quartz API核心接口有：

- Scheduler – 与scheduler交互的主要API；
- Job – 你通过scheduler执行任务，你的任务类需要实现的接口；
- JobDetail – 定义Job的实例；
- Trigger – 触发Job的执行；
- JobBuilder – 定义和创建JobDetail实例的接口;
- TriggerBuilder – 定义和创建Trigger实例的接口；


二、定时任务框架选型及项目实际使用
* 1、项目目前定时任务现状
* 使用Linux系统的crontab直接调用Java服务
* 优缺点：
* 优点：部署简单，由linux系统维护相对Java进程维护更加维定
* 缺点：单机部署，风险大；出问题后排错难度大；需要运维介入成本大
* 总结：针对目前项目情况，弊大于利
* 2、Java主流三大定时任务框架优缺点
* 选型时原则:
* 少服务器 后期维护方便 增加任务省事 而且快捷 不涉及启停服务
* 
* Quartz
* 优点：支持集群部署
* 缺点：没有自带的管理界面；调度逻辑和执行任务耦合在一起；维护需要重启服务
* 总结：针对目前项目情况，利弊相同
*  
* 
* xxl-job
* 优点：支持集群部署；提供运维界面维护成本小；自带错误预警；相对elastic-job来说不需要额外的组件（zookeeper）；支持调度策略；支持分片； 故障转移 ；更适合分布式
* 缺点：相对Quartz来说需要多部署调度中心
* 总结：针对目前项目情况，利大于弊
* elastic-job
* 优点：支持集群部署；维护成本小
* 缺点：elastic-job需要zookeeper，zookeeper集群高可用至少需要三台服务器
* 总结：针对目前项目情况，弊大于利
* 小结:
* 综合选型原则及三个定时任务框架的优缺点和目前项目的状况，建议选用xxl-job
```
四、针对项目目前定时任务的选用策略建议
目前项目的所有定时任务（和运维亚光确认）共8个
不同定时任务策略的建议：
        （1）结算相关

              a.罚息定时任务（wf-job-practical）
                       路由策略:轮询
                       阻塞处理:单机串行
                       失败处理:失败告警
               b.结清定时任务（wf-job-settlement）
                     路由策略:轮询
                     阻塞处理:单机串行
                     失败处理:失败告警
            (2)  短信相关
                 a. 短信发送定时任务（wf-sms-send-job）
                           路由策略:轮询
                          阻塞处理:丢弃后续调度
                           失败处理:失败告警
                 b.短信业务定时任务（wf-sms-write-job）
                            路由策略:轮询
                            阻塞处理:单机串行
                            失败处理:失败告警
                c.短信数据清理定时任务（wf-sms-cleardata-job）
                            路由策略:轮询
                            阻塞处理:单机串行
                            失败处理:失败告警
            (3).报表相关
                  a.风控定时任务wf-bi-risk-job
                       路由策略:轮询
                       阻塞处理:单机串行
                       失败处理:失败告警
                 b. wf-bi-market-job
                       路由策略:轮询
                      阻塞处理:单机串行
                      失败处理:失败告警
                c.wf-bi-job
                       路由策略:轮询
                       阻塞处理:单机串行
                        失败处理:失败告警
```
## 分布式任务调度-XXL-JOB

XXL-JOB是一个分布式任务调度平台，其核心设计目标是开发迅速、学习简单、轻量级、易扩展。现已开放源代码并接入多家公司线上产品线，开箱即用.
* 1.3 特性
- 1、简单：支持通过Web页面对任务进行CRUD操作，操作简单，一分钟上手；
- 2、动态：支持动态修改任务状态、启动/停止任务，以及终止运行中任务，即时生效；
- 3、调度中心HA（中心式）：调度采用中心式设计，“调度中心”自研调度组件并支持集群部署，可保证调度中心HA；
- 4、执行器HA（分布式）：任务分布式执行，任务"执行器"支持集群部署，可保证任务执行HA；
- 5、注册中心: 执行器会周期性自动注册任务, 调度中心将会自动发现注册的任务并触发执行。同时，也支持手动录入执行器地址；
- 6、弹性扩容缩容：一旦有新执行器机器上线或者下线，下次调度时将会重新分配任务；
- 7、路由策略：执行器集群部署时提供丰富的路由策略，包括：第一个、最后一个、轮询、随机、一致性HASH、最不经常使用、最近最久未使用、故障转移、忙碌转移等；
- 8、故障转移：任务路由策略选择"故障转移"情况下，如果执行器集群中某一台机器故障，将会自动Failover切换到一台正常的执行器发送调度请求。
- 9、阻塞处理策略：调度过于密集执行器来不及处理时的处理策略，策略包括：单机串行（默认）、丢弃后续调度、覆盖之前调度；
- 10、任务超时控制：支持自定义任务超时时间，任务运行超时将会主动中断任务；
- 11、任务失败重试：支持自定义任务失败重试次数，当任务失败时将会按照预设的失败重试次数主动进行重试；其中分片任务支持分片粒度的失败重试；
- 12、任务失败告警；默认提供邮件方式失败告警，同时预留扩展接口，可方便的扩展短信、钉钉等告警方式；
- 13、分片广播任务：执行器集群部署时，任务路由策略选择"分片广播"情况下，一次任务调度将会广播触发集群中所有执行器执行一次任务，可根据分片参数开发分片任务；
- 14、动态分片：分片广播任务以执行器为维度进行分片，支持动态扩容执行器集群从而动态增加分片数量，协同进行业务处理；在进行大数据量业务操作时可显著提升任务处理能力和速度。
- 15、事件触发：除了"Cron方式"和"任务依赖方式"触发任务执行之外，支持基于事件的触发任务方式。调度中心提供触发任务单次执行的API服务，可根据业务事件灵活触发。
- 16、任务进度监控：支持实时监控任务进度；
- 17、Rolling实时日志：支持在线查看调度结果，并且支持以Rolling方式实时查看执行器输出的完整的执行日志；
- 18、GLUE：提供Web IDE，支持在线开发任务逻辑代码，动态发布，实时编译生效，省略部署上线的过程。支持30个版本的历史版本回溯。
- 19、脚本任务：支持以GLUE模式开发和运行脚本任务，包括Shell、Python、NodeJS、PHP、PowerShell等类型脚本;
- 20、命令行任务：原生提供通用命令行任务Handler（Bean任务，"CommandJobHandler"）；业务方只需要提供命令行即可；
- 21、任务依赖：支持配置子任务依赖，当父任务执行结束且执行成功后将会主动触发一次子任务的执行, 多个子任务用逗号分隔；
- 22、一致性：“调度中心”通过DB锁保证集群分布式调度的一致性, 一次任务调度只会触发一次执行；
- 23、自定义任务参数：支持在线配置调度任务入参，即时生效；
- 24、调度线程池：调度系统多线程触发调度运行，确保调度精确执行，不被堵塞；
- 25、数据加密：调度中心和执行器之间的通讯进行数据加密，提升调度信息安全性；
- 26、邮件报警：任务失败时支持邮件报警，支持配置多邮件地址群发报警邮件；
- 27、推送maven中央仓库: 将会把最新稳定版推送到maven中央仓库, 方便用户接入和使用;
- 28、运行报表：支持实时查看运行数据，如任务数量、调度次数、执行器数量等；以及调度报表，如调度日期分布图，调度成功分布图等；
- 29、全异步：任务调度流程全异步化设计实现，如异步调度、异步运行、异步回调等，有效对密集调度进行流量削峰，理论上支持任意时长任务的运行；
- 30、跨语言：调度中心与执行器提供语言无关的 RESTful API 服务，第三方任意语言可据此对接调度中心或者实现执行器。除此之外，还提供了 “多任务模式”和“httpJobHandler”等其他跨语言方案；
- 31、国际化：调度中心支持国际化设置，提供中文、英文两种可选语言，默认为中文；
- 32、容器化：提供官方docker镜像，并实时更新推送dockerhub，进一步实现产品开箱即用；
- 33、线程池隔离：调度线程池进行隔离拆分，慢任务自动降级进入"Slow"线程池，避免耗尽调度线程，提高系统稳定性；
- 34、用户管理：支持在线管理系统用户，存在管理员、普通用户两种角色；
- 35、权限控制：执行器维度进行权限控制，管理员拥有全量权限，普通用户需要分配执行器权限后才允许相关操作；
- 详细介绍：https://www.cnblogs.com/xuxueli/p/5021979.html

##  Elastic-Job
* Elastic-Job是一个分布式调度解决方案，由两个相互独立的子项目Elastic-Job-Lite和Elastic-Job-Cloud组成。
* Elastic-Job-Lite定位为轻量级无中心化解决方案，使用jar包的形式提供分布式任务的协调服务。
* Elastic-Job-Cloud使用Mesos + Docker的解决方案，额外提供资源治理、应用分发以及进程隔离等服务。

### 1. Elastic-Job-Lite

* 分布式调度协调
* 弹性扩容缩容
* 失效转移
* 错过执行作业重触发
* 作业分片一致性，保证同一分片在分布式环境中仅一个执行实例
* 自诊断并修复分布式不稳定造成的问题
* 支持并行调度
* 支持作业生命周期操作
* 丰富的作业类型
* Spring整合以及命名空间提供
* 运维平台

### 2. Elastic-Job-Cloud
* 应用自动分发
* 基于Fenzo的弹性资源分配
* 分布式调度协调
* 弹性扩容缩容
* 失效转移
* 错过执行作业重触发
* 作业分片一致性，保证同一分片在分布式环境中仅一个执行实例
* 支持并行调度
* 支持作业生命周期操作
* 丰富的作业类型
* Spring整合
* 运维平台
* 基于Docker的进程隔离(TBD)

### 打包作业
tar -cvf yourJobs.tar.gz yourJobs

### 发布APP

```shell
curl -l -H "Content-type: application/json" -X POST -d '{"appName":"foo_app","appURL":"http://app_host:8080/yourJobs.gz","cpuCount":0.1,"memoryMB":64.0,"bootstrapScript":"bin/start.sh","appCacheEnable":true,"eventTraceSamplingCount":0}' http://elastic_job_cloud_host:8899/api/app
```

### 发布作业

```shell
curl -l -H "Content-type: application/json" -X POST -d '{"jobName":"foo_job","jobClass":"yourJobClass","jobType":"SIMPLE","jobExecutionType":"TRANSIENT","cron":"0/5 * * * * ?","shardingTotalCount":5,"cpuCount":0.1,"memoryMB":64.0,"appName":"foo_app","failover":true,"misfire":true,"bootstrapScript":"bin/start.sh"}' http://elastic_job_cloud_host:8899/api/job/register
```