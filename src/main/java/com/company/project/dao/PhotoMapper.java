package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.Photo;
import com.company.project.model.PhotoDetail;

import java.util.List;

public interface PhotoMapper extends Mapper<Photo> {
    PhotoDetail getPhotoDetail(int id,String name);
    String[] selectCountry();
    String[] selectScale();
}