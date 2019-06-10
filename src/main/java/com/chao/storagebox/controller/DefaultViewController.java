package com.chao.storagebox.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DefaultViewController
{
    @RequestMapping("/")
    public String index() {
        return "home.html";
    }
}
