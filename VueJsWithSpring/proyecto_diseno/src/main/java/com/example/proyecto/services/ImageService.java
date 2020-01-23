package com.example.proyecto.services;


import com.example.proyecto.daos.ImageDAO;
import com.example.proyecto.modelo.ImageHotel;
import com.example.proyecto.modelo.ImageRoom;

import java.util.ArrayList;


public interface ImageService {

    ImageDAO ImageDAO = null;

    public ArrayList<ImageRoom> retrieveRoomImages(int roomId);

    public ArrayList<ImageHotel> retrieveHotelImages(int hotelId);


}
