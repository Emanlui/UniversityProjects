package com.example.proyecto.modelo;

import java.util.Objects;

public class Room {

    private int habitacion_id;
    private int precio;
    private String tipo_id;
    private int hotel_id;

    public int getHabitacion_id() {
        return habitacion_id;
    }

    public int getPrecio() {
        return precio;
    }

    public String getTipo_id() {
        return tipo_id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHabitacion_id(int habitacion_id) {
        this.habitacion_id = habitacion_id;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setTipo_id(String tipo_id) {
        this.tipo_id = tipo_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return habitacion_id == room.habitacion_id &&
                precio == room.precio &&
                tipo_id == room.tipo_id &&
                hotel_id == room.hotel_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(habitacion_id, precio, tipo_id, hotel_id);
    }
}
