package org.schlocknet.slugjar.controller;

import org.schlocknet.slugjar.dao.UserDao;
import org.schlocknet.slugjar.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

  /** Local logger */
  private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserDao userDao;

  @RequestMapping(value="/listall", method= RequestMethod.GET, produces="application/json")
  public List<UserInfo> listUsers() {
    LOGGER.debug("Got request to list all users");

    return (List<UserInfo>)(List<?>)userDao.findAll();
  }

}
