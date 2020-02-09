package com.hotelSys.dao;

import com.hotelSys.model.Item;
import com.hotelSys.model.Order;
import com.hotelSys.model.OrderWrapper;

import java.util.List;

public interface OrderDao {

  void addOrder(OrderWrapper orderWrapper, String type);

  void deleteOrder(int id);

  List<Order> viewAllOrders(String type);

  List<Order> viewPendingOrders(String type);

  void addItem(Item item);

  void deleteItem(int id);

  List<Item> viewMenu(String type);

  void deliverOrder(int id);

  List<Order> viewUserOrders(int id);
}
