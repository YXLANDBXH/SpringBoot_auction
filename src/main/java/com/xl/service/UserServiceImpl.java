package com.xl.service;

import com.xl.domain.User;
import com.xl.domain.UserExample;
import com.xl.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XLong
 * @create 2021-08-04 21:55
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户名和密码登录
     * @param username userPassword
     * @return
     */
    @Override
    public List<User> loginByUsernameAndPwd(String username,String userPassword) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andUserpasswordEqualTo(userPassword);
        List<User> userList = this.userMapper.selectByExample(userExample);
        return userList;
    }

    /**
     * 注册功能
     * @param user
     */
    @Override
    public void userRegister(User user) {
        this.userMapper.insert(user);
    }
}
