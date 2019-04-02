package com.sky.team.business.scheduler;


import com.sky.team.business.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyScheduler {

    @Autowired
    private UserDao userDao;
    @Scheduled(cron="0 0/10 * * * ?")
    public void deleteRegisterUser(){
        System.out.println("delete registerUser");
        userDao.deleteRegisterUser(new Date(new Date().getTime()-150000));
    }
}
