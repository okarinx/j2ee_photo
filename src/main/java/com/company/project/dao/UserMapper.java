package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.User;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    User selectByName(String name);

    User selectByToken(String token);

    List<User> selectUserList();
}