
package vistas;

import controlador.AlumnoData;
import controlador.InscripcionData;
import controlador.MateriaData;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Alumno;
import modelo.Conexion;
import modelo.Inscripcion;
import modelo.Materia;

public class ViewCargaDeNotas extends javax.swing.JInternalFrame {

    private DefaultTableModel modelo;
    private ArrayList<Inscripcion> listarInscripcion;
    private ArrayList<Materia> listarMaterias;
    private ArrayList<Alumno> listaAlumnos;
    private InscripcionData inscripcionData;
    private MateriaData materiaData;
    private AlumnoData alumnoData;
    private Conexion conexion;

    public ViewCargaDeNotas() {
        initComponents();

        conexion = new Conexion();
        modelo = new DefaultTableModel();

        inscripcionData = new InscripcionData(conexion);
        listarInscripcion = (ArrayList) inscripcionData.listarInscripciones();

        materiaData = new MateriaData(conexion);
        listarMaterias = (ArrayList) materiaData.listarMateriasActivas();

        alumnoData = new AlumnoData(conexion);
        listaAlumnos = (ArrayList) alumnoData.listarAlumnosActivos();

        cargarAlumnos();
        armarCabeceraTabla();
        borrarFilasT();
        cargarTabla();
    }

    private void armarCabeceraTabla() {
        ArrayList<Object> columnas = new ArrayList<Object>();
        columnas.add("ID");
        columnas.add("Materia");
        columnas.add("Año");
        columnas.add("Nota");

        for (Object it : columnas) {
            modelo.addColumn(it);
        }
        jtCargaDeNotas.setModel(modelo);
    }

    private void cargarAlumnos() {
        for (Alumno item : listaAlumnos) {
            jComboBoxAlumno.addItem(item);
        }
    }

    public void borrarFilasT() {
        int fila = modelo.getRowCount() - 1;
        for (int i = fila; i >= 0; i--) {
            modelo.removeRow(i);
        }
    }

    private void cargarTabla() {
        borrarFilasT();

        Alumno a = (Alumno) jComboBoxAlumno.getSelectedItem();
        int id = a.getId_alumno();
        List<Inscripcion> listIns = inscripcionData.buscarInscripcionesPorAlumno(id);
        for (Inscripcion ins : listIns) {
            modelo.addRow(new Object[]{ins.getMateria().getId_materia(), ins.getMateria().getNombre(), ins.getMateria().getAnio(), ins.getNota()});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxAlumno = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtCargaDeNotas = new javax.swing.JTable();
        jbGuardar = new javax.swing.JButton();
        txtNota = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jbCancelar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("CARGA DE NOTAS");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("ALUMNO");

        jComboBoxAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAlumnoActionPerformed(evt);
            }
        });

        jtCargaDeNotas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)));
        jtCargaDeNotas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jtCargaDeNotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "MATERIA", "AÑO", "NOTA"
            }
        ));
        jtCargaDeNotas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtCargaDeNotasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtCargaDeNotas);

        jbGuardar.setText("GUARDAR");
        jbGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGuardarActionPerformed(evt);
            }
        });

        txtNota.setEnabled(false);
        txtNota.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNotaFocusLost(evt);
            }
        });
        txtNota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNotaKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("NOTA");

        jbCancelar.setText("SALIR");
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(68, 68, 68)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(80, 80, 80))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(68, 68, 68)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBoxAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(82, 82, 82)
                                    .addComponent(jbGuardar)
                                    .addGap(44, 44, 44)
                                    .addComponent(jbCancelar))))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jbGuardar)
                            .addComponent(jbCancelar))
                        .addGap(56, 56, 56))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jComboBoxAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAlumnoActionPerformed
       
        borrarFilasT();
        cargarTabla();


    }//GEN-LAST:event_jComboBoxAlumnoActionPerformed

    private void jbGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGuardarActionPerformed

         int filaSeleccionada = jtCargaDeNotas.getSelectedRow();
        if (!txtNota.getText().isEmpty()&& filaSeleccionada != -1) {

           
            Alumno a = (Alumno) jComboBoxAlumno.getSelectedItem();

            double nota_n = Double.parseDouble(txtNota.getText());
            int id_materia = (Integer) jtCargaDeNotas.getValueAt(filaSeleccionada, 0);
            String nombre = (String) jtCargaDeNotas.getValueAt(filaSeleccionada, 1);
            int anio = (Integer) jtCargaDeNotas.getValueAt(filaSeleccionada, 2);

            InscripcionData ins = new InscripcionData(conexion);

            ins.registrarNota(nota_n, a.getId_alumno(), id_materia);
            txtNota.setText("");
            cargarTabla();
            txtNota.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fila ");
        }
    }//GEN-LAST:event_jbGuardarActionPerformed

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
      

        dispose();
    }//GEN-LAST:event_jbCancelarActionPerformed

    private void jtCargaDeNotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtCargaDeNotasMouseClicked
      
        int filaSeleccionada = jtCargaDeNotas.getSelectedRow();
        try {
            if (filaSeleccionada != -1) {

                txtNota.setEnabled(true);

                Alumno a = (Alumno) jComboBoxAlumno.getSelectedItem();

                int id_materia = (Integer) jtCargaDeNotas.getValueAt(filaSeleccionada, 0);
                String nombre = (String) jtCargaDeNotas.getValueAt(filaSeleccionada, 1);
                int anio = (Integer) jtCargaDeNotas.getValueAt(filaSeleccionada, 2);
                double nota = (Double) jtCargaDeNotas.getValueAt(filaSeleccionada, 3);

                txtNota.setText(nota + "");

            } else {
                JOptionPane.showMessageDialog(this, " Debe seleccionar una fila");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, " Volver a seleccionar una fila");
        }
    }//GEN-LAST:event_jtCargaDeNotasMouseClicked

    private void txtNotaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNotaFocusLost
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_txtNotaFocusLost

    private void txtNotaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNotaKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        boolean numeros = key >= 48 && key <= 57;
        if (!numeros) {
            JOptionPane.showMessageDialog(this,"La nota debe ser numérica");
            evt.consume();
        }    
        
        
    }//GEN-LAST:event_txtNotaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Alumno> jComboBoxAlumno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbGuardar;
    private javax.swing.JTable jtCargaDeNotas;
    private javax.swing.JTextField txtNota;
    // End of variables declaration//GEN-END:variables
}
