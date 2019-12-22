package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "modify_photo")
public class ModifyPhoto {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "photo_id")
    private Integer photoId;

    private String operation;

    @Column(name = "bjs_operate")
    private Date bjsOperate;

    /**
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

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
     * @return operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * @param operation
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * @return bjs_operate
     */
    public Date getBjsOperate() {
        return bjsOperate;
    }

    /**
     * @param bjsOperate
     */
    public void setBjsOperate(Date bjsOperate) {
        this.bjsOperate = bjsOperate;
    }
}