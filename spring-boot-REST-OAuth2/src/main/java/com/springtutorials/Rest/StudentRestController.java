package com.springtutorials.Rest;

import com.springtutorials.Service.CountryService;
import com.springtutorials.Service.StudentService;
import com.springtutorials.Service.SubjectService;
import com.springtutorials.model.Country;
import com.springtutorials.model.Sex;
import com.springtutorials.model.Student;
import com.springtutorials.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@RestController
@RequestMapping("/rest/student")
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void postStudentForm(@RequestBody Student student) {
        studentService.save(student);
    }

    @RequestMapping(value = "/show/all", method = RequestMethod.GET)
    public List<Student> viewStudents(@RequestParam(name = "page", defaultValue = "1") int pageNumber) {

        int resultPerPage = 3;
        List<Student> students = studentService.getPage(pageNumber, resultPerPage);
        System.out.println(students.toString());
        return students;
    }

    @RequestMapping(value = "/show/{email:.+}", method = RequestMethod.GET)
    public Student viewStudentByEmail(@PathVariable("email") String email) {

        Student student = studentService.getStudentByEmail(email);

        return student;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteStudents(@PathVariable("id") String id) {
        studentService.delete(Integer.parseInt(id));
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void updateStudent_POST(@RequestBody Student student) {
        studentService.update(student);
    }

}

