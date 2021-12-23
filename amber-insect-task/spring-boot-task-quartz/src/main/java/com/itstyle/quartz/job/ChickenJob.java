package com.itstyle.quartz.job;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.quartz.*;

/**
 * 实现序列化接口、防止重启应用出现quartz Couldn't retrieve job because a required class was not found 的问题
 * Job 的实例要到该执行它们的时候才会实例化出来。每次 Job 被执行，一个新的 Job 实例会被创建。
 * 其中暗含的意思就是你的 Job 不必担心线程安全性，因为同一时刻仅有一个线程去执行给定 Job 类的实例，甚至是并发执行同一 Job 也是如此。
 * @DisallowConcurrentExecution 保证上一个任务执行完后，再去执行下一个任务，这里的任务是同一个任务
 */
@DisallowConcurrentExecution
public class ChickenJob implements  Job,Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public void execute(JobExecutionContext context){
        JobDetail jobDetail = context.getJobDetail();
        JobDataMap dataMap = jobDetail.getJobDataMap();
        /**
         * 获取任务中保存的方法名字，动态调用方法
         */
        String methodName = dataMap.getString("jobMethodName");
        try {
            ChickenJob job = new ChickenJob();
            Method method = job.getClass().getMethod(methodName);
            method.invoke(job);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    /*
     * 说明：每个定时任务都必须有一个分组，名称和corn表达式,corn表达式也就是定时任务的触发时间，关于corn表达式格式以及含义可以参考一些网络资源。
     * 每个定时任务都有一个入口类在这里我把类名当成定时任务的分组名称，例如：只要创建定时任务的分组是JobOne的都会执行JobOne这个任务类里面的逻辑。
     * 如果定时任务需要额外的参数可以使用JobDataMap传递参数，当然也可以从数据库中获取需要的数据。
     * @PersistJobDataAfterExecution和@DisallowConcurrentExecution注解是不让某个定时任务并发执行，只有等当前任务完成下一个任务才会去执行。
     */


    public void test1(){
        System.out.println("测试方法1");
    }
    public void test2(){
        System.out.println("测试方法2");
    }
    public void test3(){
        System.out.println("测试方法3");
    }
}