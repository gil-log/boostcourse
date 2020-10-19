package com.noryangjin.boostcourse.controller;

import com.noryangjin.boostcourse.domain.Category;
import com.noryangjin.boostcourse.dto.CategoryDTO;
import com.noryangjin.boostcourse.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Map getAllCategoires(){

        List<CategoryDTO.Categories> categories = categoryService.findAllCategories();

        Long categoriesSize = categoryService.CategoriesSize();

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("size", categoriesSize);
        map.put("items", categories);

        return map;
    }

}
