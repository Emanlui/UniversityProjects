package com.example.proyecto.daos;

import java.util.ArrayList;

import com.example.proyecto.modelo.ImageHotel;
import com.example.proyecto.modelo.ImageRoom;

public interface ImageDAO {
    public ArrayList<ImageRoom> findByRoomId(int roomId);
    public ArrayList<ImageHotel> findByHotelId(int hotelId);


}
