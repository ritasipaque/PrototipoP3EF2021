/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hoteleria.vista;

import Hoteleria.datos.ConexionHoteleria;
import Hoteleria.datos.GuardarBitacoraDAO;
import Hoteleria.datos.ServiciosDAO;
import Hoteleria.dominio.Servicios;
import java.io.File;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import seguridad.datos.BitacoraDao;
import seguridad.dominio.Bitacora;
import seguridad.vista.Aplicacion_Perfil;
import seguridad.vista.GenerarPermisos;
import seguridad.vista.Login;

/**
 *
 * @author leone
 */
public class Mantenimiento_Servicios extends javax.swing.JInternalFrame {
    DefaultTableModel modelo1;
    DefaultTableCellRenderer centro= new DefaultTableCellRenderer();
    String codigoAplicacion="2006";
    void habilitarAcciones() {

        var codigoAplicacion = 2006;
        var usuario = Login.usuarioHoteleria;

        BtnIng.setEnabled(false);
        BtnMod.setEnabled(false);
        BtnElim.setEnabled(false);
        BtnBus.setEnabled(false);

        GenerarPermisos permisos = new GenerarPermisos();

        String[] permisosApp = new String[5];

        for (int i = 0; i < 5; i++) {
            permisosApp[i] = permisos.getAccionesAplicacion(codigoAplicacion, usuario)[i];
        }

        if (permisosApp[0].equals("1")) {
            BtnIng.setEnabled(true);
        }
        if (permisosApp[1].equals("1")) {
            BtnBus.setEnabled(true);
        }
        if (permisosApp[2].equals("1")) {
            BtnMod.setEnabled(true);
        }
        if (permisosApp[3].equals("1")) {
            BtnElim.setEnabled(true);
        }
    }
    /**
     * Creates new form Mantenimiento_Servicios
     */
    public Mantenimiento_Servicios() {
        initComponents();
        habilitarAcciones();
        aux_tipo.setVisible(false);
        aux_estado.setVisible(false);
        actualizartabla();
    }
    private static boolean isNumeric(String cadena){
        try {
                Integer.parseInt(cadena);
                return true;
        } catch (NumberFormatException nfe){
                return false;
        }
    }
    private void limpiar(){
        aux_estado.setSelected(true);
        aux_tipo.setSelected(true);
        txt_id.setText("");
        txt_nombre.setText("");
        txt_descripcion.setText("");
        txt_precio.setText("");
    }
    private void actualizartabla(){
        modelo1=new DefaultTableModel();   
        modelo1.addColumn("ID");      
        modelo1.addColumn("NOMBRE");
        modelo1.addColumn("DESCRIPCION");  
        modelo1.addColumn("PRECIO");     
        modelo1.addColumn("TIPO");    
        modelo1.addColumn("ESTADO");
        tabla.setModel(modelo1);
        centro.setHorizontalAlignment(JLabel.CENTER);
        tabla.getColumnModel().getColumn(0).setCellRenderer(centro);
        tabla.getColumnModel().getColumn(1).setCellRenderer(centro);
        tabla.getColumnModel().getColumn(2).setCellRenderer(centro);
        tabla.getColumnModel().getColumn(3).setCellRenderer(centro);
        tabla.getColumnModel().getColumn(4).setCellRenderer(centro);
        tabla.getColumnModel().getColumn(5).setCellRenderer(centro);
        
        tabla.getColumnModel().getColumn(0).setPreferredWidth(25);        
        tabla.getColumnModel().getColumn(1).setPreferredWidth(75);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(275);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(75);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(75);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(75);
        
        ServiciosDAO serviciosdao = new ServiciosDAO();
        List<Servicios> servicios = serviciosdao.select();
        String datos[]= new String[6];
        for (Servicios servicio : servicios) {
               datos[0]=servicio.getId();
               datos[1]=servicio.getNombre();
               datos[2]=servicio.getDescripcion();
               datos[3]=servicio.getPrecio();
               datos[4]=servicio.getTipo();  
               datos[5]=servicio.getEstado();  
               modelo1.addRow(datos);
               tabla.setModel(modelo1);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TIPO = new javax.swing.ButtonGroup();
        ESTADO = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        BtnBus = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_descripcion = new javax.swing.JTextArea();
        btn_cancelar = new javax.swing.JButton();
        BtnMod = new javax.swing.JButton();
        BtnIng = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txt_activo = new javax.swing.JRadioButton();
        txt_inactivo = new javax.swing.JRadioButton();
        aux_estado = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        txt_basico = new javax.swing.JRadioButton();
        txt_adicional = new javax.swing.JRadioButton();
        aux_tipo = new javax.swing.JRadioButton();
        BtnElim = new javax.swing.JButton();
        BtnAyuda = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txt_precio = new javax.swing.JTextField();
        BtnReporte = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("MANTENIMIENTO DE SERVICIOS:");
        setVisible(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameDeactivated(evt);
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("DATOS DEL SERVICIO:"));

        jLabel1.setText("ID:");

        BtnBus.setText("BUSCAR");
        BtnBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBusActionPerformed(evt);
            }
        });

        jLabel2.setText("NOMBRE:");

        jLabel3.setText("DESCRIPCIÓN:");

        txt_descripcion.setColumns(20);
        txt_descripcion.setRows(5);
        jScrollPane1.setViewportView(txt_descripcion);

        btn_cancelar.setText("CANCELAR");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        BtnMod.setText("MODIFICAR");
        BtnMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModActionPerformed(evt);
            }
        });

        BtnIng.setText("GUARDAR");
        BtnIng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnIngActionPerformed(evt);
            }
        });

        jLabel4.setText("ESTADO:");

        ESTADO.add(txt_activo);
        txt_activo.setText("ACTIVO");

        ESTADO.add(txt_inactivo);
        txt_inactivo.setText("INACTIVO");

        ESTADO.add(aux_estado);

        jLabel5.setText("TIPO DE SERVICIO:");

        TIPO.add(txt_basico);
        txt_basico.setText("BÁSICO");

        TIPO.add(txt_adicional);
        txt_adicional.setText("ADICIONAL");

        TIPO.add(aux_tipo);

        BtnElim.setText("ELIMINAR");
        BtnElim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnElimActionPerformed(evt);
            }
        });

        BtnAyuda.setText("AYUDA");
        BtnAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAyudaActionPerformed(evt);
            }
        });

        jLabel6.setText("PRECIO:");

        BtnReporte.setText("REPORTE");
        BtnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnReporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_id)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BtnBus))
                            .addComponent(txt_nombre)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                .addComponent(txt_basico))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_activo)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_inactivo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(aux_estado))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_adicional)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(aux_tipo))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BtnIng, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnElim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BtnMod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnReporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnAyuda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txt_precio)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnBus))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txt_basico)
                        .addComponent(txt_adicional))
                    .addComponent(aux_tipo))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(aux_estado, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txt_activo)
                        .addComponent(txt_inactivo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnIng)
                    .addComponent(btn_cancelar)
                    .addComponent(BtnMod))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnElim)
                    .addComponent(BtnAyuda)
                    .addComponent(BtnReporte))
                .addGap(13, 13, 13))
        );

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tabla);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnIngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnIngActionPerformed
    if(Mantenimiento_Servicios.isNumeric(txt_id.getText())){
        if (txt_nombre.getText().length()!=0&&txt_descripcion.getText().length()!=0&&txt_id.getText().length()!=0&&(
            txt_basico.isSelected()||txt_adicional.isSelected())&&(txt_activo.isSelected()||txt_inactivo.isSelected())
            &&txt_precio.getText().length()!=0) {
            ServiciosDAO serviciosdao = new ServiciosDAO();
            Servicios servicios = new Servicios();
            servicios.setId(txt_id.getText());
            servicios.setNombre(txt_nombre.getText());
            servicios.setDescripcion(txt_descripcion.getText());
            
            if (txt_basico.isSelected()) {
                servicios.setTipo("1");
            }else if(txt_adicional.isSelected()){
                servicios.setTipo("2");
            }
            
            if (txt_activo.isSelected()) {
                servicios.setEstado("1");
            }else if(txt_inactivo.isSelected()){
                servicios.setEstado("0");
            }
            servicios.setPrecio(txt_precio.getText());
            serviciosdao.insert(servicios);
            JOptionPane.showMessageDialog(null, "Servicio agregado correctamente");
            limpiar();actualizartabla();
            GuardarBitacoraDAO guardaraccion = new GuardarBitacoraDAO();
            guardaraccion.GuardarEnBitacora("Insertar",codigoAplicacion, Login.usuarioHoteleria);
        }else{
            JOptionPane.showMessageDialog(null, "Existen campos vacios o sin seleccionar");
        }
    }else{
        JOptionPane.showMessageDialog(null, "El ID del servicio solo lleva números, intentelo nuevamente");
    }
    }//GEN-LAST:event_BtnIngActionPerformed

    private void BtnModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModActionPerformed
    if(Mantenimiento_Servicios.isNumeric(txt_id.getText())){
        if (txt_nombre.getText().length()!=0&&txt_descripcion.getText().length()!=0&&txt_id.getText().length()!=0&&(
            txt_basico.isSelected()||txt_adicional.isSelected())&&(txt_activo.isSelected()||txt_inactivo.isSelected())
            &&txt_precio.getText().length()!=0) {
                ServiciosDAO serviciosdao = new ServiciosDAO();
                Servicios servicios = new Servicios();
                servicios.setId(txt_id.getText());
                servicios.setNombre(txt_nombre.getText());
                servicios.setDescripcion(txt_descripcion.getText());

                if (txt_basico.isSelected()) {
                    servicios.setTipo("1");
                }else if(txt_adicional.isSelected()){
                    servicios.setTipo("2");
                }

                if (txt_activo.isSelected()) {
                    servicios.setEstado("1");
                }else if(txt_inactivo.isSelected()){
                    servicios.setEstado("0");
                }
                servicios.setPrecio(txt_precio.getText());
                serviciosdao.update(servicios);
                JOptionPane.showMessageDialog(null, "Servicio modificado correctamente");
                limpiar();actualizartabla();
                GuardarBitacoraDAO guardaraccion = new GuardarBitacoraDAO();
                guardaraccion.GuardarEnBitacora("Modificacion",codigoAplicacion,  Login.usuarioHoteleria);    
        }else{
            JOptionPane.showMessageDialog(null, "Existen campos vacios o sin seleccionar");
        }
    }else{
        JOptionPane.showMessageDialog(null, "El ID del servicio solo lleva números, intentelo nuevamente");
    }
    }//GEN-LAST:event_BtnModActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        limpiar();
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void BtnBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBusActionPerformed
    if(Mantenimiento_Servicios.isNumeric(txt_id.getText())){
        ServiciosDAO serviciodao = new ServiciosDAO();
        Servicios servicio = new Servicios();
        
        servicio.setId(txt_id.getText());
        servicio = serviciodao.query(servicio);
        txt_nombre.setText(servicio.getNombre());
        txt_descripcion.setText(servicio.getDescripcion());
            if ("0".equals(servicio.getEstado())) {
                txt_inactivo.setSelected(true);
                }else if("1".equals(servicio.getEstado())){
                    txt_activo.setSelected(true);
                }
            if ("1".equals(servicio.getTipo())) {
                txt_basico.setSelected(true);
            }else if("2".equals(servicio.getTipo())){
                txt_adicional.setSelected(true);
            }
            txt_precio.setText(servicio.getPrecio());
        
    }
    }//GEN-LAST:event_BtnBusActionPerformed

    private void BtnElimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnElimActionPerformed
    if (Mantenimiento_Servicios.isNumeric(txt_id.getText())) {
        ServiciosDAO serviciosdao = new ServiciosDAO();
        Servicios eliminarservicio = new Servicios();
        eliminarservicio.setId(txt_id.getText());
        serviciosdao.delete(eliminarservicio);
        
        JOptionPane.showMessageDialog(null, "Forma de pago eliminado exitosamente");
        actualizartabla();
        limpiar();        
        GuardarBitacoraDAO guardaraccion = new GuardarBitacoraDAO();
        guardaraccion.GuardarEnBitacora("Eliminacion",codigoAplicacion,  Login.usuarioHoteleria);
     }else{
         JOptionPane.showMessageDialog(null, "El codigo de metodo son solamente números");
     }
    }//GEN-LAST:event_BtnElimActionPerformed

    private void BtnAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAyudaActionPerformed
    try {
            if ((new File("src\\main\\java\\Hoteleria\\ayuda\\AyudaServicios.chm")).exists()) {
                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler src\\main\\java\\Hoteleria\\ayuda\\AyudaServicios.chm");
                p.waitFor();
            } else {
                JOptionPane.showMessageDialog(null, "La ayuda no Fue encontrada");
            }
            //System.out.println("Correcto");
        } catch (Exception ex) {
            ex.printStackTrace();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAyudaActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
    MDIHoteleria.logo.setVisible(true);
    }//GEN-LAST:event_formInternalFrameClosed

    private void formInternalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameDeactivated
    MDIHoteleria.logo.setVisible(true);
    }//GEN-LAST:event_formInternalFrameDeactivated
    private Connection connection = null;

    private void BtnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnReporteActionPerformed
        Map p = new HashMap();
        JasperReport report;
        JasperPrint print;
        
        try {
            connection = ConexionHoteleria.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/main/java/Hoteleria/reportes/ReporteServicios.jrxml");
            print = JasperFillManager.fillReport(report, p, connection);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Servicios");
            view.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_BtnReporteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAyuda;
    private javax.swing.JButton BtnBus;
    private javax.swing.JButton BtnElim;
    private javax.swing.JButton BtnIng;
    private javax.swing.JButton BtnMod;
    private javax.swing.JButton BtnReporte;
    private javax.swing.ButtonGroup ESTADO;
    private javax.swing.ButtonGroup TIPO;
    private javax.swing.JRadioButton aux_estado;
    private javax.swing.JRadioButton aux_tipo;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabla;
    private javax.swing.JRadioButton txt_activo;
    private javax.swing.JRadioButton txt_adicional;
    private javax.swing.JRadioButton txt_basico;
    private javax.swing.JTextArea txt_descripcion;
    private javax.swing.JTextField txt_id;
    private javax.swing.JRadioButton txt_inactivo;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_precio;
    // End of variables declaration//GEN-END:variables
}
