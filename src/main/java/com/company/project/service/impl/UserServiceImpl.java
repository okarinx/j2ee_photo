package com.company.project.service.impl;

import com.company.project.dao.UserMapper;
import com.company.project.model.User;
import com.company.project.service.UserService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * Created by CodeGenerator on 2019/12/09.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserByName(String name) {
        System.out.println(name);
        return userMapper.selectByName(name);
    }


    @Override
    public String updateTokenByName(String name) {
        User user = userMapper.selectByName(name);
        Date date = new Date();
        user.setGmtModify(date);
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        userMapper.updateByPrimaryKey(user);
        return token;
    }

    public User getUserByToken(String token) {
        return userMapper.selectByToken(token);
    }

    @Override
    public List<User> getUserList(){
        List<User> users= new ArrayList<>();
        users=userMapper.selectUserList();
        return users;
    }

    @Override
    public User updateRoleByName(String name) {
        User user = userMapper.selectByName(name);
        user.setRole("admin");
        userMapper.updateByPrimaryKey(user);
        return user;
    }
}
