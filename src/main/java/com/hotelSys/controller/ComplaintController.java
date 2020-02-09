package com.hotelSys.controller;


import com.hotelSys.impl.ComplaintDaoImpl;
import com.hotelSys.model.Complaint;
import com.hotelSys.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ComplaintController {

  @Autowired
  ComplaintDaoImpl complaintDao;


  @RequestMapping(value = "/complaintForm", method = RequestMethod.GET)
  public ModelAndView placeAnOrder(HttpServletRequest request, HttpServletResponse response) {

    HttpSession session = request.getSession(false);
    ModelAndView mav = null;

    if (session.getAttribute("id") == null) {
      mav = new ModelAndView("login");
      mav.addObject("login", new Login());
      mav.addObject("message", "You Have to be Logged in First");
      return mav;
    } else {
      mav = new ModelAndView("complaintForm");
      mav.addObject("complaint", new Complaint());
      return mav;
    }
  }


  @RequestMapping(value = "/complaintProcess", method = RequestMethod.POST)
  public ModelAndView orderProcess(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute("complaint") Complaint complaint) {

    ModelAndView mav = new ModelAndView("Home");
    complaintDao.registerComplaint(complaint);
    mav.addObject("message", "Complaint Registered");

    return mav;
  }

  @RequestMapping(value = "/complaintsUser", method = RequestMethod.GET)
  public ModelAndView showComplaints(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav;
    HttpSession session = request.getSession(false);
    List<Complaint> list = complaintDao.viewComplaintsForUser((Integer) session.getAttribute("id"));
    if (list.isEmpty()) {
      mav = new ModelAndView("listOfComplaintsUser");
      mav.addObject("message", " No Complaint Were Found");
    }
    mav = new ModelAndView("listOfComplaintsUser");
    mav.addObject("list", list);
    return mav;
  }

}
