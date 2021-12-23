### rainze-shiro
#### 简介

Apache Shiro™** is a powerful and easy-to-use Java security framework that performs authentication, authorization, cryptography, and session management. With Shiro’s easy-to-understand API, you can quickly and easily secure any application -- from the smallest mobile applications to the largest web and enterprise applications.
Apache Shiro™**是一个强大且易用的 Java 安全框架, 能够用于身份验证、授权、加密和会话管理。Shiro 拥有易于理解的 API, 您可以快速、轻松地获得任何应用程序----从最小的移动应用程序到最大的网络和企业应用程序。

#### Shiro的功能

  * -- 验证用户身份
  * -- 用户访问权限控制，比如：1、判断用户是否分配了一定的安全角色。2、判断用户是否被授予完成某个操作的权限
  * -- 在非 Web 或 EJB 容器的环境下可以任意使用 Session API
  * -- 可以响应认证、访问控制，或者 Session 生命周期中发生的事件
  * -- 可将一个或以上用户安全数据源数据组合成一个复合的用户 “view”(视图)
  * -- 支持单点登录 (SSO) 功能
  * -- 支持提供 “Remember Me” 服务，获取用户关联信息而无需登录

#### Shiro的特点

* 易于使用——易用性是项目的最终目标。应用程序安全非常令人困惑和沮丧, 被认为是 “不可避免的灾难”。如果你让它简化到新手都可以使用它, 它就将不再是一种痛苦了。
* 全面——没有其他安全框架的宽度范围可以同 Apache Shiro 一样, 它可以成为你的 “一站式” 为您的安全需求提供保障。
* 灵活——Apache Shiro 可以在任何应用程序环境中工作。虽然在网络工作、EJB 和 IoC 环境中可能并不需要它。但 Shiro 的授权也没有任何规范, 甚至没有许多依赖关系。
* Web 支持——Apache Shiro 拥有令人兴奋的 web 应用程序支持, 允许您基于应用程序的 url 创建灵活的安全策略和网络协议 (例如 REST), 同时还提供一组 JSP 库控制页面输出。
* 低耦合——Shiro 干净的 API 和设计模式使它容易与许多其他框架和应用程序集成。你会看到 Shiro 无缝地集成 Spring 这样的框架, 以及 Grails, Wicket, Tapestry, Mule, Apache Camel, Vaadin... 等。
* 被广泛支持——Apache Shiro 是 Apache 软件基金会的一部分。项目开发和用户组都有友好的网民愿意帮助。这样的商业公司如果需要 Katasoft 还提供专业的支持和服务。
### 认证流程

首先调用 Subject.login(token) 进行登录，其会自动委托给 Security Manager，调用之前必须通过 SecurityUtils.setSecurityManager() 设置；
SecurityManager 负责真正的身份验证逻辑；它会委托给 Authenticator 进行身份验证；
Authenticator 才是真正的身份验证者，Shiro API 中核心的身份认证入口点，此处可以自定义插入自己的实现；
Authenticator 可能会委托给相应的 AuthenticationStrategy 进行多 Realm 身份验证，默认 ModularRealmAuthenticator 会调用 AuthenticationStrategy 进行多 Realm 身份验证；
Authenticator 会把相应的 token 传入 Realm，从 Realm 获取身份验证信息，如果没有返回 / 抛出异常表示身份验证失败了。此处可以配置多个 Realm，将按照相应的顺序及策略进行访问。
#### 技术
--  springDataJpa、shiro

### 功能
* --  shiro基础原理、认证流程详细演示。
* --  利用spring boot + shiro 搭建一个简单的登录系统，实现登录、登出、权限管理、授权认证等。
* --  核心点在于里面各种配置以及demo演示的认证原理流程，非常详细。
