package com.chiubilly.springbootmall.dao;

import com.chiubilly.springbootmall.dto.UserRegisterRequest;
import com.chiubilly.springbootmall.model.User;

public interface UserDao {

    User getUserById(Integer userId);
    User getUserByEmail(String email);

    Integer createUser(UserRegisterRequest userRegisterRequest);

}
