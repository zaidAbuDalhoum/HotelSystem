package com.hotelSys.controller;


import com.hotelSys.impl.ComplaintDaoImpl;
import com.hotelSys.impl.OrderDaoImpl;
import com.hotelSys.impl.RoomDaoImpl;
import com.hotelSys.impl.UserDaoImpl;
import com.hotelSys.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {

  @Autowired
  UserDaoImpl userDaoAdmin;

  @Autowired
  RoomDaoImpl roomDaoAdmin;

  @Autowired
  OrderDaoImpl orderDaoAdmin;

  @Autowired
  ComplaintDaoImpl complaintDaoAdmin;

  @RequestMapping(value = "/adminHomePage", method = RequestMethod.GET)
  public ModelAndView getAdminHomePage(HttpServletRequest request, HttpServletResponse response) {

    ModelAndView mav = new ModelAndView("adminHomePage");

    HttpSession session = request.getSession(false);

    return checkAdminPermission((String) session.getAttribute("permission"), mav);
  }

  @RequestMapping(value = "/createEmployeeAccount", method = RequestMethod.GET)
  public ModelAndView employeeAccount(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("createEmployeeAccount");
    HttpSession session = request.getSession(false);
    mav.addObject("user", new User());
    return checkAdminPermission((String) session.getAttribute("permission"), mav);
  }

  @RequestMapping(value = "/employeeAccountProcess", method = RequestMethod.POST)
  public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute("user") User user) {
    userDaoAdmin.register(user);

    return new ModelAndView("adminHomePage", "message", "Employee is registered");
  }

  @RequestMapping(value = "/reservations", method = RequestMethod.GET)
  public ModelAndView showReservations(HttpServletRequest request, HttpServletResponse response) {

    List<Reservation> list = roomDaoAdmin.getReservations();
    ModelAndView mav = new ModelAndView("reservationsList");
    HttpSession session = request.getSession(false);
    if (list.isEmpty()) {

      mav.addObject("message", "There are no Upcoming Reservations");
    } else {
      mav = new ModelAndView("reservationsList");
      mav.addObject("list", list);
    }
    return checkAdminPermission((String) session.getAttribute("permission"), mav);
  }

  @RequestMapping(value = "/viewRooms", method = RequestMethod.GET)
  public ModelAndView viewRooms(HttpServletRequest request, HttpServletResponse response) {

    List<Room> list = roomDaoAdmin.getRooms();
    HttpSession session = request.getSession(false);
    ModelAndView mav = new ModelAndView("listOfRooms");
    if (list.isEmpty()) {
      mav.addObject("message", "No Rooms Were Found");
    } else {
      mav.addObject("list", list);
    }
    return checkAdminPermission((String) session.getAttribute("permission"), mav);
  }

  @RequestMapping(value = "/addRoom", method = RequestMethod.GET)
  public ModelAndView roomAddingForm(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("addRoomForm");
    HttpSession session = request.getSession(false);
    mav.addObject("room", new Room());
    return checkAdminPermission((String) session.getAttribute("permission"), mav);
  }

  @RequestMapping(value = "/addRoomProcess", method = RequestMethod.POST)
  public ModelAndView addRoom(HttpServletRequest request, HttpServletResponse response
      , @ModelAttribute("room") Room room) {

    HttpSession session = request.getSession(false);
    roomDaoAdmin.addRoom(room);
    ModelAndView mav = new ModelAndView("adminHomePage");
    mav.addObject("message", "Room Added Successfully");

    return checkAdminPermission((String) session.getAttribute("permission"), mav);
  }

  @RequestMapping(value = "/allOrders", method = RequestMethod.GET)
  public ModelAndView showOrders(HttpServletRequest request, HttpServletResponse response) {

    ModelAndView mav = new ModelAndView("listOfOrders");
    HttpSession session = request.getSession(false);
    List<Order> list = orderDaoAdmin.viewAllOrders("all");
    mav.addObject("list", list);
    return checkAdminPermission((String) session.getAttribute("permission"), mav);
  }


  @RequestMapping(value = "/complaints", method = RequestMethod.GET)
  public ModelAndView showComplaints(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("listOfComplaintsAdmin");
    List<Complaint> list = complaintDaoAdmin.viewComplaintsForAdmin();
    HttpSession session = request.getSession(false);
    if (list.isEmpty()) {
      mav.addObject("message", " No Complaint Were Found");
    }
    mav.addObject("list", list);
    return checkAdminPermission((String) session.getAttribute("permission"), mav);
  }

  @RequestMapping(value = "/complaintReply/{id}", method = RequestMethod.GET)
  public ModelAndView replyToComplaint(HttpServletRequest request, HttpServletResponse response,
      @PathVariable int id) {

    Complaint complaint = complaintDaoAdmin.getComplaintById(id);

    HttpSession session = request.getSession(false);

    ModelAndView mav = new ModelAndView("complaintReplyAdmin");
    mav.addObject("complaint", complaint);
    mav.addObject("complaintReply", new ComplaintReply());
    return checkAdminPermission((String) session.getAttribute("permission"), mav);

  }


  @RequestMapping(value = "/complaintReply/registerReply", method = RequestMethod.POST)
  public ModelAndView registerReply(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute("complaintReply") ComplaintReply complaintReply) {

    HttpSession session = request.getSession(false);
    complaintDaoAdmin.replyToComplaint(complaintReply);
    ModelAndView mav = new ModelAndView("listOfComplaintsAdmin");
    mav.addObject("message", "Your Reply has been Registered");
    return checkAdminPermission((String) session.getAttribute("permission"), mav);
  }

  private static ModelAndView checkAdminPermission(String permission, ModelAndView modelAndView) {

    if (permission.equalsIgnoreCase("admin")) {
      return modelAndView;
    } else {
      return ViewsGenerator.getHomePage(permission);
    }


  }
}
