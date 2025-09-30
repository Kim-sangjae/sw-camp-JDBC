package com.sangjae.section03.delete;

import com.sangjae.section01.insert.Menu;
import com.sangjae.section01.insert.MenuService;

import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void select(){
        MenuService menuService = new MenuService();

        List<Menu> menus = menuService.selectMenuList();
        menus.stream().forEach(m -> System.out.println(
                m.getMenuCode()+"|"+
                        m.getMenuName()+"|"+
                        m.getMenuPrice() +"|"+
                        m.getCategoryCode() +"|"+
                        m.getOrderableStatus()
        ));

    };

    public static void main(String[] args) {
        select();

        MenuService menuService = new MenuService();
        Scanner sc = new Scanner(System.in);



        System.out.println("-- 메뉴 삭제 --");
        System.out.print("삭제할 메뉴 번호: ");
        int menuCode = sc.nextInt();


        menuService.deleteMenu(menuCode);



    }
}
