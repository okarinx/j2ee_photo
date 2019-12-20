package com.company.project.service.impl;

import com.company.project.dao.FavorsMapper;
import com.company.project.model.Favors;
import com.company.project.service.FavorsService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/12/17.
 */
@Service
@Transactional
public class FavorsServiceImpl extends AbstractService<Favors> implements FavorsService {
    @Resource
    private FavorsMapper favorsMapper;

}
