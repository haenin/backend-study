package com.haenin.chap04;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ResolverController {
    /* 설명.
    *   ViewResolver(뷰리졸버): 인터페이스를 구현한 ThymleafResolver가 현재 처리하게 된다.
    *   접두사(prefix): classpath:/templates/
    *   접미사(suffix): .html
    *   핸들러 메소드가 반환하는 문자열은 뷰리졸버로 향하게 되고
    *   'redirect:'가 들어간 문자열의 경우에는
    *   접두사와 접미사가 추가되지 않는다.
    * */

    @GetMapping("string")
    public String stringReturning(Model model){
        model.addAttribute("forwardMessage", "문자열로 뷰 이름 반환함...");
        return "result";
    }

    @GetMapping("string-redirect")
    public String stringRedirect(Model model){
        /* 설명. redirect 되어 요청이 다시 왔을 때
                응답되는 view의 재료로 담는걸 시도 */
        /* 설명. 결과: 모델에 담는걸로 전달 x*/
        model.addAttribute("message1","문자열로 뷰 이동");
        return "redirect:/";
    }

    @GetMapping("string-redirect-attr")
    public String stringRedirectFlashAttr(RedirectAttributes rttr){
        /* 설명. 스프링부트에서 리다이렉트 시에 값이 전달되게 하고 싶다면
                RedirectAttribute에 FlashAttribute로 추가한다.*/
        /* 설명. 추가로 내부적으로는 HttpSession을 활용하기 때문에
                Flash Attribute의 키 값이 기존 session의 키 값과 중복되면 X*/
        rttr.addFlashAttribute("flashMessage1","문자열로 뷰 이름 반환하면 리다이렉트");
        return "redirect:/";
    }

    /* 설명. forward시에는 Model에 재료를,
            redirect시에는 RedirectAttribute에 재료를 담는다.  */

    @GetMapping("modelandview")
    // 모델과 뷰가 합쳐진 타입
    public ModelAndView modelAndViewTest(ModelAndView mv){
        // 모델
        mv.addObject("forwardMessage", "ModelAndView를 이용한 forward");
        // 뷰
        mv.setViewName("result");
        return mv;
    }

    @GetMapping("modelandview-redirect")
    public ModelAndView modelAndViewRedirect(ModelAndView mv){
        /* 설명. url 경로 상에 쿼리스티링형태로 파라미터로 넘어간다 */
        mv.addObject("message2","ModelAndView를 이용한 redirect");
        mv.setViewName("redirect:/");

        return mv;
    }

    @GetMapping("modelandview-redirect-attr")
    public ModelAndView modelAndViewRedirectFlashAttr(ModelAndView mv,
                                                      RedirectAttributes rttr){
        rttr.addFlashAttribute("flashMessage2",
                "ModelAndView를 이용한 redirect attr");
        mv.setViewName("redirect:/");

        return mv;
    }
}
