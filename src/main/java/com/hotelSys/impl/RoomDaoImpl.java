package com.hotelSys.impl;

import com.hotelSys.dao.RoomDao;
import com.hotelSys.model.Reservation;
import com.hotelSys.model.ReservationWrapper;
import com.hotelSys.model.Room;
import com.hotelSys.pdfGeneration.PDFCreator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class RoomDaoImpl implements RoomDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static Logger logger = Logger.getLogger(RoomDaoImpl.class);

    //searches for available rooms based on checkin and checkout dates and room size
    @Override
    public List<Room> searchValidRooms(final ReservationWrapper reservationWrapper) {


        final String SELECT = "SELECT * FROM rooms WHERE roomsIdInc NOT IN (SELECT roomsIdInc FROM reservations WHERE " +
                "(checkin <= (?) AND checkout >= (?)) OR" +
                "   (checkin <= (?) AND checkout >= (?)) OR" +
                "   (checkin >= (?) AND checkout <=(?))) AND roomsSize = ?";

        return jdbcTemplate.query(SELECT, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {


                reservationWrapper.setCheckIn(formatDate(reservationWrapper.getCheckIn(), 12, 0, 0));
                reservationWrapper.setCheckOut(formatDate(reservationWrapper.getCheckOut(), 11, 59, 59));


                logger.info("New Room Reservation Made By " + reservationWrapper.getUserName());

                preparedStatement.setString(1, (reservationWrapper.getCheckIn()));
                preparedStatement.setString(2, (reservationWrapper.getCheckIn()));
                preparedStatement.setString(3, (reservationWrapper.getCheckOut()));
                preparedStatement.setString(4, (reservationWrapper.getCheckOut()));
                preparedStatement.setString(5, (reservationWrapper.getCheckIn()));
                preparedStatement.setString(6, (reservationWrapper.getCheckOut()));
                preparedStatement.setString(7, reservationWrapper.getRoomSize());
            }
        }, new RowMapper<Room>() {

            @Override
            public Room mapRow(ResultSet resultSet, int rowNums) throws SQLException {
                Room room = new Room();
                room.setId(resultSet.getInt("roomsIdInc"));
                room.setRoomId(resultSet.getString("roomsId"));
                room.setPricePerNight(resultSet.getDouble("pricePerNight"));
                room.setRoomSize(resultSet.getString("roomsSize"));
                room.setDescription(resultSet.getString("description"));

                return room;

            }
        });

    }
    //adds a row in the reservation table with the reservation attributes
    @Override
    public Reservation reserveRoom(final Room room, final ReservationWrapper reservationWrapper, final int id) {

        final String INSERT = "INSERT INTO reservations(usersId,roomsIdInc,checkIn,checkOut,invoiceValue," +
                "roomsSize,pricePerNight,userName,roomId)" +
                "VALUES(?,?,?,?,?,?,?,?,?)";


        reservationWrapper.setPricePerNight(room.getPricePerNight());

        try {
            reservationWrapper.setInvoiceValue(room.getPricePerNight() * getDaysBetweenDates(reservationWrapper.getCheckOut()
                    , reservationWrapper.getCheckIn()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        PDFCreator.getPdfInvoice(reservationWrapper);
        jdbcTemplate.update(INSERT, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, id);
                preparedStatement.setInt(2, room.getId());
                preparedStatement.setString(3, reservationWrapper.getCheckIn());
                preparedStatement.setString(4, reservationWrapper.getCheckOut());
                preparedStatement.setDouble(5, reservationWrapper.getInvoiceValue());
                preparedStatement.setString(6, reservationWrapper.getRoomSize());
                preparedStatement.setDouble(7, reservationWrapper.getPricePerNight());
                preparedStatement.setString(8, reservationWrapper.getUserName());
                preparedStatement.setString(9, reservationWrapper.getRoomId());

            }
        });
        return null;
    }
    //for admin to add rooms to the system
    @Override
    public void addRoom(final Room room) {
        String INSERT = "INSERT INTO rooms(roomsId,roomsSize,pricePerNight,description) VALUES(?,?,?,?)";

        jdbcTemplate.update(INSERT, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1,room.getRoomId());
                preparedStatement.setString(2,room.getRoomSize());
                preparedStatement.setDouble(3,room.getPricePerNight());
                preparedStatement.setString(4,room.getDescription());
            }
        });
    }

    @Override
    public void checkOut(Room room) {

        final String UPDATE = "update rooms set currentlyReserved = 'NO' where roomId = ? ;";

        jdbcTemplate.update(UPDATE, room.getRoomId());

    }
    //formats dates to correspond to the DATETIME type of mysql
    public String formatDate(String date, int hours, int minutes, int seconds) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");

        Date date1 = null;

        try {
            date1 = (format1.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar formattedDate = Calendar.getInstance();
        formattedDate.setTime(date1);
        formattedDate.set(Calendar.MILLISECOND, 0);
        formattedDate.set(Calendar.SECOND, seconds);
        formattedDate.set(Calendar.MINUTE, minutes);
        formattedDate.set(Calendar.HOUR, hours);

        return format.format(formattedDate.getTime());
    }
    //returns a room object
    public Room getRoomById(final int id) {

        final String SELECT = "SELECT * FROM rooms WHERE roomsIdInc = ? " ;

        List<Room> list = jdbcTemplate.query(SELECT, new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1,id);
                    }
                }
                , new RowMapper<Room>() {

                    @Override
                    public Room mapRow(ResultSet resultSet, int rowNums) throws SQLException {
                        Room room = new Room();
                        room.setId(resultSet.getInt("roomsIdInc"));
                        room.setRoomId(resultSet.getString("roomsId"));
                        room.setPricePerNight(resultSet.getDouble("pricePerNight"));
                        room.setRoomSize(resultSet.getString("roomsSize"));
                        room.setDescription(resultSet.getString("description"));

                        return room;
                    }

                });


        return list.size() > 0 ? list.get(0) : null;

    }

    //gets a list of the upcoming reservations for admin
    public List<Reservation> getReservations() {

        String SELECT = "SELECT * FROM reservations WHERE checkIn >= ? ORDER BY reservationsId ASC";

        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Date date = new Date();


        List<Reservation> list = jdbcTemplate.query(SELECT, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, format.format(date.getTime()));
            }
        }, new RowMapper<Reservation>() {

            @Override
            public Reservation mapRow(ResultSet resultSet, int i) throws SQLException {
                Reservation reservation = new Reservation();
                reservation.setReservationId(resultSet.getInt("reservationsId"));
                reservation.setUserId(resultSet.getInt("usersId"));
                reservation.setRoomIdInc(resultSet.getInt("roomsIdInc"));
                reservation.setCheckIn(resultSet.getString("checkIn"));
                reservation.setCheckOut(resultSet.getString("checkOut"));
                reservation.setRoomSize(resultSet.getString("roomsSize"));
                reservation.setUserName(resultSet.getString("userName"));
                reservation.setRoomId(resultSet.getString("roomId"));

                return reservation;
            }
        });

        return list;
    }

    //gets the list of rooms in the system
    @Override
    public List<Room> getRooms() {
        String SELECT = "SELECT * FROM rooms ";

        List<Room> list = jdbcTemplate.query(SELECT, new RowMapper<Room>() {
            @Override
            public Room mapRow(ResultSet resultSet, int rowNums) throws SQLException {
                Room room = new Room();
                room.setId(resultSet.getInt("roomsIdInc"));
                room.setRoomId(resultSet.getString("roomsId"));
                room.setPricePerNight(resultSet.getDouble("pricePerNight"));
                room.setRoomSize(resultSet.getString("roomsSize"));
                room.setDescription(resultSet.getString("description"));

                return room;
            }
        });

        return list;
    }

    //to calculate the days between two dates
    public int getDaysBetweenDates(String one, String two) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one1 = format.parse(one);
        Date two1 = format.parse(two);

        double difference = (one1.getTime() - two1.getTime()) / 86400000;

        int result = (int) Math.round(Math.abs(difference)) +1 ;


        return result;

    }


}
