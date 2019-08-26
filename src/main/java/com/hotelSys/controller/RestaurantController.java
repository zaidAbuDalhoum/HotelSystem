package com.hotelSys.controller;

import com.hotelSys.impl.OrderDaoImpl;
import com.hotelSys.model.Item;
import com.hotelSys.model.Login;
import com.hotelSys.model.Order;
import com.hotelSys.model.OrderWrapper;
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
public class RestaurantController {

    @Autowired
    public OrderDaoImpl orderDaoImpl;


    @RequestMapping(value = "/restaurantMenu", method = RequestMethod.GET)
    public ModelAndView showMenu(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("restaurantMenu");
        List<Item> list = orderDaoImpl.viewMenu("restaurant");
        mav.addObject("list", list);
        return mav;
    }

    @RequestMapping(value = "/restaurantOrder", method = RequestMethod.GET)
    public ModelAndView placeAnOrder(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        ModelAndView mav = null;

        if (session.getAttribute("id") == null) {
            mav = new ModelAndView("login");
            mav.addObject("login", new Login());
            mav.addObject("message", "You Have to be Logged in First");
            return mav;
        } else {
            mav = new ModelAndView("placeOrderRestaurant");
            List<Item> list = orderDaoImpl.viewMenu("restaurant");
            mav.addObject("list", list);
            mav.addObject("orderWrapper", new OrderWrapper());
            return mav;
        }
    }

    @RequestMapping(value = "/restaurantOrderProcess", method = RequestMethod.POST)
    public ModelAndView orderProcess(HttpServletRequest request, HttpServletResponse response,
                                     @ModelAttribute("orderWrapper") OrderWrapper orderWrapper) {

        ModelAndView mav = new ModelAndView("restaurantMenu");
        orderDaoImpl.addOrder(orderWrapper,"restaurant");

        mav.addObject("message", "Order Registered");

        return mav;
    }
    @RequestMapping(value = "/listOfOrdersUser",method = RequestMethod.GET)
    public ModelAndView showUserOrders(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav =new ModelAndView("listOfOrdersUser") ;
        HttpSession session = request.getSession();
        List<Order> list = orderDaoImpl.viewUserOrders((Integer) session.getAttribute("id"));
        if (list.isEmpty()){
            mav.addObject("message", "There Are No Orders");
        }
        mav.addObject("list",list);
        return mav;
    }

    @RequestMapping(value = "/deleteOrder/{id}",method = RequestMethod.GET)
    public ModelAndView deleterOrder(HttpServletRequest request, HttpServletResponse response,
                                     @PathVariable int id){

        orderDaoImpl.deleteOrder(id);

        ModelAndView mav = new ModelAndView("listOfOrdersUser");
        mav.addObject("message","Order Is Delivered");
        return mav;

    }
}
