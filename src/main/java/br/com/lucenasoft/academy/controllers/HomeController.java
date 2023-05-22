package br.com.lucenasoft.academy.controllers;

import br.com.lucenasoft.academy.models.StudentModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
public class HomeController {
    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        model.setViewName("home/index");
        model.addObject("student", new StudentModel());
        return model;
    }
}
