/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comercial.vista;
import Comercial.datos.ProveedorDAO1;
import Comercial.datos.Conexion;
import Comercial.dominio.Proveedor;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import seguridad.datos.BitacoraDao;
import seguridad.dominio.Bitacora;
import seguridad.vista.Aplicacion_Perfil;
import Comercial.datos.Conexion;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import seguridad.vista.GenerarPermisos;
import seguridad.vista.Login;

/**
 *
 * @author SIPAQUE.RITA
 */
public class MantenimientoProveedor extends javax.swing.JInternalFrame {
int codigoAplicacion = 3003;

    /**
     * Creates new form MantenimientoProveedor
     */
     public void llenadoDeTablas() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Proveedor");
        modelo.addColumn("nombre");
        modelo.addColumn("Dirección");
        modelo.addColumn("Contacto");
        modelo.addColumn("Telefono");
        modelo.addColumn("Nit");
        modelo.addColumn("Email");
        modelo.addColumn("Estdo");
        ProveedorDAO1 proveedorDAO = new ProveedorDAO1();
        
        List<Proveedor> proveedor = proveedorDAO.select();
        TablaProveedor.setModel(modelo);
        String[] dato = new String[8];
        for (int i = 0; i < proveedor.size(); i++) {
            dato[0] = Integer.toString(proveedor.get(i).getPK_id_proveedor());
            dato[1] = proveedor.get(i).getNombre_proveedor();
            dato[2] = proveedor.get(i).getDireccion_proveedor();
            dato[3] = proveedor.get(i).getContacto_proveedor();
            dato[4] = proveedor.get(i).getTelefono_proveedor();
            dato[5] = proveedor.get(i).getNit_proveedor();
            dato[6] = proveedor.get(i).getEmail_proveedor();
            dato[7] = proveedor.get(i).getEstatus_proveedor();
            //System.out.println("vendedor:" + vendedores);
            modelo.addRow(dato);
        }
    }
    
     void habilitarAcciones() {

        int codigoAplicacion = 3001;
        var usuario = Login.usuarioComercial;

        btnGuardar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnBuscar.setEnabled(false);

        GenerarPermisos permisos = new GenerarPermisos();

        String[] permisosApp = new String[4];

        for (int i = 0; i < 4; i++) {
            permisosApp[i] = permisos.getAccionesAplicacion(codigoAplicacion, usuario)[i];
        }

        if (permisosApp[0].equals("1")) {
            btnGuardar.setEnabled(true);
        }
        if (permisosApp[1].equals("1")) {
            btnBuscar.setEnabled(true);
        }
        if (permisosApp[2].equals("1")) {
            btnModificar.setEnabled(true);
        }
        if (permisosApp[3].equals("1")) {
            btnEliminar.setEnabled(true);
        }
     }
     
     
     
     
     
          public void buscar() {
       Proveedor proveedorAConsultar = new Proveedor();
        ProveedorDAO1 proveedorDAO = new ProveedorDAO1();
        proveedorAConsultar.setPK_id_proveedor(Integer.parseInt(txt_IdProveedor.getText()));
        proveedorAConsultar = proveedorDAO.query(proveedorAConsultar);
        
        txt_Nombre.setText(proveedorAConsultar.getNombre_proveedor());
        txt_Direccion.setText(proveedorAConsultar.getDireccion_proveedor());
        txt_Contacto.setText(proveedorAConsultar.getContacto_proveedor());
        txt_Telefono.setText(proveedorAConsultar.getTelefono_proveedor());
        txt_Nit.setText(proveedorAConsultar.getNit_proveedor());
        txt_Email.setText(proveedorAConsultar.getEmail_proveedor());
        txt_Estado.setText(proveedorAConsultar.getEstatus_proveedor());
        
                
   
                
    }        
        public void limpiar() {
        txt_IdProveedor.setText("");
        txt_Nombre.setText("");
        txt_Direccion.setText("");
        txt_Contacto.setText("");
        txt_Telefono.setText("");
        txt_Nit.setText("");
        txt_Email.setText("");
        txt_Estado.setText("");
        
        
    }
    
    
    
    public MantenimientoProveedor() {
        initComponents();
        llenadoDeTablas();
        int codigoAplicacion = 3003;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txt_IdProveedor = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txt_Nombre = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_Direccion = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txt_Contacto = new javax.swing.JTextField();
        txt_Telefono = new javax.swing.JTextField();
        txt_Nit = new javax.swing.JTextField();
        txt_Email = new javax.swing.JTextField();
        txt_Estado = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaProveedor = new javax.swing.JTable();
        btnAyuda = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("ManteniminetoProveedor");
        setVisible(true);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Proveedores"));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Id Proveedor:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Nombre Proveedor:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Direccion Proveedor:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Estado Proveedor:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Contacto Proveedor:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Telefono Proveedor:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Nit Proveedor:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Email Proveedor:");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jButton2.setText("Reporte");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(txt_Contacto, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel16))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txt_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_IdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_Direccion))))
                            .addComponent(txt_Nit, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(btnGuardar)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(btnModificar)
                                        .addGap(8, 8, 8)
                                        .addComponent(btnEliminar)
                                        .addGap(26, 26, 26)
                                        .addComponent(jButton2))
                                    .addComponent(txt_Estado, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txt_IdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(7, 7, 7)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txt_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txt_Direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txt_Contacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txt_Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Nit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txt_Estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(jButton2))
                .addGap(40, 40, 40))
        );

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("Mantenimiento Proveedores");

        TablaProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(TablaProveedor);

        btnAyuda.setText("Ayuda");
        btnAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAyudaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(231, 231, 231)
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(btnAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        getAccessibleContext().setAccessibleName("kk");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAyudaActionPerformed
        try {
            if ((new File("src\\main\\java\\Comercial\\reportes\\AyudaMantemientoProveedor.chm")).exists()) {
                Process p = Runtime
                .getRuntime()
                .exec("rundll32 url.dll,FileProtocolHandler src\\main\\java\\Comercial\\reportes\\AyudaMantemientoProveedor.chm");
                p.waitFor();
            } else {
                JOptionPane.showMessageDialog(null, "La ayuda no Fue encontrada");
            }
            //System.out.println("Correcto");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnAyudaActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        ProveedorDAO1 proveedorDAO = new ProveedorDAO1();
        Proveedor proveedorAInsertar = new Proveedor();
        proveedorAInsertar.setPK_id_proveedor((int) Integer.parseInt(txt_IdProveedor.getText()));
        proveedorAInsertar.setNombre_proveedor(txt_Nombre.getText());
        proveedorAInsertar.setDireccion_proveedor(txt_Direccion.getText());
        proveedorAInsertar.setContacto_proveedor(txt_Contacto.getText());
        proveedorAInsertar.setTelefono_proveedor(txt_Telefono.getText());
        proveedorAInsertar.setNit_proveedor(txt_Nit.getText());
        proveedorAInsertar.setEmail_proveedor(txt_Email.getText());
        proveedorAInsertar.setEstatus_proveedor(txt_Estado.getText());

        proveedorDAO.insert(proveedorAInsertar);
BitacoraDao BitacoraDAO = new BitacoraDao();
            
        
       Bitacora Insertar = new Bitacora();
        Insertar.setId_Usuario(Login.usuarioComercial);
        Insertar.setAccion("Insertar");

        Insertar.setCodigoAplicacion("3003");
        Insertar.setModulo("3000");
            try {
                BitacoraDAO.insert(Insertar);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Aplicacion_Perfil.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        llenadoDeTablas();
        limpiar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        ProveedorDAO1 proveedorDAO = new  ProveedorDAO1();
        Proveedor proveedorAActualizar = new Proveedor();
        proveedorAActualizar.setPK_id_proveedor(Integer.parseInt(txt_IdProveedor.getText()));
        proveedorAActualizar.setNombre_proveedor(txt_Nombre.getText());
        proveedorAActualizar.setDireccion_proveedor(txt_Direccion.getText());
        proveedorAActualizar.setContacto_proveedor(txt_Contacto.getText());
        proveedorAActualizar.setTelefono_proveedor(txt_Telefono.getText());
        proveedorAActualizar.setNit_proveedor(txt_Nit.getText());
        proveedorAActualizar.setEmail_proveedor(txt_Email.getText());
        proveedorAActualizar.setEstatus_proveedor(txt_Estado.getText());

        proveedorDAO.update(proveedorAActualizar);
        JOptionPane.showMessageDialog(null, "Modificación Exitosa.");
        
        BitacoraDao BitacoraDAO = new BitacoraDao();
            
        Bitacora Insertar = new Bitacora();
        Insertar.setId_Usuario(Login.usuarioComercial);
        Insertar.setAccion("Modifcar");

        Insertar.setCodigoAplicacion("3003");
        Insertar.setModulo("3000");
            try {
                BitacoraDAO.insert(Insertar);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Aplicacion_Perfil.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        llenadoDeTablas();
        limpiar();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        ProveedorDAO1 proveedorDAO = new ProveedorDAO1();
        Proveedor proveedorAEliminar = new Proveedor();
        proveedorAEliminar.setPK_id_proveedor(Integer.parseInt(txt_IdProveedor.getText()));
        proveedorDAO.delete(proveedorAEliminar);
        JOptionPane.showMessageDialog(null, "Registro Eliminado.");
        BitacoraDao BitacoraDAO = new BitacoraDao();
            
        Bitacora Insertar = new Bitacora();
        Insertar.setId_Usuario(Login.usuarioComercial);
        Insertar.setAccion("Eliminar");

        Insertar.setCodigoAplicacion("3003");
        Insertar.setModulo("3000");
            try {
                BitacoraDAO.insert(Insertar);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Aplicacion_Perfil.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        llenadoDeTablas();
        limpiar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        buscar();
        BitacoraDao BitacoraDAO = new BitacoraDao();
            
        Bitacora Insertar = new Bitacora();
        Insertar.setId_Usuario(Login.usuarioComercial);
        Insertar.setAccion("Buscar");

        Insertar.setCodigoAplicacion("3003");
        Insertar.setModulo("3000");
            try {
                BitacoraDAO.insert(Insertar);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Aplicacion_Perfil.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_btnBuscarActionPerformed
private Connection connection = null;
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Map p = new HashMap();
        JasperReport report;
        JasperPrint print;

        try {
            connection = Conexion.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                + "/src/main/java/Comercial/reportes/Proveedor.jrxml");
            print = JasperFillManager.fillReport(report, p, connection);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte Mantenimiento Proveedor");
            view.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaProveedor;
    public javax.swing.JButton btnAyuda;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTextField txt_Contacto;
    public javax.swing.JTextField txt_Direccion;
    public javax.swing.JTextField txt_Email;
    public javax.swing.JTextField txt_Estado;
    public javax.swing.JTextField txt_IdProveedor;
    public javax.swing.JTextField txt_Nit;
    public javax.swing.JTextField txt_Nombre;
    public javax.swing.JTextField txt_Telefono;
    // End of variables declaration//GEN-END:variables
}
