package com.hotelSys.model;

import java.util.*;

public class OrderWrapper {


  List<Integer> items;
  List<Integer> quantity;
  double tax;
  String userName;
  String roomId;
  int userId;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getRoomId() {
    return roomId;
  }

  public void setRoomId(String roomId) {
    this.roomId = roomId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public List<Integer> getItems() {
    return items;
  }

  public void setItems(List<Integer> items) {
    this.items = items;
  }

  public List<Integer> getQuantity() {
    return quantity;
  }

  public void setQuantity(List<Integer> quantity) {
    this.quantity = quantity;
  }

  public void setTax(double tax) {
    this.tax = tax;
  }

  public double getTax() {
    return tax;
  }


}
