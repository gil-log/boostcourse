package com.noryangjin.boostcourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("main")
    public String main(Model model){
        String message = "테스트임";
        model.addAttribute("test", message);
        return "main";
    }

    @RequestMapping("test")
    public String test(Model model){
        String message = "테스트임";
        model.addAttribute("test", message);
        return "board/list";
    }
}
