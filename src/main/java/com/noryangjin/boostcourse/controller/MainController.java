package com.noryangjin.boostcourse.controller;

import com.noryangjin.boostcourse.domain.Category;
import com.noryangjin.boostcourse.domain.User;
import com.noryangjin.boostcourse.dto.CategoryDTO;
import com.noryangjin.boostcourse.dto.ProductDTO;
import com.noryangjin.boostcourse.dto.PromotionDTO;
import com.noryangjin.boostcourse.repository.promotion.PromotionRepository;
import com.noryangjin.boostcourse.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("main")
public class MainController {

    @Autowired
    PromotionService promotionService;

    @GetMapping
    public String getMain(Model model){

        // 로그인 후 로그인 유저 정보로 대체 예정 테스트용도임
        ProductDTO.ProductImages productImages = new ProductDTO.ProductImages();
        productImages.setFileName("testest하나둘셋");
        model.addAttribute("product", productImages);

        // 프로모션 정보
        List<PromotionDTO.Promotions> promotionsList = promotionService.getPromotions();
        model.addAttribute("promotions", promotionsList);

        return "layout/basic";
    }

}
