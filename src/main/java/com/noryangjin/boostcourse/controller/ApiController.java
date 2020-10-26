package com.noryangjin.boostcourse.controller;

import com.noryangjin.boostcourse.dto.*;
import com.noryangjin.boostcourse.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/*")
public class ApiController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DisplayInfoService displayInfoService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private PromotionService promotionService;

    @GetMapping
    @RequestMapping("/categories")
    public Map getAllCategoires(){

        List<CategoryDTO.Categories> categories = categoryService.findAllCategories();

        Long categoriesSize = categoryService.CategoriesSize();

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("size", categoriesSize);
        map.put("items", categories);

        return map;
    }

    @GetMapping
    @RequestMapping("/displayinfos")
    public Map getDisplayInfos(@RequestParam(name="categoryId", required=false, defaultValue="0") long category_id,
                               @RequestParam(name="start", required=false, defaultValue="0") int start,
                               @RequestParam(name="productId", required = false, defaultValue = "0") long product_id){


        Map<String, Object> map = new HashMap<>();

        // productId에 대한 댓글 로직
        if(product_id != 0){
            List<ReservationDTO.ReservationUserComments> reservationUserComments
                    = reservationService.getReservationUserCommentsByProductId(product_id, start);

            long totalCount = reservationService.countReservationUserCommentByProductId(product_id);
            long commentCount = reservationUserComments.size();

            map.put("totalCount", totalCount);
            map.put("commentCount", commentCount);
            map.put("reservationUserComments", reservationUserComments);

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

    @GetMapping
    @RequestMapping("/displayinfos/{displayId}")
    public Map getDisplayInfosByDisplayId(@PathVariable(name = "displayId") long display_id){

        Map<String, Object> map = new HashMap<>();

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



    @GetMapping
    @RequestMapping("/promotions")
    public Map getPromotions(){

        List<PromotionDTO.Promotions> promotions = promotionService.getPromotions();

        Map<String, Object> map = new HashMap<>();

        map.put("size", promotions.size());
        map.put("items", promotions);

        return map;
    }
}
