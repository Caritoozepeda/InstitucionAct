/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Alumno;
import modelo.Conexion;
import java.time.LocalDate;

/**
 *
 * @author Caro_Z
 */
public class AlumnoData {

    private Connection con = null;
    private String sql;

    public AlumnoData(Conexion conexion) {

        con = conexion.getConexion();
    }
    // ok
    public void agregarAlumno(Alumno alumno) {
        try {
            sql = "INSERT INTO alumno(apellido, nombre, fechaNac, activo) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, alumno.getApellido());
            ps.setString(2, alumno.getNombre());
            ps.setDate(3, Date.valueOf(alumno.getFechaNac()));
            ps.setBoolean(4, alumno.isActivo());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                alumno.setId_alumno(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Se agregó el alumno");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo ingresar el alumno");

            }
            ps.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, " Error");
        }

    }
 // ok
    public void eliminarAlumno(int id) {
        //cambiar de estado de activo a inactivo

        sql = "UPDATE alumno SET activo = 0 WHERE id_alumno = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();

            ps.close();
            JOptionPane.showMessageDialog(null, " Se eliminó el alumno");
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, " No se pudo elimiar el alumno");
        }

    }
// ok
    public void modificarAlumno(int id,Alumno alumno) {
      
        sql = "UPDATE alumno SET apellido=?, nombre=?, fechaNac=? WHERE id_alumno=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
           
            ps.setString(1, alumno.getApellido());
            ps.setString(2, alumno.getNombre());
            ps.setDate(3, Date.valueOf(alumno.getFechaNac()));
            ps.setInt(4, id);
            ps.executeUpdate();

            ps.close();
            JOptionPane.showMessageDialog(null, " Se modificó el alumno");
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, " No se pudo modificar el alumno");
        }

    }
 // ok
    public Alumno buscarAlumno(int id) {

        Alumno alumno = null;
        try {

            sql = "SELECT * FROM alumno WHERE id_alumno=? AND activo= 1";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id); 

            ResultSet rs = ps.executeQuery(); 
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setId_alumno(rs.getInt(1));
                alumno.setApellido(rs.getString(2));
                alumno.setNombre(rs.getString(3));
                alumno.setFechaNac(rs.getDate(4).toLocalDate()); // pasar de Date a LocalDate
                alumno.setActivo(rs.getBoolean(5));

            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error en la busqueda del alumno.");
        }
        return alumno;

    }
 // ok
    public List<Alumno> listarAlumnosActivos() {

        List<Alumno> alumnos = new ArrayList<>();
        try {
            sql = "SELECT * FROM alumno WHERE activo= 1  ORDER BY id_alumno ASC";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Alumno alumno = new Alumno();

                alumno.setId_alumno(rs.getInt(1));
                alumno.setApellido(rs.getString(2));
                alumno.setNombre(rs.getString(3));
                alumno.setFechaNac(rs.getDate(4).toLocalDate());
                alumno.setActivo(rs.getBoolean(5));
                alumnos.add(alumno);

            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error en la busqueda. ");
        }
        return alumnos;
    }
    
    public List<Alumno> listarAlumnosInactivos() {

        List<Alumno> alumnos = new ArrayList<>();
        try {
            sql = "SELECT * FROM alumno WHERE activo= 0  ORDER BY id_alumno ASC";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Alumno alumno = new Alumno();

                alumno.setId_alumno(rs.getInt(1));
                alumno.setApellido(rs.getString(2));
                alumno.setNombre(rs.getString(3));
                alumno.setFechaNac(rs.getDate(4).toLocalDate());
                alumno.setActivo(rs.getBoolean(5));
                alumnos.add(alumno);

            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error en la busqueda. ");
        }
        return alumnos;
    }
    
    
    
 // ok
    public void activarAlumno(int id) {
        sql = "UPDATE alumno SET activo =1 WHERE id_alumno=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();

            ps.close();
            JOptionPane.showMessageDialog(null, " Se dió de alta el alumno");
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, " No se puedo activar el alumno");
        }

    }

}
