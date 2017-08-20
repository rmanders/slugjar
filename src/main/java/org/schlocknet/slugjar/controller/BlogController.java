package org.schlocknet.slugjar.controller;

import org.schlocknet.slugjar.dao.BlogPostDao;
import org.schlocknet.slugjar.model.blog.BlogPost;
import org.schlocknet.slugjar.model.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/blog/posts")
public class BlogController {

  /** Local logger */
  private static final Logger LOGGER = LoggerFactory.getLogger(BlogController.class);

  @Autowired
  BlogPostDao blogPostDao;

  @RequestMapping(method= RequestMethod.POST, consumes = "application/json", produces="application/json")
  public ApiResponse<String> makeNewPost(
      @RequestBody BlogPost blogPost,
      HttpServletResponse response ) {
    LOGGER.info("Got request to post new blog entry");
    blogPostDao.save(blogPost);
    return new ApiResponse<String>("success");
  }
}
