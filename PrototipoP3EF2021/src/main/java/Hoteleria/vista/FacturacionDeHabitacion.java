package Hoteleria.vista;

import Hoteleria.datos.ConexionHoteleria;
import Hoteleria.datos.FacturacionDAO;
import Hoteleria.datos.FormasDePagoDAO;
import Hoteleria.datos.ReservacionDAO;
import Hoteleria.datos.ServiciosDAO;
import Hoteleria.dominio.Facturacion;
import Hoteleria.dominio.FormasDePago;
import Hoteleria.dominio.Reservacion;
import Hoteleria.dominio.Servicios;
import java.io.File;
import java.sql.Connection;
//import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import seguridad.vista.GenerarPermisos;
import seguridad.vista.Login;

/**
 *
 * @author Jeff
 */
public class FacturacionDeHabitacion extends javax.swing.JInternalFrame {

    DefaultTableModel modelo1;
    DefaultTableModel modelo2;
    DefaultTableModel modelo3;
    DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
    //FacturacionDAO cargarCbx = new FacturacionDAO();
    int suma = 0, total;

    void habilitarAcciones() {

        var codigoAplicacion = 2203;
        var usuario = Login.usuarioHoteleria;

        btnConfirmarPago.setEnabled(false);
        btnVerificar.setEnabled(false);

        GenerarPermisos permisos = new GenerarPermisos();

        String[] permisosApp = new String[2];

        for (int i = 0; i < 2; i++) {
            permisosApp[i] = permisos.getAccionesAplicacion(codigoAplicacion, usuario)[i];
        }

        if (permisosApp[0].equals("1")) {
            btnVerificar.setEnabled(true);
        }
        if (permisosApp[1].equals("1")) {
            btnConfirmarPago.setEnabled(true);
        }
    }

    public FacturacionDeHabitacion() {
        initComponents();
        tabla();
        //cargarCbx.llenarCbx(cbxPago);
        cargar_habitaciones();
        tabla1();
        tabla2();
    }

    public void cargar_habitaciones() {
        cbxPago.addItem("Seleccionar...");
        FormasDePagoDAO personaDAO = new FormasDePagoDAO();
        List<FormasDePago> habitaciones = personaDAO.select();
        for (FormasDePago habitacion : habitaciones) {
            if (Integer.parseInt(habitacion.getEstado())== 1) {
                cbxPago.addItem(String.valueOf(habitacion.getNombre()));
            }
        }
    }

    public void tabla() {
        modelo1 = new DefaultTableModel();
        modelo1.addColumn("Factura");
        modelo1.addColumn("Reserva");
        modelo1.addColumn("Nombre");
        modelo1.addColumn("Pago");
//        modelo1.addColumn("No. Tarjeta");
        modelo1.addColumn("Reservación");
        modelo1.addColumn("Servicios");
        modelo1.addColumn("Total");
        jTable.setModel(modelo1);

        centro.setHorizontalAlignment(JLabel.CENTER);
        jTable.getColumnModel().getColumn(0).setCellRenderer(centro);
        jTable.getColumnModel().getColumn(1).setCellRenderer(centro);
        jTable.getColumnModel().getColumn(2).setCellRenderer(centro);
        jTable.getColumnModel().getColumn(3).setCellRenderer(centro);
//        jTable.getColumnModel().getColumn(4).setCellRenderer(centro);
        jTable.getColumnModel().getColumn(4).setCellRenderer(centro);
        jTable.getColumnModel().getColumn(5).setCellRenderer(centro);
        jTable.getColumnModel().getColumn(6).setCellRenderer(centro);

        jTable.getColumnModel().getColumn(0).setPreferredWidth(25);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(25);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(65);
//        jTable.getColumnModel().getColumn(4).setPreferredWidth(60);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(60);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(60);
        jTable.getColumnModel().getColumn(6).setPreferredWidth(60);

        String datos[] = new String[7];
        FacturacionDAO ama_De_Llaves_DAO = new FacturacionDAO();
        List<Facturacion> ama_De_Llaves = ama_De_Llaves_DAO.select();
        for (Facturacion ama_De_Llave : ama_De_Llaves) {
            datos[0] = String.valueOf(ama_De_Llave.getId_Factura());
            datos[1] = String.valueOf(ama_De_Llave.getId_Reservacion());
            datos[2] = ama_De_Llave.getNombre_Factura();
            datos[3] = ama_De_Llave.getFormaPago_Factura();
//            datos[4] = String.valueOf(ama_De_Llave.getNoTarjeta_Factura());
            datos[4] = String.valueOf(ama_De_Llave.getTotalReservacion_Factura());
            datos[5] = String.valueOf(ama_De_Llave.getTotalServicios_Factura());
            datos[6] = String.valueOf(ama_De_Llave.getTotalFactura_Factura());
            modelo1.addRow(datos);
            jTable.setModel(modelo1);
        }
    }

