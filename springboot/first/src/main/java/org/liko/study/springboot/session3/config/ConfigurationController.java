package org.liko.study.springboot.session3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigurationController {

    @Value("${blog.address}")
    private String address;

    @Value("${blog.author}")
    private String author;

    @Value("${blog.out}")
    private String out;

    @Value("${blog.value}")
    private String value;

    @Value("${blog.number}")
    private String number;

    @Value("${blog.bignumber}")
    private String bigNumber;

    @Autowired
    private Config config;

    @RequestMapping("/configTest/out")
    public String out() {
        return out;
    }

    @RequestMapping("configTest")
    public String configTest() {
        return "Welcome to " + address + ", author is " + author + ";";
    }

    @RequestMapping("/configTest/value")
    public String value() {
        return "value : " + value + ", number : " + number + ", bigNumber : " + bigNumber;
    }

    @RequestMapping("/configTest/requestBean")
    public String requestBean() {
        return config.toString();
    }
}
