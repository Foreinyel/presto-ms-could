package com.presto.common.constants;

/**
 * Created by shihao on 16/11/2.
 */
public class CommonConstants {

    public static class PlantBookType{
        public static final int DONATE = 0; //捐赠书籍给平台
        public static final int SHARE = 1;  //分享书籍给平台
        public static final int KEEP = 2;   //书籍自己保留,其他可借阅
    }

    public static class PlantBookMethod{
        public static final int QRCODE = 0; //扫码植书
        public static final int SEARCH = 1; //搜索植书
        public static final int MANUAL = 2; //手工植书
    }

    public static class PlantBookReqStatus{
        public static final int NEW = 0; //新建
        public static final int APPROVE = 1; //新建
        public static final int REJECT = 2; //新建

    }

}
