package com.sky.team.business.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.team.business.pojo.Comment;
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
import java.util.Date;
import java.util.UUID;
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
//Bearer eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1NTczNDE5MzEsInVzZXJuYW1lIjoiMjQ1MCJ9.hDReQqf0pzFgjwgbvnmwdWtNdC4x8mpOeGC1d0GVevVRUNT0wfyED634pdSoF4c4GHbUau5sCKvrvlpsnC7Zow
    /*登录测试*/
    @Test
    public void login() throws Exception {
        User user = new User();
        /*设置参数*/
        user.setUserId("2450");
        user.setUserPassword("135792468");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(mapper.writeValueAsString(user))
                //使用writeValueAsString()方法来获取对象的JSON字符串表示
        ).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    /*注册邮箱*/
    @Test
    public void getEmail() throws Exception {
        User user = new User();
        /*设置参数*/
        user.setUserId("2430");
        user.setUserEmail("1107229735@qq.com");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/getEmail")
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
    /*/api/comment*/
    @Test
    public void testComment()throws Exception{
        Comment comment = new Comment(UUID.randomUUID().toString(),null,"欢迎使用","111","111",new Date(),"1");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/comment")
                        .header("Authorization","'Bearer eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1NTcyNzkzMzcsInVzZXJuYW1lIjoiMTExIn0.SbvWPvaYEiWJYIRdHXvdNd09NHHzr7KpMQA5HgruPQXdnSWF2lu0tnliYOVZ-B_dj1rgeGSmMTN3_FGtCbRfpw'")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(mapper.writeValueAsString(comment))
                //使用writeValueAsString()方法来获取对象的JSON字符串表示
        ).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

    }

}
