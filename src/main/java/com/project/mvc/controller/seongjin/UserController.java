package com.project.mvc.controller.seongjin;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    @GetMapping("sign-in")
    public String signInGet() {



        return "sign-in";
    }
}
