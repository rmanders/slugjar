package org.schlocknet.slugjar.controller;

import org.schlocknet.slugjar.dao.NestedListDao;
import org.schlocknet.slugjar.model.list.NestedList;
import org.schlocknet.slugjar.model.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/nestedlists", produces = "application/json")
public class NestedListController {

  /** Local logger */
  private static final Logger LOGGER = LoggerFactory.getLogger(NestedListController.class);

  @Autowired
  private NestedListDao nestedListDao;

  @RequestMapping(method= RequestMethod.GET)
  public List<NestedList> getNestedLists() {
    LOGGER.debug("Got request to show all nested lists");
    return nestedListDao.findAll();
  }

  @RequestMapping(method= RequestMethod.POST, consumes = "application/json")
  public ApiResponse saveNewNestedList(@RequestBody NestedList nestedList) {
    LOGGER.debug("Got request to save or update new nested list");

    nestedList.setListId(UUID.randomUUID());
    nestedListDao.insert(nestedList);
    return new ApiResponse("success", nestedList.getListId().toString());
  }

  @RequestMapping(value="/{listId}", method= RequestMethod.GET)
  public NestedList getNestedList(@PathVariable UUID listId) {
    LOGGER.debug("Got request to retrieve nested list with listId: {}", listId);
    return nestedListDao.findOne(listId);
  }

  @RequestMapping(value="/{listId}", method= RequestMethod.POST, consumes = "application/json")
  public ApiResponse updateNestedList(@PathVariable UUID listId, @RequestBody NestedList nestedList, HttpServletResponse response) {
    LOGGER.debug("Got request to update nested list with listId: {}", listId);

    if (!Objects.equals(listId, nestedList.getListId())) {
      LOGGER.warn("nestedList listId path variable and object value mismatch: {} != {}", listId, nestedList.getListId());
      response.setStatus(HttpStatus.BAD_REQUEST.value());
      return new ApiResponse("failed", "request body listId does not match URL listId");
    }

    nestedListDao.save(nestedList);
    return new ApiResponse("success");
  }

  @RequestMapping(value="/{listId}", method= RequestMethod.DELETE)
  public ApiResponse deleteNestedList(@PathVariable UUID listId) {
    LOGGER.debug("Got request to delete nested list with listId: {}", listId);
    nestedListDao.delete(listId);
    return new ApiResponse("success");
  }

}
