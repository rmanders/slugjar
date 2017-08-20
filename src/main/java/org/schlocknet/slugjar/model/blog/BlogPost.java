package org.schlocknet.slugjar.model.blog;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class BlogPost {

  @Id
  private String slug;

  private String title;

  private String contents;

  private String owner;

  private Long dateCreated = System.currentTimeMillis();
}
