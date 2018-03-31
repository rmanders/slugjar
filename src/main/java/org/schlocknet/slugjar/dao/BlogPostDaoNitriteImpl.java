package org.schlocknet.slugjar.dao;

import org.dizitart.no2.*;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.schlocknet.slugjar.model.blog.BlogPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

public class BlogPostDaoNitriteImpl implements BlogPostDao {

  /** Local logger instance */
  private static final Logger LOGGER = LoggerFactory.getLogger(BlogPostDaoNitriteImpl.class);

  /** The database collection to store these objects in */
  private static final String DB_COLLECTION_NAME = "BlogPosts";

  /** Provides access to the Nitrite database */
  private final Nitrite nitriteDb;

  /** provides access to the collection */
  private final NitriteCollection blogPostCollection;

  /** Object repository for Blog Posts */
  final ObjectRepository<BlogPost> objectRepository;

  /**
   * Default Constructor
   */
  public BlogPostDaoNitriteImpl(Nitrite nitriteDb) {
    if (nitriteDb == null) {
      throw new IllegalArgumentException("Aurgument: nitriteDb cannot be null");
    }
    this.nitriteDb = nitriteDb;
    this.blogPostCollection = nitriteDb.getCollection(DB_COLLECTION_NAME);
    this.objectRepository = this.nitriteDb.getRepository(BlogPost.class);
  }

  @Override
  public void saveOrUpdateBlogPost(BlogPost blogPost) {
    final Cursor<BlogPost> cursor = objectRepository.find(eq("blogEntryId",blogPost.getBlogEntryId()));
    if (cursor.size() == 0) {
      LOGGER.debug("Saving Blog Post with ID: {}", blogPost.getBlogEntryId());
      objectRepository.insert(blogPost);
    } else {
      LOGGER.debug("Updating Blog POst with ID: {}", blogPost.getBlogEntryId());
      objectRepository.update(blogPost);
    }
  }

  @Override
  public void deleteBlogPost(String slug) {
    LOGGER.debug("Deleting Blog Post with slug: {}", slug);
    final WriteResult result = objectRepository.remove(eq("slug", slug));
    LOGGER.debug("Deleted {} Blog Posts with slug: {}", result.getAffectedCount(), slug);
  }

  @Override
  public BlogPost getBlogPost(String slug) {
    LOGGER.debug("Searching for Blog Post with slug: {}", slug);
    final Cursor<BlogPost> cursor = objectRepository.find(eq("slug", slug));
    LOGGER.debug("Found {} Blog Post(s) with slug: {}", cursor.size(), slug);
    if (cursor.size() != 0) {
      return cursor.firstOrDefault();
    }
    return null;
  }

  @Override
  public List<BlogPost> getBlogPosts(long beginDate, int maxPosts) {
    final Cursor<BlogPost> cursor = objectRepository.find(
        ObjectFilters.lte("dateCreated", beginDate),
        FindOptions.sort("dateCreated", SortOrder.Ascending).thenLimit(0, maxPosts)
    );
    return cursor.toList();
  }

}
