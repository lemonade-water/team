package com.sky.team.business.controller;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.team.business.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestController {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;
    @Test
    public void getStudentList() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/protected")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("")
                //使用writeValueAsString()方法来获取对象的JSON字符串表示
        ).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

    }
//  "token"
// "Bearer eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1NTcyNzkzMzcsInVzZXJuYW1lIjoiMTExIn0.SbvWPvaYEiWJYIRdHXvdNd09NHHzr7KpMQA5HgruPQXdnSWF2lu0tnliYOVZ-B_dj1rgeGSmMTN3_FGtCbRfpw"
    /*登录测试*/
    @Test
    public void login() throws Exception {
        User user = new User();
        /*设置参数*/
        user.setUserId("111");
        user.setUserPassword("111111");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(mapper.writeValueAsString(user))
                //使用writeValueAsString()方法来获取对象的JSON字符串表示
        ).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    public void autologin() throws Exception {

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/autoLogin")
                        .header("Authorization","'Bearer eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1NTcyNzkzMzcsInVzZXJuYW1lIjoiMTExIn0.SbvWPvaYEiWJYIRdHXvdNd09NHHzr7KpMQA5HgruPQXdnSWF2lu0tnliYOVZ-B_dj1rgeGSmMTN3_FGtCbRfpw'")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)

                //使用writeValueAsString()方法来获取对象的JSON字符串表示
        ).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

    }

}
