package com.mainul35.chainservice.repositories.sqlRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mainul35.chainservice.model.domain.sqlDomains.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

    @Query("select u from UserEntity u where u.username = ?1")
    UserEntity findByUsername(@Param("username") String username);

    @Query("select u from UserEntity u where u.email = ?1")
    UserEntity findByEmail(@Param("email") String email);
    
    @Query("delete from UserEntity u where u.id = ?1")
	void deleteById(@Param("id") Long id);
}
