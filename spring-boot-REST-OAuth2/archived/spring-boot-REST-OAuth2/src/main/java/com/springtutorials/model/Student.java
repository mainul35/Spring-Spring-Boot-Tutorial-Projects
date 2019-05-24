package com.springtutorials.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 11)
    private Integer studentId;
    @Column(name = "name", nullable = false, length = 100)
    private String studentName;
    @Column(name = "sex", nullable = false, length = 10)
    private String sex;
    @Email
    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;
    @Column(name = "subjects", nullable = false, length = 200)
    private String subject;
    @Column(name = "country", nullable = false, length = 30)
    private String country;

    public Student() {
    }

    public Student(String studentName, String sex, String email, String subject, String country) {
        this.studentName = studentName;
        this.sex = sex;
        this.email = email;
        this.subject = subject;
        this.country = country;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", subject='" + subject + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
