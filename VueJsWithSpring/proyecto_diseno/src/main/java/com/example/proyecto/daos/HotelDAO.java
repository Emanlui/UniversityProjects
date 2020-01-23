package com.example.proyecto.daos;

import com.example.proyecto.modelo.Hotel;
import com.example.proyecto.modelo.ImageHotel;
import com.example.proyecto.modelo.Reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface HotelDAO {

    public void Create(String name,int score,String country, String region, String roomType);

    public ArrayList<ImageHotel> listAllHotelsImage();

    public Hotel findByName(String name);

    public ArrayList<Hotel> listAllHotels();

    public ArrayList<Hotel> listAllHotelsByPuntuation();

    public ArrayList<Hotel> listAllHotelsByRegion();

    public ArrayList<Hotel> listAllHotelsByCountry();

}
