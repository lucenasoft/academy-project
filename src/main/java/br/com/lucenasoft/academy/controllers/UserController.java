package br.com.lucenasoft.academy.controllers;

import br.com.lucenasoft.academy.Exceptions.EmailExistsException;
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
import java.util.UUID;

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
    public ModelAndView saveUser(@Valid UserModel user, BindingResult br, HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView();
        if(br.hasErrors()) {
            mv.setViewName("login/register");
            mv.addObject("user", user);
        } else {
            try {
                userService.saveUser(user);
                mv.setViewName("redirect:/");
                System.out.println("bateu aqui try");
            } catch (Exception e) {
                System.out.println("bateu aqui catch");
                mv.setViewName("login/register");
                mv.addObject("user", user);
                mv.addObject("error", "Email is already used.");
            }
        }
        return mv;
    }

    @GetMapping("/index")
    public ModelAndView index(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        UserModel userLogged = (UserModel) session.getAttribute("userLogged");
        if (userLogged == null) {
            return signIn();
        }
        mv.setViewName("home/index");
        mv.addObject("student", new StudentModel());
        return mv;
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
            mv.setViewName("login/sign-in");
        } else {
            String csrfToken = UUID.randomUUID().toString();
            session.setAttribute("userLogged", userSignIn);
            session.setAttribute("csrfToken", csrfToken);
            mv.setViewName("redirect:/index");
        }
        return mv;
    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpSession session){
        session.invalidate();
        return signIn();
    }
}
