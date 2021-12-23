### rainze-springboot-websocket

websocket的学习以及基于springBot整合websocket搭建的简单的在线聊天系统。

#### 简介

* WebSocket用于在Web浏览器和服务器之间进行任意的双向数据传输的一种技术。WebSocket协议基于TCP协议实现，包含初始的握手过程，以及后续的多次数据帧双向传输过程。其目的是在WebSocket应用和WebSocket服务器进行频繁双向通信时，可以使服务器避免打开多个HTTP连接进行工作来节约资源，提高了工作效率和资源利用率。
* WebSocket是HTML5出的东西（协议），也就是说HTTP协议没有变化，或者说没关系，但HTTP是不支持持久连接的（长连接，循环连接的不算）
首先HTTP有 1.1 和 1.0 之说，也就是所谓的 keep-alive ，把多个HTTP请求合并为一个，但是 Websocket 其实是一个新协议，跟HTTP协议基本没有关系，只是为了兼容现有浏览器的握手规范而已，也就是说它是HTTP协议上的一种补充.
* WebSocket 协议是基于 TCP 的一种新的网络协议。该协议提供了通过一个套接字实现全双工通信的功能，全双工意味着服务器可以发送给浏览器，浏览器也可以发送给服务器。
WebSocket 使得客户端和服务器之间的数据交换变得更加简单，允许服务端主动向客户端推送数据。在 WebSocket API 中，浏览器和服务器只需要完成一次握手，两者之间就直接可以创建持久性的连接，并进行双向数据传输。
如果我们想实现一个简单的聊天应用的话，在没有 WebSocket 的时候一般会选择 Ajax 轮询的方式，从服务器拉取消息。这种方式虽然能实现我们的需求但是显得非常笨拙，同时由于不停的轮询给服务器造成很大的压力。而 WebSocket 在浏览器和服务器建立连接之后，任何一方都可以主动发起通信。
#### Websocket原理

浏览器发出webSocket的连线请求，服务器发出响应，这个过程称为握手,握手的过程只需要一次，就可以实现持久连接。
#### 握手与连接

浏览器发出连线请求,此时的request如下：
* 通过get可以表明此次连接的建立是以HTTP协议为基础的，返回101状态码。
* 
* 如果不是101状态码，表示握手升级的过程失败了
* 
* 101是Switching Protocols,表示服务器已经理解了客户端的请求，并将通过Upgrade 消息头通知客户端采用不同的协议来完成这个请求。在发送这个响应后的空档，将http升级到webSocket。
* 
* 其中Upgrade和Connection字段告诉服务端，发起的是webSocket协议
* 
* Sec-WebSocket-Key是浏览器经过Base64加密后的密钥，用来和response里面的Sec-WebSocket-Accept进行比对验证
* 
* Sec-WebSocket-Version是当前的协议版本
* 
* Sec-WebSocket-Extensions是对WebSocket的协议扩展
