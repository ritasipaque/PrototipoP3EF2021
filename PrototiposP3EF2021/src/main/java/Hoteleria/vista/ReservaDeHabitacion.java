package Hoteleria.vista;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Hoteleria.datos.ConexionHoteleria;
import Hoteleria.datos.GuardarBitacoraDAO;
import Hoteleria.datos.HabitacionesDAO;
import Hoteleria.datos.HuespedesDAO;
import Hoteleria.datos.ReservacionDAO;
import Hoteleria.dominio.Habitaciones;
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
import java.util.concurrent.TimeUnit;
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
import seguridad.vista.GenerarPermisos;
import seguridad.vista.Login;

/**
 *
 * @author leone
 */
public class ReservaDeHabitacion extends javax.swing.JInternalFrame {

    DefaultTableModel modelo1;
    DefaultTableCellRenderer centro= new DefaultTableCellRenderer();
    boolean activar_boton = false;
    String codigoAplicacion="2201";
    int cantidadpersonas=0;
    GuardarBitacoraDAO guardaraccion = new GuardarBitacoraDAO();
    
void habilitarAcciones() {

        var codigoAplicacion = 2201;
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

    /**
     * Creates new form ProcesoReservaDeHS
     */
    public ReservaDeHabitacion() {
        initComponents();
        habilitarAcciones();
        estado(false);
        cargar_habitaciones();
        actualizar_tabla();
        fecha_actual();
    }

    private static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public void cargar_habitaciones() {
        id_habitacion.addItem("Seleccionar...");
        HabitacionesDAO personaDAO = new HabitacionesDAO();
        List<Habitaciones> habitaciones = personaDAO.select();
        for (Habitaciones habitacion : habitaciones) {
            if (habitacion.getEstado_Habitacion() == 1) {
                id_habitacion.addItem(String.valueOf(habitacion.getId_Habitaciones()));
            }
        }
    }

    public void actualizar_tabla() {
        modelo1 = new DefaultTableModel(){
            @Override
            public boolean isCellEditable (int row, int column)
                {
                    if (column == 1&&column==2&&column==3&&column==4&&column==5&&column==6&&column==7&&column==8)
                       return true;
                    return false;
                }
        };   //ASIGNAMOS UN NUEVO DEFAULTABLEMODEL AL OBJETO MODELO1
        modelo1.addColumn("No. Reservación");      //LE AÑADIMOS COLUMNAS AL OBJETO MODELO
        modelo1.addColumn("DPI Cliente");
        modelo1.addColumn("ID Habitacion");
        modelo1.addColumn("Cantidad Personas:");
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
        tabla.getColumnModel().getColumn(8).setCellRenderer(centro);
        
        tabla.getColumnModel().getColumn(0).setPreferredWidth(75);        
        tabla.getColumnModel().getColumn(1).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);        
        tabla.getColumnModel().getColumn(4).setPreferredWidth(75);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(75);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(75);        
        tabla.getColumnModel().getColumn(7).setPreferredWidth(50);       
        tabla.getColumnModel().getColumn(8).setPreferredWidth(50);
        
        String datos[] = new String[9];
        ReservacionDAO reservaciondao = new ReservacionDAO();
        List<Reservacion> reservacion = reservaciondao.select();
        for (Reservacion reservar : reservacion) {

            datos[0] = reservar.getId_reservacion();
            datos[1] = reservar.getDpi();
            datos[2] = reservar.getId_habitacion();
            datos[3] = reservar.getCantidadpersonas();
            datos[4] = reservar.getF_reserva();
            datos[5] = reservar.getDesde();
            datos[6] = reservar.getHasta();
            datos[7] = reservar.getPrecio();
            datos[8] = reservar.getEstado();
            modelo1.addRow(datos);
            tabla.setModel(modelo1);
        }
    }

    public void limpiar() {
        id_reservacion.setText("");
        id_cliente.setText("");
        txt_nombrec.setText("");
        txt_nit.setText("");
        txt_telefono.setText("");
        txt_precio.setText("");
        fecha_entrada.setDate(null);
        fecha_salida.setDate(null);
        id_habitacion.setSelectedIndex(0);
        txt_total.setText("");
        cpersonas.setText("");
        estado(false);
    }

