package com.scsb.springsecurity01.controller;

import com.scsb.springsecurity01.entity.User;
import com.scsb.springsecurity01.repository.UserRepository;
import com.scsb.springsecurity01.sercive.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {
    @Autowired
    private UserService userService;


    @RequestMapping({"/", "/login"})
    public String login() {
        return "login";
    }

    @RequestMapping({"/login-success"})
    public String success(Model model, Authentication authentication) {
        // 透過認證資訊取得當前登入使用者的 username
        String loginUsername = authentication.getName();

        // 取得使用者資料
        User user = userService.getUserByUsername(loginUsername);

        // 將 user 放入 model 屬性中
        model.addAttribute("user", user);
        return "success";
    }

    @RequestMapping({"/login-error"})
    public String error() {
        return "error";
    }

}
