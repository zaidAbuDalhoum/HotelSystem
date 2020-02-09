package com.hotelSys.controller;


import com.hotelSys.impl.RoomDaoImpl;
import com.hotelSys.model.Reservation;
import com.hotelSys.model.ReservationWrapper;
import com.hotelSys.model.Room;
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
public class ReservationController {

  @Autowired
  public RoomDaoImpl roomDaoImpl;

  @RequestMapping(value = "/reservationForm", method = RequestMethod.GET)
  public ModelAndView showReservation(HttpServletRequest request, HttpServletResponse response) {

    ModelAndView mav = new ModelAndView("reservationForm");

    HttpSession session = request.getSession(false);
    mav.addObject("reservationWrapper", new ReservationWrapper());
    mav.addObject("fullName", session.getAttribute("fullName"));

    return mav;
  }

  @RequestMapping(value = "/searching", method = RequestMethod.POST)
  public ModelAndView searchingProcess(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute("reservationWrapper") ReservationWrapper reservationWrapper) {

    ModelAndView mav;
    HttpSession session = request.getSession(false);
    session.setAttribute("reservationWrapper", reservationWrapper);

    List<Room> list = roomDaoImpl.searchValidRooms(reservationWrapper);
    mav = new ModelAndView();
    mav.addObject("reservationWrapper", reservationWrapper);
    if (list.isEmpty() == false) {
      mav = new ModelAndView("roomsForReservation");
      mav.addObject("list", list);
      mav.addObject("fullName", session.getAttribute("fullName"));
    } else {
      mav = new ModelAndView("reservationForm");
      mav.addObject("message", "No Available Rooms at This Date");
    }

    return mav;
  }


  @RequestMapping(value = "/roomInformation/{id}", method = RequestMethod.GET)
  public ModelAndView reservationConfirmation(HttpServletRequest request,
      HttpServletResponse response,
      @PathVariable int id) {

    Room room = roomDaoImpl.getRoomById(id);
    HttpSession session = request.getSession(false);
    session.setAttribute("room", room);

    ModelAndView modelAndView = new ModelAndView("roomInformation");
    modelAndView.addObject("room", room);
    modelAndView.addObject("reservation", new Reservation());

    return modelAndView;
  }


  @RequestMapping(value = "/roomInformation/reservationProcess", method = RequestMethod.POST)
  public ModelAndView reservationProcess(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute("reservation") Reservation reservation) {

    ModelAndView mav = new ModelAndView("Home");
    HttpSession session = request.getSession(false);

    roomDaoImpl.reserveRoom((Room) session.getAttribute("room"),
        (ReservationWrapper) session.getAttribute("reservationWrapper"),
        (Integer) session.getAttribute("id"));
    mav.addObject("message", "room is booked");
    session.removeAttribute("reservationWrapper");
    session.removeAttribute("room");

    return mav;
  }


}
