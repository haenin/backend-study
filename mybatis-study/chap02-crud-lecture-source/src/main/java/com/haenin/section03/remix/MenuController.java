package com.haenin.section03.remix;

import java.util.List;
import java.util.Map;

public class MenuController {
    private final MenuService menuService;
    private final PrintResult printResult; // 응답 층력용 페이지 느낌으로 만든 클래스

    public MenuController() {
        this.menuService = new MenuService();
        printResult = new PrintResult();
    }

    public void findAllMenus() {
        List<MenuDTO> menuList = menuService.findAllMenus();
//        menuList != null 로 if문 작성할 수 없다.
        if(!menuList.isEmpty()){ // 하나의 메뉴라도 조회 성공
            printResult.printMenus(menuList);
        } else{                 // 조회 실패(하나의 메뉴도 없는 상태)
            printResult.printErrorMessage("전체 메뉴 조회 실패");
        }
    }

    public void findMenuByMenuCode(Map<String, String> parameter) {
        int menuCode = Integer.parseInt(parameter.get("menuCode"));
        MenuDTO menu = menuService.findMenuByMenuCode(menuCode);
        if(menu != null){
            printResult.printMenu(menu);
        }else{
            printResult.printErrorMessage(menuCode + "번의 메뉴는 없습니다!");
        }
    }

    public void registMenu(Map<String, String> parameter) {
        String menuName =  parameter.get("menuName");
        int menuPrice = Integer.parseInt(parameter.get("menuPrice"));
        int categoryCode = Integer.parseInt(parameter.get("categoryCode"));

        MenuDTO menu = new MenuDTO();
        menu.setMenuName(menuName);
        menu.setMenuPrice(menuPrice);
        menu.setCategoryCode(categoryCode);

        /* 설명. DML 작업 이후 Controller로는 boolean형이 돌아오게 작성할 예정 */
        if(menuService.registMenu(menu)){
            printResult.printSuccessMessage("regist");
        } else{
            printResult.printErrorMessage("메뉴 추가 실패!");
        }
    }

    public void modifyMenu(Map<String, String> parameter) {
        int menuCode = Integer.parseInt(parameter.get("menuCode"));
        String menuName = parameter.get("menuName");
        int menuPrice = Integer.parseInt(parameter.get("menuPrice"));

        MenuDTO menu = new MenuDTO();
        menu.setMenuCode(menuCode);
        menu.setMenuName(menuName);
        menu.setMenuPrice(menuPrice);

        if(menuService.modifyMenu(menu)){
            printResult.printSuccessMessage("modify");
        }else{
            printResult.printErrorMessage("메뉴 수정 실패!");
        }

    }

    public void removeMenu(Map<String, String> parameter) {
        int menuCode = Integer.parseInt(parameter.get("menuCode"));
        if(menuService.removeMenu(menuCode)){
            printResult.printSuccessMessage("delete");
        }else{
            printResult.printErrorMessage("메뉴 삭제 실패!");
        }

    }
}
