package com.example.proyecto.Controllers;


import com.example.proyecto.modelo.Room;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


@RestController
public class RoomController {

    @Autowired
    com.example.proyecto.services.RoomService RoomService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/RoomFindById/{id}", method = RequestMethod.GET)
    public @ResponseBody Room findById(@PathVariable("id") int id) {

        System.out.println(id);
        return RoomService.findByID(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/findAvailable/{id}/{d1}/{d2}", method = RequestMethod.GET)
    public @ResponseBody Boolean isAvailable(@PathVariable("id") int id, @PathVariable("d1")  String d1, @PathVariable("d2") String d2) throws ParseException {
        System.out.println();
        Date date1= Date.valueOf(d1);;
        Date date2= Date.valueOf(d2);;
        System.out.println(date1);
        System.out.println(date2);
        return RoomService.isAvailable(id,date1,date2);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/listAllRooms", method = RequestMethod.GET)
    public @ResponseBody ArrayList<Room> listAllRooms(){
        return RoomService.listAllRooms();
    }

}
