package com.company.project.service;
import com.company.project.model.Photo;
import com.company.project.core.Service;
import com.company.project.model.PhotoDetail;
import com.company.project.model.SortItem;

import java.util.List;


/**
 * Created by CodeGenerator on 2019/12/16.
 */
public interface PhotoService extends Service<Photo> {
    PhotoDetail getPhotoDetail(int id,String name);
    List<SortItem> getCountryList();
    List<SortItem> getScaleList();
}
