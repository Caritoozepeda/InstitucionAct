/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package institucion;

import controlador.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.*;
import modelo.Alumno;
import modelo.Conexion;
import modelo.Materia;
import vistas.ViewInstitucion;

/**
 *
 * ORDER BY inscripcion.nota DESC, materia.nombre ASC
 */
public class Institucion {

    private static Conexion conexion;
    private static AlumnoData ad;
    private static MateriaData md;

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     // TODO code application logic here
     ViewInstitucion cv= new  ViewInstitucion();
        cv.setVisible(true);
      /*     conexion = new Conexion();
         ad = new AlumnoData(conexion);
        Alumno a = new Alumno(1, "Nuñez", "Pablo", LocalDate.of(1990, 8, 20), true);
        Alumno a2 = new Alumno(2, "Mino", "Sofia", LocalDate.of(1990, 9, 10), true);
   */ 
        // ..................METODOS ALUMNO DATA............................////
//        ad.agregarAlumno(a);
//       ad.agregarAlumno(a2);
//      System.out.println(" "+ ad.buscarAlumno(1));
       
        //      ad.eliminarAlumno(1);
        //      ad.activarAlumno(1);       
        //        ad.modificarAlumno(3,a);
        /*   
         List<Alumno> alum =ad. listarAlumnos();
            for( Alumno g :alum){
                System.out.println(" "+ g.getId_alumno()+ " "+g.getApellido()+" "+g.getNombre()+" "+g.getFechaNac()+ " "+ g.isActivo());
            }
         */
        // ..................METODOS MATERIA DATA............................////      
   /*       md = new MateriaData(conexion);

        Materia m = new Materia(11, "Dibujo", 2, true);
        Materia m1 = new Materia("Algoritmos", 1, true);
  */ 
        //         md.agregarMateria(m);
        //           md.eliminarMateria(12);
        //      md.modificarMateria(12,m1);
        //   System.out.println(""+ md.buscarMateria(3)) ;
//      md.activarMateria(11);
        /*
       List<Materia> mat= md.listarMaterias();
          for(Materia p: mat){
           System.out.println(p.getId_materia()+" "+p.getNombre()+" "+p.getAnio()+" "+p.isActivo()); 
            
          }
         */
// ..................METODOS INSCRIPCION DATA............................//// 
   /*     Inscripcion in = new Inscripcion(a2, m, 7.7);
        InscripcionData id = new InscripcionData(conexion);
    
    */     /* 1
        List<Materia> mat = id.listarMateriasPorAlumno(7);
            for(Materia t: mat ){
                System.out.println(" "+t.getId_materia()+ " - "+ t.getNombre()+ "-"+ t.getAnio()+ "- "+t.isActivo()); 
            }
         */
 /*  2
       List<Alumno> alu = id.listarAlumnosPorMateria(8);
            for(Alumno al:alu){
                System.out.println(al.getId_alumno()+"  - "+al.getApellido()+ " - "+ al.getNombre()+ "/ "+al.getFechaNac()+"  /"+al.isActivo());
            }
         */
        //  3   id.inscribir(in);
        //  4     id.eliminarInscripcion(1,1);
        //  5    id.registrarNota(8, 1, 1);
        /* 6
    
         List<Inscripcion> it=id.buscarInscripcionesPorAlumno(2);
            for( Inscripcion t:it){
              System.out.println("id_inscrip " + t.getId_inscripcion()+ "- "+ t.getAlumno()+ " -"+t.getMateria()+ " nota "+t.getNota());    
            }
         */
 /* 7
        List<Alumno> alu = id.listarAlumnosNoInscriptosPorMateria(8);
            for(Alumno al:alu){
                System.out.println(al.getId_alumno()+" "+al.getApellido()+ " "+ al.getNombre()+ " "+al.getFechaNac()+"-"+al.isActivo());
            }
         */
 /* 8
        List <Materia> mate=id.listarMateriasNoInscriptasPorAlumno(2);
            for( Materia h : mate){
                System.out.println(" "+h.getId_materia()+ " - "+h.getNombre()+ " "+h.getAnio()+ " - "+h.isActivo());
            }
         
         */
 /*  9
         List<Inscripcion> it=id.listarInscripciones();
            for( Inscripcion t:it){
       
            System.out.println(" "+ t.getId_inscripcion()+" - "+t.getAlumno().getApellido()+" "+t.getAlumno().getNombre());
                System.out.println(" "+t.getMateria().getNombre()+"  "+t.getMateria().getAnio()+" - "+t.getNota() );
            }
         */
 /*     10 
         List<Inscripcion> insc= id.alumnosDesaprobados();
            for(Inscripcion p: insc){
             
           System.out.print("materia "+ p.getMateria().getId_materia()+ "- ");
           System.out.print(p.getMateria().getNombre() +" ,");
            System.out.print(p.getAlumno().getApellido()+ " ,"+p.getAlumno().getNombre());
             System.out.println(" - nota "+p.getNota());
           
          }
       */ 
        // 
        /*  11   
        List<Inscripcion> insc= id.alumnosAprobados();
            for(Inscripcion p: insc){
              
         System.out.print("materia "+ p.getMateria().getId_materia()+ "- ");
           System.out.print(p.getMateria().getNombre() +" ,");
       System.out.print(p.getAlumno().getApellido() + ","+p.getAlumno().getNombre());
             System.out.println(" - nota "+p.getNota());
           
          }
 
      */ 
        
     /*  // 12  
        List<Inscripcion> i= id.listarInscripcionesSinNota();
            for(Inscripcion p: i){
           
           System.out.print("materia "+ p.getMateria().getId_materia()+ "- ");
           
           System.out.print(p.getMateria().getNombre() +" ,");
          /*  //
           System.out.print(p.getAlumno().getApellido()+ " ,"+p.getAlumno().getNombre());
            
            
            System.out.println(" - nota "+p.getNota());
    }
  */
}
}
