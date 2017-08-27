package org.schlocknet.slugjar.model.list;

import lombok.Data;

@Data
public class NestedListItem {

  private String listItemName;

  private String listItemDesc;

  private Boolean checked = false;
}
