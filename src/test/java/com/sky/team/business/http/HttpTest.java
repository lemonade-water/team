package com.sky.team.business.http;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpTest {


    //192.168.43.163
    //

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void tuijian(){
        String url = "http://192.168.43.163:5000/sketch/close";
        MyBean myBean = new MyBean();
        myBean.setPid("2");
        myBean.setNum(2);
        myBean.setOpt("0");
        JSON json = restTemplate.postForObject(url, myBean, JSON.class);
        System.out.println(json);
    }

    @Test
    public void gettuijian(){
        String url = "http://192.168.43.163:5000/sketch/close/?pid=84&num=6&opt=0";
        String s = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(s);
        JSONArray flag = jsonObject.getJSONArray("flag");
        System.out.println(flag);

        String msg = jsonObject.getString("msg");
        System.out.println(msg);
    }
}
class MyBean{
    private String pid;
    private Integer num;
    private String opt;

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}