    public void fecha_actual() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        fecha_actual.setDate(date);
    }

    public int calcular_dias(Date fechaEntrada, Date fechaSalida) {
        long diff = fechaSalida.getTime() - fechaEntrada.getTime();
        TimeUnit time = TimeUnit.DAYS;
        long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
        int total = (int) (diffrence) * Integer.parseInt(txt_precio.getText());
        return total;
    }

    public void estado(boolean estado){
        estado_reservacion.setVisible(estado);
        PE.setVisible(estado);
        ET.setVisible(estado);
        RC.setVisible(estado);
        Cancelar.setVisible(estado);
        limpio.setVisible(false);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        estado = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        id_reservacion = new javax.swing.JTextField();
        fecha_actual = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        id_cliente = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txt_nombrec = new javax.swing.JTextField();
        BtnBus = new javax.swing.JButton();
        BtnBusC = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txt_telefono = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txt_nit = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        id_habitacion = new javax.swing.JComboBox<>();
        BtnCatalogo = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt_precio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fecha_entrada = new com.toedter.calendar.JDateChooser();
        fecha_salida = new com.toedter.calendar.JDateChooser();
        BtnIng = new javax.swing.JButton();
        BtnMod = new javax.swing.JButton();
        BtnReporte = new javax.swing.JButton();
        estado_reservacion = new javax.swing.JLabel();
        PE = new javax.swing.JRadioButton();
        ET = new javax.swing.JRadioButton();
        Cancelar = new javax.swing.JRadioButton();
        limpio = new javax.swing.JRadioButton();
        txt_total = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        BtnTotal = new javax.swing.JButton();
        BtnCancelar = new javax.swing.JButton();
        BtnAyuda = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cpersonas = new javax.swing.JTextField();
        RC = new javax.swing.JRadioButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Reservación de Habitación o Salón");
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

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla.setGridColor(new java.awt.Color(204, 204, 255));
        jScrollPane1.setViewportView(tabla);

        jLabel5.setText("No. de Reservación:");

        fecha_actual.setDateFormatString("yyyy-MM-dd");
        fecha_actual.setEnabled(false);

        jLabel12.setText("No. de DPI:");

        jLabel16.setText("Nombre:");

        txt_nombrec.setEditable(false);

        BtnBus.setText("BUSCAR");
        BtnBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBusActionPerformed(evt);
            }
        });

        BtnBusC.setText("CARGAR");
        BtnBusC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBusCActionPerformed(evt);
            }
        });

        jLabel10.setText("Telefono:");

        txt_telefono.setEditable(false);

        jLabel17.setText("NIT:");

        txt_nit.setEditable(false);

        jLabel18.setText("ID Habitación:");

        id_habitacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                id_habitacionItemStateChanged(evt);
            }
        });

        BtnCatalogo.setText("MOSTRAR CATALOGO");
        BtnCatalogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCatalogoActionPerformed(evt);
            }
        });

        jLabel2.setText("Precio por Noche:");

        txt_precio.setEditable(false);

        jLabel3.setText("Día de Inicio:");

        jLabel4.setText("Día de Fin:");

        fecha_entrada.setDateFormatString("yyyy-MM-dd");

        fecha_salida.setDateFormatString("yyyy-MM-dd");

        BtnIng.setText("GUARDAR");
        BtnIng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnIngActionPerformed(evt);
            }
        });

        BtnMod.setText("MODIFICAR");
        BtnMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModActionPerformed(evt);
            }
        });

        BtnReporte.setText("REPORTE");
        BtnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnReporteActionPerformed(evt);
            }
        });

        estado_reservacion.setText("ESTADO DE LA RESERVACIÓN:");

        estado.add(PE);
        PE.setText("Pendiente de Entrega");

        estado.add(ET);
        ET.setText("Entregada");

        estado.add(Cancelar);
        Cancelar.setText("Cancelar");

        estado.add(limpio);

        txt_total.setEditable(false);

        jLabel1.setText("TOTAL:");

        BtnTotal.setText("CARGAR TOTAL");
        BtnTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTotalActionPerformed(evt);
            }
        });

        BtnCancelar.setText("CANCELAR");
        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });

        BtnAyuda.setText("AYUDA");
        BtnAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAyudaActionPerformed(evt);
            }
        });

        jLabel6.setText("Cantidad de Personas:");

        estado.add(RC);
        RC.setText("Recibida");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(cpersonas))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txt_nombrec, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(id_reservacion)
                                                    .addComponent(id_cliente, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(BtnBusC)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(BtnBus)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(fecha_actual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel17)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txt_nit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(BtnIng)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(BtnMod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(BtnAyuda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(BtnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(BtnReporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txt_precio)
                                            .addComponent(id_habitacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(fecha_entrada, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                            .addComponent(txt_total, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(18, 18, 18)
                                                .addComponent(fecha_salida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(BtnCatalogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(BtnTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                        .addGap(48, 48, 48))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(estado_reservacion)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(ET)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(RC))
                            .addComponent(PE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(Cancelar)
                                .addGap(18, 18, 18)
                                .addComponent(limpio)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(id_reservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnBus))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(id_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(BtnBusC))))
                    .addComponent(fecha_actual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(txt_nombrec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17))
                    .addComponent(txt_nit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(id_habitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnCatalogo))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4))
                    .addComponent(fecha_entrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fecha_salida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnTotal)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cpersonas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(estado_reservacion)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(PE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ET)
                            .addComponent(RC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Cancelar)
                            .addComponent(limpio))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnCancelar)
                    .addComponent(BtnAyuda))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnIng)
                    .addComponent(BtnMod)
                    .addComponent(BtnReporte))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnIngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnIngActionPerformed
        boolean reservada=false;
        int cantidad= Integer.parseInt(cpersonas.getText());
        if (ReservaDeHabitacion.isNumeric(id_reservacion.getText())&&ReservaDeHabitacion.isNumeric(id_cliente.getText())) {
                ReservacionDAO dao = new ReservacionDAO();
                String fechaactual = new SimpleDateFormat("yyyy-MM-dd").format(fecha_actual.getDate());
                String fechaentrada = new SimpleDateFormat("yyyy-MM-dd").format(fecha_entrada.getDate());
                String fechasalida = new SimpleDateFormat("yyyy-MM-dd").format(fecha_salida.getDate());
                
                ReservacionDAO.id=id_habitacion.getSelectedItem().toString();
                ReservacionDAO.entrada=fechaentrada;
                ReservacionDAO.salida=fechasalida;
                
                List<Reservacion> reservacion = dao.select2();
                for (Reservacion reservar : reservacion) {
                    if (reservar.getDesde()==null&&reservar.getHasta()==null) {
                        reservada=false;
                    }else{
                        reservada=true;
                    }
                }
                
                if (reservada==false) {
                    
                    
                    if (txt_total.getText().length()!=0) {
                        Reservacion insertar = new Reservacion();
                        if (cantidad <= cantidadpersonas  && cantidad>0) {
                            insertar.setId_reservacion(id_reservacion.getText());
                            insertar.setDpi(id_cliente.getText());
                            insertar.setId_habitacion(id_habitacion.getSelectedItem().toString());
                            insertar.setCantidadpersonas(String.valueOf(cantidadpersonas));
                            insertar.setF_reserva(fechaactual);
                            insertar.setDesde(fechaentrada);
                            insertar.setHasta(fechasalida);
                            insertar.setPrecio(txt_total.getText());
                            insertar.setEstado("0");
                            JOptionPane.showMessageDialog(null, "Reservación guardada exitosamente");
                            guardaraccion.GuardarEnBitacora("Insertar",codigoAplicacion, Login.usuarioHoteleria);
                            dao.insert(insertar);
                            actualizar_tabla();
                            limpiar();
                        }else{
                            JOptionPane.showMessageDialog(null, "La cantidad de personas supera la debida, la cantidad de "
                                    + "personas que puede tener la habitación es de " + cantidadpersonas);
                        }
                        
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "Cargue el total a pagar");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Habitación no Disponible para esas fechas");
                }
        }
    }//GEN-LAST:event_BtnIngActionPerformed

    private void id_habitacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_id_habitacionItemStateChanged
        if (id_habitacion.getSelectedItem().toString().equals("Seleccionar...")) {
            txt_precio.setText("");
        }else{
            int id=Integer.parseInt(id_habitacion.getSelectedItem().toString());
            HabitacionesDAO habitacionesdao = new HabitacionesDAO();
            Habitaciones habitaciones = new Habitaciones();
            habitaciones.setId_Habitaciones(id);
            habitaciones = habitacionesdao.query(habitaciones);
            txt_precio.setText(String.valueOf(habitaciones.getPrecio()));
            cantidadpersonas = Integer.parseInt(habitaciones.getMax_personas());
        }
    }//GEN-LAST:event_id_habitacionItemStateChanged

    private void BtnBusCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBusCActionPerformed
        if (ReservaDeHabitacion.isNumeric(id_cliente.getText())) {
            HuespedesDAO huespedesdao = new HuespedesDAO();
            Huespedes buscarmetodo = new Huespedes();

            buscarmetodo.setCodigo(id_cliente.getText());
            buscarmetodo = huespedesdao.query(buscarmetodo);
            if (buscarmetodo.getNombre()!=null) {
            txt_nombrec.setText(buscarmetodo.getNombre()+" "+buscarmetodo.getApellido());
            txt_nit.setText(buscarmetodo.getNit());
            txt_telefono.setText(buscarmetodo.getTelefono());
            }else{
                JOptionPane.showMessageDialog(null, "DPI no encontrado");
            }
        }else{
            JOptionPane.showMessageDialog(null, "El DPI son unicamente números revise nuevamente");
        }
    }//GEN-LAST:event_BtnBusCActionPerformed

    private void BtnBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBusActionPerformed
        if (ReservaDeHabitacion.isNumeric(id_reservacion.getText())&&id_reservacion.getText().length()!=0) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaentrada = null, fechasalida=null;
            ReservacionDAO dao = new ReservacionDAO();
            Reservacion buscar = new Reservacion();
            buscar.setId_reservacion(id_reservacion.getText());
            buscar = dao.query(buscar);
            if (buscar.getDpi()!=null) {
            id_cliente.setText(buscar.getDpi());
            id_habitacion.setSelectedItem(buscar.getId_habitacion());
            cpersonas.setText(buscar.getCantidadpersonas());
            try {
                fechaentrada=formato.parse(buscar.getDesde());
                fechasalida=formato.parse(buscar.getHasta());
            } catch (ParseException ex) {
                Logger.getLogger(ReservaDeHabitacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            fecha_entrada.setDate(fechaentrada);
            fecha_salida.setDate(fechasalida);
            txt_total.setText(buscar.getPrecio());
            if (buscar.getEstado().equals("0")) {
                PE.setSelected(true);
            }else if(buscar.getEstado().equals("3")){
                Cancelar.setSelected(true);
            }else if(buscar.getEstado().equals("1")){
                ET.setSelected(true);
            }else if(buscar.getEstado().equals("2")){
                RC.setSelected(true);
            }
            
            estado(true);
            }
        }else{
            JOptionPane.showMessageDialog(null, "El codigo son solo numeros y/o el campo no tiene que estar vacio");
        }
    }//GEN-LAST:event_BtnBusActionPerformed

    private void BtnTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTotalActionPerformed
        if (txt_precio.getText().length()!=0&&fecha_entrada.getDate()!=null&&fecha_salida.getDate()!=null) {
            txt_total.setText(String.valueOf(calcular_dias(fecha_entrada.getDate(), fecha_salida.getDate())));
        }
    }//GEN-LAST:event_BtnTotalActionPerformed

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
    limpiar();
    }//GEN-LAST:event_BtnCancelarActionPerformed

    private void BtnModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModActionPerformed

        int cantidad= Integer.parseInt(cpersonas.getText());
        
        if (ReservaDeHabitacion.isNumeric(id_reservacion.getText())&&ReservaDeHabitacion.isNumeric(id_cliente.getText())) {
            ReservacionDAO dao = new ReservacionDAO();
            Reservacion modificar = new Reservacion();
            
            String fechaactual = new SimpleDateFormat("yyyy-MM-dd").format(fecha_actual.getDate());
            String fechaentrada = new SimpleDateFormat("yyyy-MM-dd").format(fecha_entrada.getDate());
            String fechasalida = new SimpleDateFormat("yyyy-MM-dd").format(fecha_salida.getDate());
            
            if (cantidad <= cantidadpersonas  && cantidad>0) {
            modificar.setId_reservacion(id_reservacion.getText());
            modificar.setDpi(id_cliente.getText());
            modificar.setId_habitacion(id_habitacion.getSelectedItem().toString());
            modificar.setCantidadpersonas(cpersonas.getText());
            modificar.setF_reserva(fechaactual);
            modificar.setDesde(fechaentrada);
            modificar.setHasta(fechasalida);
            modificar.setPrecio(txt_total.getText());
            if (PE.isSelected()) {
               modificar.setEstado("0");
            } else if(ET.isSelected()){
                modificar.setEstado("1");
            } else if(RC.isSelected()){
                modificar.setEstado("2");
            } else if(Cancelar.isSelected()){
                modificar.setEstado("3");
            }
            
            JOptionPane.showMessageDialog(null, "Reservación modificada exitosamente");
            guardaraccion.GuardarEnBitacora("Insertar", codigoAplicacion, Login.usuarioHoteleria);
            dao.update(modificar);   
            }else{
                            JOptionPane.showMessageDialog(null, "La cantidad de personas supera la debida, la cantidad de "
                                    + "personas que puede tener la habitación es de " + cantidadpersonas);
                        }
            actualizar_tabla();
            limpiar();
        }
    }//GEN-LAST:event_BtnModActionPerformed

    private void BtnCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCatalogoActionPerformed
    CatalogoDeHabitaciones catalogo = null;
        try {
            catalogo = new CatalogoDeHabitaciones();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ReservaDeHabitacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    catalogo.show();
    }//GEN-LAST:event_BtnCatalogoActionPerformed

    private void BtnAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAyudaActionPerformed
