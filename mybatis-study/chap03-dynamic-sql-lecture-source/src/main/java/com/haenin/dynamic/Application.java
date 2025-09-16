package com.haenin.dynamic;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("==== 마이바티스 동적 SQL ====");
            System.out.println("1. if 확인하기");
            System.out.println("2. choose(when, otherwise) 확인하기");
            System.out.println("3. foreach 확인하기");
            System.out.println("4. trim(where, set) 확인하기");
            System.out.println("9. 종료하기");
            System.out.print("메뉴를 선택하세요: ");

            int input = sc.nextInt();
            switch (input) {
                case 1:
                    ifSubMenu();
                    break;
                case 2:
                    chooseSubMenu();
                    break;
                case 3:
                    foreachSubMenu();
                    break;
                case 4:
                    trimSubMenu();
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("번호를 잘못 입력하셨습니다.");
            }
        } while (true);
    }

    private static void trimSubMenu() {
        Scanner sc = new Scanner(System.in);
        MenuService ms = new MenuService();

        do {
            System.out.println("==== trim 서브 메뉴 ====");
            System.out.println("1. 검색 조건이 있는 경우 메뉴 코드로 조회," +
                                "단, 없으면 전체 조회");
            System.out.println("2. 메뉴 혹은 카테고리로 검색" +
                    "단, 메뉴와 카테고리가 둘 다 일치하는 경우도 검색하며," +
                    "검색 조건이 없는 경우 전체 조회");
            System.out.println("3. 원하는 메뉴 정보만 수정하기");
            System.out.println("9. 이전메뉴로");
            System.out.print("메뉴 번호를 입력해 주세요: ");
            int input = sc.nextInt();
            switch(input) {
                case 1:
                    ms.searchMenuByCodeOrSearchAll(inputAllOrOne());
                    break;
                case 2:
                    ms.searchMenuByNameOrCategory(inputSearchCriteriaMap());
                    break;
                case 3:
                    ms.modifyMenu(inputChangeInfo());
                    break;
                case 9:
                    return;
            }
        } while (true);
    }

    private static Map<String, Object> inputChangeInfo() {
        Scanner sc = new Scanner(System.in);

        System.out.print("변경할 메뉴 코드를 입력하세요: ");
        int menuCode = sc.nextInt();
        System.out.print("변경할 메뉴 이름을 입력하세요: ");
        sc.nextLine();
        String menuName = sc.nextLine();
        System.out.print("변경할 판매 여부를 결정해 주세요(Y/N): ");
        String orderableStatus = sc.nextLine().toUpperCase();

        Map<String, Object> critMap = new HashMap<>();
        critMap.put("menuCode", menuCode);
        critMap.put("menuName", menuName);
        critMap.put("orderableStatus", orderableStatus);

        return critMap;
    }
    private static SearchCriteria inputAllOrOne() {
        Scanner sc = new Scanner(System.in);
        System.out.print("검색 조건을 입력하시겠습니까?(예 or 아니오)");
        boolean hasSearchValue = "예".equals(sc.nextLine()) ? true : false;
        SearchCriteria searchCriteria = new SearchCriteria();
        if(hasSearchValue){
            System.out.print("검색할 메뉴 코드를 입력하세요: ");
            String menuCode = sc.nextLine();
            searchCriteria.setCondition("menuCode");
            searchCriteria.setValue(menuCode);
        }
        return searchCriteria;
    }

    private static void ifSubMenu() {
        Scanner sc = new Scanner(System.in);
        MenuService ms = new MenuService();

        do {
            System.out.println("==== if 서브 메뉴 ====");
            System.out.println("1. 메뉴 이름 혹은 카테고리명으로 검색하여 메뉴 목록 보여주기");
            System.out.println("2. 메뉴 이름 혹은 카테고리명으로 검색하여 메뉴 목록 보여주기");
            System.out.println("9. 이전 메뉴로");
            System.out.print("메뉴 번호를 입력해 주세요: ");
            int input = sc.nextInt();
            switch(input) {
                case 1:
                    ms.findMenuByPrice(inputPrice());
                    break;
                case 2:
                    ms.searchMenuByNameOrCategory(inputSearchCriteriaMap());
                    break;
                case 9:
                    return;
            }
        } while (true);
    }

    private static Map<String, Object> inputSearchCriteriaMap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("검색 조건을 입력하세요(category or name or both or none): ");
        String condition = sc.nextLine();

        Map<String, Object> criteria = new HashMap<>();
        if("category".equals(condition)){
            System.out.print("검색 할 카테고리 코드를 입력하세요: ");
            int categoryCode = sc.nextInt();

            criteria.put("categoryCode", categoryCode);
        }else if("name".equals(condition)){
            System.out.print("검색 할 메뉴 이름을 입력하세요: ");
            String nameValue = sc.nextLine();

            criteria.put("name", nameValue);
        }else if("both".equals(condition)){
            System.out.print("검색 할 카테고리 코드를 입력하세요: ");
            int categoryCode = sc.nextInt();
            sc.nextLine();
            System.out.print("검색 할 메뉴 이름을 입력하세요: ");
            String nameValue = sc.nextLine();

            criteria.put("categoryCode", categoryCode);
            criteria.put("name", nameValue);
        }
        return criteria;
    }

    private static int inputPrice() {
        Scanner sc = new Scanner(System.in);
        System.out.print("검색 할 가격대의 최대 금액을 입력해 주세요: "); // 0, 1만, 2만, 3만 구간에서 입력한 가격까지
        return sc.nextInt();
    }
    /* 설명. 검색을 위해 필요한 내용만을 가지는 SearchCriteria를
     *       반환하는 메소드 */
    private static SearchCriteria intputSearchCriteria() {
        Scanner sc = new Scanner(System.in);
        System.out.print("검색 기준을 입력해 주세요(name or category): ");
        String condition =sc.nextLine();
        System.out.print("검색어를 입력해 주세요: ");
        String value = sc.nextLine();

        return new  SearchCriteria(condition, value);
    }

    private static void chooseSubMenu() {
        Scanner sc = new Scanner(System.in);
        MenuService ms = new MenuService();

        do {
            System.out.println("==== choose 서브 메뉴 ====");
            System.out.println("1. 카테고리 상위 분류별 메뉴 보여주기(식사, 음료, 디저트)");
            System.out.println("9. 이전 메뉴로");
            System.out.print("메뉴 번호를 입력해 주세요: ");
            int input = sc.nextInt();
            switch (input) {
                case 1:
                    ms.searchMenuBySupCategory(inputSupCategory());
                    break;
                case 9:
                    return;
            }
        } while (true);
    }

    private static SearchCriteria inputSupCategory() {
        Scanner sc = new Scanner(System.in);
        System.out.print("메뉴의 상위 분류를 입력해 주세요(식사, 음료, 디저트): ");
        String value = sc.nextLine();

        return new SearchCriteria("category", value);
    }

    private static void foreachSubMenu() {
        Scanner sc = new Scanner(System.in);
        MenuService ms = new MenuService();

        do {
            System.out.println("==== foreach 서브 메뉴 ====");
            System.out.println("1. 랜덤한 메뉴 5개 추출해서 조회하기");
            System.out.println("9. 이전 메뉴로");
            System.out.print("메뉴 번호를 입력해 주세요: ");
            int input = sc.nextInt();
            switch (input) {
                case 1:
                    ms.searchMenuByRandomMenuCode(generateRandomMenuCodeList());
                    break;
                case 9:
                    return;
            }
        } while (true);
    }

    /* 설명. 중복되지 않는 메뉴 5개의 번호 생성 메소드 */
    private static List<Integer> generateRandomMenuCodeList() {
        Set<Integer> set = new HashSet<>();
        while(set.size() < 5){
            set.add((int)(Math.random() * 21) + 1);
        }
        /* 설명. Set -> List */
        // ArrayList의 Collection타입 매개변수 있는 생성자 활용
        List<Integer> list = new ArrayList<>(set);

        // 오름차순 정렬 필요 시
        Collections.sort(list);
        System.out.println("생성된 난수: " + list);
        return list;
    }
}
