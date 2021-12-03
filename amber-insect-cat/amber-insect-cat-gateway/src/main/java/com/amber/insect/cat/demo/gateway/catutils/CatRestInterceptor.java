package com.amber.insect.cat.demo.gateway.catutils;
import com.alidaodao.app.Cat;
import com.alidaodao.app.CatConstants;
import com.alidaodao.app.message.Transaction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>Description: </p>
 * @date  
 * @author   
 * @version 1.0
 * @name   
 * <p>Copyright:Copyright(c)2020</p>
 */
@Component
public class CatRestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        Transaction t = Cat.newTransaction(CatConstants.TYPE_REMOTE_CALL, request.getURI().toString());

        try {
            HttpHeaders headers = request.getHeaders();

            // 保存和传递CAT调用链上下文
            Cat.Context ctx = new CatContext();
            Cat.logRemoteCallClient(ctx);
            headers.add(CatHttpConstants.CAT_HTTP_HEADER_ROOT_MESSAGE_ID, ctx.getProperty(Cat.Context.ROOT));
            headers.add(CatHttpConstants.CAT_HTTP_HEADER_PARENT_MESSAGE_ID, ctx.getProperty(Cat.Context.PARENT));
            headers.add(CatHttpConstants.CAT_HTTP_HEADER_CHILD_MESSAGE_ID, ctx.getProperty(Cat.Context.CHILD));

            // 继续执行请求
            ClientHttpResponse response =  execution.execute(request, body);
            t.setStatus(Transaction.SUCCESS);
            return response;
        } catch (Exception e) {
            Cat.getProducer().logError(e);
            t.setStatus(e);
            throw e;
        } finally {
            t.complete();
        }
    }

}
