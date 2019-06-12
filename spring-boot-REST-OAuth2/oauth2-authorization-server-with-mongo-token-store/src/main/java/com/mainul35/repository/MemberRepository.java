package com.mainul35.repository;

import com.mainul35.domain.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends MongoRepository<Member, String> {

    public Member findByUsername(String username);
}
