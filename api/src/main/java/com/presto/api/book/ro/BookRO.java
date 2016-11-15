package com.presto.api.book.ro;

import lombok.Data;

/**
 * Created by shihao on 16/11/13.
 */
@Data
public class BookRO {

    private Long bookId;
    private String bookName;
    private String authorName;
    private String bookImgUrl;

}
