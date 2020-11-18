package cn.likoli.study.springboottest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 *
 * @author liko
 * @date 2020/11/11
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/sayHi")
    public String sayHi() {
        return "hi";
    }

    @PostMapping("/sayHello")
    public String sayHello() {
        return "hello";
    }
}
