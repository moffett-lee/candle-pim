package com.rainze.cloud.assembly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;


/**
 * @author LYZ
 * @version 1.0
 * @description
 * @projectName rainze-spring-cloud-assembly
 * @data 2020/5/4 11:38
 */
@SpringBootApplication
@EnableEurekaClient
@EnableBinding
public class RainzeSpringCloudStreamApplication {

    /**
     *  Spring Cloud Stream应用由第三方的中间件组成。应用间的通信通过输入通道（input channel）和输出通道（output channel）完成。
     *  这些通道是有Spring Cloud Stream 注入的。而通道与外部的代理（可以理解为上文所说的数据中心）的连接又是通过Binder实现的。
     *
     *  @EnableBinding 注解接收一个参数，参数类型是class。上面代码中，传入的参数是“Processor.class”,这是一个接口，定义了两个channel，分别是input和output。看名称就知道，一个是输出通道（input channel），一个是输出通道（output channel）。“@EnableBinding(value = { Processor.class })”这整段代表创建Processor定义的通道，并将通道和Binder绑定。
     *
     * Porcessor是Spring Cloud Stream为方便使用而预先定义好的，除了Processor还有Sink和Source，这些接口定义了一些通道（channel），需要时，直接使用就好。我们也能自己定义通道（channel）
     *
     *
     *   1、1 Binder
     * ​  Binder 是Spring Cloud Steram的一个重要的抽象，目前Spring Cloud Stream实现了面向Kafka和RabbitMQ的Binder。有了Binder有很方便的连接中间件了。Binder提供了消费者分组和消息分区的特性。
     *
     *   1、2 Channel
     *  即通道，是队列Queue的一种抽象，在具体的消息通讯系统中，队列作用就是实现存储和转发的媒介，我们通过Channel对队列进行配置。
     *
     *   1、3 Source和Sink
     *  我们不是第一次看到Source和Sink了，简单的可理解为输入和输出。注意：这里的参照对象是Spring Cloud Stream自身，从Stream发布消息就是输出，接受消息就是输入。
     *
     *  在Spring Cloud Stream中，Source组件是使用一个POJO对象发布消息的，该对象需要序列化然后发布到Channel中，Sink反序列化POJO对象。在底层的处理机制上，需要借助Spring Integration这个企业服务总线的组件。
     *
     *
     *    好了到现在为止，我们进行了一个简单的消息发送和接收，用的是Stream给我们提供的默认Source，Sink，接下来我们要自己进行自定义，这种方式在工作中还是用的比较多的，因为我们要往不同的消息通道发消息，必然不能全都叫input,output的，那样的话就乱套了，因此首先自定义一个接口，如下：
     *
     * Source（发射器） : 一个接口类，内部定义了一个输出管道，例如定义一个输出管道 @output（"XXOO"）。说明这个发射器将会向这个管道发射数据。
     * Sink（接收器） : 一个接口类，内部定义了一个输入管道，例如定义一个输入管道 @input（"XXOO"）。说明这个接收器将会从这个管道接收数据。
     * Binder（绑定器）：用于与管道进行绑定。Binder将于消息中间件进行关联。@EnableBinding （Source.class/Sink.class）。@EnableBinding（）里面是可以定义多个发射器/接收器
     *
     *
     */
    public static void main(String[] args) {
        SpringApplication.run(RainzeSpringCloudStreamApplication.class, args);
    }

}
