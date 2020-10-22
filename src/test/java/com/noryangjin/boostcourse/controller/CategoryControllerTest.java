package com.noryangjin.boostcourse.controller;

import com.noryangjin.boostcourse.repository.category.CategoryRepository;
import com.noryangjin.boostcourse.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    public void countingTest(){

        long result = categoryRepository.CategoryCounting(2);

        assertEquals(10, result);
    }

    @Test
    public void queryDslTest(){

        String result = "a";

/*

        final ResultActions actions = mvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(content().string(result));

*/

        //when
        //List<Category> results = categoryService.findAllCategories();

        //then
        //assertEquals(results, null);

    }

}
