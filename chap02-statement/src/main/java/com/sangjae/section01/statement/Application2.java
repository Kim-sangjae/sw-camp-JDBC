package com.sangjae.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.sangjae.common.JDBCTemplate.close;
import static com.sangjae.common.JDBCTemplate.getConnection;


public class Application2 {
    public static void main(String[] args) {
        Statement stmt = null;
        ResultSet rest = null;
        Connection con = getConnection();
        Scanner sc = new Scanner(System.in);
        System.out.println("조회할 사번 입력 : ");
        int empId = sc.nextInt();


        try {
            stmt = con.createStatement();
            rest = stmt.executeQuery("SELECT emp_id , emp_name , salary FROM employee WHERE emp_id= " + empId);

            if(rest.next()){
                String empName = rest.getString("emp_name");
                int salary = rest.getInt("salary");
                System.out.println(empId + " " + empName + " " + salary);
            } else {
                System.out.println("없는 사원 번호입니다");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rest);
            close(stmt);
            close(con);
        }


    }//main
}
