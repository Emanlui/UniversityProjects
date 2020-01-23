package com.example.proyecto.services;


import com.example.proyecto.daos.ImageDAO;
import com.example.proyecto.modelo.ImageHotel;
import com.example.proyecto.modelo.ImageRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageDAO ImageDAO;

    @Override
    public ArrayList<ImageRoom> retrieveRoomImages(int roomId) {return ImageDAO.findByRoomId(roomId);}

    @Override
    public ArrayList<ImageHotel> retrieveHotelImages(int hotelId) {return ImageDAO.findByHotelId(hotelId);}
}
