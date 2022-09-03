package com.rainze.shiro.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @author LYZ
 * @version 1.0
 * @description shiro 认证过程演示  Apache Shiro 是一个强大灵活的开源安全框架，可以完全处理身份验证、授权、加密和会话管理。
 * @projectName rainze-shiro
 * @data 2020/5/4 16:08
 */
public class AuthenticationTest {

    /**
     *
     *   shiro 的作用：
     *   验证用户身份
     *   用户访问权限控制，比如：1、判断用户是否分配了一定的安全角色。2、判断用户是否被授予完成某个操作的权限
     *   在非 Web 或 EJB 容器的环境下可以任意使用 Session API
     *   可以响应认证、访问控制，或者 Session 生命周期中发生的事件
     *   可将一个或以上用户安全数据源数据组合成一个复合的用户 “view”(视图)
     *   支持单点登录 (SSO) 功能
     *   支持提供 “Remember Me” 服务，获取用户关联信息而无需登录
     *   ···
     *   shiro 的特点：
     *
     *   易于使用——易用性是项目的最终目标。应用程序安全非常令人困惑和沮丧, 被认为是 “不可避免的灾难”。如果你让它简化到新手都可以使用它, 它就将不再是一种痛苦了。
     *   全面——没有其他安全框架的宽度范围可以同 Apache Shiro 一样, 它可以成为你的 “一站式” 为您的安全需求提供保障。
     *   灵活——Apache Shiro 可以在任何应用程序环境中工作。虽然在网络工作、EJB 和 IoC 环境中可能并不需要它。但 Shiro 的授权也没有任何规范, 甚至没有许多依赖关系。
     *   Web 支持——Apache Shiro 拥有令人兴奋的 web 应用程序支持, 允许您基于应用程序的 url 创建灵活的安全策略和网络协议 (例如 REST), 同时还提供一组 JSP 库控制页面输出。
     *   低耦合——Shiro 干净的 API 和设计模式使它容易与许多其他框架和应用程序集成。你会看到 Shiro 无缝地集成 Spring 这样的框架, 以及 Grails, Wicket, Tapestry, Mule, Apache Camel, Vaadin... 等。
     *   被广泛支持——Apache Shiro 是 Apache 软件基金会的一部分。项目开发和用户组都有友好的网民愿意帮助。这样的商业公司如果需要 Katasoft 还提供专业的支持和服务。
     *
     *
     *  Authentication（认证）, Authorization（授权）, Session Management（会话管理）, Cryptography（加密）被 Shiro 框架的开发团队称之为应用安全的四大基石。那么就让我们来看看它们吧：
     *
     *  **Authentication（认证）：**用户身份识别，通常被称为用户 “登录”
     *  **Authorization（授权）：**访问控制。比如某个用户是否具有某个操作的使用权限。
     *  **Session Management（会话管理）：**特定于用户的会话管理, 甚至在非 web 或 EJB 应用程序。
     *  **Cryptography（加密）：**在对数据源使用加密算法加密的同时，保证易于使用。
     *  还有其他的功能来支持和加强这些不同应用环境下安全领域的关注点。特别是对以下的功能支持：
     *
     *  **Web 支持：**Shiro 的 Web 支持 API 有助于保护 Web 应用程序。
     *  **缓存：**缓存是 Apache Shiro API 中的第一级，以确保安全操作保持快速和高效。
     *  **并发性：**Apache Shiro 支持具有并发功能的多线程应用程序。
     *  **测试：**存在测试支持，可帮助您编写单元测试和集成测试，并确保代码按预期得到保障。
     *  **“运行方式”：**允许用户承担另一个用户的身份 (如果允许) 的功能，有时在管理方案中很有用。
     *  **“记住我”：**记住用户在会话中的身份，所以用户只需要强制登录即可。
     *
     *   认证过程 创建SecurityManager > 主体提交认证 》 securityManager认证 》 authenticator认证 》 realm 认证
     *
     */
    //shiro 提供的api
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before // 在方法开始前添加一个用户
    public void addUser() {
        simpleAccountRealm.addAccount("lyz", "123456");
    }


    @Test
    public void testAuthentication() {


        // 1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);
        // 2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager); // 设置SecurityManager环境
        Subject subject = SecurityUtils.getSubject(); // 获取当前主体
        UsernamePasswordToken token = new UsernamePasswordToken("lyz", "123456");
        subject.login(token); // 登录
        // subject.isAuthenticated()方法返回一个boolean值,用于判断用户是否认证成功
        System.out.println("isAuthenticated:" + subject.isAuthenticated()); // 输出true
        subject.logout(); // 登出

        System.out.println("isAuthenticated:" + subject.isAuthenticated()); // 输出false
    }




}
