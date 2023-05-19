package br.com.lucenasoft.academy.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
public class HomeController {
    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        model.setViewName("home/index");
        return model;
    }
}
