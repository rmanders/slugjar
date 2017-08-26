package org.schlocknet.slugjar.controller;


// Controller for managing lists

import org.schlocknet.slugjar.dao.ListDao;
import org.schlocknet.slugjar.model.list.ListContainer;
import org.schlocknet.slugjar.model.list.ListDetails;
import org.schlocknet.slugjar.model.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lists")
public class ListController {

  /** Local logger */
  private static final Logger LOGGER = LoggerFactory.getLogger(ListController.class);

  @Autowired
  ListDao listDao;

  @RequestMapping(method= RequestMethod.GET, produces="application/json")
  public List<ListContainer> getAllLists() {
    LOGGER.debug("Got request to show all Lists");
    final List<ListDetails> results = listDao.findAll();
    return (List<ListContainer>)(List<?>) results;
  }

  @RequestMapping(method=RequestMethod.POST, consumes="application/json", produces="application/json")
  public ApiResponse addNewList(@RequestBody ListDetails listDetails) {
    LOGGER.debug("Git request to add a new List");
    if (listDetails != null && listDetails.getListId() == null) {

    }
    return new ApiResponse("success");
  }
}
