package org.liko.study.springboot.session3.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "config")
@Data
public class Config {
    private String code;
    private String name;
    private List<String> hobby;
}
