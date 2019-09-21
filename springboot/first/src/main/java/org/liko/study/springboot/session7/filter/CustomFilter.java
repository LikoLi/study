package org.liko.study.springboot.session7.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(filterName = "customFilter", urlPatterns = "/*")
@Slf4j
public class CustomFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("filter 初始化....");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("filter doFilter...");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("filter 销毁...");
    }
}
