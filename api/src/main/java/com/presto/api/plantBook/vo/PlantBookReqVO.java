package com.presto.api.plantBook.vo;

import lombok.Data;

/**
 * Created by shihao on 16/11/2.
 */
@Data
public class PlantBookReqVO {

    private Long id;
    private Long userId;
    private Integer status;
    private Integer plantType;
    private Integer plantMethod;
    private Long bookId;
    private String bookName;
    private String bookAuthor;
    private String bookPress;
    private String bookIsbn;
    private String bookImgUrl;
    private Integer deletedFlag;

}
