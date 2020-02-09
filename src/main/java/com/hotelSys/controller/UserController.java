package com.hotelSys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotelSys.model.*;
import com.hotelSys.impl.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {

  @Autowired
  public UserDaoImpl userDaoImpl;


  @RequestMapping(value = "/welcomePage")
  public ModelAndView homePage() {
    ModelAndView mav = new ModelAndView("welcomePage");

    return mav;
  }

  @RequestMapping(value = "/signUp", method = RequestMethod.GET)
  public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("signUp");
    mav.addObject("user", new User());
    return mav;
  }

  @RequestMapping(value = "/signUpProcess", method = RequestMethod.POST)
  public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute("user") User user) {
    userDaoImpl.register(user);
    HttpSession session = request.getSession();
    session.setAttribute("fullName", user.getFirstName() + " " + user.getLastName());
    session.setAttribute("permission", user.getPermission());
    session.setAttribute("id", user.getId());
    return new ModelAndView("Home", "firstName", session.getAttribute("fullName"));
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("login");

    mav.addObject("login", new Login());
    return mav;
  }


  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute("login") Login login, User user) {

    ModelAndView mav;
    user = userDaoImpl.validateUser(login);

    HttpSession session = request.getSession();

    if (null != user) {
      session.setAttribute("fullName", user.getFirstName() + " " + user.getLastName());
      session.setAttribute("permission", user.getPermission());
      session.setAttribute("id", user.getId());
      mav = new ViewsGenerator().getHomePage((String) session.getAttribute("permission"));
      mav.addObject("firstName", user.getFirstName());

    } else {
      mav = new ModelAndView("login");
      mav.addObject("message", "Username or Password is wrong!!");
    }

    return mav;
  }

  @RequestMapping(value = "/logOut")
  public ModelAndView logOut(HttpServletRequest request, HttpServletResponse response) {

    HttpSession session = request.getSession(false);
    session.removeAttribute("id");
    session.removeAttribute("fullName");
    session.removeAttribute("permission");
    ModelAndView mav = new ModelAndView("welcomePage");

    return mav;
  }

}