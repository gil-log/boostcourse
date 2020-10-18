package com.noryangjin.boostcourse.controller;

import com.noryangjin.boostcourse.domain.Category;
import com.noryangjin.boostcourse.dto.CategoryDTO;
import com.noryangjin.boostcourse.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> getAllCategoires(){

        List<Category> categories = categoryService.findAllCategories();

        Iterator<Category> categoryIterator = categories.iterator();

        List<CategoryDTO> categoriesDTO = new List<CategoryDTO>();

        while(categoryIterator.hasNext()){
            categoryIterator.next();
        }

        return categoryDTO;
    }

}
