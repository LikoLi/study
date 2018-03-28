package org.liko.event;

import org.liko.event.util.EventUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class Main {
    public static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath:dubbo-server.xml"});
    public static void main(String[] args) {

        try {

            // --
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
            EventUtil eventUtil = (EventUtil) context.getBean("eventUtil");
            eventUtil.generator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
