package com.webclass.carservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping(value = {"/", "/clients", "/logout"})
    public String index() {
        return "index";
    }
}
