package com.example.proyecto.services;

import com.example.proyecto.daos.HotelDAO;
import com.example.proyecto.modelo.Hotel;
import com.example.proyecto.modelo.ImageHotel;

import java.util.ArrayList;
import java.util.Optional;

public interface HotelService{

    com.example.proyecto.daos.HotelDAO HotelDAO = null;

    public Hotel Create(String name, int score, String country, String region, String roomType);

    public Hotel findByName(String name);

    public ArrayList<Hotel> listAllHotels();

    public ArrayList<Hotel> listAllHotelsByPuntuation();

    public ArrayList<Hotel> listAllHotelsByCountry();

    public ArrayList<Hotel> listAllHotelsByRegion();

    public ArrayList<ImageHotel> listAllHotelsImage();

}
