package com.presto.api.plantBook.ro;

import lombok.Data;

import java.util.Date;

/**
 * Created by shihao on 16/12/13.
 */
@Data
public class PlantBookReqRO {

    private Long id;
    private Integer status;
    private Integer plantType;
    private Integer plantMethod;
    private Long bookId;
    private String bookName;
    private String bookAuthor;
    private String bookPress;
    private String bookIsbn;
    private String bookImgUrl;
    private Date createdDate;
    private Long userId;
    private String mobile;
    private String name;

}
