package com.example.core.common.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String traceId = request.getHeader("TRACE_ID");
        String spanId = request.getHeader("SPAN_ID");
        if (traceId == null) {
            traceId = UUID.randomUUID().toString();
        }
        // You can generate new spanId or use the same one
        if (spanId == null) {
            spanId = UUID.randomUUID().toString();
        }
        MDC.put("TRACE_ID", traceId);
        MDC.put("traceId", traceId);
        MDC.put("SPAN_ID", spanId);
        MDC.put("spanId", spanId);
        log.info("[preHandle] HTTP: {}, URL: {} ", request.getMethod(), request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView)
            throws Exception {
        log.info("[postHandle] HTTP: {}, URL: {} ", request.getMethod(), request.getRequestURI());
        MDC.clear();
    }
}
