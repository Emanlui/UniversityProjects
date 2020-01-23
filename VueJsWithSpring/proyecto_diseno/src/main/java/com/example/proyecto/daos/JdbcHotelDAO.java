package com.example.proyecto.daos;

import com.example.proyecto.modelo.Conexion;
import com.example.proyecto.modelo.Hotel;
import com.example.proyecto.modelo.ImageHotel;
import com.example.proyecto.modelo.Reservation;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcHotelDAO implements HotelDAO {

    @Override
    public void Create(String name, int score, String country, String region, String roomType) {

    }

    @Override
    public ArrayList<ImageHotel> listAllHotelsImage(){
        Connection co = null;
        Statement stm= null;
        ResultSet rs=null;
        String sql="Select * from hotel h, imagenes_hotel ih\n" +
                "where h.hotel_id = ih.hotel_id_fk";
        ArrayList<ImageHotel> resultado = new ArrayList<>();

        try {
            co = Conexion.conectar();
            stm=co.createStatement();
            rs=stm.executeQuery(sql);
            while (rs.next()) {

                ImageHotel h = new ImageHotel();
                h.setRegion(rs.getString(1));
                h.setScore(rs.getFloat(2));
                h.setName(rs.getString(3));
                h.setHotelId(rs.getInt(4));
                h.setCountry(rs.getString(5));
                h.setId(rs.getInt(6));
                h.setPath(rs.getString(7));


                resultado.add(h);
            }
            stm.close();
            co.close();
        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }


    @Override
    public Hotel findByName(String name) {

        Connection co = null;

        String sql="";

        Hotel h = new Hotel();
        try {
            co = Conexion.conectar();

            PreparedStatement ps=co.prepareStatement("Select * from hotel where nombre = ?;");
            ps.setString(1,name);
            ResultSet rs=ps.executeQuery();

            if(rs.next()) {
                h.setRegion(rs.getString(1));
                h.setScore(rs.getFloat(2));
                h.setName(rs.getString(3));
                h.setId(rs.getInt(4));
                h.setCountry(rs.getString(5));
            }

            co.close();
            return h;
        } catch (SQLException e) {
            return null;
        }
    }

        public ArrayList<Hotel> listAllHotelsByRegion(){
        Connection co = null;
        Statement stm= null;
        ResultSet rs=null;
        String sql="Select *\n" +
                "from hotel h, imagenes_hotel ih\n" +
                "where h.hotel_id = ih.hotel_id_fk";

        ArrayList<Hotel> resultado = new ArrayList<>();

        try {
            co = Conexion.conectar();
            stm=co.createStatement();
            rs=stm.executeQuery(sql);
            while (rs.next()) {

                Hotel h = new Hotel();
                h.setRegion(rs.getString(1));
                h.setScore(rs.getFloat(2));
                h.setName(rs.getString(3));
                h.setId(rs.getInt(4));
                h.setCountry(rs.getString(5));

                resultado.add(h);
            }
            stm.close();
            co.close();
        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

    public ArrayList<Hotel> listAllHotelsByCountry(){
        Connection co = null;
        Statement stm= null;
        ResultSet rs=null;
        String sql="Select *\n" +
                "from hotel \n" +
                "order by pais;";
        ArrayList<Hotel> resultado = new ArrayList<>();

        try {
            co = Conexion.conectar();
            stm=co.createStatement();
            rs=stm.executeQuery(sql);
            while (rs.next()) {

                Hotel h = new Hotel();
                h.setRegion(rs.getString(1));
                h.setScore(rs.getFloat(2));
                h.setName(rs.getString(3));
                h.setId(rs.getInt(4));
                h.setCountry(rs.getString(5));

                resultado.add(h);
            }
            stm.close();
            co.close();
        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

    public ArrayList<Hotel> listAllHotelsByPuntuation(){
        Connection co = null;
        Statement stm= null;
        ResultSet rs=null;
        String sql="Select *\n" +
                "from hotel \n" +
                "order by puntuacion desc;";
        ArrayList<Hotel> resultado = new ArrayList<>();

        try {
            co = Conexion.conectar();
            stm=co.createStatement();
            rs=stm.executeQuery(sql);
            while (rs.next()) {

                Hotel h = new Hotel();
                h.setRegion(rs.getString(1));
                h.setScore(rs.getFloat(2));
                h.setName(rs.getString(3));
                h.setId(rs.getInt(4));
                h.setCountry(rs.getString(5));

                resultado.add(h);
            }
            stm.close();
            co.close();
        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

    public ArrayList<Hotel> listAllHotels(){
        Connection co = null;
        Statement stm= null;
        ResultSet rs=null;
        String sql="Select * from hotel";
        ArrayList<Hotel> resultado = new ArrayList<>();

        try {
            co = Conexion.conectar();
            stm=co.createStatement();
            rs=stm.executeQuery(sql);
            while (rs.next()) {

                Hotel h = new Hotel();
                h.setRegion(rs.getString(1));
                h.setScore(rs.getFloat(2));
                h.setName(rs.getString(3));
                h.setId(rs.getInt(4));
                h.setCountry(rs.getString(5));

                resultado.add(h);
            }
            stm.close();
            co.close();
        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

}