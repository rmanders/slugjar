package org.schlocknet.slugjar.dao;

import org.schlocknet.slugjar.model.list.ListItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ListItemDao extends MongoRepository<ListItem, UUID> {

  List<ListItem> findByListId(UUID listId);
}