try {
            if ((new File("src\\main\\java\\Hoteleria\\ayuda\\Ayuda de Reservacion de Habitación.chm")).exists()) {
                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler src\\main\\java\\Hoteleria\\ayuda\\Ayuda de Reservacion de Habitación.chm");
                p.waitFor();
            } else {
                JOptionPane.showMessageDialog(null, "La ayuda no Fue encontrada");
            }
            //System.out.println("Correcto");
        } catch (Exception ex) {
            ex.printStackTrace();
        }         
    }//GEN-LAST:event_BtnAyudaActionPerformed
private Connection connection = null;
    private void BtnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnReporteActionPerformed
        Map p = new HashMap();
        JasperReport report;
        JasperPrint print;
        
        try {
            connection = ConexionHoteleria.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/main/java/Hoteleria/reportes/ReportesReservacionDeHabitacion.jrxml");
            print = JasperFillManager.fillReport(report, p, connection);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Servicios");
            view.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_BtnReporteActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
MDIHoteleria.logo.setVisible(true);             // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameClosed

    private void formInternalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameDeactivated
MDIHoteleria.logo.setVisible(true);             // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameDeactivated

                                      


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAyuda;
    private javax.swing.JButton BtnBus;
    private javax.swing.JButton BtnBusC;
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JButton BtnCatalogo;
    private javax.swing.JButton BtnIng;
    private javax.swing.JButton BtnMod;
    private javax.swing.JButton BtnReporte;
    private javax.swing.JButton BtnTotal;
    private javax.swing.JRadioButton Cancelar;
    private javax.swing.JRadioButton ET;
    private javax.swing.JRadioButton PE;
    private javax.swing.JRadioButton RC;
    private javax.swing.JTextField cpersonas;
    private javax.swing.ButtonGroup estado;
    private javax.swing.JLabel estado_reservacion;
    private com.toedter.calendar.JDateChooser fecha_actual;
    private com.toedter.calendar.JDateChooser fecha_entrada;
    private com.toedter.calendar.JDateChooser fecha_salida;
    private javax.swing.JTextField id_cliente;
    private javax.swing.JComboBox<String> id_habitacion;
    private javax.swing.JTextField id_reservacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton limpio;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txt_nit;
    private javax.swing.JTextField txt_nombrec;
    private javax.swing.JTextField txt_precio;
    private javax.swing.JTextField txt_telefono;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables
}
