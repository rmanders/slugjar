package org.schlocknet.slugjar.model.list;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Data
@Document(collection="lists")
public class ListDetails  {

  @Id
  private UUID listId = UUID.randomUUID();

  private String listName;

  private String listDescription;

  private String listOwner;
}
