package com.example.proyecto.services;

import com.example.proyecto.modelo.Reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationService {

    public String CreateReservation(int room, int userId, Date checkInDate, Date checkOutDate, long creditCard);

    public ArrayList<Reservation> findBy();

}
