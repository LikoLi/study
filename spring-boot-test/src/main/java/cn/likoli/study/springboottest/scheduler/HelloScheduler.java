package cn.likoli.study.springboottest.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/scheduler")
@Slf4j
public class HelloScheduler {

    @GetMapping("/hello")
    public String hello() {
        log.info(new Date().toString());
        return "success";
    }
}
