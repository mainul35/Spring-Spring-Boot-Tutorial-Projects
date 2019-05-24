package com.springtutorials.Service;

import com.springtutorials.Repository.SubjectRepository;
import com.springtutorials.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    public void saveOrUpdate(Subject subject) {
        subjectRepository.save(subject);
    }

    public Subject getSubjectById(Integer id) {
        return this.findAll().stream()
                .filter(p -> Objects.equals(p.getSubjectId(), id))
                .findFirst()
                .orElse(null);
    }

    public boolean exists(Integer id) {
        return this.getSubjectById(id) instanceof Subject ? true : false;
    }

    public List<Subject> findAll() {
        List<Subject> subjects = new ArrayList();
//        subjectRepository.findAll().forEach(subjects::add);
        subjects.add(new Subject(1, "Bangla", false));
        subjects.add(new Subject(2, "English", false));
        subjects.add(new Subject(3, "Math", false));
        subjects.add(new Subject(4, "ICT", false));
        subjects.add(new Subject(5, "Chemistry", false));
        subjects.add(new Subject(6, "Physics", false));

        return subjects;
    }

    public void delete(Integer id) {
        subjectRepository.delete(id);
    }
}
