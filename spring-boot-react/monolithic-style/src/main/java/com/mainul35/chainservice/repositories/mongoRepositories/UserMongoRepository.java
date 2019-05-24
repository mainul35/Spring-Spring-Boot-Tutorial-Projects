package com.mainul35.chainservice.repositories.mongoRepositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mainul35.chainservice.model.domain.mongoDomains.User;


@Repository
public interface UserMongoRepository extends MongoRepository<User, String> {
}
