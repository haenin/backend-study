package com.haenin.section01.insert;

import java.util.Scanner;

public class Application2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("메뉴의 이름을 입력하세요: ");
        String menuName = sc.nextLine();
        System.out.print("메뉴의 가격을 입력하세요: ");
        int menuPrice = sc.nextInt();
        System.out.print("카테고리 코드를 입력하세요: ");
        int categoryCode = sc.nextInt();
        System.out.print("판매 상태를 입력하세요: ");
        sc.nextLine();
        String orderableStatus = sc.nextLine();

        Menu menu = new Menu(menuName, menuPrice, categoryCode, orderableStatus);

        MenuService service = new MenuService();
        service.registMenu(menu);

    }
}
