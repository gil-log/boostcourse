package com.noryangjin.boostcourse.controller;

import com.noryangjin.boostcourse.domain.Category;
import com.noryangjin.boostcourse.domain.User;
import com.noryangjin.boostcourse.dto.CategoryDTO;
import com.noryangjin.boostcourse.dto.ProductDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("main")
    public String main(Model model){

        // 로그인 후 로그인 유저 정보로 대체 예정 테스트용도임
        ProductDTO.ProductImages productImages = new ProductDTO.ProductImages();
        productImages.setFileName("testest하나둘셋");



        model.addAttribute("product", productImages);
        return "layout/basic";
    }

}
