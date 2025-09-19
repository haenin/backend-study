package com.haenin.springdatajpa.main.controller;

import com.haenin.springdatajpa.menu.service.MenuService;
import jdk.jfr.Category;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    private final MenuService menuService;

    public MainController(MenuService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping(value = {"/main", "/"})
    public String main(){
        return "main/main";
    }

}
