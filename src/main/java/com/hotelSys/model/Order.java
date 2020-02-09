package com.hotelSys.model;


public class Order {

  private int orderId;
  private int userId;
  private String roomId;
  private String userName;
  private String bill;
  private double billAmount;
  private double tax;
  private double billAmountWithTax;
  private String orderDate;
  private String orderStatus;
  private String orderType;

  public String getOrderType() {
    return orderType;
  }

  public void setOrderType(String orderType) {
    this.orderType = orderType;
  }

  public String getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(String orderStatus) {
    this.orderStatus = orderStatus;
  }

  public int getOrderId() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getRoomId() {
    return roomId;
  }

  public void setRoomId(String roomId) {
    this.roomId = roomId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(String orderDate) {
    this.orderDate = orderDate;
  }

  public String getBill() {
    return bill;
  }

  public void setBill(String bill) {
    this.bill = bill;
  }

  public double getBillAmount() {
    return billAmount;
  }

  public void setBillAmount(double billAmount) {
    this.billAmount = billAmount;
  }

  public double getTax() {
    return tax;
  }

  public void setTax(double tax) {
    this.tax = tax;
  }

  public double getBillAmountWithTax() {
    return billAmountWithTax;
  }

  public void setBillAmountWithTax(double billAmountWithTax) {
    this.billAmountWithTax = billAmountWithTax;
  }


}
