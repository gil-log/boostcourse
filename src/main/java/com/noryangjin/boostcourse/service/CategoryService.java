package com.noryangjin.boostcourse.service;

import com.noryangjin.boostcourse.domain.Category;
import com.noryangjin.boostcourse.dto.CategoryDTO;
import com.noryangjin.boostcourse.repository.category.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    CategoryRepository categoryRepo;


    public List<CategoryDTO.Categories> findAllCategories(){

        logger.info("CategoryService > findAllCategories()");
        List<Category> category = categoryRepo.findAllCategories();

        Iterator<Category> categoryIterator = category.iterator();

        List<CategoryDTO.Categories> categoriesList = new LinkedList<CategoryDTO.Categories>();

        while(categoryIterator.hasNext()){
            CategoryDTO.Categories categories = new CategoryDTO.Categories(categoryIterator.next());

            categories.setCount(categoriesCount(categories.getId()));

            categoriesList.add(categories);
            //logger.info("DTO : " +categories.getId() +", "+categories.getName());
        }
        return categoriesList;
    }

    public Long CategoriesSize(){
        return categoryRepo.CategoriesSize();
    }

    public Long categoriesCount(Long category_id){
        return categoryRepo.CategoryCounting(category_id);
    }

}