    public void tabla1() {
        modelo2 = new DefaultTableModel();
        modelo2.addColumn("Nombre");
        modelo2.addColumn("Precio");
        jTable1.setModel(modelo2);

        centro.setHorizontalAlignment(JLabel.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(centro);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(centro);

        jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(50);

        String datos[] = new String[2];
        ServiciosDAO dao = new ServiciosDAO();
        List<Servicios> personas = dao.select();
        for (Servicios persona : personas) {
            datos[0] = persona.getNombre();
            datos[1] = persona.getPrecio();
            modelo2.addRow(datos);
            jTable1.setModel(modelo2);
        }
    }

    public void tabla2() {
        modelo3 = new DefaultTableModel();
        modelo3.addColumn("Nombre");
        modelo3.addColumn("Precio");
        jTable2.setModel(modelo3);

        centro.setHorizontalAlignment(JLabel.CENTER);
        jTable2.getColumnModel().getColumn(0).setCellRenderer(centro);
        jTable2.getColumnModel().getColumn(1).setCellRenderer(centro);

        jTable2.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable2.getColumnModel().getColumn(1).setPreferredWidth(50);
    }

    public void LimpiarTabla1() {
        modelo2 = new DefaultTableModel();
        modelo2.addColumn("Nombre");
        modelo2.addColumn("Precio");
        jTable1.setModel(modelo2);

        centro.setHorizontalAlignment(JLabel.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(centro);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(centro);

        jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(50);
    }

    public void LimpiarTabla2() {
        modelo3 = new DefaultTableModel();
        modelo3.addColumn("Nombre");
        modelo3.addColumn("Precio");
        jTable2.setModel(modelo3);

        centro.setHorizontalAlignment(JLabel.CENTER);
        jTable2.getColumnModel().getColumn(0).setCellRenderer(centro);
        jTable2.getColumnModel().getColumn(1).setCellRenderer(centro);

        jTable2.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable2.getColumnModel().getColumn(1).setPreferredWidth(50);
    }

    public void limpiarCompra() {
        for (int i = 0; i < jTable2.getRowCount(); i++) {
            String Vector[] = new String[2];
            Vector[0] = jTable2.getValueAt(i, 0).toString();
            Vector[1] = jTable2.getValueAt(i, 1).toString();
            modelo2.addRow(Vector);
            suma = suma - Integer.parseInt(Vector[1]);
        }
        LimpiarTabla2();
    }

    public void limpiar() {
        txtFactura.setText("");
        txtReserva.setText("");
        txtNombre.setText("");
        txtEntrada.setText("");
        txtSalida.setText("");
        cbxPago.setSelectedIndex(0);
//        txtNoTarjeta.setText("");
//        txtCvv.setText("");
//        jdateCaducidad.setDate(null);
        txtTotalReservacion.setText("");
        txtTotalServicios.setText("");
        txtTotalFacturacion.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFactura = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtReserva = new javax.swing.JTextField();
        btnVerificar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtEntrada = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSalida = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbxPago = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnAsignarUno = new javax.swing.JButton();
        btnAsignarTodos = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        btnQuitarUno = new javax.swing.JButton();
        btnQuitarTodos = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtTotalReservacion = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtTotalServicios = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtTotalFacturacion = new javax.swing.JTextField();
        btnConfirmarPago = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Facturación");
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Factura"));

        jLabel1.setText("No. Factura:");

        txtFactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFacturaActionPerformed(evt);
            }
        });
        txtFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFacturaKeyTyped(evt);
            }
        });

        jLabel2.setText("No. Reservación:");

        txtReserva.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtReservaActionPerformed(evt);
            }
        });
        txtReserva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtReservaKeyTyped(evt);
            }
        });

        btnVerificar.setText("Verificar");
        btnVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });

        jLabel3.setText("Nombre:");

        jLabel4.setText("Fecha de Entrada:");

        txtNombre.setEditable(false);
        txtNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtEntrada.setEditable(false);
        txtEntrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEntradaActionPerformed(evt);
            }
        });

        jLabel5.setText("Fecha de Salida:");

        txtSalida.setEditable(false);
        txtSalida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalidaActionPerformed(evt);
            }
        });

        jLabel6.setText("Forma de Pago:");

        cbxPago.setEnabled(false);
        cbxPago.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxPagoItemStateChanged(evt);
            }
        });
        cbxPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxPagoActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("¿Desea algún servicio?");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("asignar");

        btnAsignarUno.setText(">");
        btnAsignarUno.setEnabled(false);
        btnAsignarUno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarUnoActionPerformed(evt);
            }
        });

        btnAsignarTodos.setText(">>");
        btnAsignarTodos.setEnabled(false);
        btnAsignarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarTodosActionPerformed(evt);
            }
        });

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("quitar");

        btnQuitarUno.setText("<");
        btnQuitarUno.setEnabled(false);
        btnQuitarUno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarUnoActionPerformed(evt);
            }
        });

        btnQuitarTodos.setText("<<");
        btnQuitarTodos.setEnabled(false);
        btnQuitarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarTodosActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Servicios Asignados");

        jLabel14.setText("Total Reservación:");

        txtTotalReservacion.setEditable(false);
        txtTotalReservacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel15.setText("Total Servicios:");

        txtTotalServicios.setEditable(false);
        txtTotalServicios.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Total Facturación:");

        txtTotalFacturacion.setEditable(false);
        txtTotalFacturacion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtTotalFacturacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalFacturacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalFacturacionActionPerformed(evt);
            }
        });

        btnConfirmarPago.setText("Confirmar Pago");
        btnConfirmarPago.setEnabled(false);
        btnConfirmarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarPagoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxPago, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(24, 24, 24)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtReserva))
                                    .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(txtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnVerificar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(7, 7, 7)
                                .addComponent(txtTotalFacturacion, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtTotalReservacion))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnAsignarUno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAsignarTodos, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnQuitarUno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnQuitarTodos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnConfirmarPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotalServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerificar))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbxPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAsignarUno)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAsignarTodos)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQuitarUno)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQuitarTodos))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtTotalReservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtTotalServicios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtTotalFacturacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirmarPago))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Registro Facturas"));

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTable);

        jButton1.setText("Reporte");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("?");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFacturaActionPerformed

    private void txtReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReservaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReservaActionPerformed

    private void txtEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEntradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEntradaActionPerformed

    private void txtSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSalidaActionPerformed

    private void txtTotalFacturacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalFacturacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalFacturacionActionPerformed

    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed
        Facturacion ama_De_Llaves_Buscar = new Facturacion();
        FacturacionDAO ama_De_Llaves_DAO = new FacturacionDAO();

        ReservacionDAO huespedesdao = new ReservacionDAO();
        Reservacion buscarReserva = new Reservacion();

        if (txtFactura.getText().length() != 0 && txtReserva.getText().length() != 0) {
            ama_De_Llaves_Buscar.setId_Reservacion(Integer.parseInt(txtReserva.getText()));
            ama_De_Llaves_Buscar = ama_De_Llaves_DAO.query(ama_De_Llaves_Buscar);

            if (String.valueOf(ama_De_Llaves_Buscar.getNombre_Factura()).equals("null")) {
                JOptionPane.showMessageDialog(null, "No. de Reserva no registrado.");
            } else {
                buscarReserva.setId_reservacion(String.valueOf(ama_De_Llaves_Buscar.getId_Reservacion()));
                buscarReserva = huespedesdao.query(buscarReserva);

                txtNombre.setText(String.valueOf(ama_De_Llaves_Buscar.getNombre_Factura()));
                txtEntrada.setText(String.valueOf(buscarReserva.getDesde()));
                txtSalida.setText(String.valueOf(buscarReserva.getHasta()));
                txtTotalReservacion.setText(String.valueOf(buscarReserva.getPrecio()));

                //txtNombre.setEditable(true);
                cbxPago.setEnabled(true);
                //txtNoTarjeta.setEditable(true);
                //txtCvv.setEditable(true);
                //jdateCaducidad.setEnabled(true);
                txtTotalServicios.setText(String.valueOf(0));
                txtTotalFacturacion.setText(txtTotalReservacion.getText());
                btnAsignarUno.setEnabled(true);
                btnAsignarTodos.setEnabled(true);
                btnQuitarUno.setEnabled(true);
                btnQuitarTodos.setEnabled(true);
                btnConfirmarPago.setEnabled(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "El número de factura y de reservación deben estar llenos.");
        }

    }//GEN-LAST:event_btnVerificarActionPerformed

    private void btnAsignarUnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarUnoActionPerformed
        int filaSeleccionada = jTable1.getSelectedRow();
        if (1 == 1) {
            if (filaSeleccionada >= 0) {
                String Vector[] = new String[2];
                Vector[0] = jTable1.getValueAt(filaSeleccionada, 0).toString();
                Vector[1] = jTable1.getValueAt(filaSeleccionada, 1).toString();
                modelo3.addRow(Vector);
                modelo2.removeRow(filaSeleccionada);
                suma = suma + Integer.parseInt(Vector[1]);
            }
        }
        txtTotalServicios.setText(String.valueOf(suma));
        total = suma + Integer.parseInt(txtTotalReservacion.getText());
        txtTotalFacturacion.setText(String.valueOf(total));
    }//GEN-LAST:event_btnAsignarUnoActionPerformed

    private void btnAsignarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarTodosActionPerformed
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            String Vector[] = new String[2];
            Vector[0] = jTable1.getValueAt(i, 0).toString();
            Vector[1] = jTable1.getValueAt(i, 1).toString();
            modelo3.addRow(Vector);
            suma = suma + Integer.parseInt(Vector[1]);
        }
        LimpiarTabla1();
        txtTotalServicios.setText(String.valueOf(suma));
        total = suma + Integer.parseInt(txtTotalReservacion.getText());
        txtTotalFacturacion.setText(String.valueOf(total));
    }//GEN-LAST:event_btnAsignarTodosActionPerformed

    private void btnQuitarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarTodosActionPerformed
        for (int i = 0; i < jTable2.getRowCount(); i++) {
            String Vector[] = new String[2];
            Vector[0] = jTable2.getValueAt(i, 0).toString();
            Vector[1] = jTable2.getValueAt(i, 1).toString();
            modelo2.addRow(Vector);
            suma = suma - Integer.parseInt(Vector[1]);
        }
        LimpiarTabla2();
        txtTotalServicios.setText(String.valueOf(suma));
        total = suma + Integer.parseInt(txtTotalReservacion.getText());
        txtTotalFacturacion.setText(String.valueOf(total));
    }//GEN-LAST:event_btnQuitarTodosActionPerformed

    private void btnQuitarUnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarUnoActionPerformed
        int filaSeleccionada = jTable2.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String Vector[] = new String[2];
            Vector[0] = jTable2.getValueAt(filaSeleccionada, 0).toString();
            Vector[1] = jTable2.getValueAt(filaSeleccionada, 1).toString();
            modelo2.addRow(Vector);
            modelo3.removeRow(filaSeleccionada);
            suma = suma - Integer.parseInt(Vector[1]);
        }
        txtTotalServicios.setText(String.valueOf(suma));
        total = suma + Integer.parseInt(txtTotalReservacion.getText());
        txtTotalFacturacion.setText(String.valueOf(total));
    }//GEN-LAST:event_btnQuitarUnoActionPerformed

    private void btnConfirmarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarPagoActionPerformed
        Facturacion ama_De_Llaves_Insertar = new Facturacion();
        FacturacionDAO ama_De_Llaves_DAO = new FacturacionDAO();

        String cbx_Pago = cbxPago.getSelectedItem().toString();

        if (cbx_Pago.equals("Tarjeta")) {
            if (txtFactura.getText().length() != 0 && txtReserva.getText().length() != 0
                    /*&& txtNoTarjeta.getText().length() != 0 && txtCvv.getText().length() != 0*/
                    && txtTotalReservacion.getText().length() != 0 && txtTotalServicios.getText().length() != 0
                    && txtTotalFacturacion.getText().length() != 0 /*&& jdateCaducidad.getDate() != null
                    && cbx_Pago != "Seleccionar..."*/) {
                {
                    //String fecha = new SimpleDateFormat("yyyy-MM-dd").format(jdateCaducidad.getDate());
                    ama_De_Llaves_Insertar.setId_Factura(Integer.parseInt(txtFactura.getText()));
                    ama_De_Llaves_Insertar.setId_Reservacion(Integer.parseInt(txtReserva.getText()));
                    ama_De_Llaves_Insertar.setNombre_Factura(txtNombre.getText());
                    ama_De_Llaves_Insertar.setFechaEntrada_Factura(txtEntrada.getText());
                    ama_De_Llaves_Insertar.setFechaSalida_Factura(txtSalida.getText());
                    ama_De_Llaves_Insertar.setFormaPago_Factura(cbx_Pago);
//                    ama_De_Llaves_Insertar.setNoTarjeta_Factura(Integer.parseInt(txtNoTarjeta.getText()));
//                    ama_De_Llaves_Insertar.setCvv_Factura(Integer.parseInt(txtCvv.getText()));
//                    ama_De_Llaves_Insertar.setCaducidad_Factura(fecha);
                    ama_De_Llaves_Insertar.setTotalReservacion_Factura(Integer.parseInt(txtTotalReservacion.getText()));
                    ama_De_Llaves_Insertar.setTotalServicios_Factura(Integer.parseInt(txtTotalServicios.getText()));
                    ama_De_Llaves_Insertar.setTotalFactura_Factura(Integer.parseInt(txtTotalFacturacion.getText()));
                    ama_De_Llaves_Insertar.setEstado_Factura(1);

                    int confirmar = JOptionPane.showConfirmDialog(this, "¿Desea seguir adelante con su pago?", "Pago", JOptionPane.YES_NO_OPTION);
                    if (confirmar == 0) {
                        ama_De_Llaves_DAO.insert(ama_De_Llaves_Insertar);
                        limpiar();
                        limpiarCompra();
                        //GuardarBitacoraDAO guardarBitacora = new GuardarBitacoraDAO();
                        //guardarBitacora.GuardarEnBitacora("Facturacion", Integer.toString(codigoAplicacion), Login.usuarioHoteleria);
                        txtNombre.setEditable(false);
                        cbxPago.setEnabled(false);
//                        txtNoTarjeta.setEditable(false);
//                        txtCvv.setEditable(false);
//                        jdateCaducidad.setEnabled(false);
                        btnAsignarUno.setEnabled(false);
                        btnAsignarTodos.setEnabled(false);
                        btnQuitarUno.setEnabled(false);
                        btnQuitarTodos.setEnabled(false);
                        btnConfirmarPago.setEnabled(false);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Todos los campos tienen que estar llenos");
            }

        } else if (cbx_Pago.equals("Efectivo")) {
            if (txtFactura.getText().length() != 0 && txtReserva.getText().length() != 0
                    /*&& txtNoTarjeta.getText().length() != 0 && txtCvv.getText().length() != 0*/
                    && txtTotalReservacion.getText().length() != 0 && txtTotalServicios.getText().length() != 0
                    && txtTotalFacturacion.getText().length() != 0 /*&& jdateCaducidad.getDate() != null*/
                    && cbx_Pago != "Seleccionar...") {
                {
                    ama_De_Llaves_Insertar.setId_Factura(Integer.parseInt(txtFactura.getText()));
                    ama_De_Llaves_Insertar.setId_Reservacion(Integer.parseInt(txtReserva.getText()));
                    ama_De_Llaves_Insertar.setNombre_Factura(txtNombre.getText());
                    ama_De_Llaves_Insertar.setFechaEntrada_Factura(txtEntrada.getText());
                    ama_De_Llaves_Insertar.setFechaSalida_Factura(txtSalida.getText());
                    ama_De_Llaves_Insertar.setFormaPago_Factura(cbx_Pago);
//                    ama_De_Llaves_Insertar.setNoTarjeta_Factura(0);
//                    ama_De_Llaves_Insertar.setCvv_Factura(0);
//                    ama_De_Llaves_Insertar.setCaducidad_Factura(null);
                    ama_De_Llaves_Insertar.setTotalReservacion_Factura(Integer.parseInt(txtTotalReservacion.getText()));
                    ama_De_Llaves_Insertar.setTotalServicios_Factura(Integer.parseInt(txtTotalServicios.getText()));
                    ama_De_Llaves_Insertar.setTotalFactura_Factura(Integer.parseInt(txtTotalFacturacion.getText()));
                    ama_De_Llaves_Insertar.setEstado_Factura(1);

                    int pago = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la totalidad de su pago"));
                    int pago2 = Integer.parseInt(txtTotalFacturacion.getText());

                    if (pago < pago2) {
                        JOptionPane.showConfirmDialog(null, "El monto ingresado no es la totalidad de la factura.");
                    } else if (pago > pago2) {
                        int cambio = pago - pago2;
                        int confirmar = JOptionPane.showConfirmDialog(this, "¿Desea seguir adelante con su pago?", "Pago", JOptionPane.YES_NO_OPTION);
                        if (confirmar == 0) {
                            JOptionPane.showMessageDialog(null, "Su cambio es de " + cambio);
                            ama_De_Llaves_DAO.insert(ama_De_Llaves_Insertar);
                            limpiar();
                            limpiarCompra();
                            txtNombre.setEditable(false);
                            cbxPago.setEnabled(false);
//                            txtNoTarjeta.setEditable(false);
//                            txtCvv.setEditable(false);
//                            jdateCaducidad.setEnabled(false);
                            btnAsignarUno.setEnabled(false);
                            btnAsignarTodos.setEnabled(false);
                            btnQuitarUno.setEnabled(false);
                            btnQuitarTodos.setEnabled(false);
                            btnConfirmarPago.setEnabled(false);
                        }
                    } else if (pago == pago2) {
                        int confirmar = JOptionPane.showConfirmDialog(this, "¿Desea seguir adelante con su pago?", "Pago", JOptionPane.YES_NO_OPTION);
                        if (confirmar == 0) {
                            ama_De_Llaves_DAO.insert(ama_De_Llaves_Insertar);
                            limpiar();
                            limpiarCompra();
                            //GuardarBitacoraDAO guardarBitacora = new GuardarBitacoraDAO();
                            //guardarBitacora.GuardarEnBitacora("Facturacion", Integer.toString(codigoAplicacion), Login.usuarioHoteleria);
                            txtNombre.setEditable(false);
                            cbxPago.setEnabled(false);
//                            txtNoTarjeta.setEditable(false);
//                            txtCvv.setEditable(false);
//                            jdateCaducidad.setEnabled(false);
                            btnAsignarUno.setEnabled(false);
                            btnAsignarTodos.setEnabled(false);
                            btnQuitarUno.setEnabled(false);
                            btnQuitarTodos.setEnabled(false);
                            btnConfirmarPago.setEnabled(false);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Todos los campos tienen que estar llenos");
            }
        } else if (cbx_Pago.equals("Criptomoneda Ethereum") || cbx_Pago.equals("PAYPAL")
                || cbx_Pago.equals("MovilPay")) {
            if (txtFactura.getText().length() != 0 && txtReserva.getText().length() != 0
                    /*&& txtNoTarjeta.getText().length() != 0*/
                    && txtTotalReservacion.getText().length() != 0 && txtTotalServicios.getText().length() != 0
                    && txtTotalFacturacion.getText().length() != 0 && cbx_Pago != "Seleccionar...") {
                {
                    ama_De_Llaves_Insertar.setId_Factura(Integer.parseInt(txtFactura.getText()));
                    ama_De_Llaves_Insertar.setId_Reservacion(Integer.parseInt(txtReserva.getText()));
                    ama_De_Llaves_Insertar.setNombre_Factura(txtNombre.getText());
                    ama_De_Llaves_Insertar.setFechaEntrada_Factura(txtEntrada.getText());
                    ama_De_Llaves_Insertar.setFechaSalida_Factura(txtSalida.getText());
                    ama_De_Llaves_Insertar.setFormaPago_Factura(cbx_Pago);
//                    ama_De_Llaves_Insertar.setNoTarjeta_Factura(Integer.parseInt(txtNoTarjeta.getText()));
//                    ama_De_Llaves_Insertar.setCvv_Factura(0);
//                    ama_De_Llaves_Insertar.setCaducidad_Factura(null);
                    ama_De_Llaves_Insertar.setTotalReservacion_Factura(Integer.parseInt(txtTotalReservacion.getText()));
                    ama_De_Llaves_Insertar.setTotalServicios_Factura(Integer.parseInt(txtTotalServicios.getText()));
                    ama_De_Llaves_Insertar.setTotalFactura_Factura(Integer.parseInt(txtTotalFacturacion.getText()));
                    ama_De_Llaves_Insertar.setEstado_Factura(1);

                    int confirmar = JOptionPane.showConfirmDialog(this, "¿Desea seguir adelante con su pago?", "Pago", JOptionPane.YES_NO_OPTION);
                    if (confirmar == 0) {
                        ama_De_Llaves_DAO.insert(ama_De_Llaves_Insertar);
                        limpiar();
                        limpiarCompra();
                        //GuardarBitacoraDAO guardarBitacora = new GuardarBitacoraDAO();
                        //guardarBitacora.GuardarEnBitacora("Facturacion", Integer.toString(codigoAplicacion), Login.usuarioHoteleria);
                        txtNombre.setEditable(false);
                        cbxPago.setEnabled(false);
//                        txtNoTarjeta.setEditable(false);
//                        txtCvv.setEditable(false);
//                        jdateCaducidad.setEnabled(false);
                        btnAsignarUno.setEnabled(false);
                        btnAsignarTodos.setEnabled(false);
                        btnQuitarUno.setEnabled(false);
                        btnQuitarTodos.setEnabled(false);
                        btnConfirmarPago.setEnabled(false);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Todos los campos tienen que estar llenos");
            }

        }
        tabla();
    }//GEN-LAST:event_btnConfirmarPagoActionPerformed

    private void txtFacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFacturaKeyTyped
        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo números.");
        }
    }//GEN-LAST:event_txtFacturaKeyTyped

    private void txtReservaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReservaKeyTyped
        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo números.");
        }
    }//GEN-LAST:event_txtReservaKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        char validar = evt.getKeyChar();
        if (Character.isDigit(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo letras.");
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void cbxPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxPagoActionPerformed

    }//GEN-LAST:event_cbxPagoActionPerformed

    private void cbxPagoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxPagoItemStateChanged
        String cbx_Pago = cbxPago.getSelectedItem().toString();
        if (cbx_Pago.equals("Tarjeta")) {
//            txtNoTarjeta.setEditable(false);
//            txtCvv.setEditable(true);
//            jdateCaducidad.setEnabled(true);
        } else if (cbx_Pago.equals("Efectivo")) {
//            txtNoTarjeta.setEditable(false);
//            txtCvv.setEditable(false);
//            jdateCaducidad.setEnabled(false);
        } else if (cbx_Pago.equals("Criptomoneda Ethereum")) {
//            txtNoTarjeta.setEditable(true);
//            txtCvv.setEditable(false);
//            jdateCaducidad.setEnabled(false);
        } else if (cbx_Pago.equals("PAYPAL")) {
//            txtNoTarjeta.setEditable(true);
//            txtCvv.setEditable(false);
//            jdateCaducidad.setEnabled(false);
        } else if (cbx_Pago.equals("MovilPay")) {
//            txtNoTarjeta.setEditable(true);
//            txtCvv.setEditable(false);
//            jdateCaducidad.setEnabled(false);
        }
    }//GEN-LAST:event_cbxPagoItemStateChanged

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        MDIHoteleria.logo.setVisible(true);
    }//GEN-LAST:event_formInternalFrameClosed

    private void formInternalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameDeactivated
        MDIHoteleria.logo.setVisible(true);
    }//GEN-LAST:event_formInternalFrameDeactivated

    private Connection connection = null;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Map p = new HashMap();
        JasperReport report;
        JasperPrint print;

        try {
            connection = ConexionHoteleria.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/main/java/Hoteleria/reportes/ReporteFacturacion.jrxml");
            print = JasperFillManager.fillReport(report, p, connection);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Ama de Llaves");
            view.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            if ((new File("src\\main\\java\\Hoteleria\\ayuda\\AyudaMantenimientoAmaDeLlaves.chm")).exists()) {
                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler src\\main\\java\\Hoteleria\\ayuda\\AyudaFacturacion.chm");
                p.waitFor();
            } else {
                JOptionPane.showMessageDialog(null, "La ayuda no Fue encontrada");
            }
            //System.out.println("Correcto");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsignarTodos;
    private javax.swing.JButton btnAsignarUno;
    private javax.swing.JButton btnConfirmarPago;
    private javax.swing.JButton btnQuitarTodos;
    private javax.swing.JButton btnQuitarUno;
    private javax.swing.JButton btnVerificar;
    private javax.swing.JComboBox<String> cbxPago;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTable jTable2;
    private javax.swing.JTextField txtEntrada;
    private javax.swing.JTextField txtFactura;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtReserva;
    private javax.swing.JTextField txtSalida;
    private javax.swing.JTextField txtTotalFacturacion;
    private javax.swing.JTextField txtTotalReservacion;
    private javax.swing.JTextField txtTotalServicios;
    // End of variables declaration//GEN-END:variables
}
