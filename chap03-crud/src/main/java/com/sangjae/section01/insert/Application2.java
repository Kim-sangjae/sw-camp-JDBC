package com.sangjae.section01.insert;

import java.util.Scanner;

// 콘솔을 통해 입출력 해보기 (View)
public class Application2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---메뉴추가---");
        System.out.print("메뉴명 : ");
        String menuName = sc.nextLine();
        System.out.print("메뉴 가격 : ");
        int menuPrice = sc.nextInt();
        System.out.print("카테고리 코드 : ");
        int categoryCode = sc.nextInt();
        sc.nextLine();
        System.out.print("판매상태 : ");
        String orderableStatus = sc.nextLine();

        // View -> Service로 사용자에게 입력받을 데이터 전달 하기위해
        // Menu 클래스를 별도로 만들어서 묶어서 전달

        MenuService menuService = new MenuService();
        Menu newMenu = new Menu(menuName,menuPrice,categoryCode,orderableStatus);

        menuService.registMenu(newMenu);
    }
}
