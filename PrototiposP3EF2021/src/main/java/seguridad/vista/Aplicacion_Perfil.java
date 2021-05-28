/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad.vista;
import java.io.File;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
import seguridad.dominio.Aplicacion;
import seguridad.dominio.Perfil;
import seguridad.dominio.Asignacion_Aplicacion_Perfil;
import seguridad.datos.AplicacionDAO;
import seguridad.datos.PerfilDAO;
import seguridad.datos.Asignacion_Aplicacion_PerfilDAO;
import seguridad.datos.BitacoraDao;
import seguridad.dominio.Bitacora;

/**
 *
 * @author leelu
 */
public class Aplicacion_Perfil extends javax.swing.JInternalFrame {
    DefaultTableModel modelo1;
    DefaultTableModel modelo2; 
        int estadovalidacion;
        String codigoAplicacion = "110";

    /**
     * Creates new form Perfil_App
     */
    public Aplicacion_Perfil() {
        initComponents();
        String perfil;
        PerfilDAO personaDAO = new PerfilDAO();
        List<Perfil> perfiles = personaDAO.listar();
        consulta_perfil.addItem("Seleccionar...");
        for (Perfil persona : perfiles) {
           
           consulta_perfil.addItem(String.valueOf(persona.getPk_id_perfil()));
        }
//-----------------------------------------------------------------------------------------------------------//     
modelo1=new DefaultTableModel();   //ASIGNAMOS UN NUEVO DEFAULTABLEMODEL AL OBJETO MODELO1
        modelo1.addColumn("CODIGO");      //LE AÑADIMOS COLUMNAS AL OBJETO MODELO
        modelo1.addColumn("NOMBRE");
        tabla1.setModel(modelo1);
String datos[]= new String[2];
        AplicacionDAO dao = new  AplicacionDAO();
        List<Aplicacion> personas = dao.select2();
        for (Aplicacion persona : personas) {       
               
               datos[0]=String.valueOf(persona.getId_Aplicacion());
               datos[1]=persona.getNombre_Aplicacion();
               
                modelo1.addRow(datos);
        tabla1.setModel(modelo1);
                        
        }
    }
    
public void LimpiarTabla2(){
        modelo2=new DefaultTableModel();   //ASIGNAMOS UN NUEVO DEFAULTABLEMODEL AL OBJETO MODELO1
    
       modelo2.addColumn("ID PERFIL");
        modelo2.addColumn("ID APLICACION");
        modelo2.addColumn("INGRESAR");
        modelo2.addColumn("CONSULTAR");
        modelo2.addColumn("MODIFICAR");
        modelo2.addColumn("ELIMINAR");
        modelo2.addColumn("IMPRIMIR");
        tabla2.setModel(modelo2);         //LE SETEAMOS EL OBJETO MODELO1 (TABLEMODEL) A LA TABLA (JTABLE) 
    }

private void GuardarEnBitacora(String accion, String codigoModulo, String idUsuario){
        BitacoraDao BitacoraDAO = new BitacoraDao();
        Bitacora AInsertar = new Bitacora();
        boolean estado=false;
        switch(accion){
            case "Insertar":
                AInsertar.setId_Usuario(idUsuario);
                AInsertar.setAccion("Inserción");
                AInsertar.setCodigoAplicacion(codigoModulo);estado=true;
                break;
            case "Modificacion":
                AInsertar.setId_Usuario(idUsuario);
                AInsertar.setAccion("Modificación");
                AInsertar.setCodigoAplicacion(codigoModulo);estado=true;
                break;
            case "Eliminacion":
                AInsertar.setId_Usuario(idUsuario);
                AInsertar.setAccion("Eliminar");
                AInsertar.setCodigoAplicacion(codigoModulo);estado=true;
        }
        if (estado==true) {
        try {
            BitacoraDAO.insert(AInsertar);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Aplicacion_Perfil.class.getName()).log(Level.SEVERE, null, ex);
        }
        }         
    }

