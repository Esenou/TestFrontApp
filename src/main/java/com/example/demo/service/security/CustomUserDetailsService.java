package com.example.demo.service.security;


import com.example.demo.enums.UserStatus;
import com.example.demo.model.UserCreat;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String password = request.getParameter("password");

        UserCreat userCreat = new UserCreat();
        userCreat.setUsername(username);
        userCreat.setPassword(password);
        userService.signIn(userCreat);

        UserModel user = this.userService.findByUsernameAndStatus(username, UserStatus.ACTIVE);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new UserPrincipal(user);
    }
}
