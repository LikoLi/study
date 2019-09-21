package org.liko.study.springboot.session7.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
@Slf4j
public class CustomListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        log.info("listener destroyes...");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        log.info("listener initialized...");
    }
}
