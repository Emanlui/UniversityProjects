package com.example.proyecto.daos;

import com.example.proyecto.modelo.Room;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public interface RoomDAO {

    public ArrayList<Room> listAllRooms();

    public ArrayList<Room> listAllRoomsAndImages();

    public Room findByID(int id);

    public boolean isAvailable(int id, Date StartDate1 , Date EndDate2);
}

