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

            // 커밋 설정 자동 커밋을 수동 커밋으로 설정 하여 트랜잭션 컨트롤
            con.setAutoCommit(false);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }


    // close
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



    // 트랜잭션 컨트롤을 위한 메서드 추가
    // 커밋
    public static void commit(Connection con){
        try {
            if(con != null && !con.isClosed()) con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 롤백
    public static void rollback(Connection con){
        try {
            if(con != null && !con.isClosed()) con.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}// class

