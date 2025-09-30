package com.sangjae.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

// 이후 JDBC 관련 코드를 한번에 관리하기 위한 TEMPLATE 클래스 작성
public class JDBCTemplate {


    public static Connection getConnection() {
        Properties properties = new Properties();
        Connection con = null;
        try {

            properties.load(new FileReader("src/main/java/com/sangjae/config/jdbc-config.properties"));
            String url = properties.getProperty("url");
            con = DriverManager.getConnection(url, properties);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    // Connection 사용 후 닫는 메소드를 따로 분리해서 Service 계층에서 진행한다
    public static void close(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void close(Statement stmt) {
        try {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void close(ResultSet rest) {
        try {
            if (rest != null && !rest.isClosed()) {
                rest.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}// class

