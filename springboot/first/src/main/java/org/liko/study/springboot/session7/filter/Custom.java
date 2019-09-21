package org.liko.study.springboot.session7.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class Custom {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(customFilter());
        filterRegistrationBean.setName("customFilter");
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(10);
        return filterRegistrationBean;
    }

    @Bean
    public Filter customFilter() {
        return new CustomFilter();
    }
}
