package br.com.lucenasoft.academy.controllers;

import br.com.lucenasoft.academy.models.StudentModel;
import br.com.lucenasoft.academy.repositories.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
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
    public ModelAndView addStudent(@Valid StudentModel student, BindingResult br) {
        ModelAndView mv = new ModelAndView();
        if (br.hasErrors()) {
            mv.addObject("student", student);
            mv.setViewName("student/formStudent");
        } else {
            System.out.println("Ta passando aqui, sa bomba");
            mv.setViewName("redirect:/list-students");
            student.generateEnrollment();
            repository.save(student);
        }
        return mv;
    }

    @GetMapping("/list-students")
    public ModelAndView getAllstudents() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("student/listStudents");
        mv.addObject("studentslist", repository.findAll());
        return mv;
    }

    @GetMapping("/alter/{id}")
    public ModelAndView alterStudent(@PathVariable("id") UUID id){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("student/formAlter");
        StudentModel student = repository.getReferenceById(id);
        mv.addObject("student", student);
        return mv;
    }

    @PostMapping("/alterStudent")
    public ModelAndView alterStudent(StudentModel student) {
        ModelAndView mv = new ModelAndView();
        repository.save(student);
        mv.setViewName("redirect:/list-students");
        return mv;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteStudent(@PathVariable("id") UUID id){
        ModelAndView mv = new ModelAndView();
        repository.deleteById(id);
        mv.setViewName("redirect:/list-students");
        return mv;
    }
}
