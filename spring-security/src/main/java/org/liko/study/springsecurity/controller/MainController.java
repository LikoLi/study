package org.liko.study.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Liko
 * @Description:
 * @Date: Created at 19:28 2018/8/27
 */
@Controller
public class MainController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "hello world!";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError() {
        return "login-error";
    }
}
