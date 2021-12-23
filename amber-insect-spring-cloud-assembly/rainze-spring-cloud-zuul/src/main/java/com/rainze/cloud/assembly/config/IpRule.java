package com.rainze.cloud.assembly.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
* @Description  自定义路由策略
* @Date 2019/4/9 8:48
* @Author ljq
* @Since 1.0
*
*/
@Component
public class IpRule extends AbstractLoadBalancerRule {


    RoundRobinRule roundRobinRule = new RoundRobinRule();

    //@Value("${serverIp}")
    private String serverIp;
//    private String serverIp = "192.168.0.92";

    @Autowired
    private SpringClientFactory factory;

	private static Logger log = LoggerFactory.getLogger(com.rainze.cloud.assembly.config.IpRule.class);

	public Server choose(ILoadBalancer lb) {
        if (lb == null) {
            log.warn("no load balancer");
            return null;
        }
 
        Server server = null;
        int count = 0;
        while (server == null && count++ < 10) {
            //1.	获取注册中心上的服务列表
            RequestContext ctx = RequestContext.getCurrentContext();
            List<Server> reachableServers = lb.getReachableServers();
            List<Server> allServers = lb.getAllServers();
            int upCount = reachableServers.size();
            int serverCount = allServers.size();

 
            if ((upCount == 0) || (serverCount == 0)) {
                log.warn("No up servers available from load balancer: " + lb);
                return null;
            }
            //2.	获取访问服务的ip地址
            String ownIp = getRemoteAddr();
            log.info("get remote address is {}",ownIp);
            //3.	根据ip地址找到服务列表上对应的服务
            server = findOwnServer(allServers,ownIp);
            //server = allServers.get(nextServerIndex);
 
            if (server == null) {
                /* Transient. */
                Thread.yield();
                continue;
            }
            //4.	返回找到的服务
            if (server.isAlive() && (server.isReadyToServe())) {
                return (server);
            }
 
            // Next.
            server = null;
        }
 
        if (count >= 10) {
            log.warn("No available alive servers after 10 tries from load balancer: "
                    + lb);
        }
        return server;
 
    }

    private Server findOwnServer(List<Server> serverList, String ownIp) {
        Server ser = null;
        for (Server server: serverList) {
            if (StringUtils.equals(server.getHost(),ownIp)|| server.getHost() ==ownIp) {
                ser = server;
                log.info("find micro server ip is {}",ownIp);
                break;
            }
        }
        if (Objects.isNull(ser)) {
            for (Server server: serverList) {
                if (StringUtils.equals(server.getHost(),serverIp)|| server.getHost() ==serverIp) {
                    ser = server;
                    log.info("find micro server ip is {}",serverIp);
                    break;
                }
            }
        }
        return ser;
    }


 
	private String getRemoteAddr() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String ip = null;
        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
	}

    @Override
	public Server choose(Object key) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ILoadBalancer lb = factory.getLoadBalancer((String) ctx.get("serviceId"));
        if (null != SpringContextUtil.getActiveProfile() && SpringContextUtil.getActiveProfile().equals("dev")) {
            return choose(lb);
        }else{
            return this.roundRobinRule.choose(lb,key);
        }
	}
 
	@Override
	public void initWithNiwsConfig(IClientConfig clientConfig) {
        this.roundRobinRule = new RoundRobinRule();
	}
 
}
