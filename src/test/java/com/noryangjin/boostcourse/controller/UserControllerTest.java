package com.noryangjin.boostcourse.controller;

import com.noryangjin.boostcourse.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void userEmailCheck() throws Exception{

        String request = "kimmy@connect.co.kr2";

        String failResult = "그런건 없어용";

        String successResult = request + " 님이 있어용";

        given(userService.checkEmail(request))
                .willReturn(null);

        final ResultActions actions = mvc.perform(get("/user?email=kimmy@connect.co.kr2"))
                .andExpect(status().isOk())
                .andExpect(content().string(failResult));

    }
}
