/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hoteleria.vista;

import java.io.File;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Hoteleria.datos.AmaDeLlavesDAO;
import Hoteleria.datos.ConexionHoteleria;
import Hoteleria.dominio.AmaDeLlaves;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import seguridad.vista.GenerarPermisos;
import seguridad.vista.Login;
import Hoteleria.datos.GuardarBitacoraDAO;

/**
 *
 * @author Jeff
 */
public class Mantenimiento_AmaDeLlaves extends javax.swing.JInternalFrame {

    AmaDeLlavesDAO cargarCombobox = new AmaDeLlavesDAO();
    ButtonGroup grupoDeRadios;
    DefaultTableModel modelo1;
    DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
    int codigoAplicacion = 2001;

    void habilitarAcciones() {

        codigoAplicacion = 2001;
        var usuario = Login.usuarioHoteleria;

        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnBuscar.setEnabled(false);

        GenerarPermisos permisos = new GenerarPermisos();

        String[] permisosApp = new String[5];

        for (int i = 0; i < 5; i++) {
            permisosApp[i] = permisos.getAccionesAplicacion(codigoAplicacion, usuario)[i];
        }

        if (permisosApp[0].equals("1")) {
            btnAgregar.setEnabled(true);
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

    /**
     * Creates new form MantenimientoAplicacion
     */
    public Mantenimiento_AmaDeLlaves() {
        initComponents();
        habilitarAcciones();
        cargarCombobox.llenarCbx(cbxPiso);
        grupoDeRadios = new ButtonGroup();
        grupoDeRadios.add(btnRadioActivo);
        grupoDeRadios.add(btnRadioInactivo);
        grupoDeRadios.add(btnRadioVacaciones);
        grupoDeRadios.add(btnRadioVacio);
        btnRadioVacio.setVisible(false);
        tabla();
    }

    public void tabla() {
        modelo1 = new DefaultTableModel();
        modelo1.addColumn("ID");
        modelo1.addColumn("Nombre");
        modelo1.addColumn("Apellido");
        modelo1.addColumn("No. Piso");
        modelo1.addColumn("Entrada");
        modelo1.addColumn("Salida");
        modelo1.addColumn("Inicio Laboral");
        modelo1.addColumn("Descripción");
        modelo1.addColumn("Estado");
        jTable.setModel(modelo1);

        centro.setHorizontalAlignment(JLabel.CENTER);
        jTable.getColumnModel().getColumn(0).setCellRenderer(centro);
        jTable.getColumnModel().getColumn(1).setCellRenderer(centro);
        jTable.getColumnModel().getColumn(2).setCellRenderer(centro);
        jTable.getColumnModel().getColumn(3).setCellRenderer(centro);
        jTable.getColumnModel().getColumn(4).setCellRenderer(centro);
        jTable.getColumnModel().getColumn(5).setCellRenderer(centro);
        jTable.getColumnModel().getColumn(6).setCellRenderer(centro);
        jTable.getColumnModel().getColumn(7).setCellRenderer(centro);
        jTable.getColumnModel().getColumn(8).setCellRenderer(centro);

        jTable.getColumnModel().getColumn(0).setPreferredWidth(25);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(80);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(6).setPreferredWidth(80);
        jTable.getColumnModel().getColumn(7).setPreferredWidth(175);
        jTable.getColumnModel().getColumn(8).setPreferredWidth(25);

        String datos[] = new String[9];
        AmaDeLlavesDAO ama_De_Llaves_DAO = new AmaDeLlavesDAO();
        List<AmaDeLlaves> ama_De_Llaves = ama_De_Llaves_DAO.select();
        for (AmaDeLlaves ama_De_Llave : ama_De_Llaves) {
            datos[0] = String.valueOf(ama_De_Llave.getId_Ama_De_Llaves());
            datos[1] = ama_De_Llave.getNombre_Ama_De_Llaves();
            datos[2] = ama_De_Llave.getApellido_Ama_De_Llaves();
            datos[3] = String.valueOf(ama_De_Llave.getPiso_Ama_De_Llaves());
            datos[4] = ama_De_Llave.getEntrada_Ama_De_Llaves();
            datos[5] = ama_De_Llave.getSalida_Ama_De_Llaves();
            datos[6] = ama_De_Llave.getInicio_Ama_De_Llaves();
            datos[7] = ama_De_Llave.getDescripcion_Ama_De_Llaves();
            datos[8] = String.valueOf(ama_De_Llave.getEstado_Ama_De_Llaves());
            modelo1.addRow(datos);
            jTable.setModel(modelo1);
        }
    }

    public void limpiar() {
        txtId.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        cbxPiso.setSelectedIndex(0);
        cbxEntrada.setSelectedIndex(0);
        cbxSalida.setSelectedIndex(0);
        jdateInicio.setDate(null);
        txtaDescripcion.setText("");
        txtBuscar.setText("");
        btnRadioActivo.setSelected(false);
        btnRadioInactivo.setSelected(false);
        btnRadioVacaciones.setSelected(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblModulo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbxPiso = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbxEntrada = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cbxSalida = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jdateInicio = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaDescripcion = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        btnRadioActivo = new javax.swing.JRadioButton();
        btnRadioInactivo = new javax.swing.JRadioButton();
        btnRadioVacaciones = new javax.swing.JRadioButton();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnAyuda = new javax.swing.JButton();
        btnRadioVacio = new javax.swing.JRadioButton();
        btnReporte = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Mantenimiento Ama de Llaves");
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado Ama de Llaves"));

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Buscar:");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregar Ama de Llaves"));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("ID:");

        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Nombre:");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Apellido:");

        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Piso:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Horario");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("Entrada");

        cbxEntrada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar...", "8am", "8pm" }));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("Salida");

        cbxSalida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar...", "8am", "8pm" }));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Inicio Labores:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Descripción:");

