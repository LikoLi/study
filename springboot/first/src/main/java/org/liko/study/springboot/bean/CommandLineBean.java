package org.liko.study.springboot.bean;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CommandLineBean implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("-----------CommandLineBean-----------------");
        System.out.println(Arrays.toString(args));
    }
}
