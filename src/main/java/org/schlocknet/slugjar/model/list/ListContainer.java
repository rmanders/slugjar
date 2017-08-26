package org.schlocknet.slugjar.model.list;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
public class ListContainer {

  @Id
  private String listId = UUID.randomUUID().toString();

  private String listName;


}
