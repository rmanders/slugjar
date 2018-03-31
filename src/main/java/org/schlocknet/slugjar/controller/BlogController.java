package org.schlocknet.slugjar.controller;

import org.schlocknet.slugjar.dao.BlogPostDao;
import org.schlocknet.slugjar.model.blog.BlogPost;
import org.schlocknet.slugjar.model.response.ApiResponse;
import org.schlocknet.slugjar.model.response.ApiResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/blog/posts")
public class BlogController {

  /** Local logger */
  private static final Logger LOGGER = LoggerFactory.getLogger(BlogController.class);

  private final BlogPostDao blogPostDao;

  @Autowired
  public BlogController(BlogPostDao blogPostDao) {
    if (blogPostDao == null) {
      throw new IllegalArgumentException("Argument: \"blogPostDao\" cannot be null");
    }
    this.blogPostDao = blogPostDao;
  }


  @RequestMapping(method= RequestMethod.POST, consumes = "application/json", produces="application/json")
  public ApiResponse makeNewPost(
      @RequestBody BlogPost blogPost,
      HttpServletResponse response ) {
    LOGGER.info("Got request to post new blog entry");
    blogPostDao.saveOrUpdateBlogPost(blogPost);
    return new ApiResponse(ApiResponseStatus.SUCCEEDED);
  }

  @RequestMapping(method=RequestMethod.GET, produces = "application/json")
  public List<BlogPost> getBlogPosts(
      @RequestParam(value = "beginDate", defaultValue = "0") long beginDate,
      @RequestParam(value = "maxPosts", defaultValue = "10") int maxPosts
  ) {
    LOGGER.debug("Got request for /api/blog/posts");
    return blogPostDao.getBlogPosts(
        beginDate == 0 ? System.currentTimeMillis() : beginDate,
        maxPosts);
  }
}
