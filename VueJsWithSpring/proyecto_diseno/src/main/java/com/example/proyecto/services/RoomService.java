package com.example.proyecto.services;

import com.example.proyecto.modelo.Room;

import java.util.ArrayList;
import java.util.Date;


public interface RoomService {
    public Room findByID(int id);

    public boolean isAvailable(int id, Date StartDate1, Date EndDate1);

    public ArrayList<Room> listAllRooms();
}
