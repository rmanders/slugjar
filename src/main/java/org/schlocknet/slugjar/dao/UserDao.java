package org.schlocknet.slugjar.dao;

import org.schlocknet.slugjar.model.UserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends MongoRepository<UserDetails, String> {
}
