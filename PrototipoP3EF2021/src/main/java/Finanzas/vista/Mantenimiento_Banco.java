/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Finanzas.vista;

import Finanzas.datos.BancoDAO;
import Finanzas.datos.Conexion;
import Finanzas.dominio.Banco;
import java.io.File;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import seguridad.datos.BitacoraDao;
import seguridad.dominio.Bitacora;
import seguridad.vista.Login;

/**
 *
 * @author Nay Ale
 */
public class Mantenimiento_Banco extends javax.swing.JInternalFrame {
int codigoAplicacion = 1008;
    /**
     * Creates new form Mantenimiento_Banco
     */
    public Mantenimiento_Banco() {
        initComponents();
        int codigoAplicacion = 1008; // numero de asignacion para perfiles para bitacora.
        llenadodetablas();    // metodo para el llenado de la tabla con los datos automaticamente
    }
  public void limpiar() {   //metodo de Limpiar automaticamente las cajas de textos y radio boton
        txt_CodigoBanco.setText("");
        txt_NombreBanco.setText("");
        txt_ClaveBanco.setText("");
        txt_TelefonoBanco.setText("");
        txt_Buscar.setText("");
    }
  public void buscarbanco(){    ///metodo para buscar un perfil ingresado y guardado a la base de datos
    Banco bancoconsultar = new Banco();
    BancoDAO bancoDAO = new BancoDAO();
    bancoconsultar.setCodigo_Banco((txt_Buscar.getText().toString()));
    //-------------------------------------------------------------------
    bancoconsultar = bancoDAO.query(bancoconsultar);
    txt_CodigoBanco.setText((bancoconsultar.getCodigo_Banco()));
    txt_NombreBanco.setText(bancoconsultar.getNombre_Banco());
    txt_ClaveBanco.setText(bancoconsultar.getClave_Banco());
    txt_TelefonoBanco.setText(bancoconsultar.getTelefono_Banco());
  }
  public void llenadodetablas(){   // metodo de llenado de tablas automaticamente aparecen los datos guardados en bd y se despliega en automatico
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Codigo Banco");
        modelo.addColumn("Nombre Banco");
        modelo.addColumn("Clave Banco");
        modelo.addColumn("Telefono Banco");
        BancoDAO bancoDAO = new BancoDAO();
        List<Banco> bancos = bancoDAO.listar();
        Tabla_Banco.setModel(modelo);
        String[] dato = new String[4];
        for (int i = 0; i < bancos.size(); i++) {
            dato[0] = bancos.get(i).getCodigo_Banco();
            dato[1] = bancos.get(i).getNombre_Banco();
            dato[2] = bancos.get(i).getClave_Banco();
            dato[3] = bancos.get(i).getTelefono_Banco();   
            modelo.addRow(dato);
        }
}
  //bitacora
        BitacoraDao BitacoraDAO = new BitacoraDao();
        Bitacora AInsertar = new Bitacora();
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_ClaveBanco = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_CodigoBanco = new javax.swing.JTextField();
        txt_NombreBanco = new javax.swing.JTextField();
        BtnIng = new javax.swing.JButton();
        BtnMod = new javax.swing.JButton();
        BtnElim = new javax.swing.JButton();
        BtnBus = new javax.swing.JButton();
        BtnAyu = new javax.swing.JButton();
        txt_Buscar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_TelefonoBanco = new javax.swing.JTextField();
        btnImprimir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_Banco = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Mantenimiento_Banco");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));

        jLabel1.setText("Codigo Banco:");

        jLabel2.setText("Nombre Banco:");

        jLabel3.setText("Clave Banco:");

