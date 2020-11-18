package org.liko.event;

import org.liko.event.util.EventUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 事件生成器
 */
public class EventGenerator {
    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:dubbo-server.xml"});
    private static EventUtil eventUtil;

    static {
        eventUtil = (EventUtil) context.getBean("eventUtil");
    }

    public static void generate() {
        try {
            eventUtil.generator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getEvent(int eventId) {
        try {
            eventUtil.getEvent(eventId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
