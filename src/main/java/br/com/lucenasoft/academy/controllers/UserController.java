package br.com.lucenasoft.academy.controllers;

import br.com.lucenasoft.academy.Exceptions.CriptoExistsException;
import br.com.lucenasoft.academy.models.UserModel;
import br.com.lucenasoft.academy.repositories.UserRepository;
import br.com.lucenasoft.academy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public ModelAndView signIn(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/sign-in");
        return mv;
    }

    @GetMapping("/register")
    public ModelAndView register(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", new UserModel());
        mv.setViewName("login/register");
        return mv;
    }

    @PostMapping("/saveUser")
    public ModelAndView saveUser(UserModel user) throws Exception {
        ModelAndView mv = new ModelAndView();
        userService.saveUser(user);
        mv.setViewName("redirect:/");
        return mv;
    }
}