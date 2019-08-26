package com.hotelSys.dao;


import com.hotelSys.model.Login;
import com.hotelSys.model.User;

public interface UserDao {

    void register(User user);

    User validateUser(Login login);

}