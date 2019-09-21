package org.liko.study.springboot.session2.lombok;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoMain {
    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.setName("name");
        demo.setCode("code");
        System.out.println(demo);
        log.info("lombok for slf4j!");
        log.error("error log test.");
    }
}
