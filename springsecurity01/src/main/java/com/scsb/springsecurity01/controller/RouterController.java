package com.scsb.springsecurity01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {

    @RequestMapping({"/", "/login"})
    public String login() {
        return "login";
    }

    @RequestMapping({"/login-success"})
    public String success() {
        return "success";
    }

    @RequestMapping({"/login-error"})
    public String error() {
        return "error";
    }

}
