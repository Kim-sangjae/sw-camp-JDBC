package com.sangjae.section02.update;

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

        System.out.println("-- 메뉴 변경 --");
        Scanner sc = new Scanner(System.in);
        System.out.print("변경할 메뉴 번호 : ");
        int menuCode = sc.nextInt();
        System.out.print("변경할 메뉴 이름 : ");
        sc.nextLine();
        String menuName = sc.nextLine();
        System.out.print("변경할 메뉴 가격 : ");
        int menuPrice = sc.nextInt();

        menuService.updateMenu(menuCode,menuName,menuPrice);
    }
}
