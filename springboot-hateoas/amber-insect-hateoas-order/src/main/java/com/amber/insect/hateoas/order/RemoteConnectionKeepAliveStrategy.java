package com.amber.insect.hateoas.order;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import java.util.Arrays;

/**
 * @ClassName RemoteConnectionKeepAliveStrategy
 * @Description TODO
 * @Author Amber.L
 * @Date 2021/12/9 20:49
 * @Version 1.0
 **/
public class RemoteConnectionKeepAliveStrategy  implements org.apache.http.conn.ConnectionKeepAliveStrategy{

    private final long DEFAULT_SECONDS = 30;

    /**
     ** 远程连接， keepalive的设置策略
     */
    @Override
    public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
        return Arrays.asList(response.getHeaders(HTTP.CONN_KEEP_ALIVE))
                .stream()
                .filter(h -> StringUtils.endsWithIgnoreCase(h.getName(), "timeout")
                        && StringUtils.isNumeric(h.getValue()))
                .findFirst()
                .map(h -> NumberUtils.toLong(h.getValue(), DEFAULT_SECONDS))
                .orElse(DEFAULT_SECONDS) * 1000;
    }
}
