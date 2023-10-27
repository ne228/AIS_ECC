package com.example.ais_ecc.controllers;


import com.example.ais_ecc.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.PermitAll;

@Controller
@RequestMapping("/home")
public class HomeController {


    @GetMapping()
    public String profile(Model model) {
        var auth =  SecurityContextHolder.getContext().getAuthentication();
        var user = (User)auth.getPrincipal();
        model.addAttribute("user",user);


        return "/Home/profile";
    }
}
