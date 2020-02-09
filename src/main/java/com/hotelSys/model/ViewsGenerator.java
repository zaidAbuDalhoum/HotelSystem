package com.hotelSys.model;

import org.springframework.web.servlet.ModelAndView;

public class ViewsGenerator {

  //Provide HomePage Views based on user's permission
  public static ModelAndView getHomePage(String permission) {

    if (permission.equalsIgnoreCase("user")) {
      return new ModelAndView("Home");
    }
    if (permission.equalsIgnoreCase("restaurant") ||
        permission.equalsIgnoreCase("dryCleaning")) {
      return new ModelAndView("employeeHomePage");
    }
    if (permission.equalsIgnoreCase("admin")) {
      return new ModelAndView("adminHomePage");
    } else {
      return new ModelAndView("welcomePage");
    }

  }
}
