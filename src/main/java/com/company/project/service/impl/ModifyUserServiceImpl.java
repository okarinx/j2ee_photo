package com.company.project.service.impl;

import com.company.project.dao.ModifyUserMapper;
import com.company.project.model.ModifyUser;
import com.company.project.service.ModifyUserService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/12/20.
 */
@Service
@Transactional
public class ModifyUserServiceImpl extends AbstractService<ModifyUser> implements ModifyUserService {
    @Resource
    private ModifyUserMapper modifyUserMapper;

}
