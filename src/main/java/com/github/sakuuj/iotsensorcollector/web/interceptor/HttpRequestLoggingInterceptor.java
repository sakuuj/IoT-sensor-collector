package com.github.sakuuj.iotsensorcollector.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.event.Level;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

@Slf4j
public class HttpRequestLoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {
        log.makeLoggingEventBuilder(Level.INFO)
                .addKeyValue("protocol", request.getProtocol())
                .addKeyValue("method", request.getMethod())
                .addKeyValue("path", request.getRequestURL() + Optional.ofNullable(request.getQueryString())
                        .map(queryString -> "?" + queryString)
                        .orElse(""))
                .addKeyValue("host", request.getRemoteHost())
                .addKeyValue("user-agent", request.getHeader(HttpHeaders.USER_AGENT))
                .log();
        return true;
    }
}
