package org.schlocknet.slugjar.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Data
@Document
public class UserInfo {

  @Id
  private UUID userId = UUID.randomUUID();

  @Indexed(unique = true)
  private String username;

  private List<String> roles = Collections.emptyList();

  public UserInfo() {
    // Do Nothing
  }

  public UserInfo(UUID userId, String username, List<String> roles) {
    this.userId = userId;
    this.username = username;
    this.roles = roles;
  }
}
