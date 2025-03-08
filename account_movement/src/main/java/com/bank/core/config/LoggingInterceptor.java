package com.bank.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
@Slf4j
public class LoggingInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        log.info("Processing interceptor request for, Request Method={}, Request URI={} ,endpointMethod={}",
                request.getURI(), request.getMethod(),"intercept");
        return execution.execute(request, body);
    }
}
