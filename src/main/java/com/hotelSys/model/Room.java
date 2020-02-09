package com.hotelSys.model;

public class Room {

  private int id;
  private String roomId;
  private double pricePerNight;
  private String roomSize;
  private String currentlyReserved;
  private String houseKeeping;
  private String description;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getRoomId() {
    return roomId;
  }

  public void setRoomId(String roomId) {
    this.roomId = roomId;
  }

  public double getPricePerNight() {
    return pricePerNight;
  }

  public void setPricePerNight(double pricePerNight) {
    this.pricePerNight = pricePerNight;
  }

  public String getRoomSize() {
    return roomSize;
  }

  public void setRoomSize(String roomSize) {
    this.roomSize = roomSize;
  }

  public String getCurrentlyReserved() {
    return currentlyReserved;
  }

  public void setCurrentlyReserved(String currentlyReserved) {
    this.currentlyReserved = currentlyReserved;
  }

  public String getHouseKeeping() {
    return houseKeeping;
  }

  public void setHouseKeeping(String houseKeeping) {
    this.houseKeeping = houseKeeping;
  }
}
