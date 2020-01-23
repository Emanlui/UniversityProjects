package com.example.proyecto.modelo;

import java.util.Objects;

public class User {

    private int usuarioId;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String correo;
    private String numeroTarjeta;

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return usuarioId == user.usuarioId &&
                Objects.equals(nombre, user.nombre) &&
                Objects.equals(apellido1, user.apellido1) &&
                Objects.equals(apellido2, user.apellido2) &&
                Objects.equals(correo, user.correo) &&
                Objects.equals(numeroTarjeta, user.numeroTarjeta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, nombre, apellido1, apellido2, correo, numeroTarjeta);
    }

    @Override
    public String toString() {
        return "User{" +
                "usuarioId=" + usuarioId +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", correo='" + correo + '\'' +
                ", numeroTarjeta='" + numeroTarjeta + '\'' +
                '}';
    }
}
