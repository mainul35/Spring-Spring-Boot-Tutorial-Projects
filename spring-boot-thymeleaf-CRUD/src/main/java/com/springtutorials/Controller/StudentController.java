package com.springtutorials.Controller;

import com.springtutorials.Service.CountryService;
import com.springtutorials.Service.StudentService;
import com.springtutorials.Service.SubjectService;
import com.springtutorials.model.Country;
import com.springtutorials.model.Sex;
import com.springtutorials.Entity.Student;
import com.springtutorials.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Controller
@RequestMapping("/student")
public class StudentController {

    @ModelAttribute
    public void init(Model model) {
        System.out.println("called...................");
        List<Sex> sexList = new ArrayList<>();
        sexList.add(new Sex("Male"));
        sexList.add(new Sex("Female"));
        model.addAttribute("sexList", sexList);
    }

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private CountryService countryService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getStudentForm(Model model, @ModelAttribute("msg") String msg) {


        model.addAttribute("msg", "");
        System.out.println(msg);
        if (null != msg || !msg.isEmpty()) {
            model.addAttribute("msg", msg);
        }

        List<Subject> subjects = subjectService.findAll();

        List<Country> countries = countryService.findAll();



        Student student = new Student();

        model.addAttribute("subjects", subjects);
        model.addAttribute("countries", countries);
        model.addAttribute("student", student);



        return "student/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postStudentForm(Model model,
                                  @ModelAttribute("student") Student student,
                                  @RequestParam(value = "subjects", defaultValue = "") String subs,
                                  @RequestParam(value = "country", defaultValue = "") String country,
                                  BindingResult errors,
                                  RedirectAttributes redirectAttributes
    ) {


        if (null == student.getEmail() || student.getEmail().isEmpty()) {
            redirectAttributes.addFlashAttribute("msg", "Some fields are missing.");
            return "redirect:/student/create";
        }

        if (null == student.getSex() || student.getSex().isEmpty()) {
            redirectAttributes.addFlashAttribute("msg", "Some fields are missing.");
            return "redirect:/student/create";
        }

        if (null == student.getStudentName() || student.getStudentName().isEmpty()) {
            redirectAttributes.addFlashAttribute("msg", "Some fields are missing.");
            return "redirect:/student/create";
        }

        if (null == subs || subs.isEmpty()) {
            redirectAttributes.addFlashAttribute("msg", "Some fields are missing.");
            return "redirect:/student/create";
        }

        if (null == country || country.isEmpty()) {
            redirectAttributes.addFlashAttribute("msg", "Some fields are missing.");
            return "redirect:/student/create";
        }

        if (studentService.exists(student.getEmail())) {
            redirectAttributes.addFlashAttribute("msg", "This email is already registered.");
            return "redirect:/student/create";
        }

        List<Subject> subjects = subjectService.findAll();

        StringTokenizer stk = new StringTokenizer(subs, ",");

        while (stk.hasMoreTokens()) {
            String subjectName = stk.nextToken();
            int i = 0;
            for (Subject subject : subjects) {
                if (subject.getSubjectName().equalsIgnoreCase(subjectName)) {
                    subject.setSelected(true);
                }
            }
        }
        System.out.println(subjects.toString());

        List<Country> countries = countryService.findAll();

        for (Country c : countries) {
            if (c.getCountryName().equalsIgnoreCase(country)) {
                c.setSelected(true);
            }
        }
        List<Sex> sexList = new ArrayList<>();
        sexList.add(new Sex("Male"));
        sexList.add(new Sex("Female"));

        for (Sex sex : sexList) {
            if (sex.getType().equalsIgnoreCase(student.getSex())) {
                sex.setSelected(true);
            }
        }

        model.addAttribute("subjects", subjects);
        model.addAttribute("countries", countries);
        model.addAttribute("sexList", sexList);
        model.addAttribute("student", student);
        model.addAttribute("msg", "");

        student.setCountry(country);
        student.setSubject(subs);
        System.out.println(student.toString());
        studentService.save(student);
        return "redirect:/student/show";
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String viewStudents(Model model, @RequestParam(name = "page", defaultValue = "1") int pageNumber) {

        int resultPerPage = 3;
        int totalResults = studentService.count();
        int pages =(int)Math.ceil(((double)totalResults) / resultPerPage);
        System.out.println(pages);
        List<Student> students = studentService.getPage(pageNumber, resultPerPage);
        model.addAttribute("students", students);
        model.addAttribute("pages",
                resultPerPage == 1
                ?studentService.count()-1:resultPerPage==totalResults
                ?0:pages-1);
        model.addAttribute("currentPage", pageNumber);
        return "student/show";
    }

//    @RequestMapping(value = "/show-page/{page}", method = RequestMethod.GET)
//    public @ResponseBody
//    List<Student> fetchPageResult(@PathVariable("page") int pageNumber) {
//
//        List<Student> students = studentService.getPage(pageNumber);
//
//        return students;
//    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteStudents(Model model, @RequestParam("id") String id) {
        studentService.delete(Integer.parseInt(id));
        return "redirect:/student/show";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateStudent_GET(Model model, @RequestParam("id") Integer id) {
        Student student = studentService.getStudentById(id);

        List<Subject> subjects = subjectService.findAll();

        List<Country> countries = countryService.findAll();

        List<Sex> sexList = new ArrayList<>();
        sexList.add(new Sex("Male"));
        sexList.add(new Sex("Female"));


        StringTokenizer tokenizer = new StringTokenizer(student.getSubject(), ",");
        while (tokenizer.hasMoreTokens()) {
            String subject = tokenizer.nextToken();
            for (Subject s : subjects) {
                if (s.getSubjectName().equalsIgnoreCase(subject)) {
                    s.setSelected(true);
                }
            }
        }

        for (Country c : countries) {
            if (c.getCountryName().equalsIgnoreCase(student.getCountry())) {
                c.setSelected(true);
            }
        }

        for (Sex s : sexList) {
            if (s.getType().equalsIgnoreCase(student.getSex())) {
                s.setSelected(true);
            }
        }


        model.addAttribute("subjects", subjects);
        model.addAttribute("countries", countries);
        model.addAttribute("sexList", sexList);
        model.addAttribute("student", student);
        model.addAttribute("id", id);
        model.addAttribute("msg", "");

        return "student/update";
    }
//@RequestParam("id")int id,
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateStudent_POST(Model model,
                                     @ModelAttribute("student") Student student,
                                     @RequestParam(name = "subjects", defaultValue = "") String subs,
                                     @RequestParam(name = "country", defaultValue = "") String country,

                                     BindingResult errors,
                                     RedirectAttributes redirectAttributes
    ) {

        if (null == student.getEmail() || student.getEmail().isEmpty()) {
            redirectAttributes.addFlashAttribute("msg", "Some fields are missing.");
            return "redirect:/student/update";
        }

        if (null == student.getSex() || student.getSex().isEmpty()) {
            redirectAttributes.addFlashAttribute("msg", "Some fields are missing.");
            return "redirect:/student/update";
        }

        if (null == student.getStudentName() || student.getStudentName().isEmpty()) {
            redirectAttributes.addFlashAttribute("msg", "Some fields are missing.");
            return "redirect:/student/update";
        }

        if (null == subs || subs.isEmpty()) {
            redirectAttributes.addFlashAttribute("msg", "Some fields are missing.");
            return "redirect:/student/update";
        }

        if (null == country || country.isEmpty()) {
            redirectAttributes.addFlashAttribute("msg", "Some fields are missing.");
            return "redirect:/student/update";
        }

        List<Subject> subjects = subjectService.findAll();

        StringTokenizer stk = new StringTokenizer(subs, ",");

        while (stk.hasMoreTokens()) {
            String subjectName = stk.nextToken();
            int i = 0;
            for (Subject subject : subjects) {
                if (subject.getSubjectName().equalsIgnoreCase(subjectName)) {
                    subject.setSelected(true);
                }
            }
        }
        System.out.println(subjects.toString());

        List<Country> countries = countryService.findAll();

        for (Country c : countries) {
            if (c.getCountryName().equalsIgnoreCase(country)) {
                c.setSelected(true);
            }
        }
        List<Sex> sexList = new ArrayList<>();
        sexList.add(new Sex("Male"));
        sexList.add(new Sex("Female"));

        for (Sex sex : sexList) {
            if (sex.getType().equalsIgnoreCase(student.getSex())) {
                sex.setSelected(true);
            }
        }

        model.addAttribute("subjects", subjects);
        model.addAttribute("countries", countries);
        model.addAttribute("sexList", sexList);
        model.addAttribute("student", student);
        model.addAttribute("msg", "");

        student.setCountry(country);
        student.setSubject(subs);
        System.out.println(student.toString());
        studentService.update(student);
        return "redirect:/student/show";
    }

}
