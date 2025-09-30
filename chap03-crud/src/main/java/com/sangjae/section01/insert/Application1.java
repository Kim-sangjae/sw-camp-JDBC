package com.sangjae.section01.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.sangjae.common.JDBCTemplate.*;

public class Application1 {
    public static void main(String[] args) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        String sql = "insert into tbl_menu(menu_name,menu_price,category_code,orderable_status) "
                + "values(?,?,?,?)";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,"봉골레청국장");
            pstmt.setInt(2,12000);
            pstmt.setInt(3,4);
            pstmt.setString(4,"Y");

            // insert,update,delete 수행시에는 수행 된 행의 갯수를 int로 반환
            // 호출 메서드는 executeUpdate()
            result = pstmt.executeUpdate();

            // 서비스 내에서 트랜잭션을 컨트롤 하기위해 자동커밋을 false 처리한후 직접 수동으로 처리한다
            if(result > 0){
                commit(con);
            } else {
                rollback(con);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
        }
    }
}
