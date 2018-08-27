package org.liko.pigeonrace.listener;

import org.liko.pigeonrace.socket.PigeonRaceServer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

/**
 * @Author: Liko
 * @Description:
 * @Date: Created at 17:20 2018/7/27
 */
@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            PigeonRaceServer.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
