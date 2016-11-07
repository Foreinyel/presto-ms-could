package com.presto.api.book.entity;

import com.presto.common.dao.BaseEntity;

/**
 * 书
 *
 * Auto Generated by <code>CodeGenerator</code>
 */
public class Books extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private String bookName;//书名
    private String bookImgUrl;//book图片地址
    private String bookVersion;//书籍版本号
    private String bookIssn;//
    private String bookIsbn;//
    private String bookAsin;//
    private String bookAuthor;//作者
    private String bookDesc;//简介
    private String bookPrice;//定价
    private String bookPress;//出版社
    private String bookUrl;//书籍来源url
    private String bookSrc;//书籍来源站点

    /**
     * return 书名
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * @param bookName 书名
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * return book图片地址
     */
    public String getBookImgUrl() {
        return bookImgUrl;
    }

    /**
     * @param bookImgUrl book图片地址
     */
    public void setBookImgUrl(String bookImgUrl) {
        this.bookImgUrl = bookImgUrl;
    }

    /**
     * return 书籍版本号
     */
    public String getBookVersion() {
        return bookVersion;
    }

    /**
     * @param bookVersion 书籍版本号
     */
    public void setBookVersion(String bookVersion) {
        this.bookVersion = bookVersion;
    }

    /**
     * return 
     */
    public String getBookIssn() {
        return bookIssn;
    }

    /**
     * @param bookIssn 
     */
    public void setBookIssn(String bookIssn) {
        this.bookIssn = bookIssn;
    }

    /**
     * return 
     */
    public String getBookIsbn() {
        return bookIsbn;
    }

    /**
     * @param bookIsbn 
     */
    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    /**
     * return 
     */
    public String getBookAsin() {
        return bookAsin;
    }

    /**
     * @param bookAsin 
     */
    public void setBookAsin(String bookAsin) {
        this.bookAsin = bookAsin;
    }

    /**
     * return 作者
     */
    public String getBookAuthor() {
        return bookAuthor;
    }

    /**
     * @param bookAuthor 作者
     */
    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    /**
     * return 简介
     */
    public String getBookDesc() {
        return bookDesc;
    }

    /**
     * @param bookDesc 简介
     */
    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
    }

    /**
     * return 定价
     */
    public String getBookPrice() {
        return bookPrice;
    }

    /**
     * @param bookPrice 定价
     */
    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    /**
     * return 出版社
     */
    public String getBookPress() {
        return bookPress;
    }

    /**
     * @param bookPress 出版社
     */
    public void setBookPress(String bookPress) {
        this.bookPress = bookPress;
    }

    /**
     * return 书籍来源url
     */
    public String getBookUrl() {
        return bookUrl;
    }

    /**
     * @param bookUrl 书籍来源url
     */
    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    /**
     * return 书籍来源站点
     */
    public String getBookSrc() {
        return bookSrc;
    }

    /**
     * @param bookSrc 书籍来源站点
     */
    public void setBookSrc(String bookSrc) {
        this.bookSrc = bookSrc;
    }
}