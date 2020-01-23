package com.example.proyecto.Controllers;

import com.example.proyecto.modelo.Conexion;
import com.example.proyecto.modelo.ImageHotel;
import com.example.proyecto.modelo.User;
import com.example.proyecto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
public class UserController {

    @Autowired
    UserService UserService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/returnAllUser")
    public ArrayList<User> returnAllUser(){return UserService.returnAllUser();}

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/returnUserDate/{Date1}/{Date2}", method = RequestMethod.GET)
    public @ResponseBody ArrayList<User> returnUserDate(@PathVariable("Date1")String dateInicio, @PathVariable("Date2")String dateFinal) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date inicio = simpleDateFormat.parse(dateInicio);
        Date fin = simpleDateFormat.parse(dateFinal);

        return UserService.returnUserDate(inicio, fin);
    }

}
