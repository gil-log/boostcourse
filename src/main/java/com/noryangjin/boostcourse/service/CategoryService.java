package com.noryangjin.boostcourse.service;

import com.noryangjin.boostcourse.domain.Category;
import com.noryangjin.boostcourse.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    CategoryRepository categoryRepo;


    public List<Category> findAllCategories(){

        List<Category> category = new List<Category>();



        return category;
    }

}
