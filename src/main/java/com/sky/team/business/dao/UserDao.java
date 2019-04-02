package com.sky.team.business.dao;


import com.sky.team.business.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UserDao {
    User getUser(String userid);

    void registerUser(String userid,String username, String userpassword, String useremail, String code, Date date);

    User selectRegister(String userid,String useremail);

    void deleteRegisterUser(Date date);

    void addUser(String userId,String userName,String userPassword,String userEmail,String userEmailCode,String userDepartment);

    void updateLoginTime(String userid, Date date);

    void updatePassword(User user);

    boolean updateUser(User user);
}
