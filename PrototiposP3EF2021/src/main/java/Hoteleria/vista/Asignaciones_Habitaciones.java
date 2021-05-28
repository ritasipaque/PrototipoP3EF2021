package Hoteleria.vista;





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Hoteleria.datos.ConexionHoteleria;
import Hoteleria.datos.EntregaHabitacionDAO;
import Hoteleria.datos.HuespedesDAO;
import Hoteleria.datos.ReservacionDAO;
import Hoteleria.dominio.EntregaHabitaciones;
import Hoteleria.dominio.Huespedes;
import Hoteleria.dominio.Reservacion;
import java.io.File;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @author gegmo
 */
public class Asignaciones_Habitaciones extends javax.swing.JInternalFrame {

    DefaultTableModel modelo1;
    DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
    String codigoAplicacion = "2100";
    String idreservacion, noidentificacion, idhabitacion, fechareservada, desde, hasta, precio, cantidadpersonas,estado;
    boolean option = false, option2 = false;

    /**
     * Creates new form Mantenimiento_FomasDePago
     */
    public Asignaciones_Habitaciones() {
        initComponents();
        listado.addItem("Seleccione...");
        fecha_actual();
        actualizartabla();
        actualizar_tabla();
        
    }

    void habilitarAcciones() {

        var codigoAplicacion = 2202;
        var usuario = Login.usuarioHoteleria;

        BtnIng.setEnabled(false);
        BtnMod.setEnabled(false);
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
            BtnMod.setEnabled(true);
        }
    }

    public void fecha_actual() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        fecha_actual.setDate(date);
    }

    private static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private void limpiar() {
        txt_Entrega.setText("");
        txt_reserva.setText("");
        txt_Habitaciones.setText("");
        txt_no_identificacion.setText("");
        txt_Nombre.setText("");
    }

    private void actualizartabla() {
        modelo1 = new DefaultTableModel();
        modelo1.addColumn("ID Entrega");
        modelo1.addColumn("No. de reserva");
        modelo1.addColumn("ID Habitacion");
        modelo1.addColumn("No de Identificacion");
        modelo1.addColumn("Nombres");
        modelo1.addColumn("Fecha");
        modelo1.addColumn("Estado");
        tabla_Entrega.setModel(modelo1);
        centro.setHorizontalAlignment(JLabel.CENTER);
        tabla_Entrega.getColumnModel().getColumn(2).setCellRenderer(centro);
        tabla_Entrega.getColumnModel().getColumn(0).setCellRenderer(centro);
        tabla_Entrega.getColumnModel().getColumn(1).setCellRenderer(centro);
        tabla_Entrega.getColumnModel().getColumn(3).setCellRenderer(centro);
        tabla_Entrega.getColumnModel().getColumn(4).setCellRenderer(centro);
        tabla_Entrega.getColumnModel().getColumn(5).setCellRenderer(centro);
        tabla_Entrega.getColumnModel().getColumn(6).setCellRenderer(centro);
        tabla_Entrega.getColumnModel().getColumn(0).setPreferredWidth(250);
        tabla_Entrega.getColumnModel().getColumn(1).setPreferredWidth(250);
        tabla_Entrega.getColumnModel().getColumn(2).setPreferredWidth(350);
        tabla_Entrega.getColumnModel().getColumn(3).setPreferredWidth(350);
        tabla_Entrega.getColumnModel().getColumn(4).setPreferredWidth(350);
        tabla_Entrega.getColumnModel().getColumn(5).setPreferredWidth(350);
        tabla_Entrega.getColumnModel().getColumn(6).setPreferredWidth(350);
        String datos[] = new String[7];
        EntregaHabitacionDAO entregahabitaciondao = new EntregaHabitacionDAO();
        List<EntregaHabitaciones> Entrega = entregahabitaciondao.select();
        for (EntregaHabitaciones entregar : Entrega) {
            datos[0] = entregar.getPk_id_entrega();
            datos[1] = entregar.getPk_id_reservacion();
            datos[2] = entregar.getPk_id_habitacion();
            datos[3] = entregar.getPk_no_identificacion();
            datos[4] = entregar.getNombre();
            datos[5] = entregar.getFecha();
            datos[6] = entregar.getEstado();
            modelo1.addRow(datos);
            tabla_Entrega.setModel(modelo1);
        }
    }

    public void actualizar_tabla() {
        modelo1 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 1 && column == 2 && column == 3 && column == 4 && column == 5 && column == 6 && column == 7 && column == 8) {
                    return true;
                }
                return false;
            }
        };   //ASIGNAMOS UN NUEVO DEFAULTABLEMODEL AL OBJETO MODELO1
        modelo1.addColumn("No. Reservación");      //LE AÑADIMOS COLUMNAS AL OBJETO MODELO
        modelo1.addColumn("DPI Cliente");
        modelo1.addColumn("ID Habitacion");
        modelo1.addColumn("Fecha de Reserva");
        modelo1.addColumn("Fecha de Entrada");
        modelo1.addColumn("Fecha de Salida");
        modelo1.addColumn("Precio");
        modelo1.addColumn("Estado");
        tabla.setModel(modelo1);

        centro.setHorizontalAlignment(JLabel.CENTER);
        tabla.getColumnModel().getColumn(0).setCellRenderer(centro);
        tabla.getColumnModel().getColumn(1).setCellRenderer(centro);
        tabla.getColumnModel().getColumn(2).setCellRenderer(centro);
        tabla.getColumnModel().getColumn(3).setCellRenderer(centro);
        tabla.getColumnModel().getColumn(4).setCellRenderer(centro);
        tabla.getColumnModel().getColumn(5).setCellRenderer(centro);
        tabla.getColumnModel().getColumn(6).setCellRenderer(centro);
        tabla.getColumnModel().getColumn(7).setCellRenderer(centro);

        tabla.getColumnModel().getColumn(0).setPreferredWidth(75);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(75);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(75);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(75);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(75);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(50);

        String datos[] = new String[8];
        ReservacionDAO reservaciondao = new ReservacionDAO();
        List<Reservacion> reservacion = reservaciondao.select();
        for (Reservacion reservar : reservacion) {

            datos[0] = reservar.getId_reservacion();
            datos[1] = reservar.getDpi();
            datos[2] = reservar.getId_habitacion();
            datos[3] = reservar.getF_reserva();
            datos[4] = reservar.getDesde();
            datos[5] = reservar.getHasta();
            datos[6] = reservar.getPrecio();
            datos[7] = reservar.getEstado();
            modelo1.addRow(datos);
            tabla.setModel(modelo1);
        }
    }

    private void GuardarEnBitacora(String accion, String codigoModulo, String idUsuario) {
        BitacoraDao BitacoraDAO = new BitacoraDao();
        Bitacora AInsertar = new Bitacora();
        boolean estado = false;
        switch (accion) {
            case "Insertar":
                AInsertar.setId_Usuario(idUsuario);
                AInsertar.setAccion("Inserción");
                AInsertar.setCodigoAplicacion(codigoModulo);
                estado = true;
                break;
            case "Modificacion":
                AInsertar.setId_Usuario(idUsuario);
                AInsertar.setAccion("Modificación");
                AInsertar.setCodigoAplicacion(codigoModulo);
                estado = true;
                break;
            case "Eliminacion":
                AInsertar.setId_Usuario(idUsuario);
                AInsertar.setAccion("Eliminar");
                AInsertar.setCodigoAplicacion(codigoModulo);
                estado = true;
        }
        if (estado == true) {
            try {
                BitacoraDAO.insert(AInsertar);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Aplicacion_Perfil.class.getName()).log(Level.SEVERE, null, ex);
            }
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        txt_Entrega = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_Habitaciones = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        BtnIng = new javax.swing.JButton();
        BtnMod = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_ayuda = new javax.swing.JButton();
        txt_Nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_reserva = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        opcion = new javax.swing.JComboBox<>();
        listado = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        BtnBus = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_no_identificacion = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        Entregar = new javax.swing.JRadioButton();
        Recibir = new javax.swing.JRadioButton();
        limpiar = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        fecha_actual = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        cantidad = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_Entrega = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Asignaciones Habitaciones\n");
        setVisible(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Asignaciones Habitaciones:"));
        jPanel1.setToolTipText("");

        txt_Entrega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_EntregaKeyTyped(evt);
            }
        });

        jLabel2.setText("ID Habitacion:");

        txt_Habitaciones.setEditable(false);

        jLabel4.setText("Nombre de la persona");

        BtnIng.setText("ENTREGAR");
        BtnIng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnIngActionPerformed(evt);
            }
        });

        BtnMod.setText("RECIBIR");
        BtnMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModActionPerformed(evt);
            }
        });

        btn_cancelar.setText("CANCELAR");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        btn_ayuda.setText("AYUDA");
        btn_ayuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ayudaActionPerformed(evt);
            }
        });

        txt_Nombre.setEditable(false);

        jLabel3.setText("No. de reserva:");

        txt_reserva.setEditable(false);

        jLabel6.setText("Fecha:");

        jLabel8.setText("Estado:");

        opcion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "seleccione ....", "Entregar", "Recibir" }));
        opcion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                opcionItemStateChanged(evt);
            }
        });

        jLabel9.setText("ID Entrega");

        BtnBus.setText("CARGAR");
        BtnBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBusActionPerformed(evt);
            }
        });

        jLabel1.setText("No. Identificacion:");

        txt_no_identificacion.setEnabled(false);

        jButton2.setText("REPORTE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(Entregar);
        Entregar.setText("Entregar");

        buttonGroup1.add(Recibir);
        Recibir.setText("Recibir");

        buttonGroup1.add(limpiar);
        limpiar.setText("Limpiar");

        jLabel10.setText("Habitaciones");

        jLabel11.setText("No. Habitacion");

        fecha_actual.setDateFormatString("yyyy-MM-dd");
        fecha_actual.setEnabled(false);

        jLabel12.setText("Cantidad de personas");

        cantidad.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(opcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addGap(39, 39, 39)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(listado, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnBus))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(BtnIng)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btn_cancelar)
                                            .addGap(18, 18, 18)
                                            .addComponent(btn_ayuda, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel2))
                                .addGap(40, 40, 40))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel3))
                                        .addGap(35, 35, 35)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_no_identificacion, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                                            .addComponent(txt_Habitaciones)
                                            .addComponent(txt_reserva)
                                            .addComponent(txt_Entrega)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(123, 123, 123)
                                        .addComponent(BtnMod)))
                                .addGap(0, 104, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Entregar)
                        .addGap(18, 18, 18)
                        .addComponent(Recibir)
                        .addGap(18, 18, 18)
                        .addComponent(limpiar)
                        .addGap(122, 122, 122)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fecha_actual, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(opcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(listado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnBus)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_Entrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_reserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_Habitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_no_identificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txt_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fecha_actual, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(Entregar)
                    .addComponent(Recibir)
                    .addComponent(limpiar))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnMod)
                    .addComponent(btn_ayuda)
                    .addComponent(btn_cancelar)
                    .addComponent(BtnIng))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap())
        );

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla);

        tabla_Entrega.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tabla_Entrega);

        jLabel7.setText("Habitaciones entregadas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 419, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel5.setText("Listado de Habitaciones:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2))
            .addGroup(layout.createSequentialGroup()
                .addGap(493, 493, 493)
                .addComponent(jLabel5)
                .addContainerGap(465, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.getAccessibleContext().setAccessibleName("Asignaciones Habitaciones");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnIngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnIngActionPerformed
        EntregaHabitaciones entrega = new EntregaHabitaciones();
        EntregaHabitacionDAO entregaDAO = new EntregaHabitacionDAO();

        String fechaactual = new SimpleDateFormat("yyyy-MM-dd").format(fecha_actual.getDate());

        if (txt_Entrega.getText().length() != 0 && txt_reserva.getText().length() != 0 && Entregar.isSelected()
                && txt_Habitaciones.getText().length() != 0 && txt_no_identificacion.getText().length() != 0
                && txt_Nombre.getText().length() != 0 && cantidad.getText().length() != 0 && fecha_actual.getDate() != null) {

            entrega.setPk_id_entrega(txt_Entrega.getText());
            entrega.setPk_id_reservacion(txt_reserva.getText());
            entrega.setPk_id_habitacion(txt_Habitaciones.getText());
            entrega.setPk_no_identificacion(txt_no_identificacion.getText());
            entrega.setNombre(txt_Nombre.getText());
            entrega.setFecha(fechaactual);
            if (Entregar.isSelected()) {
                entrega.setEstado("1");
            } else if (Recibir.isSelected()) {
                entrega.setEstado("2");
            }

            entregaDAO.insert(entrega);

            
            JOptionPane.showMessageDialog(null, "Habitación Entregada correctamente");

            ReservacionDAO reservaciondao = new ReservacionDAO();
            Reservacion reservacion = new Reservacion();

            reservacion.setId_reservacion(listado.getSelectedItem().toString());
            reservacion.setDpi(noidentificacion);
            reservacion.setId_habitacion(idhabitacion);
            reservacion.setF_reserva(fechareservada);
            reservacion.setDesde(desde);
            reservacion.setHasta(hasta);
            reservacion.setPrecio(precio);
            reservacion.setCantidadpersonas(cantidadpersonas);
            reservacion.setEstado("1");
            reservaciondao.update(reservacion);
            opcion.setSelectedIndex(0);
            listado.setSelectedIndex(0);
        } else {
            JOptionPane.showMessageDialog(null, "Todos los campos tienen que estar llenos :)");
        }
        actualizartabla();
        actualizar_tabla();
            limpiar();
    }//GEN-LAST:event_BtnIngActionPerformed

    private void BtnModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModActionPerformed
        EntregaHabitaciones modificar = new EntregaHabitaciones();
        EntregaHabitacionDAO modificarDAO = new EntregaHabitacionDAO();

        if (txt_Entrega.getText().length() != 0 && txt_reserva.getText().length() != 0 && Recibir.isSelected()
                && txt_Habitaciones.getText().length() != 0 && txt_no_identificacion.getText().length() != 0
                && txt_Nombre.getText().length() != 0 && fecha_actual.getDate() != null) {

            String id_entrega = txt_Entrega.getText();
            String reserva = txt_reserva.getText();
            String habitaciones = txt_Habitaciones.getText();
            String id_persona = txt_no_identificacion.getText();
            String nombre = txt_Nombre.getText();
            String fechaactual = new SimpleDateFormat("yyyy-MM-dd").format(fecha_actual.getDate());

            modificar.setPk_id_entrega(id_entrega);
            modificar.setPk_id_reservacion(reserva);
            modificar.setPk_id_habitacion(habitaciones);
            modificar.setPk_no_identificacion(id_persona);
            modificar.setNombre(nombre);
            modificar.setFecha(fechaactual);
            modificar.setEstado(estado);
            if (Recibir.isSelected()) {
                modificar.setEstado("2");
            }
            modificarDAO.update(modificar);
            actualizartabla();
            

            ReservacionDAO reservaciondao = new ReservacionDAO();
            Reservacion reservacion = new Reservacion();

            reservacion.setId_reservacion(listado.getSelectedItem().toString());
            reservacion.setDpi(noidentificacion);
            reservacion.setId_habitacion(idhabitacion);
            reservacion.setF_reserva(fechareservada);
            reservacion.setDesde(desde);
            reservacion.setHasta(hasta);
            reservacion.setPrecio(precio);
            reservacion.setCantidadpersonas(cantidadpersonas);
            reservacion.setEstado("2");
            reservaciondao.update(reservacion);
            opcion.setSelectedIndex(0);
            listado.setSelectedIndex(0);
        } else {
            JOptionPane.showMessageDialog(null, "Todos los campos tienen que estar llenos :)");
        }
        actualizar_tabla();
        limpiar();
    }//GEN-LAST:event_BtnModActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        limpiar();
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_ayudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ayudaActionPerformed
        try {
            if ((new File("src\\main\\java\\Hoteleria\\ayuda\\AyudaAsignacionHabitacion.chm")).exists()) {
                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler src\\main\\java\\Hoteleria\\ayuda\\AyudaAsignacionHabitacion.chm");
                p.waitFor();
            } else {
                JOptionPane.showMessageDialog(null, "La ayuda no Fue encontrada");
            }
            //System.out.println("Correcto");
        } catch (Exception ex) {
            ex.printStackTrace();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ayudaActionPerformed

    private void opcionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_opcionItemStateChanged

        if (opcion.getSelectedItem().toString() == "Entregar") {
            option = false;
            option2 = true;
            listado.removeAllItems();
            listado.addItem("Seleccione...");

            ReservacionDAO reservaciondao = new ReservacionDAO();
            List<Reservacion> reservaciones = reservaciondao.select();

            for (Reservacion reservacion : reservaciones) {
                if (reservacion.getEstado().equals("0")) {
                    listado.addItem(String.valueOf(reservacion.getId_reservacion()));
                }

            }

        } else if (opcion.getSelectedItem().toString() == "Recibir") {
            option = true;
            option2 = false;
            listado.removeAllItems();
            listado.addItem("Seleccione...");

            EntregaHabitacionDAO DAO = new EntregaHabitacionDAO();
            List<EntregaHabitaciones> entrega = DAO.select();

            for (EntregaHabitaciones habitacion : entrega) {
                if (habitacion.getEstado().equals("1")) {
                    listado.addItem(habitacion.getPk_id_entrega());
                }
            }
        }

    }//GEN-LAST:event_opcionItemStateChanged

    private void BtnBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBusActionPerformed
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        Date fechaentrada = null;

        if (option) {
            if (listado.getSelectedItem().toString() != "Seleccione...") {
                
                
                EntregaHabitacionDAO DAO = new EntregaHabitacionDAO();
                EntregaHabitaciones entrega = new EntregaHabitaciones();

                entrega.setPk_id_entrega(listado.getSelectedItem().toString());
                entrega = DAO.query(entrega);
                String fechaactual = new SimpleDateFormat("yyyy-MM-dd").format(fecha_actual.getDate());

                try {
                    fechaentrada = formato.parse(entrega.getFecha());
                } catch (ParseException ex) {
                    Logger.getLogger(ReservaDeHabitacion.class.getName()).log(Level.SEVERE, null, ex);
                }

                txt_Entrega.setText(entrega.getPk_id_entrega());
                txt_reserva.setText(entrega.getPk_id_reservacion());
                txt_Habitaciones.setText(entrega.getPk_id_habitacion());
                txt_no_identificacion.setText(entrega.getPk_no_identificacion());
                txt_Nombre.setText(entrega.getNombre());
                fecha_actual.setDate(fechaentrada);
                if (entrega.getEstado().equals("1")) {
                    Entregar.setSelected(true);
                } else if (entrega.getEstado().equals("2")) {
                    Recibir.setSelected(true);
                }
                
                ReservacionDAO reservaciondao = new ReservacionDAO();
                Reservacion reservacion = new Reservacion();
                reservacion.setId_reservacion(entrega.getPk_id_reservacion());
                reservacion = reservaciondao.query(reservacion);

                idreservacion = reservacion.getId_reservacion();
                noidentificacion = reservacion.getDpi();
                idhabitacion = reservacion.getId_habitacion();
                fechareservada = reservacion.getF_reserva();
                desde = reservacion.getDesde();
                hasta = reservacion.getHasta();
                precio = reservacion.getPrecio();
                cantidadpersonas = reservacion.getCantidadpersonas();

            }
        }

        if (option2) {
            if (listado.getSelectedItem().toString() != "Seleccione...") {

                ReservacionDAO reservaciondao = new ReservacionDAO();
                Reservacion reservacion = new Reservacion();
                reservacion.setId_reservacion(listado.getSelectedItem().toString());
                reservacion = reservaciondao.query(reservacion);

                idreservacion = reservacion.getId_reservacion();
                noidentificacion = reservacion.getDpi();
                idhabitacion = reservacion.getId_habitacion();
                fechareservada = reservacion.getF_reserva();
                desde = reservacion.getDesde();
                hasta = reservacion.getHasta();
                precio = reservacion.getPrecio();
                cantidadpersonas = reservacion.getCantidadpersonas();

                txt_reserva.setText(reservacion.getId_reservacion());
                txt_Habitaciones.setText(reservacion.getId_habitacion());

                HuespedesDAO huespedesdao = new HuespedesDAO();
                Huespedes buscarmetodo = new Huespedes();
                buscarmetodo.setCodigo(reservacion.getDpi());
                buscarmetodo = huespedesdao.query(buscarmetodo);
                txt_Nombre.setText(buscarmetodo.getNombre() + " " + buscarmetodo.getApellido());
                cantidad.setText(reservacion.getCantidadpersonas());

                txt_no_identificacion.setText(buscarmetodo.getCodigo());
                try {
                    fechaentrada = formato.parse(reservacion.getDesde());
                } catch (ParseException ex) {
                    Logger.getLogger(ReservaDeHabitacion.class.getName()).log(Level.SEVERE, null, ex);
                }
                fecha_actual.setDate(fechaentrada);

            }
        }

    }//GEN-LAST:event_BtnBusActionPerformed

    private void txt_EntregaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_EntregaKeyTyped
        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo números.");
        }
    }//GEN-LAST:event_txt_EntregaKeyTyped
    private Connection connection = null;
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        Map p = new HashMap();
        JasperReport report;
        JasperPrint print;

        try {
            connection = ConexionHoteleria.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/main/java/Hoteleria/reportes/EntregaHabitaciones.jrxml");
            print = JasperFillManager.fillReport(report, p, connection);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Entrega Habitaciones");
            view.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBus;
    private javax.swing.JButton BtnIng;
    private javax.swing.JButton BtnMod;
    private javax.swing.JRadioButton Entregar;
    private javax.swing.JRadioButton Recibir;
    private javax.swing.JButton btn_ayuda;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField cantidad;
    private com.toedter.calendar.JDateChooser fecha_actual;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JRadioButton limpiar;
    private javax.swing.JComboBox<String> listado;
    private javax.swing.JComboBox<String> opcion;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tabla_Entrega;
    private javax.swing.JTextField txt_Entrega;
    private javax.swing.JTextField txt_Habitaciones;
    private javax.swing.JTextField txt_Nombre;
    private javax.swing.JTextField txt_no_identificacion;
    private javax.swing.JTextField txt_reserva;
    // End of variables declaration//GEN-END:variables
}
