package com.noryangjin.boostcourse.controller;

import com.noryangjin.boostcourse.dto.DisplayInfoDTO;
import com.noryangjin.boostcourse.service.CategoryServiceImpl;
import com.noryangjin.boostcourse.service.DisplayInfoServiceImpl;
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

    @GetMapping
    public Map getDisplayInfos(@RequestParam(name="categoryid", required=false, defaultValue="0") long category_id,
                               @RequestParam(name="start", required=false, defaultValue="0") int start){
        Map<String, Object> map = new HashMap<>();

        // category_id = 0 일때 어떻게 할지 정해야한다.
        long categoryCount = 0;
        if ( category_id==0){
            categoryCount = displayInfoService.CoutingDisplayInfo();
        } else{
            categoryCount = categoryService.categoriesCount(category_id);
        }
        List<DisplayInfoDTO.DisplayInfos> displayInfos = displayInfoService.findDisplayInfos(category_id, start);

        int productCount = displayInfos.size();

        // categoryId, name, description, content, event, fileid
        map.put("totalCount", categoryCount);

        map.put("productCount", productCount);

        map.put("products", displayInfos);

        return map;
    }

}
