package org.schlocknet.slugjar.controller;


// Controller for managing lists

import org.schlocknet.slugjar.model.list.ListDetails;
import org.schlocknet.slugjar.model.list.ListItem;
import org.schlocknet.slugjar.model.response.ApiResponse;
import org.schlocknet.slugjar.model.response.ApiResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/lists")
public class ListController {

  /** Local logger */
  private static final Logger LOGGER = LoggerFactory.getLogger(ListController.class);

  @RequestMapping(method= RequestMethod.GET, produces="application/json")
  public List<ListDetails> getAllLists() {
    LOGGER.debug("Got request to show all Lists");
    return Collections.emptyList();
  }

  @RequestMapping(method=RequestMethod.POST, consumes="application/json", produces="application/json")
  public ApiResponse addNewList(@RequestBody ListDetails listDetails) {
    LOGGER.debug("Git request to add a new List");

    return new ApiResponse(ApiResponseStatus.SUCCEEDED);
  }

  @RequestMapping(value="/{listId}", method=RequestMethod.POST, consumes="application/json", produces="application/json")
  public ApiResponse addListItems(@PathVariable UUID listId, @RequestBody List<ListItem> listItems) {
    LOGGER.debug("Got request to add list items");

    return new ApiResponse(ApiResponseStatus.SUCCEEDED);
  }

  @RequestMapping(value="/{listId}", method=RequestMethod.GET, produces="application/json")
  public List<ListItem> getListItems(@PathVariable UUID listId) {
    LOGGER.debug("Got request to show list items for list: {}", listId);
    return Collections.emptyList();
  }
}
