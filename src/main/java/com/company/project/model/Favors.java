package com.company.project.model;

import javax.persistence.*;

public class Favors {
    @Id
    @Column(name = "photo_id")
    private Integer photoId;

    @Column(name = "user_name")
    private String userName;

    /**
     * @return photo_id
     */
    public Integer getPhotoId() {
        return photoId;
    }

    /**
     * @param photoId
     */
    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}