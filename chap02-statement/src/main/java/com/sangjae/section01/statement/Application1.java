package com.sangjae.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.sangjae.common.JDBCTemplate.close;
import static com.sangjae.common.JDBCTemplate.getConnection;


public class Application1 {
    public static void main(String[] args) {
        Statement stmt = null;
        ResultSet rest = null;
        Connection con = getConnection();
        try {
            // Statement : 쿼리를 운반하고 그결과를 반환하는 객체
            stmt = con.createStatement();
            // ResultSet : statement 객체를 통해 처리 된 결과를 다루는 객체
            rest = stmt.executeQuery("SELECT * FROM employee");

            while (rest.next()){ // next 를 통해 결과 행의 존재 여부 확인
                System.out.println(rest.getString("emp_name")+" "
                        + rest.getString("salary")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {

            close(rest);
            close(stmt);
            close(con);
        }


    }//main
}
