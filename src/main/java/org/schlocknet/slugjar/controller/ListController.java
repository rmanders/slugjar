package org.schlocknet.slugjar.controller;


// Controller for managing lists

import org.schlocknet.slugjar.dao.ListDao;
import org.schlocknet.slugjar.dao.ListItemDao;
import org.schlocknet.slugjar.model.list.ListDetails;
import org.schlocknet.slugjar.model.list.ListItem;
import org.schlocknet.slugjar.model.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/lists")
public class ListController {

  /** Local logger */
  private static final Logger LOGGER = LoggerFactory.getLogger(ListController.class);

  @Autowired
  ListDao listDao;

  @Autowired
  ListItemDao listItemDao;

  @RequestMapping(method= RequestMethod.GET, produces="application/json")
  public List<ListDetails> getAllLists() {
    LOGGER.debug("Got request to show all Lists");

    final List<ListDetails> results = listDao.findAll();
    return results;
  }

  @RequestMapping(method=RequestMethod.POST, consumes="application/json", produces="application/json")
  public ApiResponse addNewList(@RequestBody ListDetails listDetails) {
    LOGGER.debug("Git request to add a new List");

    if (listDetails != null && listDetails.getListId() == null) {
      listDetails.setListId(UUID.randomUUID());
    }
    listDao.save(listDetails);
    return new ApiResponse("success");
  }

  @RequestMapping(value="/{listId}", method=RequestMethod.POST, consumes="application/json", produces="application/json")
  public ApiResponse addListItems(@PathVariable UUID listId, @RequestBody List<ListItem> listItems) {
    LOGGER.debug("Got request to add list items");

    for (ListItem item : listItems) {
      if (item.getListItemId() == null) item.setListItemId(UUID.randomUUID());
      if (item.getListId() == null) {
        item.setListId(listId);
      } else if (!Objects.equals(listId, item.getListId())) {
        throw new RuntimeException("Invalid listItemId: " + item.getListId());
      }
    }

    listItemDao.save(listItems);
    return new ApiResponse("success");
  }

  @RequestMapping(value="/{listId}", method=RequestMethod.GET, produces="application/json")
  public List<ListItem> getListItems(@PathVariable UUID listId) {
    LOGGER.debug("Got request to show list items for list: {}", listId);

    return listItemDao.findByListId(listId);
  }
}
