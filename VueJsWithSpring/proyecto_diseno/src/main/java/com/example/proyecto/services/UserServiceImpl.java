package com.example.proyecto.services;

import com.example.proyecto.daos.UserDAO;
import com.example.proyecto.modelo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO UserDAO;


    @Override
    public User createUser(User u) {
        return UserDAO.createUser(u);
    }

    @Override
    public ArrayList<User> returnAllUser() {
        return UserDAO.returnAllUser();
    }

    @Override
    public ArrayList<User> returnUserDate(Date inicio, Date fin) {
        return UserDAO.returnUserDate(inicio,fin);
    }
}
