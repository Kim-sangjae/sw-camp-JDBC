package com.sangjae.section01.connection;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Application2 {
    public static void main(String[] args) {
        // url 이나 user 정보가 너무 리터럴하게 (직관적으로) 적혀있다
        // 추후 유지보수를 위해서라도 따로 만들어놓고 쓰는게 좋다
        // properties 설정파일을 만들어 따로 명시 해놓고 가져와서 쓴다
        Properties properties = new Properties();
        Connection con = null;

        try {
            properties.load(
                    new FileReader("src/main/java/com/sangjae/section01/connection/jdbc-config.properties")
            );

            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");

            con = DriverManager.getConnection(
                    url, user, password
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
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
