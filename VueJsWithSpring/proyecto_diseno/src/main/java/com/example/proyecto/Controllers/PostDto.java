package com.example.proyecto.Controllers;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PostDto {

    int userId;
    String name;
    String lastName;
    String email;
    String checkInDate;
    String checkOutDate;
    long creditCard;


    public PostDto(){}

    public PostDto(int userId, String name, String lastName, String email, String checkInDate, String checkOutDate, int creditCard) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.creditCard = creditCard;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public long getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(long creditCard) {
        this.creditCard = creditCard;
    }
}
