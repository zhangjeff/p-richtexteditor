package com.jeff.template.model.base;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    /**
     * 主键，自增长，步长＝1
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 主键，自增长，步长＝1
     * @return id 主键，自增长，步长＝1
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键，自增长，步长＝1
     * @param id 主键，自增长，步长＝1
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 用户id
     * @return user_id 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 用户id
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 用户姓名
     * @return user_name 用户姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户姓名
     * @param userName 用户姓名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 创建时间
     * @return create_date 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}