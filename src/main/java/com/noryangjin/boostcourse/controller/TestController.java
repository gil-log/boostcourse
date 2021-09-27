package com.noryangjin.boostcourse.controller;

import com.noryangjin.boostcourse.domain.User;
import com.noryangjin.boostcourse.domain.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;


@Controller
public class TestController {
    
    @GetMapping("/logintest")
    public String loginTest() {
        return "test";
    }

    @PostMapping("/registertest")
    public String createTest(User user){

        UserRole userRole = new UserRole();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        //https://xmfpes.github.io/spring/spring-security/
        //user.
    }

}
