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
import javax.swing.JOptionPane;
import modelo.Alumno;
import modelo.Conexion;
import modelo.Materia;

/**
 *
 * @author Caro_Z
 */
public class MateriaData {

    private Connection con = null;
    private String sql;

    public MateriaData(Conexion conexion) {

        con = conexion.getConexion();
    }
// ok
    public void agregarMateria(Materia materia) {
        try {
            sql = "INSERT INTO materia(nombre, año, activo) VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnio());
            ps.setBoolean(3, true);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                materia.setId_materia(rs.getInt(1));
                JOptionPane.showMessageDialog(null, " Se agregó una nueva materia.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo ingresar la materia.");

            }
            ps.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, " Error");
        }

    }
    
    public boolean materiaExiste(int id) {
        boolean ret = false;        
        try {
            sql = "SELECT * FROM `materia` WHERE `id_materia` = ?";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                ret = true;
             }
            } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al buscar la materia, no existe");
                    }
        return ret;
    }

   // ok
    public void eliminarMateria(int id) {

        sql = "UPDATE materia SET activo =0 WHERE id_materia=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();

            ps.close();
                 JOptionPane.showMessageDialog(null, " Se eliminó la materia");
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, " No se pudo elimiar la materia");
        }

    }

   // ok
    public void modificarMateria(int id, Materia materia) {
       
        sql = "UPDATE materia SET nombre=?, año=? WHERE id_materia= ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, materia.getNombre());
            ps.setInt(2, (materia.getAnio()));
            ps.setInt(3, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, " Se modificó la materia");
            ps.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, " No se pudo modificar la materia");
        }

    }
 // ok
    public Materia buscarMateria(int id) {
        Materia materia = null;
        try {

            sql = "SELECT * FROM materia WHERE id_materia=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id); 

            ResultSet rs = ps.executeQuery(); 
            if (rs.next()) {
                materia = new Materia();
                materia.setId_materia(rs.getInt(1));
                materia.setNombre(rs.getString(2));
                materia.setAnio(rs.getInt(3)); 
                materia.setActivo(rs.getBoolean(4));

            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error en la busqueda de la materia.");
        }
        return materia;

    }
 // ok
    public List<Materia> listarMateriasActivas() {

        List<Materia> materias = new ArrayList<>();
        try {
            sql = "SELECT * FROM materia WHERE activo= 1 ORDER BY id_materia ASC";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia();

                materia.setId_materia(rs.getInt(1));
                materia.setNombre(rs.getString(2));
                materia.setAnio(rs.getInt(3));
                materia.setActivo(rs.getBoolean(4));
                materias.add(materia);

            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error en la busqueda. ");
        }
        return materias;
    }
    
    public List<Materia> listarMateriasInactivas() {

        List<Materia> materias = new ArrayList<>();
        try {
            sql = "SELECT * FROM materia WHERE activo= 0 ORDER BY id_materia ASC";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia();

                materia.setId_materia(rs.getInt(1));
                materia.setNombre(rs.getString(2));
                materia.setAnio(rs.getInt(3));
                materia.setActivo(rs.getBoolean(4));
                materias.add(materia);

            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error en la busqueda. ");
        }
        return materias;
    }
    
//ok
    public void activarMateria(int id) {
        sql = "UPDATE materia SET activo =1 WHERE id_materia=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();

            ps.close();
            JOptionPane.showMessageDialog(null, " Se activó la materia ");
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, " No se puede activar la materia ");
        }
    }
}
