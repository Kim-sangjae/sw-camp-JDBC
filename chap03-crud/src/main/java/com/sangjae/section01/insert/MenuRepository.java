package com.sangjae.section01.insert;

import com.mysql.cj.jdbc.result.ResultSetImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import static com.sangjae.common.JDBCTemplate.close;

// Repository (DAO)
// DBMS를 통해 수행되는 CRUD 작업 단위의 메서드 정의
public class MenuRepository {

    private String menuXml = "src/main/java/com/sangjae/mapper/MenuMapper.xml";


    public List<Menu> selectMenu(Connection con){
        PreparedStatement pstmt = null;
        Properties properties = new Properties();
        ResultSet rest = null;
        List<Menu> menus = new ArrayList<>();

        try {
            properties.loadFromXML(new FileInputStream(
                    menuXml
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String sql = properties.getProperty("selectMenu");
        try {
            pstmt = con.prepareStatement(sql);
            rest =  pstmt.executeQuery();
            while(rest.next()) {
                Menu menu = new Menu(
                        rest.getInt("menu_code"),
                        rest.getString("menu_name"),
                        rest.getInt("menu_price"),
                        rest.getInt("category_code"),
                        rest.getString("orderable_status")
                );
                menus.add(menu);

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rest);
            close(pstmt);
        }

    return menus;

    }


    public int insertMenu(Connection con , Menu menu){
        PreparedStatement pstmt = null;

        Properties properties = new Properties();
        int result = 0;

        try {
            properties.loadFromXML(
                    new FileInputStream(
                            menuXml
                    )
            );

            String sql = properties.getProperty("insertMenu");

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,menu.getMenuName());
            pstmt.setInt(2,menu.getMenuPrice());
            pstmt.setInt(3,menu.getCategoryCode());
            pstmt.setString(4,menu.getOrderableStatus());

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }


        return result;
    }



    public int updateMenu(Connection con ,int menuCode, String newName , int newPrice) {
        PreparedStatement pstmt = null;
        int result = 0;
        Properties properties = new Properties();
        try {
            properties.loadFromXML(
                    new FileInputStream(
                            menuXml
                    )
            );

            String sql = properties.getProperty("updateMenu");

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,newName);
            pstmt.setInt(2,newPrice);
            pstmt.setInt(3,menuCode);


            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        return result;
    }


    public int deleteByCode(Connection con, int menuCode) {
        PreparedStatement pstmt = null;
        Properties properties = new Properties();
        int result = 0;

        try {
            properties.loadFromXML(new FileInputStream(
                    menuXml
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String sql = properties.getProperty("deleteByCode");

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,menuCode);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        return  result;


    }
}
