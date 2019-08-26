package com.hotelSys.model;

public class ComplaintReply {

    private String reply;
    private String dateOfReply;
    private int complaintId;

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

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
}