public void Limpiar(){
    txt_Nombre_Perfil.setText("");
        consulta_perfil.setSelectedItem("Seleccionar...");
        LimpiarTabla2();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_Nombre_Perfil = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Boton_Buscar_Perfil = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        consulta_perfil = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla2 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Asignacion Aplicacion a Perfiles");
        setVisible(true);

        txt_Nombre_Perfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_Nombre_PerfilActionPerformed(evt);
            }
        });

        jLabel1.setText("Aplicaciones Disponibles");

        jLabel2.setText("Aplicaciones Asignadas");

        jLabel3.setText("Perfil Asignado");

        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel4.setText("Guardar");

        jLabel5.setText("Quitar");

        jLabel6.setText("Asignar");

        Boton_Buscar_Perfil.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Boton_Buscar_Perfil.setText("?");
        Boton_Buscar_Perfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_Buscar_PerfilActionPerformed(evt);
            }
        });

        jButton1.setText(">");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText(">>");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("<");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("<<");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        consulta_perfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consulta_perfilActionPerformed(evt);
            }
        });

        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "NOMBRE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla1);

        tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tabla2);

        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel7.setText("Ayuda");

        jLabel8.setText("1= Permitido 0=Sin Permiso");

        jLabel9.setText("Ingresar en Cada celda el permiso que quiera dar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(consulta_perfil, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Boton_Buscar_Perfil)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_Nombre_Perfil, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(84, 84, 84))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(consulta_perfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Boton_Buscar_Perfil)
                    .addComponent(txt_Nombre_Perfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_Nombre_PerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_Nombre_PerfilActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_Nombre_PerfilActionPerformed

    private void Boton_Buscar_PerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_Buscar_PerfilActionPerformed
        LimpiarTabla2();
        String valor=consulta_perfil.getSelectedItem().toString();
        int valorint=Integer.parseInt(valor);
        PerfilDAO perfilDAO = new PerfilDAO();
        Perfil perfilconsultar = new Perfil();
        perfilconsultar.setPk_id_perfil(valorint);
        perfilconsultar=perfilDAO.query(perfilconsultar);
        if (perfilconsultar.getEstado_perfil()!=0) {
            estadovalidacion=1;
            txt_Nombre_Perfil.setText(perfilconsultar.getNombre_perfil());
        }else{
            JOptionPane.showMessageDialog(null, "Perfil no encontrado");
            estadovalidacion=0;
        }
        
//----------------------------------------------------------------------------------------//
                
String combobox=consulta_perfil.getSelectedItem().toString();
int validar=Integer.parseInt(combobox);
String datos[]= new String[7];
        Asignacion_Aplicacion_PerfilDAO dao = new  Asignacion_Aplicacion_PerfilDAO();
        List<Asignacion_Aplicacion_Perfil> personas = dao.select2();
        for (Asignacion_Aplicacion_Perfil persona : personas) {       
            if (validar==Integer.parseInt(persona.getCodigo_Perfil())) {
               
               datos[0]=persona.getCodigo_Perfil();
               datos[1]=persona.getCodigo_Aplicacion();
               datos[6]=persona.getImprimir();
               datos[4]=persona.getModificar();
               datos[5]=persona.getEliminar();
               datos[2]=persona.getIngresar();
               datos[3]=persona.getConsultar();
               
                modelo2.addRow(datos);
        tabla2.setModel(modelo2);
            }            
        }
        
//----------------------------------------------------------------------------------------//


        
    }//GEN-LAST:event_Boton_Buscar_PerfilActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
int filaSeleccionada=tabla1.getSelectedRow();  //LE ASIGNAMOS UNA VARIABLE INTEGER A LA FILA SELECCIONADA EN LA TABLA
        if (estadovalidacion==1) {
        if(filaSeleccionada>=0){   //SI EXISTE UNA FILA SELECCIONADA REALIZARA EL TRASPASO
            
               String Vector[]=new String[2];   //CREAR UN VECTOR
                //LE ASIGNAMOS AL VECTOR Y CAPTURAMOS LOS DATOS DE LA TABLA1 EN LA FILA SELECCIONADA EN LA POSICION 0 Y 1,
                //ES DECIR COLUMNA NOMBRES Y APELLIDOS
                
               Vector[0]=consulta_perfil.getSelectedItem().toString();
               Vector[1]=tabla1.getValueAt(filaSeleccionada, 0).toString();
               modelo2.addRow(Vector); 
               
               
                  //LLAMAMOS AL MODELO DE LA PANTALLA2 Y LE AGREGAMOS EL VECTOR COMO UNA NUEVA FILA
        }    
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
for (int i = 0; i < tabla1.getRowCount(); i++) {
            String Vector[]=new String[2];
            
            Vector[0]=consulta_perfil.getSelectedItem().toString();
            Vector[1]=tabla1.getValueAt(i, 0).toString();
            
            modelo2.addRow(Vector);    //LLAMAMOS AL MODELO DE LA PANTALLA2 Y LE AGREGAMOS EL VECTOR COMO UNA NUEVA FILA
            
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
int filaSeleccionada=tabla2.getSelectedRow();  //LE ASIGNAMOS UNA VARIABLE INTEGER A LA FILA SELECCIONADA EN LA TABLA
        
        if(filaSeleccionada>=0){   //SI EXISTE UNA FILA SELECCIONADA REALIZARA EL TRASPASO
            
               String Vector[]=new String[2];   //CREAR UN VECTOR
                //LE ASIGNAMOS AL VECTOR Y CAPTURAMOS LOS DATOS DE LA TABLA1 EN LA FILA SELECCIONADA EN LA POSICION 0 Y 1,
                //ES DECIR COLUMNA NOMBRES Y APELLIDOS
               Vector[0]=tabla2.getValueAt(filaSeleccionada, 0).toString();    
               Vector[1]=tabla2.getValueAt(filaSeleccionada, 1).toString();
               
               modelo2.removeRow(filaSeleccionada);  //ELIMINAMOS LA FILA TRASPASADA DE LA PANTALLA1
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    LimpiarTabla2();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void consulta_perfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consulta_perfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_consulta_perfilActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        Asignacion_Aplicacion_PerfilDAO modulosDAO = new Asignacion_Aplicacion_PerfilDAO();
        String Vector[]=new String[7];
            Asignacion_Aplicacion_Perfil moduloInsertar = new Asignacion_Aplicacion_Perfil();
            
            for (int i = 0; i < tabla2.getRowCount(); i++) {
             
           moduloInsertar.setCodigo_Perfil(consulta_perfil.getSelectedItem().toString());
            
            modulosDAO.delete(moduloInsertar);   
        }
            
            for (int i = 0; i < tabla2.getRowCount(); i++) {
             
           
           Vector[0]=(String) tabla2.getValueAt(i, 0);
           Vector[1]=(String) tabla2.getValueAt(i, 1);
           Vector[2]=(String) tabla2.getValueAt(i, 2);
           Vector[3]=(String) tabla2.getValueAt(i, 3);
           Vector[4]=(String) tabla2.getValueAt(i, 4);
           Vector[5]=(String) tabla2.getValueAt(i, 5);
           Vector[6]=(String) tabla2.getValueAt(i, 6);
            
            moduloInsertar.setCodigo_Perfil(Vector[0]);           
            moduloInsertar.setCodigo_Aplicacion(Vector[1]);
            moduloInsertar.setIngresar(Vector[2]);
            moduloInsertar.setConsultar(Vector[3]);
            moduloInsertar.setModificar(Vector[4]);
            moduloInsertar.setEliminar(Vector[5]);
            moduloInsertar.setImprimir(Vector[6]);
            
            modulosDAO.insert(moduloInsertar);   
        }
            
            JOptionPane.showMessageDialog(null, "Modulo registrado correctamente");
            Limpiar();
            GuardarEnBitacora("Insertar",codigoAplicacion,  Login.usuarioSesion);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        try {
            if ((new File("src\\main\\java\\seguridad\\ayuda\\AyudaAsignacionAplicacionAPerfiles.chm")).exists()) {
                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler src\\main\\java\\seguridad\\ayuda\\AyudaAsignacionAplicacionAPerfiles.chm");
                p.waitFor();
            } else {
                JOptionPane.showMessageDialog(null, "La ayuda no Fue encontrada");
            }
            //System.out.println("Correcto");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Boton_Buscar_Perfil;
    private javax.swing.JComboBox<String> consulta_perfil;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable tabla1;
    private javax.swing.JTable tabla2;
    private javax.swing.JTextField txt_Nombre_Perfil;
    // End of variables declaration//GEN-END:variables
}
