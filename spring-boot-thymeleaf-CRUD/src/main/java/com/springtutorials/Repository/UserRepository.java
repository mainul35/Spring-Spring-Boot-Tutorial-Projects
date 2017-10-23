package com.springtutorials.Repository;

import com.springtutorials.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    @Query("select u from User u where u.username = ?1")
    User findByUsername(@Param("username") String username);

    @Query("select u from User u where u.email = ?1")
    User findByEmail(@Param("email") String email);

}
