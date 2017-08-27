package org.schlocknet.slugjar.dao;

import org.schlocknet.slugjar.model.list.NestedList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NestedListDao extends MongoRepository<NestedList, UUID> {

  List<NestedList> findByListOwner(String listOwner);
}
