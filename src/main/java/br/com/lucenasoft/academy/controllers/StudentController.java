package br.com.lucenasoft.academy.controllers;

import br.com.lucenasoft.academy.models.StudentModel;
import br.com.lucenasoft.academy.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.Serializable;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentController{
    @Autowired
    private StudentRepository repository;

    @GetMapping("/insertStudents")
    public ModelAndView InsertStudent(StudentModel student) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("student/formStudent");
        mv.addObject("student",new StudentModel());
        return mv;
    }

    @PostMapping("/addStudents")
    public ModelAndView addStudent(StudentModel student) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/insertStudents");
        student.generateEnrollment();
        repository.save(student);
        return mv;
    }
}