        BtnIng.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        BtnIng.setText("Guardar");
        BtnIng.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnIng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnIngActionPerformed(evt);
            }
        });

        BtnMod.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        BtnMod.setText("Modificar");
        BtnMod.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModActionPerformed(evt);
            }
        });

        BtnElim.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        BtnElim.setText("Eliminar");
        BtnElim.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnElim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnElimActionPerformed(evt);
            }
        });

        BtnBus.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        BtnBus.setText("Buscar");
        BtnBus.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBusActionPerformed(evt);
            }
        });

        BtnAyu.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        BtnAyu.setText("Ayuda");
        BtnAyu.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnAyu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAyuActionPerformed(evt);
            }
        });

        jLabel4.setText("Telefono Banco:");

        btnImprimir.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addGap(92, 92, 92)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_NombreBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_CodigoBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(107, 107, 107)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_TelefonoBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_ClaveBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(BtnIng, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(BtnBus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(22, 22, 22)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(146, 146, 146)
                                .addComponent(btnImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(BtnMod, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(BtnElim, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                .addGap(30, 30, 30)
                                .addComponent(BtnAyu, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txt_CodigoBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txt_NombreBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_ClaveBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_TelefonoBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnBus)
                            .addComponent(txt_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImprimir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnIng)
                    .addComponent(BtnMod)
                    .addComponent(BtnElim)
                    .addComponent(BtnAyu))
                .addGap(29, 29, 29))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle"));

        Tabla_Banco.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Banco", "Nombre Banco", "Clave Banco", "Telefono Banco"
            }
        ));
        jScrollPane1.setViewportView(Tabla_Banco);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnIngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnIngActionPerformed
     Banco bancosInsertar = new Banco();
     BancoDAO bancoDAO = new BancoDAO();
        // Prueba insert
        bancosInsertar.setCodigo_Banco(txt_CodigoBanco.getText());
        bancosInsertar.setNombre_Banco(txt_NombreBanco.getText());
        bancosInsertar.setClave_Banco(txt_ClaveBanco.getText()); 
        bancosInsertar.setTelefono_Banco(txt_TelefonoBanco.getText());
        //bitacora
        AInsertar.setId_Usuario(Login.usuarioFianzas);
        AInsertar.setAccion("Insertar");
        AInsertar.setCodigoAplicacion("1008");
        AInsertar.setModulo("1000");
        try {
            BitacoraDAO.insert(AInsertar);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Mantenimiento_Banco.class.getName()).log(Level.SEVERE, null, ex);
        }
            JOptionPane.showMessageDialog(null, "Banco registrado Exitosamente"); 
            bancoDAO.insert(bancosInsertar);
            llenadodetablas();
            limpiar();
                          
        
    }//GEN-LAST:event_BtnIngActionPerformed

    private void BtnModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModActionPerformed
        BancoDAO bancoDAO = new BancoDAO();
        Banco bancosModificar = new Banco();
        //BANCO modificar
        bancosModificar.setCodigo_Banco(txt_CodigoBanco.getText());
        bancosModificar.setNombre_Banco(txt_NombreBanco.getText());
        bancosModificar.setClave_Banco(txt_ClaveBanco.getText()); 
        bancosModificar.setTelefono_Banco(txt_TelefonoBanco.getText());
        //bitacora
        AInsertar.setId_Usuario(Login.usuarioFianzas);
        AInsertar.setAccion("Modificar");
        AInsertar.setCodigoAplicacion("1008");
        AInsertar.setModulo("1000");
        try {
            BitacoraDAO.insert(AInsertar);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Mantenimiento_Banco.class.getName()).log(Level.SEVERE, null, ex);
        }
        bancoDAO.update(bancosModificar);
        JOptionPane.showMessageDialog(null, "Banco Modificado Exitosamente");
        llenadodetablas();
        limpiar();            
    }//GEN-LAST:event_BtnModActionPerformed

    private void BtnElimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnElimActionPerformed
        Banco bancoEliminar = new Banco();
        BancoDAO bancoDAO = new BancoDAO();
        //Prueba delete
        bancoEliminar.setCodigo_Banco(txt_CodigoBanco.getText());
        //bitacora
        AInsertar.setId_Usuario(Login.usuarioFianzas);
        AInsertar.setAccion("Eliminar");
        AInsertar.setCodigoAplicacion("1008");
        AInsertar.setModulo("1000");
        try {
            BitacoraDAO.insert(AInsertar);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Mantenimiento_Banco.class.getName()).log(Level.SEVERE, null, ex);
        }
        bancoDAO.delete(bancoEliminar);
        JOptionPane.showMessageDialog(null, "Banco Eliminado.");
        llenadodetablas();
        limpiar();
    }//GEN-LAST:event_BtnElimActionPerformed

    private void BtnBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBusActionPerformed
         buscarbanco();    //metodo que busca el BANCO ingresado en la base de datos
    }//GEN-LAST:event_BtnBusActionPerformed

    private void BtnAyuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAyuActionPerformed
        /// metodo para ejecutar la ayuda que es una guia para el mantenimiento BANCO
         try {
            if ((new File("src\\main\\java\\Finanzas\\ayudas\\MantenimientoBanco.chm")).exists()) {
                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler src\\main\\java\\Finanzas\\ayudas\\MantenimientoBanco.chm");
                p.waitFor();
            } else {
                JOptionPane.showMessageDialog(null, "La ayuda no Fue encontrada");
            }
            //System.out.println("Correcto");
        } catch (Exception ex) {
            ex.printStackTrace();
        }    
    }//GEN-LAST:event_BtnAyuActionPerformed
 private Connection connection = null;
    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        Map p = new HashMap();
        JasperReport report;
        JasperPrint print;

        try {
            connection = Conexion.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/main/java/Finanzas/reportes/reporteBancos.jrxml");
            print = JasperFillManager.fillReport(report, p, connection);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte Banco");
            view.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnImprimirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAyu;
    private javax.swing.JButton BtnBus;
    private javax.swing.JButton BtnElim;
    private javax.swing.JButton BtnIng;
    private javax.swing.JButton BtnMod;
    private javax.swing.JTable Tabla_Banco;
    public javax.swing.JButton btnImprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_Buscar;
    private javax.swing.JTextField txt_ClaveBanco;
    private javax.swing.JTextField txt_CodigoBanco;
    private javax.swing.JTextField txt_NombreBanco;
    private javax.swing.JTextField txt_TelefonoBanco;
    // End of variables declaration//GEN-END:variables
}
