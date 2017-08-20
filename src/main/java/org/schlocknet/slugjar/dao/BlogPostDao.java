package org.schlocknet.slugjar.dao;

import org.schlocknet.slugjar.model.blog.BlogPost;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostDao extends MongoRepository<BlogPost, String>{
}
