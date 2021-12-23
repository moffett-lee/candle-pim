package com.rainze.websocket.config;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *   知识点：
 *      @ServerEndpoint(value = "/wsdemo") 前端通过此 URI 和后端交互，建立连接
 *      @Component 不用说将此类交给 spring 管理
 *      @OnOpen websocket 建立连接的注解，前端触发上面 URI 时会进入此注解标注的方法，和之前关于 DWR 文章中的 onpage 方法类似
 *      @OnClose 顾名思义关闭连接，销毁 session
 *      @OnMessage 收到前端传来的消息后执行的方法
 * @author LYZ
 * @version 1.0
 * @description
 * @projectName rainze-springboot-websocket
 * @data 2020/5/5 10:55
 */
@ServerEndpoint(value = "/wsdemo")
@Component
public class MyWebSocket {
    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private static int onlineCount = 0;

    /** concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     在外部可以获取此连接的所有websocket对象，并能对其触发消息发送功能，我们的定时发送核心功能的实现在与此变量 */
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();

    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;

    /**
     * 连接建立成功调用的方法
     *
     * 类似dwr的onpage方法，参考之前文章中demo有
     * */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        try {
            sendMessage("连接已建立成功.");
        } catch (Exception e) {
            System.out.println("IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     *
     * 参考dwrsession摧毁方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //连接关闭后，将此websocket从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
    }

    // 错误提示
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    // 发送消息，在定时任务中会调用此方法
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);

    }




    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        MyWebSocket.onlineCount--;
    }



    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public static CopyOnWriteArraySet<MyWebSocket> getWebSocketSet() {
        return webSocketSet;
    }

    public static void setWebSocketSet(CopyOnWriteArraySet<MyWebSocket> webSocketSet) {
        MyWebSocket.webSocketSet = webSocketSet;
    }
}