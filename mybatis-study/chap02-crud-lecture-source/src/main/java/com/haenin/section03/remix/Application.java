package com.haenin.section03.remix;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        MenuController mc = new MenuController();
        do {
            System.out.println("===== 메뉴 관리 =====");
            System.out.println("1. 메뉴 전체 조회");
            System.out.println("2. 메뉴 코드로 메뉴 조회");
            System.out.println("3. 신규 메뉴 추가");
            System.out.println("4. 메뉴 수정");
            System.out.println("5. 메뉴 삭제");
            System.out.println("9. 프로그램 종료");
            System.out.print("메뉴 관리 번호를 입력하세요: ");
            int num = sc.nextInt();
            switch(num) {
                case 1:
                    mc.findAllMenus();
                    break;
                case 2:
                    mc.findMenuByMenuCode(inputMenuCode());
                    break;
                case 3:
                    mc.registMenu(inputMenu());
                    break;
                case 4:
                    mc.modifyMenu(inputModifyMenu());
                    break;
                case 5:
                    mc.removeMenu(inputDeleteMenuCode());
                    break;
                case 9:
                    System.out.println("프로그램을 종료하겠습니다.");
                    return;
                default:
                    System.out.println("번호를 잘 확인하고 입력해 주세요.");
            }
        } while(true);
    }


    /* 설명. Map<String, String> 형태로
            web 예시처럼 (request의 parameter 개념으로)
            key, value 형태로 넘길 예정 */
    private static Map<String,String> inputMenuCode() {
        Scanner sc = new Scanner(System.in);
        System.out.println("조회할 메뉴를 입력하세요: ");
        String menuCode = sc.nextLine();

        Map<String, String> parameter = new HashMap<>();
        parameter.put("menuCode",menuCode);
        return parameter;
    }

    private static Map<String,String> inputMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("신규 메뉴의 이름을 입력해 주세요: ");
        String menuName = sc.nextLine();
        System.out.print("신규 메뉴의 가격을 입력해 주세요: ");
        String menuPrice = sc.nextLine();
        System.out.print("신규 메뉴의 카테고리 코드를 입력해 주세요: ");
        String categoryCode = sc.nextLine();

        Map<String, String> parameter = new HashMap<>();
        parameter.put("menuName",menuName);
        parameter.put("menuPrice",menuPrice);
        parameter.put("categoryCode",categoryCode);

        return parameter;
    }

    private static Map<String,String> inputModifyMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("변경할 메뉴의 번호를 입력해 주세요: ");
        String menuCode = sc.nextLine();
        System.out.print("변경할 메뉴의 이름을 입력해 주세요: ");
        String menuName = sc.nextLine();
        System.out.print("변경할 메뉴의 가격을 입력해 주세요: ");
        String menuPrice = sc.nextLine();

        Map<String, String> parameter = new HashMap<>();
        parameter.put("menuCode",menuCode);
        parameter.put("menuName",menuName);
        parameter.put("menuPrice",menuPrice);

        return parameter;
    }

    private static Map<String, String> inputDeleteMenuCode() {
        Scanner sc = new Scanner(System.in);
        System.out.println("삭제할 메뉴의 번호를 입력하세요: ");
        String menuCode = sc.nextLine();

        Map<String, String> parameter = new HashMap<>();
        parameter.put("menuCode",menuCode);

        return parameter;
    }

}