package org.schlocknet.slugjar.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@EqualsAndHashCode(callSuper=true)
public class UserDetails extends UserInfo {

  public UserDetails() {
    super();
  }

  private String password;

  private String salt;

  public UserInfo toUserInfo() {
    return new UserInfo(
        this.getUserId(),
        this.getUsername(),
        this.getRoles()
    );
  }

}
