package com.example.proyecto.services;

import com.example.proyecto.daos.ReservationDAO;
import com.example.proyecto.modelo.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationDAO ReservationDAO;

    @Override
    public String CreateReservation(int room, int userId, Date checkInDate, Date checkOutDate, long creditCard) {
        Reservation reservation = new Reservation();
        reservation.setRoomId(room);
        reservation.setUserId(userId);
        reservation.setCheckInDate(checkInDate);
        reservation.setCheckOutDate(checkOutDate);
        reservation.setCreditCardNumber(creditCard);
        return ReservationDAO.CreateReservation(reservation);
    }

    @Override
    public ArrayList<Reservation> findBy(){return ReservationDAO.findBy();}

}
