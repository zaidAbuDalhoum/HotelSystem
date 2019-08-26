package com.hotelSys.dao;

import com.hotelSys.model.Reservation;
import com.hotelSys.model.ReservationWrapper;
import com.hotelSys.model.Room;

import java.text.ParseException;
import java.util.List;

public interface RoomDao {

   List<Room> searchValidRooms(ReservationWrapper reservationWrapper) throws ParseException;

   Reservation reserveRoom(Room room, ReservationWrapper reservationWrapper, int id);

    void addRoom(Room room);   //ByAdmin

    void checkOut(Room room);

    List<Reservation> getReservations();

    List<Room> getRooms();
}
