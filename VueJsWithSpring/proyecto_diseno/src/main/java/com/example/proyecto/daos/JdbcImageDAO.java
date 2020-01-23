package com.example.proyecto.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.proyecto.modelo.Conexion;
import com.example.proyecto.modelo.ImageHotel;
import com.example.proyecto.modelo.ImageRoom;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcImageDAO implements ImageDAO {
    @Override
    public ArrayList<ImageRoom> findByRoomId(int roomId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM imagenes_habitacion WHERE habitacion_id_fk = "+roomId;

        ArrayList<ImageRoom> imagenesHabitacion = new ArrayList<ImageRoom>();

        try {
            con = Conexion.conectar();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ImageRoom ir = new ImageRoom();
                ir.setId(rs.getInt(1));
                ir.setPath(rs.getString(2));
                ir.setRoomId(rs.getInt(3));
                imagenesHabitacion.add(ir);
            }
            stmt.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return imagenesHabitacion;
    }

    @Override
    public ArrayList<ImageHotel> findByHotelId(int hotelId) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM imagenes_hotel WHERE hotel_id_fk = "+hotelId;

        ArrayList<ImageHotel> imagenesHotel = new ArrayList<ImageHotel>();

        try {
            con = Conexion.conectar();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ImageHotel ih = new ImageHotel();
                ih.setId(rs.getInt(1));
                ih.setPath(rs.getString(2));
                ih.setHotelId(rs.getInt(3));
                imagenesHotel.add(ih);
            }
            stmt.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return imagenesHotel;
    }
}
