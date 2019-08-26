package com.hotelSys.impl;

import com.hotelSys.dao.ComplaintDao;
import com.hotelSys.model.Complaint;
import com.hotelSys.model.ComplaintReply;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ComplaintDaoImpl implements ComplaintDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static Logger logger = Logger.getLogger(ComplaintDaoImpl.class);


    //for a user to register a complaint to the system
    @Override
    public void registerComplaint(final Complaint complaint) {
        String INSERT = "INSERT INTO complaints (user_id,userName,roomId,message,dateOfComplaint,typeOfComplaint)" +
                " VALUES(?,?,?,?,?,?)";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        complaint.setDateOfComplaint(format.format(date.getTime()));

        jdbcTemplate.update(INSERT, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1,complaint.getUserId());
                preparedStatement.setString(2,complaint.getUserName());
                preparedStatement.setString(3,complaint.getRoomId());
                preparedStatement.setString(4,complaint.getMessage());
                preparedStatement.setString(5,complaint.getDateOfComplaint());
                preparedStatement.setString(6,complaint.getType());
            }
        });

        logger.info("New Complaint By user "+ complaint.getUserName());

    }
    //a reply is made by an employee or an admin
    @Override
    public void replyToComplaint(final ComplaintReply complaintReply) {

        String UPDATE = "UPDATE complaints SET reply = ? , dateOfReply = ? WHERE complaintId = ?";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        complaintReply.setDateOfReply(format.format(date.getTime()));

        jdbcTemplate.update(UPDATE, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1,complaintReply.getReply());
                preparedStatement.setString(2,complaintReply.getDateOfReply());
                preparedStatement.setInt(3,complaintReply.getComplaintId());
            }
        } );
        logger.info("Reply to Complaint With ID "+complaintReply.getComplaintId());
    }

    //gets the list of complaints made by the user
    @Override
    public List<Complaint> viewComplaintsForUser(final int id) {

        String SELECT  ="SELECT * FROM complaints where user_id = ?";

        return jdbcTemplate.query(SELECT, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, id);
            }
        }, new RowMapper<Complaint>() {
            @Override
            public Complaint mapRow(ResultSet resultSet, int i) throws SQLException {
                Complaint complaint = new Complaint();
                complaint.setComplaintId(resultSet.getInt("complaintId"));
                complaint.setUserName(resultSet.getString("userName"));
                complaint.setRoomId(resultSet.getString("roomId"));
                complaint.setMessage(resultSet.getString("message"));
                complaint.setReply(resultSet.getString("reply"));
                complaint.setDateOfComplaint(resultSet.getString("dateOfComplaint"));
                complaint.setDateOfReply(resultSet.getString("dateOfReply"));

                return complaint;
            }
        });
    }
    //gets the complaints for an employee based on his permission
    @Override
    public List<Complaint> viewComplaintsForEmployee(final String type) {

        String SELECT = "SELECT * FROM complaints WHERE typeOfComplaint = ? ORDER BY complaintId ASC";


        return jdbcTemplate.query(SELECT, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, type);
            }
        }, new RowMapper<Complaint>() {
            @Override
            public Complaint mapRow(ResultSet resultSet, int i) throws SQLException {
                Complaint complaint = new Complaint();
                complaint.setComplaintId(resultSet.getInt("complaintId"));
                complaint.setUserName(resultSet.getString("userName"));
                complaint.setRoomId(resultSet.getString("roomId"));
                complaint.setMessage(resultSet.getString("message"));
                complaint.setReply(resultSet.getString("reply"));
                complaint.setDateOfComplaint(resultSet.getString("dateOfComplaint"));
                complaint.setDateOfReply(resultSet.getString("dateOfReply"));
                return complaint;
            }
        });
    }
    //gets the complaints for the admin
    @Override
    public List<Complaint> viewComplaintsForAdmin() {

        String SELECT = "SELECT * FROM complaints WHERE typeOfComplaint = 'other'  ORDER BY complaintId ASC";

        return jdbcTemplate.query(SELECT, new RowMapper<Complaint>() {
            @Override
            public Complaint mapRow(ResultSet resultSet, int i) throws SQLException {
                Complaint complaint = new Complaint();
                complaint.setComplaintId(resultSet.getInt("complaintId"));
                complaint.setUserName(resultSet.getString("userName"));
                complaint.setRoomId(resultSet.getString("roomId"));
                complaint.setMessage(resultSet.getString("message"));
                complaint.setReply(resultSet.getString("reply"));
                complaint.setDateOfComplaint(resultSet.getString("dateOfComplaint"));
                complaint.setDateOfReply(resultSet.getString("dateOfReply"));
                complaint.setType(resultSet.getString("typeOfComplaint"));
                return complaint;
            }
        });
    }

    public Complaint getComplaintById(final int id){
        String SELECT = " SELECT * FROM complaints WHERE complaintId = ?";

        List<Complaint> list = jdbcTemplate.query(SELECT, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, id);
            }
        }, new RowMapper<Complaint>() {
            @Override
            public Complaint mapRow(ResultSet resultSet, int i) throws SQLException {
                Complaint complaint = new Complaint();
                complaint.setComplaintId(resultSet.getInt("complaintId"));
                complaint.setUserName(resultSet.getString("userName"));
                complaint.setRoomId(resultSet.getString("roomId"));
                complaint.setMessage(resultSet.getString("message"));
                complaint.setDateOfComplaint(resultSet.getString("dateOfComplaint"));
                return complaint;
            }
        });

        return list.get(0);
    }
}
