package com.noryangjin.boostcourse.controller;

import com.noryangjin.boostcourse.dto.PromotionDTO;
import com.noryangjin.boostcourse.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/promotions")
public class PromotionsController {

    @Autowired
    PromotionService promotionService;

    @GetMapping
    public Map getPromotions(){

        List<PromotionDTO.Promotions> promotions = promotionService.getPromotions();

        Map<String, Object> map = new HashMap<>();

        map.put("size", promotions.size());
        map.put("items", promotions);

        return map;
    }
}
