//package com.springtutorials.Controller;
//
//import com.springtutorials.Service.SubjectService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
////@Controller
////@RequestMapping("/subject")
//public class SubjectController {
//
//    @Autowired
//    SubjectService subjectService;
//
//    @RequestMapping(value = "/add", method = RequestMethod.GET)
//    public String addSubject_GET(Model model) {
//        Subject subject = new Subject();
//        model.addAttribute("subject", subject);
//        return "subject/add";
//    }
//
//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    public String addSubject_POST(Model model, @ModelAttribute("subject") Subject subject) {
//        System.out.println(subject.toString());
////        subjectService.saveOrUpdate(subject);
//        return "subject/add";
//    }
//}
