package com.sangjae.section02.prepared;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.sangjae.common.JDBCTemplate.close;
import static com.sangjae.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rest = null;

        // preparedStatement : 쿼리를 운반 , 결과를 반환하는객체
        // 객체를 생성할때 sql 구문을 미리 설정한다
        try {
            pstmt = con.prepareStatement("select * from employee");

            // statement 객체 생성할때 sql을 전달했으므로 rest쪽으로 그냥 객체만 보내면 된다
            rest = pstmt.executeQuery();

            while (rest.next()){ // next 를 통해 결과 행의 존재 여부 확인
                System.out.println(rest.getString("emp_name")+" "
                        + rest.getInt("salary")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            close(rest);
            close(pstmt);
            close(con);
        }

    }
}
