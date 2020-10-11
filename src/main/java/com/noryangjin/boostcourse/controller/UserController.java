package com.noryangjin.boostcourse.controller;

import com.noryangjin.boostcourse.domain.User;
import com.noryangjin.boostcourse.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

//    UserController(UserService userService){
//
//        this.userService = userService;
//
//    }

    @GetMapping
    @ResponseBody
    public String checkEmail(@RequestParam("email") String email) throws Exception {

        logger.info("call checkEmailController");

        User userResult = userService.checkEmail(email);

        String result = "";

        if(userResult == null){
            result = "그런건 없어용";
            return result;
        } else {
            result = userResult.getEmail() + " 님이 있어용";
            return result;
        }
    }




}
