package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "modify_user")
public class ModifyUser {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "modify_user_id")
    private String modifyUserId;

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
     * @return modify_user_id
     */
    public String getModifyUserId() {
        return modifyUserId;
    }

    /**
     * @param modifyUserId
     */
    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId;
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