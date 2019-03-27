package com.sky.team.business.dao;


import com.sky.team.business.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UserDao {
    User getUser(String userid);

    void registerUser(String userid,String username, String userpassword, String useremail, String code, Date date);

    User selectRegister(String userid,String userpassword,String useremail);

    void deleteRegisterUser(Date date);

    void adadUser(String userid,String username, String userpassword, String useremail, String userdepartment, String emailCode);
}
