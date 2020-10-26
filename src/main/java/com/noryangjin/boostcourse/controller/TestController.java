package com.noryangjin.boostcourse.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {

    @RequestMapping("/")
    @ResponseBody
    public String home(){
        return "좋았어";
    }

    @RequestMapping("/ttt/{test}")
    @ResponseBody
    public String pathableTest(@PathVariable("test") String test){
        return test;
    }

    @RequestMapping("/ttt")
    @ResponseBody
    public String requestParamTest(@RequestParam("test") String test){
        return test;
    }

}
