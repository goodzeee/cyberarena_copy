package com.project.mvc.controller.seongjin;


import com.project.mvc.dto.seongjin.LoginDto;
import com.project.mvc.dto.seongjin.SignUpDto;
import com.project.mvc.service.seongjin.LoginResult;
import com.project.mvc.service.seongjin.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/sign-in")
    public String signInGet() {



        return "user/sign-in";
    }

    @PostMapping("/sign-in")
    public String signInPost(LoginDto dto, HttpSession session, HttpServletResponse response, RedirectAttributes ra) {

        LoginResult result = userService.validateLogin(dto, session, response);

        if(result == LoginResult.SUCCESS) {

            return "redirect:/index";
        } else {
            return "redirect:/user/sign-in";
        }
    }
    @GetMapping("/sign-out")
    public String signOutGet(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        if(WebUtils.getCookie(request, "auto") != null) {
            userService.autoLoginClear(request, response);
        }
        session.removeAttribute("login");
        session.invalidate();

        return "redirect:/index";
    }
    @GetMapping("/sign-up")
    public String signUpGet() {

        return "user/sign-up";
    }
    @PostMapping("/sign-up")
    public String signUpPost(SignUpDto dto) {
        boolean flag = userService.join(dto);

        return flag ? "redirect:/user/sign-in" : "redirect:/user/sign-up";
    }

    @GetMapping("/check")
    @ResponseBody
    public ResponseEntity<?> checkGet(String type, String keyword) {
        boolean flag = userService.checkIdentifier(type, keyword);

        return ResponseEntity
                .ok()
                .body(flag);

    }

}
