package com.hotelSys.controller;


import com.hotelSys.impl.OrderDaoImpl;
import com.hotelSys.model.Item;
import com.hotelSys.model.Login;
import com.hotelSys.model.OrderWrapper;
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
public class DryCleaningController {

    @Autowired
   public OrderDaoImpl orderDao;

    @RequestMapping(value = "/dryCleaningMenu", method = RequestMethod.GET)
    public ModelAndView showMenu(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("dryCleaningMenu");
        List<Item> list = orderDao.viewMenu("dryCleaning");
        mav.addObject("list", list);
        return mav;
    }

    @RequestMapping(value = "/dryCleaningOrder", method = RequestMethod.GET)
    public ModelAndView placeAnOrder(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        ModelAndView mav = null;

        if (session.getAttribute("id") == null) {
            mav = new ModelAndView("login");
            mav.addObject("login", new Login());
            mav.addObject("message", "You Have to be Logged in First");
            return mav;
        } else {
            mav = new ModelAndView("placeOrderDryCleaning");
            List<Item> list = orderDao.viewMenu("dryCleaning");
            mav.addObject("list", list);
            mav.addObject("orderWrapper", new OrderWrapper());
            return mav;
        }
    }

    @RequestMapping(value = "/dryCleaningOrderProcess", method = RequestMethod.POST)
    public ModelAndView orderProcess(HttpServletRequest request, HttpServletResponse response,
                                     @ModelAttribute("orderWrapper") OrderWrapper orderWrapper) {

        ModelAndView mav = new ModelAndView("dryCleaningMenu");
        orderDao.addOrder(orderWrapper,"dryCleaning");

        mav.addObject("message", "Order Registered");

        return mav;
    }
}
