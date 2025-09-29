package clases.DAO1;/*
-- Crear base de datos
CREATE DATABASE IF NOT EXISTS escuela
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS estudiantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(150) UNIQUE NOT NULL
);

INSERT INTO estudiantes (nombre, correo) VALUES
('Juan Pérez', 'juan.perez@mail.com'),
('Ana López', 'ana.lopez@mail.com'),
('Carlos Ruiz', 'carlos.ruiz@mail.com');
*/

import java.util.List;
import java.sql.*;
import java.util.*;

public class Estudiante{
    private int id;
    private String nombre;
    private String correo;

    // Constructores
    public Estudiante() {}
    public Estudiante(int id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
}





