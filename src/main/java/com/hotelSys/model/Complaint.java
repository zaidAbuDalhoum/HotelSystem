package com.hotelSys.model;

public class Complaint {

  private int complaintId;
  private int userId;
  private String roomId;
  private String userName;
  private String type;
  private String message;
  private String dateOfComplaint;
  private String reply;
  private String dateOfReply;

  public String getReply() {
    return reply;
  }

  public void setReply(String reply) {
    this.reply = reply;
  }

  public String getDateOfReply() {
    return dateOfReply;
  }

  public void setDateOfReply(String dateOfReply) {
    this.dateOfReply = dateOfReply;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getComplaintId() {
    return complaintId;
  }

  public void setComplaintId(int complaintId) {
    this.complaintId = complaintId;
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

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getDateOfComplaint() {
    return dateOfComplaint;
  }

  public void setDateOfComplaint(String dateOfComplaint) {
    this.dateOfComplaint = dateOfComplaint;
  }
}
