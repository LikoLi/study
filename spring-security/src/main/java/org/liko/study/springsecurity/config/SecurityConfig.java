package org.liko.study.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: Liko
 * @Description:
 * @Date: Created at 19:37 2018/8/27
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").loginProcessingUrl("/login/form").failureUrl("/login-error").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("USERS")
                .and()
                .withUser("test").password(new BCryptPasswordEncoder().encode("test123")).roles("ADMIN");
    }

}
