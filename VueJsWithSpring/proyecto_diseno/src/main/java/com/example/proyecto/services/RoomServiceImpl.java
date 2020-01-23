package com.example.proyecto.services;

import com.example.proyecto.daos.RoomDAO;
import com.example.proyecto.modelo.Room;
import com.example.proyecto.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;


@Service
public class RoomServiceImpl implements RoomService {


    @Autowired
    RoomDAO RoomDAO;

    @Override
    public Room findByID(int id) {
        return RoomDAO.findByID(id);
    }

    @Override
    public boolean isAvailable(int id, Date StartDate1, Date EndDate1)
    {
        return RoomDAO.isAvailable(id,StartDate1,EndDate1);
    }

    @Override
    public ArrayList<Room> listAllRooms() {
        return RoomDAO.listAllRooms();
    }
}
