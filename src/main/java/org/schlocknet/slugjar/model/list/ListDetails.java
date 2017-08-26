package org.schlocknet.slugjar.model.list;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

@Data
@Document(collection="lists")
public class ListDetails extends ListContainer {

  @DBRef(lazy = true)
  private List<ListItem> listItems = Collections.emptyList();

  public ListDetails() {
    super();
  }
}
