package org.schlocknet.slugjar.dao;

import org.schlocknet.slugjar.model.list.ListDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ListDao extends MongoRepository<ListDetails, UUID> {
}
