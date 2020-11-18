package org.liko.study.springboottest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 *
 * @author liko
 * @date 2020/9/21
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public void hello() {
        System.out.println("Hello World!");
        throw new RuntimeException("Test");
    }
}
