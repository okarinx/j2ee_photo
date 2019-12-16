package com.company.project.service.impl;

import com.company.project.dao.PhotoMapper;
import com.company.project.model.Photo;
import com.company.project.service.PhotoService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/12/16.
 */
@Service
@Transactional
public class PhotoServiceImpl extends AbstractService<Photo> implements PhotoService {
    @Resource
    private PhotoMapper photoMapper;

}
