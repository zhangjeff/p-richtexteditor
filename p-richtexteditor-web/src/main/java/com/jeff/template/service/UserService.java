package com.jeff.template.service;

import com.jeff.template.mapper.base.UserMapper;
import com.jeff.template.model.base.User;
import com.jeff.template.model.base.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public List<User> queryUserList() {
        UserExample example = new UserExample();
        List<User> userList = userMapper.selectByExample(example);
        return userList;
    }
}
