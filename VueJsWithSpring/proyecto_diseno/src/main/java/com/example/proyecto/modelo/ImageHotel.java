package com.example.proyecto.modelo;

import java.util.ArrayList;
import java.util.Objects;

public class ImageHotel {
    private int id;
    private int hotelId;
    private String path;
    private int idHotel;
    private String name;
    private float  score;
    private String country;
    private String region;
    private ArrayList<String> roomType;

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public ArrayList<String> getRoomType() {
        return roomType;
    }

    public void setRoomType(ArrayList<String> roomType) {
        this.roomType = roomType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ImageHotel{" +
                "id=" + id +
                ", hotelId=" + hotelId +
                ", path='" + path + '\'' +
                ", idHotel=" + idHotel +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", roomType=" + roomType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageHotel that = (ImageHotel) o;
        return id == that.id &&
                hotelId == that.hotelId &&
                idHotel == that.idHotel &&
                Float.compare(that.score, score) == 0 &&
                Objects.equals(path, that.path) &&
                Objects.equals(name, that.name) &&
                Objects.equals(country, that.country) &&
                Objects.equals(region, that.region) &&
                Objects.equals(roomType, that.roomType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hotelId, path, idHotel, name, score, country, region, roomType);
    }
}
