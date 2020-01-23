package com.example.proyecto.modelo;

import java.sql.*;

public class Conexion {
    public static Connection conectar() {
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "root");
        } catch (Exception e) {
            return null;
        }
        return connection;

    }
}
