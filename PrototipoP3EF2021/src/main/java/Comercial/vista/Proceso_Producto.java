/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comercial.vista;

import Comercial.datos.Conexion;
import Comercial.datos.ProcesoProductoDAO;
import Comercial.datos.ProductoDAO;
import Comercial.datos.ProveedorDAO1;
import Comercial.dominio.ProcesoProducto;
import Comercial.dominio.Producto;
import Comercial.dominio.Proveedor;
import java.awt.HeadlessException;
import java.io.File;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import seguridad.vista.MDI_Components;

/**
 *
 * @author Diana
 */
public class Proceso_Producto extends javax.swing.JInternalFrame {

    ProcesoProductoDAO procesoproducto = new ProcesoProductoDAO();
    int estadovalidacion;
    ProcesoProducto Buscar = new ProcesoProducto();

    /**
     * Creates new form Proceso_Producto
     */
//       public void llenadoDeCombos2() {
//
//        List<ProcesoProducto> Bus =procesoproducto.select();
//        
//        for (int i = 0; i < Bus.size(); i++) {
//            cbox_empleado1.addItem(Bus.get(i).getNombre_producto());
//            String valor = cbox_empleado1.getSelectedItem().toString();
//              producto.setNombre_producto(producto.getText());
            
//
//        }
//    }
//
//    public void llenadoDeTablas() {
//        DefaultTableModel modelo = new DefaultTableModel();
//        modelo.addColumn("ID Proveedor");
//        modelo.addColumn("Producto");
//        modelo.addColumn("Bodega");
//        modelo.addColumn("Existencias");
//        modelo.addColumn("Fecha de Entrega");
//        modelo.addColumn("Bodega");
//        modelo.addColumn("Nueva Existencia");
//        modelo.addColumn("Nueva Bodega");
//
//        ProcesoProductoDAO procesoproductoDAO = new ProcesoProductoDAO();
//        List<ProcesoProducto> procesoproducto = procesoproductoDAO.select();
//        TablaProducto.setModel(modelo);
//        String[] dato = new String[7];
//        for (int i = 0; i < procesoproducto.size(); i++) {
//            dato[0] = Integer.toString(procesoproducto.get(i).getPK_id_procesoproducto());
//            dato[1] = procesoproducto.get(i).getNombre_producto();
//            dato[2] = procesoproducto.get(i).getNombre_bodega();
//            dato[3] = procesoproducto.get(i).getExistencias_producto();
//            dato[4] = procesoproducto.get(i).getBodegasNuevaExistencia();
//            dato[5] = procesoproducto.get(i).getNuevaExistencia();
//            dato[6] = procesoproducto.get(i).getBodegasNuevaExistencia();
//
//            //System.out.println("vendedor:" + vendedores);
//            modelo.addRow(dato);
//        }
//    }
//
//    public void limpiar() {
//        //   txt_combox.setText("");
//        txtnombrebodega.setText("");
//        txtExistenciasProducto.setText("");
////        txt_fechaActualizacion.setText("");
//        txtProductoNuevo.setText("");
//        txtNuevaExistencia.setText("");
//
//    }
//
//    public void buscar() {
//        //  Buscar.setNombre_producto(txt_combox.getText());
//        Buscar = procesoproducto.query(Buscar);
//        txtnombrebodega.setText(Buscar.getNombre_bodega());
//        txtExistenciasProducto.setText(Buscar.getExistencias_producto());
//        txtProductoNuevo.setText(Buscar.getProductoNuevo());
//        // cbox_BodegasNuevaExistencia.setText(Buscar.getBodegasNuevaExistencia());
//
//    }
//
//    public Proceso_Producto() {
//        initComponents();
//        Date sistFecha = new Date();
//        SimpleDateFormat formato = new SimpleDateFormat("dd-MMM-YYYY");
//        // txtfechaActualizacion.setCalendar(formato(sistFecha));
//    }

//    public void llenadoDeCombos2() {
//        // List<Cliente> Bus = Cliente.select();
////        txt_combox.addItem("Productos Disponibles");
////        for (int i = 0; i < Bus.size(); i++) {
////            txt_combox.addItem(Bus.get(i).getCliente());
////            String valor = txt_combox.getSelectedItem().toString();
////    }
//    }
//              Buscar1.setCliente(cliente.getText());
//Buscar1=Clientes.query2(Buscar1);
//
//
////precio.setText(Buscar.);
//E.setText(Buscar1.getCliente());

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        btnAyuda = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtProductoNuevo = new javax.swing.JTextField();
        txtNuevaExistencia = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtExistenciasTotales = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        txtBodegasNuevaExistencia = new javax.swing.JTextField();
        txtExistenciasProducto1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Proceso Productos");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("PROCESO STOCK MINIMO");

        btnAyuda.setText("Ayuda");
        btnAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAyudaActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Nuevos Articulos"));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Producto");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Nueva existencia");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Bodega");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Existencias totales");

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Existencias Actuales");

        jButton1.setText("Reporte");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel7)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnActualizar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtProductoNuevo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(txtNuevaExistencia, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtExistenciasTotales)
                            .addComponent(txtBodegasNuevaExistencia))
                        .addGap(31, 31, 31)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtExistenciasProducto1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtProductoNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(txtExistenciasProducto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtNuevaExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtExistenciasTotales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtBodegasNuevaExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar)
                    .addComponent(jButton1))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(117, 117, 117)
                .addComponent(btnAyuda)
                .addGap(19, 19, 19))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAyuda)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private Connection connection = null;

    private void btnAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAyudaActionPerformed
        // TODO add your handling code here:
        try {
            if ((new File("src\\main\\java\\Comercial\\reportes\\AyudaProcesoProducto.chm")).exists()) {
                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler src\\main\\java\\Comercial\\reportes\\AyudaProcesoProducto.chm");
                p.waitFor();

            } else {
                JOptionPane.showMessageDialog(null, "La ayuda no Fue encontrada");
            }
            //System.out.println("Correcto");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnAyudaActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
//        int numero1, numero2, total;
//        numero1 = Integer.parseInt(txtExistenciasProducto.getText());
//        numero2 = Integer.parseInt(txtNuevaExistencia.getText());
//
//        total = numero1 + numero2;
//        txtExistenciasTotales.setText(String.valueOf(total));
    }//GEN-LAST:event_btnActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAyuda;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtBodegasNuevaExistencia;
    public static javax.swing.JTextField txtExistenciasProducto1;
    private javax.swing.JTextField txtExistenciasTotales;
    public static javax.swing.JTextField txtNuevaExistencia;
    public static javax.swing.JTextField txtProductoNuevo;
    // End of variables declaration//GEN-END:variables
}
