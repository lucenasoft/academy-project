package br.com.lucenasoft.academy.controllers;

import br.com.lucenasoft.academy.models.StudentModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class StudentController {
    @GetMapping("/insertStudents")
    public ModelAndView InsertStudent(StudentModel student) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("student/formStudent");
        mv.addObject("student",new StudentModel());
        return mv;
    }

}
