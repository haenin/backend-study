package com.haenin.chap02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MethodMeappinggTestController {
    /* 설명. 핸들러 메소드는 어노테이션으로 요청을 매핑하고,
    *       반환하는 문자열로 VIEW를 선택한다. */
//    @RequestMapping(value="/menu/regist") //GET 요청 뿐 아니라 다른 요청도 받을 수 있음
    @RequestMapping(value="/menu/regist", method= RequestMethod.GET)
    // 모델 객체를 만들어줌
    public String registMenu(Model model){
        System.out.println("/menu/regist 경로의 요청 받아보기");

        model.addAttribute("message","신규 메뉴 등록용 핸들러 메소드 호출함...");
        // view의 이름(view가 될 html의 이름)
        return "mappingResult";
    }
    @RequestMapping(value="/menu/modify", method=RequestMethod.POST)
    public String modifyMenu(Model model){
        model.addAttribute("message","POST 방식의 메뉴 수집용 핸들러 메소드 호출함...");
        return "mappingResult";
    }

    @GetMapping("/menu/delete")
    public String getDeleterMenu(Model model){
        model.addAttribute("message","GET 방식의 메뉴 삭제용 핸들러 메소드 호출함...");
        return "mappingResult";
    }
    @PostMapping("/menu/delete")
    public String postDeleteMenu(Model model){
        model.addAttribute("message","POST 방식의 메뉴 삭제용 핸들러 메소드 호출함...");
        return "mappingResult";
    }


}
