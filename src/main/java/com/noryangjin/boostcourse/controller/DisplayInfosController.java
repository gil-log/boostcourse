package com.noryangjin.boostcourse.controller;

import com.noryangjin.boostcourse.dto.DisplayInfoDTO;
import com.noryangjin.boostcourse.dto.ProductDTO;
import com.noryangjin.boostcourse.service.CategoryServiceImpl;
import com.noryangjin.boostcourse.service.DisplayInfoServiceImpl;
import com.noryangjin.boostcourse.service.ProductService;
import com.noryangjin.boostcourse.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/displayinfos")
public class DisplayInfosController {

    @Autowired
    CategoryServiceImpl categoryService;

    @Autowired
    DisplayInfoServiceImpl displayInfoService;

    @Autowired
    ProductService productService;

    @Autowired
    ReservationService reservationService;

    @GetMapping
    public Map getDisplayInfos(@RequestParam(name="categoryId", required=false, defaultValue="0") long category_id,
                               @RequestParam(name="start", required=false, defaultValue="0") int start,
                               @RequestParam(name="displayId", required = false, defaultValue = "0") long display_id){


        Map<String, Object> map = new HashMap<>();

        // displayId에 대한 로직
        if(display_id != 0){

            // product 출력
            DisplayInfoDTO.DisplayInfos displayInfos = displayInfoService.findDisplayInfosByDisplayId(display_id);

            // productImages 출력
            ProductDTO.ProductImages productImages = productService.getProductImagesByProductId(displayInfos.getId());

            // displayInfoImages 출력
            DisplayInfoDTO.DisplayInfoImages displayInfoImages = displayInfoService.findDisplayInfoImagesByDisplayId(display_id);

            // avgScore 얻기
            int avgScore = (int) Math.round(reservationService.getAvgScoreByProductId(displayInfos.getId()));

            // productPrices 얻기
            List<ProductDTO.ProductPrices> productPrices = productService.findProductPriceByProductId(displayInfos.getId());

            map.put("product", displayInfos);
            map.put("productImages", productImages);
            map.put("displayInfoImages", displayInfoImages);
            map.put("avgScore", avgScore);
            map.put("productPrices", productPrices);

            return map;

        }

        // categoryId에 대한 로직
        long categoryCount = 0;
        if ( category_id==0){
            categoryCount = displayInfoService.CoutingDisplayInfo();
        } else{
            categoryCount = categoryService.categoriesCount(category_id);
        }
        List<DisplayInfoDTO.DisplayInfos> displayInfos = displayInfoService.findDisplayInfosByCategoryId(category_id, start);

        int productCount = displayInfos.size();

        // categoryId, name, description, content, event, fileid
        map.put("totalCount", categoryCount);

        map.put("productCount", productCount);

        map.put("products", displayInfos);

        return map;
    }

}
