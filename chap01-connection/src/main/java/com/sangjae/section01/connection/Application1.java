package com.sangjae.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application1 {
    public static void main(String[] args) {
        Connection con = null;

        try {
            // db 연결정보가 잘못 될 경우 Connection객체 생성 불가
            // SQLException 발생
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306","practice","practice"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try { // DBMS 와 연결도 입출력과 같이 사용하고 닫아줘야한다
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }




    }//main
}
