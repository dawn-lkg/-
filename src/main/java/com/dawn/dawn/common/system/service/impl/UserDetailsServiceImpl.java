package com.dawn.dawn.common.system.service.impl;

import com.dawn.dawn.common.core.exception.BusinessException;
import com.dawn.dawn.common.system.entity.User;
import com.dawn.dawn.common.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



/**
 * @author chenliming
 * @date 2024/1/7 18:41
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        if(user.getUsername()==null){
            throw new BusinessException("账号不能为空");
        }
        return user;
    }
}
