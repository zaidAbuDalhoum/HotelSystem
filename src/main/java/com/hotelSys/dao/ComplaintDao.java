package com.hotelSys.dao;

import com.hotelSys.model.Complaint;
import com.hotelSys.model.ComplaintReply;

import java.util.List;

public interface ComplaintDao {

    void registerComplaint(Complaint complaint);

    void replyToComplaint(ComplaintReply complaintReply);

    List<Complaint> viewComplaintsForUser(int id);

    List<Complaint> viewComplaintsForEmployee(String type);

    List<Complaint> viewComplaintsForAdmin();

}
