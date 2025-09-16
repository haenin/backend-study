package com.haenin.chap04;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
//    @RequestMapping("/")
//    public String defaultLocation() {
//        return "main";
//    }
//    @RequestMapping("/main")
//    public void main(){}

    @RequestMapping(value={"/", "/main"})
    public String main() {return "main";}


}
