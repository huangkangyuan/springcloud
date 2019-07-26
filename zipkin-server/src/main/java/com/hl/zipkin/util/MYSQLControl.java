package com.hl.zipkin.util;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import com.hl.zipkin.model.JdBook;
import org.apache.commons.dbutils.QueryRunner;

/*
 * Mysql操作的QueryRunner方法
 * 一个数据库操作类，别的程序直接调用即可
 */
public class MYSQLControl {

    //根据自己的数据库地址修改
    static DataSource ds = MyDataSource.getDataSource("jdbc:mysql://127.0.0.1:3306/jd?useUnicode=true&characterEncoding=utf8");
    static QueryRunner qr = new QueryRunner(ds);
    //第一类方法
    public static void executeUpdate(String sql){
        try {
            qr.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //第二类数据库操作方法
    public static void executeInsert(List<JdBook> bookList) throws SQLException {
        /*
         * 定义一个Object数组，行列
         * 3表示列数，根据自己的数据定义这里面的数字
         * params[i][0]等是对数组赋值，这里用到集合的get方法
         * 
         */
        Object[][] params = new Object[bookList.size()][3];
        for ( int i=0; i<params.length; i++ ){
            params[i][0] = bookList.get(i).getBookID();
            params[i][1] = bookList.get(i).getBookName();
            params[i][2] = bookList.get(i).getBookPrice();
        }
        qr.batch("insert into book (bookID, bookName, bookPrice)"
                + "values (?,?,?)", params);
        System.out.println("执行数据库完毕！"+"成功插入数据："+bookList.size()+"条");
    }
}