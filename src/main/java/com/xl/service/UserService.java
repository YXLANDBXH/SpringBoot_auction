package com.xl.service;

import com.xl.domain.User;

import java.util.List;

/**
 * @author XLong
 * @create 2021-08-04 21:54
 */
public interface UserService {
    //登录
    List<User> loginByUsernameAndPwd(String username,String userPassword);
}
