package com.hotelSys.controller;

import com.hotelSys.impl.ComplaintDaoImpl;
import com.hotelSys.impl.OrderDaoImpl;
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
public class EmployeeController {

  @Autowired
  OrderDaoImpl orderDaoEmployee;

  @Autowired
  ComplaintDaoImpl complaintDaoEmployee;


  @RequestMapping(value = "/employeeHomePage", method = RequestMethod.GET)
  public ModelAndView getEmployeeHomePage(HttpServletRequest request,
      HttpServletResponse response) {

    ModelAndView mav = new ModelAndView("employeeHomePage");
    HttpSession session = request.getSession(false);

    return checkEmployeePermission((String) session.getAttribute("permission"), mav);
  }

  @RequestMapping(value = "/pendingOrdersEmployee", method = RequestMethod.GET)
  public ModelAndView showPendingOrders(HttpServletRequest request, HttpServletResponse response) {

    ModelAndView mav = new ModelAndView("listOfPendingOrders");

    HttpSession session = request.getSession(false);
    List<Order> list = orderDaoEmployee
        .viewPendingOrders((String) session.getAttribute("permission"));
    if (list.isEmpty()) {
      mav.addObject("message", "There Are No Pending Orders");
    }
    mav.addObject("list", list);
    return checkEmployeePermission((String) session.getAttribute("permission"), mav);
  }

  @RequestMapping(value = "/deliverOrder/{id}", method = RequestMethod.GET)
  public ModelAndView deliverOrder(HttpServletRequest request, HttpServletResponse response,
      @PathVariable int id) {

    orderDaoEmployee.deliverOrder(id);

    ModelAndView mav = new ModelAndView("listOfPendingOrders");
    mav.addObject("message", "Order Is Delivered");
    return mav;

  }


  @RequestMapping(value = "/complaintsEmployee", method = RequestMethod.GET)
  public ModelAndView showComplaints(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav;
    HttpSession session = request.getSession(false);
    List<Complaint> list = complaintDaoEmployee
        .viewComplaintsForEmployee((String) session.getAttribute("permission"));
    if (list.isEmpty()) {
      mav = new ModelAndView("listOfComplaintsEmployee");
      mav.addObject("message", " No Complaint Were Found");
    } else {
      mav = new ModelAndView("listOfComplaintsEmployee");
      mav.addObject("list", list);
    }
    return checkEmployeePermission((String) session.getAttribute("permission"), mav);
  }

  @RequestMapping(value = "/complaintReplyEmployee/{id}", method = RequestMethod.GET)
  public ModelAndView replyToComplaint(HttpServletRequest request, HttpServletResponse response,
      @PathVariable int id) {

    Complaint complaint = complaintDaoEmployee.getComplaintById(id);

    ModelAndView mav = new ModelAndView("complaintReplyEmployee");
    mav.addObject("complaint", complaint);
    mav.addObject("complaintReply", new ComplaintReply());
    return mav;

  }


  @RequestMapping(value = "/complaintReplyEmployee/registerReplyEmployee", method = RequestMethod.POST)
  public ModelAndView registerReply(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute("complaintReply") ComplaintReply complaintReply) {

    HttpSession session = request.getSession(false);
    complaintDaoEmployee.replyToComplaint(complaintReply);
    ModelAndView mav = new ModelAndView("listOfComplaintsEmployee");
    mav.addObject("message", "Your Reply has been Registered");
    return checkEmployeePermission((String) session.getAttribute("permission"), mav);
  }

  @RequestMapping(value = "/viewItems", method = RequestMethod.GET)
  public ModelAndView showItems(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("viewItems");
    HttpSession session = request.getSession();
    List<Item> list = orderDaoEmployee.viewMenu((String) session.getAttribute("permission"));
    if (list.isEmpty()) {
      mav.addObject("message", "There Are Items in The System");
    } else {
      mav.addObject("list", list);
    }
    return checkEmployeePermission((String) session.getAttribute("permission"), mav);
  }

  @RequestMapping(value = "/deleteItem/{id}", method = RequestMethod.GET)
  public ModelAndView deleteItem(HttpServletRequest request, HttpServletResponse response,
      @PathVariable int id) {

    orderDaoEmployee.deleteItem(id);

    ModelAndView mav = new ModelAndView("viewItems");
    mav.addObject("message", "Item Is Deleted");
    return mav;

  }

  @RequestMapping(value = "/addItem", method = RequestMethod.GET)
  public ModelAndView itemAddingForm(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("addItemForm");
    HttpSession session = request.getSession(false);
    mav.addObject("item", new Item());
    return checkEmployeePermission((String) session.getAttribute("permission"), mav);
  }

  @RequestMapping(value = "/addItemProcess", method = RequestMethod.POST)
  public ModelAndView addItem(HttpServletRequest request, HttpServletResponse response
      , @ModelAttribute("item") Item item) {

    orderDaoEmployee.addItem(item);
    ModelAndView mav = new ModelAndView("viewItems");
    mav.addObject("message", "Item Added Successfully");

    return mav;
  }


  private static ModelAndView checkEmployeePermission(String permission, ModelAndView modelAndView) {

    if (permission.equalsIgnoreCase("restaurant") ||
        permission.equalsIgnoreCase("dryCleaning")) {
      return modelAndView;
    } else {
      return ViewsGenerator.getHomePage(permission);
    }


  }
}
