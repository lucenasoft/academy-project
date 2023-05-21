package br.com.lucenasoft.academy.controllers;

import br.com.lucenasoft.academy.models.StudentModel;
import br.com.lucenasoft.academy.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.Serializable;
import java.util.UUID;

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
        mv.setViewName("redirect:/list-students");
        student.generateEnrollment();
        repository.save(student);
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
