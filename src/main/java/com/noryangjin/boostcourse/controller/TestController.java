package com.noryangjin.boostcourse.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {

    @RequestMapping("/")
    @ResponseBody
    public String home(){
        return "좋았어";
    }

}
