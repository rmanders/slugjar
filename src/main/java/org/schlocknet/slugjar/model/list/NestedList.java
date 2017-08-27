package org.schlocknet.slugjar.model.list;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

/**
 * A list where the list items are contained in the same database document / object
 * as the list details descriptor.
 */
@Data
@Document(collection = "nestedLists")
public class NestedList extends ListDetails {

  public NestedList() {
    super();
  }

  private List<NestedListItem> listItems = Collections.emptyList();

}
