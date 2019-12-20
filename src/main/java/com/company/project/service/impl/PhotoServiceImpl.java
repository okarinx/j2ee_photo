package com.company.project.service.impl;

import com.company.project.dao.FavorsMapper;
import com.company.project.dao.PhotoMapper;
import com.company.project.model.Favors;
import com.company.project.model.Photo;
import com.company.project.model.PhotoDetail;
import com.company.project.model.SortItem;
import com.company.project.service.PhotoService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by CodeGenerator on 2019/12/16.
 */
@Service
@Transactional
public class PhotoServiceImpl extends AbstractService<Photo> implements PhotoService {
    @Resource
    private PhotoMapper photoMapper;
    @Resource
    private FavorsMapper favorsMapper;

    @Override
    public PhotoDetail getPhotoDetail(int id, String name) {
        Photo photo = photoMapper.selectByPrimaryKey(id);
        PhotoDetail photoDetail = new PhotoDetail(photo);
        Favors favors = new Favors();
        favors.setPhotoId(id);
        favors.setUserName(name);
        photoDetail.setFavorite(favorsMapper.selectCount(favors) != 0);

        return photoDetail;
    }

    @Override
    public List<SortItem> getCountryList() {
        List<SortItem> sortItems = new ArrayList<>();
        String[] countries = photoMapper.selectCountry();
        if (countries == null) {
            return null;
        } else {
            for (int i = 0; i < countries.length; i++) {
                SortItem sortItem = new SortItem();
                sortItem.setValue(countries[i]);
                sortItem.setLabel(countries[i]);
                sortItems.add(sortItem);
            }
            return sortItems;
        }
    }

    @Override
    public List<SortItem> getScaleList() {
        List<SortItem> sortItems = new ArrayList<>();
        String[] scales = photoMapper.selectScale();
        if (scales == null) {
            return null;
        } else {
            for (int i = 0; i < scales.length; i++) {
                SortItem sortItem = new SortItem();
                sortItem.setValue(scales[i]);
                sortItem.setLabel(scales[i]);
                sortItems.add(sortItem);
            }
            return sortItems;
        }
    }

}
