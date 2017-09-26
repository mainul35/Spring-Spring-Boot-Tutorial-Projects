package com.springtutorials.Service;

import com.springtutorials.Repository.StudentRepository;
import com.springtutorials.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Mainul35 on 9/20/2017.
 */

@Service
@Transactional
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public void save(Student student) {
        Student s = getStudentByEmail(student.getEmail());
        studentRepository.save(student);
    }

    public Student getStudentById(Integer id) {
        return this.findAll().stream()
                .filter(p -> Objects.equals(p.getStudentId(), id))
                .findFirst()
                .orElse(null);
    }

    public Student getStudentByEmail(String email){
        return studentRepository.getStudentByEmail(email);
    }

    public int update(Student student){
        System.out.println("The student is : "+student.toString());
        int res = studentRepository.updateStudent(
                student.getStudentName(),
                student.getSex(),
                student.getSubject(),
                student.getCountry(),
                student.getEmail());
        return res;
    }

    public boolean exists(String email) {
        return this.getStudentByEmail(email) instanceof Student ? true : false;
    }

    public List<Student> findAll() {
        List<Student> students = new ArrayList();
        studentRepository.findAll().forEach(students::add);
        return students;
    }

    public void delete(Integer id) {
        studentRepository.delete(id);
    }
}
