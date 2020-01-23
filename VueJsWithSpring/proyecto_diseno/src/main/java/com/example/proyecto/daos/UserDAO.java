package com.example.proyecto.daos;

import com.example.proyecto.modelo.User;

import java.util.ArrayList;
import java.util.Date;

public interface UserDAO {

    public User createUser(User u);

    public ArrayList<User> returnAllUser();

    public ArrayList<User> returnUserDate(Date inicio, Date fin);

}
