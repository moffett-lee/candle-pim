package com.itstyle.quartz;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 启动类
 * 创建者 微信搜索爪哇笔记
 * 创建时间	2018年3月28日
 * API接口测试：http://localhost:8080/task/swagger-ui.html
 */
@SpringBootApplication
public class Application {

	/*** Quartz的3个基本要素
	*  Scheduler：调度器。所有的调度都是由它控制。
	*  Trigger： 触发器。决定什么时候来执行任务。
	*  JobDetail & Job： JobDetail定义的是任务数据，而真正的执行逻辑是在Job中。使用JobDetail + Job而不是Job，这是因为任务是有可能并发执行，
	*  如果Scheduler直接使用Job，就会存在对同一个Job实例并发访问的问题。而JobDetail & Job 方式，sheduler每次执行，都会根据JobDetail创建一个新的Job实例，这样就可以规避并发访问的问题。
	*/
	private static final Logger logger = LoggerFactory.getLogger(Application.class);

		/**说明：在使用quartz做持久化的时候需要用到quartz的11张表，可以去quartz官网下载对应版本的quartz，解压打开docs/dbTables里面有对应数据库的建表语句。
	     关于quartz.properties配置的详细解释可以查看quartz官网。另外新建一张表TB_APP_QUARTZ用于存放定时任务基本信息和描述等信息，定时任务的增、删、改、执行等功能与此表没有任何关系。
		 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}