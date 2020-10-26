package com.noryangjin.boostcourse.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(TestController.class)
public class SecurityTestController {

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser
    public void loginTest() throws Exception {
        mockMvc.perform(ge)
                .accept(MediaType.TEXT_HTML)
                .andDo(print())
                .andExpect(status)
    }
}
