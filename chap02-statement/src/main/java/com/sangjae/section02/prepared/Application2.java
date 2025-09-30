package com.sangjae.section02.prepared;

import java.sql.*;
import java.util.Scanner;

import static com.sangjae.common.JDBCTemplate.close;
import static com.sangjae.common.JDBCTemplate.getConnection;


public class Application2 {
    public static void main(String[] args) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("조회할 사번 입력 : ");
        int empId = sc.nextInt();


        try {
            // preparedStatement 는 placeholder 를 사용하여 하나의 문자열 형태로 쿼리 작성 가능
            pstmt = con.prepareStatement("SELECT * FROM employee " +
                    "where emp_id=? and ent_yn=?");
            // 쿼리 실행전에 placeholder 내용 설정
            pstmt.setInt(1,empId);
            pstmt.setString(2,"N");
            rest = pstmt.executeQuery();

            if(rest.next()){
                String empName = rest.getString("emp_name");
                int salary = rest.getInt("salary");
                String entYn = rest.getString("ent_yn");

                System.out.println(empId + " " + empName + " " + salary + " " + entYn);
            } else {
                System.out.println("없는 사원 번호입니다");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rest);
            close(pstmt);
            close(con);
        }


    }//main
}
