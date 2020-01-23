package com.example.proyecto.daos;

import com.example.proyecto.modelo.Conexion;
import com.example.proyecto.modelo.Hotel;
import com.example.proyecto.modelo.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class JdbcUserDAO implements UserDAO {

    @Override
    public User createUser(User u) {


        Connection co = null;

        User usuarioNuevo = new User();
        try {
            co = Conexion.conectar();

            PreparedStatement ps=co.prepareStatement("insert into usuario values(?,'?','?','?','?','?');");
            ps.setString(1,Integer.toString(u.getUsuarioId()));
            ps.setString(2,u.getNombre());
            ps.setString(3,u.getApellido1());
            ps.setString(4,u.getApellido2());
            ps.setString(5,u.getCorreo());
            ps.setString(6,u.getNumeroTarjeta());

            ResultSet rs=ps.executeQuery();

            if(rs.next()) {
                usuarioNuevo.setUsuarioId(Integer.parseInt(rs.getString(1)));
                usuarioNuevo.setNombre(rs.getString(2));
                usuarioNuevo.setApellido1(rs.getString(3));
                usuarioNuevo.setApellido2(rs.getString(4));
                usuarioNuevo.setCorreo(rs.getString(5));
                usuarioNuevo.setNumeroTarjeta(rs.getString(6));
            }

            co.close();
            return usuarioNuevo;
        } catch (SQLException e) {
            return null;
        }

    }

    @Override
    public ArrayList<User> returnAllUser() {

        Connection co = null;

        ArrayList<User> respuesta = new ArrayList<User>();
        try {
            co = Conexion.conectar();

            PreparedStatement ps=co.prepareStatement("select * from usuario;");

            ResultSet rs=ps.executeQuery();

            while(rs.next()) {

                System.out.println(respuesta.toString());
                User usuarioNuevo = new User();

                usuarioNuevo.setUsuarioId(Integer.parseInt(rs.getString(1)));
                usuarioNuevo.setNombre(rs.getString(2));
                usuarioNuevo.setApellido1(rs.getString(3));
                usuarioNuevo.setApellido2(rs.getString(4));
                usuarioNuevo.setCorreo(rs.getString(5));
                usuarioNuevo.setNumeroTarjeta(rs.getString(6));

                respuesta.add(usuarioNuevo);
            }

            co.close();
            return respuesta;
        } catch (SQLException e) {
            return null;
        }

    }

    @Override
    public ArrayList<User> returnUserDate(Date inicio, Date fin) {

        Connection co = null;

        ArrayList<User> respuesta = new ArrayList<User>();
        try {
            co = Conexion.conectar();

            String sql = "select * from habitacion ha, reserva r, hotel ho where ha.habitacion_id not in\n" +
                    "(select h.habitacion_id \n" +
                    "from usuario u, reserva r, habitacion h\n" +
                    "where u.usuario_id = r.usuario_id_fk and r.fecha_inicio >= ? \n" +
                    "and r.fecha_fin <= ? and r.habitacion_id_fk = h.habitacion_id)\n" +
                    "and ha.habitacion_id = r.habitacion_id_fk \n" +
                    "and ha.hotel_id = ho.hotel_id;";

            PreparedStatement ps=co.prepareStatement(sql);

            ps.setString(1,inicio.toString());
            ps.setString(2,fin.toString());

            System.out.println(ps.toString());

            ResultSet rs=ps.executeQuery();

            while(rs.next()) {

                User usuarioNuevo = new User();

                usuarioNuevo.setUsuarioId(Integer.parseInt(rs.getString(1)));
                usuarioNuevo.setNombre(rs.getString(2));
                usuarioNuevo.setApellido1(rs.getString(3));
                usuarioNuevo.setApellido2(rs.getString(4));
                usuarioNuevo.setCorreo(rs.getString(5));
                usuarioNuevo.setNumeroTarjeta(rs.getString(6));

                respuesta.add(usuarioNuevo);
            }

            co.close();
            return respuesta;
        } catch (SQLException e) {
            return null;
        }

    }

}
