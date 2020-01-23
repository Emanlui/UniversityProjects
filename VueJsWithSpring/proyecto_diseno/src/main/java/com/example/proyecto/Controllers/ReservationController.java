package com.example.proyecto.Controllers;

import com.example.proyecto.services.ReservationService;
import com.example.proyecto.modelo.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Optional;

@RestController
public class ReservationController {

    @Autowired
    ReservationService ReservationService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/listarReservaciones", method = RequestMethod.GET)
    public @ResponseBody ArrayList<Reservation> listar(){
        return ReservationService.findBy();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/createReservation", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String createReservation(@RequestBody PostDto postDto) {
        /*
        int userId;
        String name;
        String lastName;
        String email;
        Date checkInDate;
        Date checkOutDate;
        int creditCard;
        */
        Date date1= Date.valueOf(postDto.getCheckInDate());
        Date date2= Date.valueOf(postDto.getCheckOutDate());
        System.out.println(Integer.toString(postDto.getUserId()) + postDto.getName() + postDto.getLastName() + postDto.getEmail() +  Long.toString(postDto.getCreditCard()));

        return ReservationService.CreateReservation(0,postDto.userId,date1,date2,postDto.creditCard);
    }

}
