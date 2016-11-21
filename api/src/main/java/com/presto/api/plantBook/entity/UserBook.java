package com.presto.api.plantBook.entity;

import com.presto.common.dao.BaseEntity;

/**
 * 用户分享书籍表
 *
 * Auto Generated by <code>CodeGenerator</code>
 */
public class UserBook extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private Long bookId;//书籍ID
    private Long userId;//用户ID
    private Integer pickMethod;//取书方式:0-上门自取,1-平台配送
    private String pickAddress;//取书地址

    /**
     * return 书籍ID
     */
    public Long getBookId() {
        return bookId;
    }

    /**
     * @param bookId 书籍ID
     */
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    /**
     * return 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * return 取书方式:0-上门自取,1-平台配送
     */
    public Integer getPickMethod() {
        return pickMethod;
    }

    /**
     * @param pickMethod 取书方式:0-上门自取,1-平台配送
     */
    public void setPickMethod(Integer pickMethod) {
        this.pickMethod = pickMethod;
    }

    /**
     * return 取书地址
     */
    public String getPickAddress() {
        return pickAddress;
    }

    /**
     * @param pickAddress 取书地址
     */
    public void setPickAddress(String pickAddress) {
        this.pickAddress = pickAddress;
    }
}