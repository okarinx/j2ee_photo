package com.company.project.service.impl;

import com.company.project.dao.ModifyPhotoMapper;
import com.company.project.model.ModifyPhoto;
import com.company.project.service.ModifyPhotoService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/12/20.
 */
@Service
@Transactional
public class ModifyPhotoServiceImpl extends AbstractService<ModifyPhoto> implements ModifyPhotoService {
    @Resource
    private ModifyPhotoMapper modifyPhotoMapper;

}
