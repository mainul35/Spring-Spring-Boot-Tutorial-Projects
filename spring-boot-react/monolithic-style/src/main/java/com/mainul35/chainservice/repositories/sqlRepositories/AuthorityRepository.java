package com.mainul35.chainservice.repositories.sqlRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mainul35.chainservice.model.domain.sqlDomains.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long>{
	
	@Query("select a from Authority a where a.id = ?1")
	Authority findAuthorityById(@Param("id")Long id);
	
	Authority findByAuthority(String authority);
}
