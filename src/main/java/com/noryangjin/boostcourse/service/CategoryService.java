package com.noryangjin.boostcourse.service;

import com.noryangjin.boostcourse.dto.CategoryDTO;
import com.noryangjin.boostcourse.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    CategoryRepository categoryRepo;
/*

    public CategoryDTO findAllCategories(){

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(categoryRepo.findAllCategories().);


        return categoryDTO;
    }
*/

}
