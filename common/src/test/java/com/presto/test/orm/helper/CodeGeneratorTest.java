package com.presto.test.orm.helper;

import com.presto.common.code.helper.orm.CodeGenerator;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by shihao on 16/10/29.
 */
public class CodeGeneratorTest {

    BasicDataSource ds;

    @Before
    public void before() {
        ds = new BasicDataSource();
//        init(ds, "cplus");
//        init13(ds, "cplus");
        initDev(ds, "presto");
    }

    void init(BasicDataSource ds, String schema) {
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUsername("presto");
        ds.setPassword("presto");
        ds.setUrl("jdbc:mysql://rm-bp1b2p58at1agm3m9.mysql.rds.aliyuncs.com:3306/" + schema + "?useUnicode=true&characterEncoding=UTF-8");
    }

    void initDev(BasicDataSource ds, String schema) {
//        ds.setDriverClassName("com.mysql.jdbc.Driver");
//        ds.setUsername("landaojia");
//        ds.setPassword("LdjMySQL@#$20150826");
//        ds.setUrl("jdbc:mysql://121.196.237.206:16033/" + schema + "?useUnicode=true&characterEncoding=UTF-8");

        ds.setUsername("presto");
        ds.setPassword("presto");
        ds.setUrl("jdbc:mysql://120.77.16.217:3306/" + schema + "?useUnicode=true&characterEncoding=UTF-8");


    }


    void init13(BasicDataSource ds, String schema) {
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUsername("presto");
        ds.setPassword("presto");
        ds.setUrl("jdbc:mysql://192.168.2.13:3306/" + schema + "?useUnicode=true&characterEncoding=UTF-8");
    }

    @Test
    public void generate() throws Exception {
        generateEntity("com.presto.api.user.entity", "u_", "u_user");
        generateEntity("com.presto.api.user.entity", "u_", "u_account");
        generateEntity("com.presto.api.user.entity", "u_", "u_account_detail");
        generateEntity("com.presto.api.user.entity", "u_", "u_recharge_item");
        generateEntity("com.presto.api.user.entity", "u_", "u_invite_code");
        generateEntity("com.presto.api.plantBook.entity", "b_", "b_plant_book_req");
        generateEntity("com.presto.api.plantBook.entity", "b_", "b_user_book");
        generateEntity("com.presto.api.book.entity", "b_", "b_books");
        generateEntity("com.presto.api.order.entity", "b_", "b_order");
        generateEntity("com.presto.api.order.entity", "b_", "b_order_detail");
        generateEntity("com.presto.api.order.entity", "b_", "b_return_book");
        generateEntity("com.presto.api.order.entity", "b_", "b_continue_order");

    }

    private void generateEntity(String pacakgeName, String pre, String tableName) throws Exception {
        new CodeGenerator(ds, "presto", pacakgeName).generateSingleCode("../../ms-cloud/api/src/main/java/", pre, tableName);


    }

}
