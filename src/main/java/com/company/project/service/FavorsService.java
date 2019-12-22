package com.company.project.service;
import com.company.project.model.Favors;
import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2019/12/17.
 */
public interface FavorsService extends Service<Favors> {
    String setFavors(String username,int photoId);
}
