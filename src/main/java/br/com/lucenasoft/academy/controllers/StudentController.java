package br.com.lucenasoft.academy.controllers;

import br.com.lucenasoft.academy.models.StudentModel;
import br.com.lucenasoft.academy.repositories.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
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
            mv.setViewName("student/formStudent");
            mv.addObject("studentModel", student);
        } else {
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

    @GetMapping("filter-students")
    public ModelAndView filterStudents(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("student/filterStudent");
        return mv;
    }

    @GetMapping("/list-actives")
    public ModelAndView listAllactives() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("student/actives");
        mv.addObject("studentsActives", repository.findByStatusActive());
        return mv;
    }

    @GetMapping("/list-braided")
    public ModelAndView listAllbraided() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("student/braideds");
        mv.addObject("studentsBraided", repository.findByStatusBraided());
        return mv;
    }

    @GetMapping("/list-canceled")
    public ModelAndView listAllcanceled() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("student/canceleds");
        mv.addObject("studentsCanceled", repository.findByStatusCanceled());
        return mv;
    }

    @PostMapping("/search-student")
    public ModelAndView searchStudent(@RequestParam(required = false) String name){
        ModelAndView mv = new ModelAndView();
        List<StudentModel> listStudents;
        if(name == null || name.trim().isEmpty()){
            listStudents = repository.findAll();
        } else {
            listStudents = repository.findByNameContainingIgnoreCase(name);
        }
        mv.addObject("listStudents", listStudents);
        mv.setViewName("student/resultSearch");
        return mv;
    }
}
