package org.schlocknet.slugjar.model.list;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Document(collection = "listItems")
public class ListItem extends NestedListItem {

  @Id
  private UUID listItemId = UUID.randomUUID();

  @NotNull(message = "List item must specify the UUID of the list it belongs to")
  private UUID listId;
}
