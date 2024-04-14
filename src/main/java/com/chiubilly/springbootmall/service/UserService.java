package com.chiubilly.springbootmall.service;

import com.chiubilly.springbootmall.dto.UserRegisterRequest;
import com.chiubilly.springbootmall.model.User;

public interface UserService {

    User getUserById(Integer userId);
    Integer register(UserRegisterRequest userRegisterRequest);
}
