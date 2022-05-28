/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
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
import modelo.Inscripcion;
import modelo.Materia;

/**
 *
 * @author Caro_Z
 */
public class InscripcionData {

    private Connection con = null;
    private String sql;
    private Conexion conexion;

    public InscripcionData(Conexion conexion) {
        this.conexion = conexion;
        con = conexion.getConexion();
    }


    public InscripcionData() {
       
    }
// ok 1 ***
    public List<Materia> listarMateriasPorAlumno(int id_alumno) {

        List<Materia> materias = new ArrayList<>();
        Materia mat;
        sql = " SELECT materia.id_materia, nombre, año, activo FROM inscripcion, materia WHERE inscripcion.id_materia = materia.id_materia AND inscripcion.id_alumno = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id_alumno);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                mat = new Materia();
                mat.setId_materia(rs.getInt("id_materia"));
                mat.setNombre(rs.getString("nombre"));
                mat.setAnio(rs.getInt("año"));
                mat.setActivo(rs.getBoolean("activo"));
                materias.add(mat);

            }
           
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo generar el listado de materias.");
        }
        return materias;
    }
//OK 2 ****

    public List<Alumno> listarAlumnosPorMateria(int id_materia) {

        List<Alumno> alumnos = new ArrayList<>();

        Alumno alum;
        sql = " SELECT alumno.id_alumno, apellido, nombre, fechaNac, activo FROM inscripcion, alumno WHERE inscripcion.id_alumno = alumno.id_alumno AND inscripcion.id_materia = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_materia);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                alum = new Alumno();
                alum.setId_alumno(rs.getInt("id_alumno"));
                alum.setApellido(rs.getString("apellido"));
                alum.setNombre(rs.getString("nombre"));
                alum.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                alum.setActivo(rs.getBoolean("activo"));

                alumnos.add(alum);

            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo generar el listado.");
        }
        return alumnos;
    }
