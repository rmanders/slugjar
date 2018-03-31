package org.schlocknet.slugjar.dao;


import org.schlocknet.slugjar.model.blog.BlogPost;

import java.util.List;

public interface BlogPostDao {

  void saveOrUpdateBlogPost(BlogPost blogPost);

  void deleteBlogPost(String slug);

  BlogPost getBlogPost(String slug);

  List<BlogPost> getBlogPosts(long beginDate, int maxPosts);

}
