package com.company.project.service;
import com.company.project.model.User;
import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2019/12/09.
 */
public interface UserService extends Service<User> {
    User getUserByName(String name);
    String updateTokenByName(String name);
    User getUserByToken(String token);
}