// OK 3 *****

    public void inscribir(Inscripcion insc) {
        sql = "INSERT INTO inscripcion (id_alumno, id_materia, nota) VALUES (?, ?, ?)";

        try {

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, insc.getAlumno().getId_alumno());
            ps.setInt(2, insc.getMateria().getId_materia());
            ps.setDouble(3, insc.getNota());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                insc.setId_inscripcion(rs.getInt(1));

                JOptionPane.showMessageDialog(null, " Se realizó la inscripción.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo ingresar la inscripción.");

            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error en la conexíon- (inscribir).");
        }

    }

    // OK  4 *****
    public void eliminarInscripcion(int id_alumno, int id_materia) {

        try {

            sql = "DELETE FROM inscripcion WHERE id_alumno =? and id_materia = ?";

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id_alumno);
            ps.setInt(2, id_materia);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Se eliminó la inscripcion ");
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la inscripcion " + ex.getMessage());
        }

    }
   // OK 5 ////

    public void registrarNota(double nota, int id_alumno, int id_materia) {

        try {

            sql = "UPDATE inscripcion SET nota = ? WHERE id_alumno =? and id_materia =?;";

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, nota);
            ps.setInt(2, id_alumno);
            ps.setInt(3, id_materia);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se registró la nota ");

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar la nota " + ex.getMessage());
        }

    }

    //ok   6
    public List<Inscripcion> buscarInscripcionesPorAlumno(int id_alumno) {
        List<Inscripcion> inscripciones = new ArrayList<>();
        Inscripcion ins;
        Alumno a;
        Materia m;
        sql = "SELECT * FROM inscripcion WHERE id_alumno = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_alumno);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ins = new Inscripcion();

                ins.setId_inscripcion(rs.getInt("id_inscripcion"));

                a = buscarAlumno(rs.getInt("id_alumno"));
                ins.setAlumno(a);

                m = buscarMateria(rs.getInt("id_materia"));
                ins.setMateria(m);
                ins.setNota(rs.getDouble("nota"));

                inscripciones.add(ins);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener las inscripciones de los alumnos" + ex.getMessage());
        }

        return inscripciones;
    }

    // ok  7
    public List<Alumno> listarAlumnosNoInscriptosPorMateria(int id_materia) {
        List<Alumno> alumnos = new ArrayList<>();

        Alumno alum;
        sql = "SELECT * FROM alumno WHERE id_alumno NOT IN (SELECT alumno.id_alumno FROM inscripcion, alumno, materia WHERE inscripcion.id_alumno = alumno.id_alumno AND inscripcion.id_materia = materia.id_materia AND alumno.activo =1 AND inscripcion.id_materia = ? ) ORDER BY alumno.id_alumno ASC";

        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_materia);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                alum = new Alumno();

                alum.setId_alumno(rs.getInt("id_alumno"));
                alum.setApellido(rs.getString("apellido"));
                alum.setNombre(rs.getString("nombre"));
                alum.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                alum.setActivo(rs.getBoolean("activo"));

                alumnos.add(alum);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener los alumnos No inscriptos " + ex.getMessage());
        }

        return alumnos;
    }

    //ok 8***********
    public List<Materia> listarMateriasNoInscriptasPorAlumno(int id_alumno) {
        List<Materia> materias = new ArrayList<>();
        Materia materia;
        sql = "SELECT * FROM materia WHERE id_materia NOT IN (SELECT materia.id_materia FROM inscripcion, alumno, materia WHERE inscripcion.id_alumno = alumno.id_alumno AND inscripcion.id_materia = materia.id_materia AND materia.activo = 1 AND inscripcion.id_alumno = ?) ORDER BY materia.id_materia ASC";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_alumno);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                materia = new Materia();
                materia.setId_materia(rs.getInt("id_materia"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materia.setActivo(rs.getBoolean("activo"));
                materias.add(materia);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener las materias  No inscriptas " + ex.getMessage());
        }

        return materias;
    }

    
    
    public Alumno buscarAlumno(int id){
    
        AlumnoData ad=new AlumnoData(conexion);
        return ad.buscarAlumno(id);
    }

    public Materia buscarMateria(int id) {

        MateriaData md = new MateriaData(conexion);
        return md.buscarMateria(id);

    }
    
    
    
    //OK    9 ******
    public List<Inscripcion> listarInscripciones() {
        List<Inscripcion> inscripciones = new ArrayList<>();

        try {
            sql = "SELECT * FROM inscripcion ORDER BY inscripcion.nota DESC ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            Inscripcion inscrip;
            while (rs.next()) {

                inscrip = new Inscripcion();
                inscrip.setId_inscripcion(rs.getInt("id_inscripcion"));

                Alumno a = buscarAlumno(rs.getInt("id_alumno"));
                inscrip.setAlumno(a);

                Materia m = buscarMateria(rs.getInt("id_materia"));
                inscrip.setMateria(m);
                inscrip.setNota(rs.getInt("nota"));

                inscripciones.add(inscrip);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error en listar inscripciones.");
        }

        return inscripciones;

    }

    
     
    //ok nuevo 10 yo

    public List<Inscripcion> alumnosDesaprobados() {
        List<Inscripcion> inscripciones = new ArrayList<>();
        Inscripcion inscrip;

        sql = "SELECT * FROM inscripcion WHERE inscripcion.nota <6 AND inscripcion.nota >0;";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                
                inscrip = new Inscripcion();
                inscrip.setId_inscripcion(rs.getInt("id_inscripcion"));

                Alumno a = buscarAlumno(rs.getInt("id_alumno"));
                inscrip.setAlumno(a);

                Materia m = buscarMateria(rs.getInt("id_materia"));
                inscrip.setMateria(m);
                inscrip.setNota(rs.getInt("nota"));
                 
                inscrip.setAlumno(a);
                inscrip.setMateria(m);

                inscripciones.add(inscrip);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error en listar alumnos.");
        }
        return inscripciones;
    }

    // ok nuevo 11  
    public List<Inscripcion> alumnosAprobados() {
        List<Inscripcion> inscripciones = new ArrayList<>();
        Inscripcion inscrip;
 try {
//        sql = " SELECT alumno.id_alumno, alumno.apellido, alumno.nombre, materia.id_materia, materia.nombre, materia.año, inscripcion.nota FROM alumno, inscripcion, materia WHERE alumno.id_alumno = inscripcion.id_alumno and materia.id_materia = inscripcion.id_materia AND inscripcion.nota > 6 ORDER BY inscripcion.nota DESC, materia.nombre ASC ";
   sql=  "SELECT * FROM inscripcion WHERE inscripcion.nota > 6" ;
       
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

              inscrip = new Inscripcion();
                inscrip.setId_inscripcion(rs.getInt("id_inscripcion"));

                Alumno a = buscarAlumno(rs.getInt("id_alumno"));
                inscrip.setAlumno(a);

                Materia m = buscarMateria(rs.getInt("id_materia"));
                inscrip.setMateria(m);
                inscrip.setNota(rs.getInt("nota"));
                 
                inscrip.setAlumno(a);
                inscrip.setMateria(m);

                inscripciones.add(inscrip);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error en listar alumnos.");
        }
        return inscripciones;
    }
//// 12
    
    
     public List<Inscripcion> listarInscripcionesSinNota() {
        List<Inscripcion> inscripciones = new ArrayList<>();

        try {
            sql = "SELECT * FROM inscripcion WHERE inscripcion.nota =0 ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            Inscripcion inscrip;
            while (rs.next()) {

                inscrip = new Inscripcion();
                inscrip.setId_inscripcion(rs.getInt("id_inscripcion"));

                Alumno a = buscarAlumno(rs.getInt("id_alumno"));
                inscrip.setAlumno(a);

                Materia m = buscarMateria(rs.getInt("id_materia"));
                inscrip.setMateria(m);
                inscrip.setNota(rs.getInt("nota"));

                inscripciones.add(inscrip);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error en listar inscripciones.");
        }

        return inscripciones;

    }
}
