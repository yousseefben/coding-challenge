package com.coding.challenge.controller;

import com.coding.challenge.dto.SignupRequestDto;
import com.coding.challenge.repository.UserRepository;
import com.coding.challenge.utils.IntegrationTestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthControllerTest {


    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }


    @Test
    public void signupUserTest_ok() throws Exception {

        SignupRequestDto user = new SignupRequestDto("test@gmail.com", "password");

        mvc.perform(post("/api/auth/signup").contentType(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtil.convertObjectToJson(user)))
                .andExpect(status().isOk());

    }

    @Test
    public void signupUserTest_badRequest() throws Exception {

        mvc.perform(post("/api/auth/signup").contentType(MediaType.APPLICATION_JSON)
                .content("{tt: tt}"))
                .andExpect(status().isBadRequest());

    }

}