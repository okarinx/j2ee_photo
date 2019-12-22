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

    @Override
    public String setFavors(String username,int photoId) {
        Favors favors = new Favors();
        favors.setUserName(username);
        favors.setPhotoId(photoId);

        if(favorsMapper.selectOne(favors)!=null){
            favorsMapper.delete(favors);
            return "取消收藏";
        }else{
            favorsMapper.insert(favors);
            return "增加收藏";
        }
    }

}
