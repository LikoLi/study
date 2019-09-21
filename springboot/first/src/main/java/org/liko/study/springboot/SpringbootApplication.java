package org.liko.study.springboot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ServletComponentScan
@PropertySource(value = "classpath:my.properties", encoding = "utf-8")
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringbootApplication.class);
//        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
