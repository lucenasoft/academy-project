package br.com.lucenasoft.academy.controllers;

import br.com.lucenasoft.academy.Exceptions.CriptoExistsException;
import br.com.lucenasoft.academy.Exceptions.ServiceExc;
import br.com.lucenasoft.academy.models.StudentModel;
import br.com.lucenasoft.academy.models.UserModel;
import br.com.lucenasoft.academy.repositories.UserRepository;
import br.com.lucenasoft.academy.services.UserService;
import br.com.lucenasoft.academy.util.Util;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public ModelAndView signIn(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", new UserModel());
        mv.setViewName("login/sign-in");
        return mv;
    }

    @GetMapping("/register")
    public ModelAndView register(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("userModel", new UserModel());
        mv.setViewName("login/register");
        return mv;
    }

    @PostMapping("/saveUser")
    public ModelAndView saveUser(@Valid UserModel user, BindingResult br) throws Exception {
        ModelAndView mv = new ModelAndView();
        if(br.hasErrors()) {
            mv.setViewName("login/register");
            mv.addObject("user", user);
        } else {
            userService.saveUser(user);
            mv.setViewName("redirect:/");
        }
        return mv;
    }

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        model.setViewName("home/index");
        model.addObject("student", new StudentModel());
        return model;
    }

    @PostMapping("/sign-in")
    public ModelAndView signIn(@Valid UserModel user, BindingResult br, HttpSession session) throws NoSuchAlgorithmException, ServiceExc {
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", new UserModel());
        if(br.hasErrors()) {
            mv.setViewName("login/sign-in");
        }
        UserModel userSignIn = userService.signUser(user.getUsername(), Util.md5(user.getPassword()));
        if(userSignIn == null) {
            mv.addObject("msg","User not found");
        } else {
            session.setAttribute("userLogged",userSignIn);
            return index();
        }
        return mv;
    }

}
