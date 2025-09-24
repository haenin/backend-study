package com.haenin.springdatajpa.menu.controller;

import com.haenin.springdatajpa.common.Pagination;
import com.haenin.springdatajpa.common.PagingButtonInfo;
import com.haenin.springdatajpa.menu.dto.CategoryDTO;
import com.haenin.springdatajpa.menu.dto.MenuDTO;
import com.haenin.springdatajpa.menu.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/menu")
@Slf4j
public class MenuController {

    private final MenuService menuService;

    /* 설명.
    *   Logger를 활용한 로그 생성
    *   1. println보다 성능적으로 우수하다.(빠르고 리소스를 적게 먹는다.)
    *   2. 외부 리소스 파일로 따로 저장 및 송출이 가능하다.
    *   3. 로그레벨에 따른 확인 가능하다.(개발: debug, 서비스:info) */
//    Logger logger = LoggerFactory.getLogger(MenuController.class);
//    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /* 설명. url 경로 상에 넘어온 값을
            변수에 바로 담을 수 있다.
            -> PathVariable    */
    @GetMapping("/{menuCode}")
    public String findMenuByCode(
            // 문자열에서 숫자로 파싱해서 담음
            @PathVariable int menuCode,
            Model model){
        System.out.println("menuCode = " + menuCode);
        log.debug("menuCode ={}", menuCode);

        MenuDTO menu = menuService.findMenuByCode(menuCode);

        model.addAttribute("menu",menu);

        return "menu/detail";
    }

//    /* 설명. 페이징 처리 전 */
//    @GetMapping("/list")
//    public String findMenuList(Model model){
//        List<MenuDTO> menuList = menuService.findMenuList();
//        model.addAttribute("menuList", menuList);
//        return "menu/list";
//    }

    /* 설명.
    *   @PageableDefault
    *   1. 기본 한 페이지에 10개의 데이트 ->value
    *   2. 기본 1페이지부터(0부터)
    *   3. 기본 오름차순(ASC)*/
    /* 설명. 페이징 처리 후 */
    @GetMapping("/list")
    public String findMenuList(
            @PageableDefault Pageable pageable, Model model){
        log.debug("pageable: {}",pageable);
        Page<MenuDTO> menuList = menuService.findMenuList(pageable);
        log.debug("조회한 내용 목록: {}", menuList.getContent());
        log.debug("총 페이지 수: {}", menuList.getTotalPages());
        log.debug("총 메뉴 수: {}", menuList.getTotalElements());
        log.debug("해당 페이지에 표시 될 요소 수: {}", menuList.getSize());
        log.debug("해당 페이지에 실제 요소 수: {}", menuList.getNumberOfElements());
        log.debug("Page의 number가 처음이면(첫 페이지면): {}",menuList.isFirst() );
        log.debug("Page의 number가 마지막이면(마지막 페이지면): {}",menuList.isLast() );
        log.debug("현재 페이지: {}", menuList.getNumber());
        log.debug("정렬 기준: {}", menuList.getSort());

        /* 설명. Page 객체를 통해 PagingButtonInfo 추출
        *       -> front가 페이징 처리 버튼을
        *       그리기 위한 재료를 지님 */
        PagingButtonInfo paging = Pagination.getPagingButtonInfo(menuList);
        model.addAttribute("menuList",menuList);
        model.addAttribute("paging",paging);
        return "menu/list";
    }

    @GetMapping("/regist")
    public void registMenu(){}

    @GetMapping("/category")
    @ResponseBody
    // 핸들러 메서드의 반환형이 View Resolver를 무시해야 할 때
    // -> json문자열로 변환
    public List<CategoryDTO> findCategoryList(){
        return menuService.findAllCategory();
    }

    @PostMapping("/regist")
    public String registMenu(MenuDTO newMenu){
        menuService.registMenu(newMenu);
        return "redirect:/menu/list";
    }

    @GetMapping("/modify")
    public void modifyMenuPage(){}

    @PostMapping("/modify")
    public String modifyMenu(MenuDTO modifyMenu){
        menuService.modifyMenu(modifyMenu);
        return "redirect:/menu/" + modifyMenu.getMenuCode();
    }

    @GetMapping("/delete")
    public void deleteMenuPage(){}

    @PostMapping("/delete")
    public String deleteMenu(@RequestParam int menuCode){
        menuService.deleteMenu(menuCode);
        return "redirect:/menu/list";
    }

    @GetMapping("/querymethod")
    public void queryMethodPage(){}

    @GetMapping("/search")
    public String findMenuPrice(@RequestParam int menuPrice,
                                Model model){
        List<MenuDTO> menuList = menuService.findMenuPrice(menuPrice);
        model.addAttribute("menuList",menuList);
        model.addAttribute("menuPrice",menuPrice);
        return "menu/searchResult";
    }
}
