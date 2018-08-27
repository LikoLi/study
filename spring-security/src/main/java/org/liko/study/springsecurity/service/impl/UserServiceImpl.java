package org.liko.study.springsecurity.service.impl;

import org.liko.study.springsecurity.model.User;
import org.liko.study.springsecurity.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @Author: Liko
 * @Description:
 * @Date: Created at 20:52 2018/8/27
 */
public class UserServiceImpl implements UserService, UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("admin")) {
            User user = new User("admin", "123456", "ROLE_ADMIN", true, true, true, true);
            return user;
        }
        return null;
    }
}