        txtaDescripcion.setColumns(20);
        txtaDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtaDescripcion);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Estado:");

        btnRadioActivo.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnRadioActivo.setText("Activo");

        btnRadioInactivo.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnRadioInactivo.setText("Inactivo");

        btnRadioVacaciones.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnRadioVacaciones.setText("Vacaciones");

        btnAgregar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnAgregar.setText("Guardar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnLimpiar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnLimpiar.setText("Nuevo");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnAyuda.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnAyuda.setText("?");
        btnAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAyudaActionPerformed(evt);
            }
        });

        btnReporte.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnReporte.setText("Reporte");
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(168, 168, 168))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnAgregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnModificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLimpiar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnReporte)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAyuda))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnRadioActivo)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnRadioInactivo)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnRadioVacaciones)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnRadioVacio))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jdateInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtId, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtApellido, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(cbxEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, Short.MAX_VALUE)
                                            .addComponent(jLabel10)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbxSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(cbxPiso, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(27, 27, 27))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5))
                    .addComponent(cbxPiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbxEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cbxSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(btnRadioInactivo)
                                .addComponent(btnRadioActivo))
                            .addComponent(btnRadioVacaciones, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnRadioVacio)))
                    .addComponent(jdateInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar)
                    .addComponent(btnAgregar)
                    .addComponent(btnModificar)
                    .addComponent(btnAyuda)
                    .addComponent(btnReporte))
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblModulo))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblModulo)
                .addGap(292, 292, 292))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        AmaDeLlaves ama_De_Llaves_Insertar = new AmaDeLlaves();
        AmaDeLlavesDAO ama_De_Llaves_DAO = new AmaDeLlavesDAO();

        String cbx_Entrada = cbxEntrada.getSelectedItem().toString();
        String cbx_Salida = cbxSalida.getSelectedItem().toString();
        String cbx_Piso = cbxPiso.getSelectedItem().toString();

        if (txtId.getText().length() != 0 && txtNombre.getText().length() != 0
                && txtApellido.getText().length() != 0 && txtaDescripcion.getText().length() != 0
                && cbx_Entrada != "Seleccionar..." && cbx_Salida != "Seleccionar..."
                && cbx_Piso != "Seleccionar..." && jdateInicio.getDate() != null
                && btnRadioActivo.isSelected() || btnRadioInactivo.isSelected()
                || btnRadioVacaciones.isSelected()) {
            {
                if (cbx_Entrada == "8am" && cbx_Salida != "8am"
                        || cbx_Entrada == "8pm" && cbx_Salida != "8pm") {
                    String fecha = new SimpleDateFormat("dd/MM/yyyy").format(jdateInicio.getDate());
                    ama_De_Llaves_Insertar.setId_Ama_De_Llaves(Integer.parseInt(txtId.getText()));
                    ama_De_Llaves_Insertar.setNombre_Ama_De_Llaves(txtNombre.getText());
                    ama_De_Llaves_Insertar.setApellido_Ama_De_Llaves(txtApellido.getText());
                    ama_De_Llaves_Insertar.setPiso_Ama_De_Llaves(Integer.parseInt(cbx_Piso));
                    ama_De_Llaves_Insertar.setEntrada_Ama_De_Llaves(cbx_Entrada);
                    ama_De_Llaves_Insertar.setSalida_Ama_De_Llaves(cbx_Salida);
                    ama_De_Llaves_Insertar.setInicio_Ama_De_Llaves(fecha);
                    ama_De_Llaves_Insertar.setDescripcion_Ama_De_Llaves(txtaDescripcion.getText());
                    if (btnRadioInactivo.isSelected()) {
                        ama_De_Llaves_Insertar.setEstado_Ama_De_Llaves(0);
                    }
                    if (btnRadioActivo.isSelected()) {
                        ama_De_Llaves_Insertar.setEstado_Ama_De_Llaves(1);
                    }
                    if (btnRadioVacaciones.isSelected()) {
                        ama_De_Llaves_Insertar.setEstado_Ama_De_Llaves(2);
                    }
                    btnRadioVacio.setSelected(true);
                    limpiar();
                    ama_De_Llaves_DAO.insert(ama_De_Llaves_Insertar);
                    GuardarBitacoraDAO guardarBitacora = new GuardarBitacoraDAO();
                    guardarBitacora.GuardarEnBitacora("Insertar", Integer.toString(codigoAplicacion), Login.usuarioHoteleria);

                } else {
                    JOptionPane.showMessageDialog(null, "Los horarios de entrada y salida no pueden ser iguales.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Todos los campos tienen que estar llenos");
        }
        tabla();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

        AmaDeLlaves ama_De_Llaves_Modificar = new AmaDeLlaves();
        AmaDeLlavesDAO ama_De_Llaves_DAO = new AmaDeLlavesDAO();

        String cbx_Entrada = cbxEntrada.getSelectedItem().toString();
        String cbx_Salida = cbxSalida.getSelectedItem().toString();
        String cbx_Piso = cbxPiso.getSelectedItem().toString();

        if (cbx_Entrada == "8am" && cbx_Salida != "8am"
                || cbx_Entrada == "8pm" && cbx_Salida != "8pm") {
            String fecha = new SimpleDateFormat("dd/MM/yyyy").format(jdateInicio.getDate());
            ama_De_Llaves_Modificar.setId_Ama_De_Llaves(Integer.parseInt(txtId.getText()));
            ama_De_Llaves_Modificar.setNombre_Ama_De_Llaves(txtNombre.getText());
            ama_De_Llaves_Modificar.setApellido_Ama_De_Llaves(txtApellido.getText());
            ama_De_Llaves_Modificar.setPiso_Ama_De_Llaves(Integer.parseInt(cbx_Piso));
            ama_De_Llaves_Modificar.setEntrada_Ama_De_Llaves(cbx_Entrada);
            ama_De_Llaves_Modificar.setSalida_Ama_De_Llaves(cbx_Salida);
            ama_De_Llaves_Modificar.setInicio_Ama_De_Llaves(fecha);
            ama_De_Llaves_Modificar.setDescripcion_Ama_De_Llaves(txtaDescripcion.getText());
            if (btnRadioInactivo.isSelected()) {
                ama_De_Llaves_Modificar.setEstado_Ama_De_Llaves(0);
            }
            if (btnRadioActivo.isSelected()) {
                ama_De_Llaves_Modificar.setEstado_Ama_De_Llaves(1);
            }
            if (btnRadioVacaciones.isSelected()) {
                ama_De_Llaves_Modificar.setEstado_Ama_De_Llaves(2);
            }
            int confirmar = JOptionPane.showConfirmDialog(this, "¿Desea modificar el registro?", "Modificar", JOptionPane.YES_NO_OPTION);

            if (confirmar == 0) {
                btnRadioVacio.setSelected(true);
                ama_De_Llaves_DAO.update(ama_De_Llaves_Modificar);
                JOptionPane.showMessageDialog(null, "Modificación Exitosa.");
                limpiar();
                jdateInicio.setEnabled(true);
                GuardarBitacoraDAO guardarBitacora = new GuardarBitacoraDAO();
                guardarBitacora.GuardarEnBitacora("Modificacion", Integer.toString(codigoAplicacion), Login.usuarioHoteleria);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Los horarios de entrada y salida no pueden ser iguales.");
        }
        tabla();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        AmaDeLlaves ama_De_Llaves_Eliminar = new AmaDeLlaves();
        AmaDeLlavesDAO ama_De_Llaves_DAO = new AmaDeLlavesDAO();

        if (txtBuscar.getText().length() != 0) {
            ama_De_Llaves_Eliminar.setId_Ama_De_Llaves(Integer.parseInt(txtBuscar.getText()));
            int confirmar = JOptionPane.showConfirmDialog(this, "¿Desea eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION);

            if (confirmar == 0) {
                ama_De_Llaves_DAO.delete(ama_De_Llaves_Eliminar);
                JOptionPane.showMessageDialog(null, "Registro Eliminado.");
                btnRadioVacio.setSelected(true);
                tabla();
                limpiar();
                jdateInicio.setEnabled(true);
                GuardarBitacoraDAO guardarBitacora = new GuardarBitacoraDAO();
                guardarBitacora.GuardarEnBitacora("Eliminacion", Integer.toString(codigoAplicacion), Login.usuarioHoteleria);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se ha ingresado ID a eliminar");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        AmaDeLlaves ama_De_Llaves_Buscar = new AmaDeLlaves();
        AmaDeLlavesDAO ama_De_Llaves_DAO = new AmaDeLlavesDAO();

        if (txtBuscar.getText().length() != 0) {

            ama_De_Llaves_Buscar.setId_Ama_De_Llaves(Integer.parseInt(txtBuscar.getText()));
            ama_De_Llaves_Buscar = ama_De_Llaves_DAO.query(ama_De_Llaves_Buscar);

            if (String.valueOf(ama_De_Llaves_Buscar.getNombre_Ama_De_Llaves()).equals("null")) {
                JOptionPane.showMessageDialog(null, "El ID no se encuentra registrado");
            } else {
                txtId.setText(String.valueOf(ama_De_Llaves_Buscar.getId_Ama_De_Llaves()));
                txtNombre.setText(String.valueOf(ama_De_Llaves_Buscar.getNombre_Ama_De_Llaves()));
                txtApellido.setText(String.valueOf(ama_De_Llaves_Buscar.getApellido_Ama_De_Llaves()));
                cbxPiso.setSelectedItem(ama_De_Llaves_Buscar.getPiso_Ama_De_Llaves());
                cbxEntrada.setSelectedItem(String.valueOf(ama_De_Llaves_Buscar.getEntrada_Ama_De_Llaves()));
                cbxSalida.setSelectedItem(String.valueOf(ama_De_Llaves_Buscar.getSalida_Ama_De_Llaves()));
                String fecha = String.valueOf(ama_De_Llaves_Buscar.getInicio_Ama_De_Llaves());
                SimpleDateFormat fecha1 = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date d1 = fecha1.parse(fecha);
                    jdateInicio.setDate(d1);
                } catch (ParseException e) {
                    e.printStackTrace(System.out);
                }
                txtaDescripcion.setText(String.valueOf(ama_De_Llaves_Buscar.getDescripcion_Ama_De_Llaves()));
                if (ama_De_Llaves_Buscar.getEstado_Ama_De_Llaves() == 0) {
                    btnRadioInactivo.setSelected(true);
                }
                if (ama_De_Llaves_Buscar.getEstado_Ama_De_Llaves() == 1) {
                    btnRadioActivo.setSelected(true);
                }
                if (ama_De_Llaves_Buscar.getEstado_Ama_De_Llaves() == 2) {
                    btnRadioVacaciones.setSelected(true);
                }
                jdateInicio.setEnabled(false);
            }

        } else {
            JOptionPane.showMessageDialog(null, "No se ha ingresado ID a buscar");
        }

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed

        btnRadioVacio.setVisible(false);
        btnRadioVacio.setSelected(true);
        limpiar();
        jdateInicio.setEnabled(true);
        jdateInicio.setDate(null);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped

        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo números.");
        }
    }//GEN-LAST:event_txtIdKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped

        char validar = evt.getKeyChar();
        if (Character.isDigit(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo letras.");
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void btnAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAyudaActionPerformed

        try {
            if ((new File("src\\main\\java\\Hoteleria\\ayuda\\AyudaMantenimientoAmaDeLlaves.chm")).exists()) {
                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler src\\main\\java\\Hoteleria\\ayuda\\AyudaMantenimientoAmaDeLlaves.chm");
                p.waitFor();
            } else {
                JOptionPane.showMessageDialog(null, "La ayuda no Fue encontrada");
            }
            //System.out.println("Correcto");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnAyudaActionPerformed

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped

        char validar = evt.getKeyChar();
        if (Character.isDigit(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo letras.");
        }
    }//GEN-LAST:event_txtApellidoKeyTyped

    private Connection connection = null;

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed

        Map p = new HashMap();
        JasperReport report;
        JasperPrint print;

        try {
            connection = ConexionHoteleria.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/main/java/Hoteleria/reportes/ReporteAmaDeLlaves.jrxml");
            print = JasperFillManager.fillReport(report, p, connection);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Ama de Llaves");
            view.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnReporteActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo números.");
        }
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        MDIHoteleria.logo.setVisible(true);
    }//GEN-LAST:event_formInternalFrameClosed

    private void formInternalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameDeactivated
        MDIHoteleria.logo.setVisible(true);
    }//GEN-LAST:event_formInternalFrameDeactivated


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAyuda;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JRadioButton btnRadioActivo;
    private javax.swing.JRadioButton btnRadioInactivo;
    private javax.swing.JRadioButton btnRadioVacaciones;
    private javax.swing.JRadioButton btnRadioVacio;
    private javax.swing.JButton btnReporte;
    private javax.swing.JComboBox<String> cbxEntrada;
    private javax.swing.JComboBox<String> cbxPiso;
    private javax.swing.JComboBox<String> cbxSalida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable;
    private com.toedter.calendar.JDateChooser jdateInicio;
    private javax.swing.JLabel lblModulo;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextArea txtaDescripcion;
    // End of variables declaration//GEN-END:variables
}
