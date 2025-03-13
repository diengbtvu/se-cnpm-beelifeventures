package com.beelifeventures.BeeLifeVentures.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UtilController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "BeeLifeVentures");
        return "/source/home";
    }
}