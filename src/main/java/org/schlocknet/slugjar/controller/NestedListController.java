package org.schlocknet.slugjar.controller;

import org.schlocknet.slugjar.model.list.NestedList;
import org.schlocknet.slugjar.model.response.ApiResponse;
import org.schlocknet.slugjar.model.response.ApiResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/nestedlists", produces = "application/json")
public class NestedListController {

  /** Local logger */
  private static final Logger LOGGER = LoggerFactory.getLogger(NestedListController.class);

  @RequestMapping(method= RequestMethod.GET)
  public List<NestedList> getNestedLists() {
    LOGGER.debug("Got request to show all nested lists");
    return Collections.emptyList();
  }

  @RequestMapping(method= RequestMethod.POST, consumes = "application/json")
  public ApiResponse saveNewNestedList(@RequestBody NestedList nestedList) {
    LOGGER.debug("Got request to save or update new nested list");
    return new ApiResponse(ApiResponseStatus.SUCCEEDED);
  }

  @RequestMapping(value="/{listId}", method= RequestMethod.GET)
  public NestedList getNestedList(@PathVariable UUID listId) {
    LOGGER.debug("Got request to retrieve nested list with listId: {}", listId);
    return null;
  }

  @RequestMapping(value="/{listId}", method= RequestMethod.POST, consumes = "application/json")
  public ApiResponse updateNestedList(@PathVariable UUID listId, @RequestBody NestedList nestedList, HttpServletResponse response) {
    LOGGER.debug("Got request to update nested list with listId: {}", listId);
    return new ApiResponse(ApiResponseStatus.SUCCEEDED);
  }

  @RequestMapping(value="/{listId}", method= RequestMethod.DELETE)
  public ApiResponse deleteNestedList(@PathVariable UUID listId) {
    return new ApiResponse(ApiResponseStatus.SUCCEEDED);
  }

}
