package com.springtutorials.Repository;

import com.springtutorials.Entity.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, Integer> {
    @Modifying(clearAutomatically = true)
    @Query("update Student s set s.studentName = ?1, s.sex = ?2, s.subject = ?3, s.country = ?4 where s.email = ?5")
    int updateStudent(@Param("studentName") String studentName,
                      @Param("sex") String sex,
                      @Param("subject") String subject,
                      @Param("country") String country,
                      @Param("email") String email
    );

    @Query("select s from Student s where s.email = ?1")
    Student getStudentByEmail(@Param("email") String email);


}
