package com.example.proyecto.daos;

import com.example.proyecto.modelo.Conexion;
import com.example.proyecto.modelo.Room;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;


@Repository
public class JdbcRoomDAO implements RoomDAO {

    @Override
    public ArrayList<Room> listAllRooms(){
        Connection co = null;
        Statement stm= null;
        ResultSet rs=null;
        String sql="SELECT * FROM habitacion;";
        ArrayList<Room> resultado = new ArrayList<>();

        try {
            co = Conexion.conectar();
            stm=co.createStatement();
            rs=stm.executeQuery(sql);
            while (rs.next()) {
                System.out.println("asd");
                Room r = new Room();
                r.setHabitacion_id(rs.getInt(1));
                r.setPrecio(rs.getInt(2));
                r.setHotel_id(rs.getInt(3));
                r.setTipo_id(rs.getString(4));
                System.out.println(r.toString());
                System.out.println("asd");
                resultado.add(r);
            }
            stm.close();
            co.close();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return resultado;
    }

    @Override
    public ArrayList<Room> listAllRoomsAndImages() {
        return null;
    }

    @Override
    public Room findByID(int id) {
        Connection co = null;

        String sql="";

        Room r = new Room();
        try {
            co = Conexion.conectar();

            PreparedStatement ps=co.prepareStatement("Select * from habitacion where habitacion_id = ?;");

            ps.setInt(1,id);
            System.out.println(ps);
            ResultSet rs=ps.executeQuery();
            if(rs.next()) {
                r.setHabitacion_id(rs.getInt(1));
                r.setPrecio(rs.getInt(2));
                r.setHotel_id(rs.getInt(3));
                r.setTipo_id(rs.getString(4));
            }
            System.out.println(r.toString());
            co.close();
            return r;

        } catch (SQLException e) {
            return null;
        }


    }

    @Override
    public boolean isAvailable(int id, Date StartDate1, Date EndDate1) {

        Connection co = null;
        Statement stm= null;
        ResultSet rs=null;
        String sql="SELECT fecha_inicio, fecha_fin FROM reserva WHERE habitacion_id_fk ="+ id +";";
        System.out.println(sql);
        try {
            co = Conexion.conectar();
            stm=co.createStatement();
            rs=stm.executeQuery(sql);
            while (rs.next()) {


                Date StartDate2 = rs.getDate(1);
                Date EndDate2 = rs.getDate(2);

                if (!(EndDate1.before(StartDate2 )|| StartDate1.after(EndDate2))){
                    return false;
                }

            }
            stm.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener fechas");;
        }
        return true;}




}
