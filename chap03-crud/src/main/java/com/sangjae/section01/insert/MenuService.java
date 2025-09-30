package com.sangjae.section01.insert;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.sangjae.common.JDBCTemplate.close;
import static com.sangjae.common.JDBCTemplate.getConnection;

// Service : 비즈니스 로직 구현하는 계층
// 기능의 수행 결과에 따라 commit , rollback 처리도 여기서 수행
public class MenuService {

    public List<Menu> selectMenuList(){
        Connection con = getConnection();
        MenuRepository menuRepository = new MenuRepository();

        List<Menu> menus = menuRepository.selectMenu(con);

        close(con);

        return menus;
    }


    public void registMenu(Menu newMenu) {
        Connection con = getConnection();
        MenuRepository menuRepository = new MenuRepository();

        int result = menuRepository.insertMenu(con, newMenu);
        try {
            if (result > 0) {

                con.commit();

            } else {
                con.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        close(con);
    }


    // update
    public void updateMenu(int menuCode , String newName , int newPrice){
        Connection con = getConnection();
        MenuRepository menuRepository = new MenuRepository();

        int result = menuRepository.updateMenu(con,menuCode,newName,newPrice);

        try {
            if (result > 0) {
                con.commit();
            } else {
                con.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        close(con);
    }


    public void deleteMenu(int menuCode) {
        Connection con = getConnection();
        MenuRepository menuRepository = new MenuRepository();

        int result = menuRepository.deleteByCode(con , menuCode);

        try {
            if(result>0){
                con.commit();
            }else {
                con.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
        }

    }
}//class
