/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comercial.vista;

import Comercial.datos.ClienteDao;
import Comercial.datos.Conexion;
import Comercial.datos.ProcesoProductoDAO;

import Comercial.datos.facturaDao;
import Comercial.dominio.Cliente;

import Comercial.dominio.Factura;
import Comercial.dominio.ProcesoProducto;
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
import seguridad.vista.Aplicacion_Perfil;

import seguridad.vista.GenerarPermisos;
import seguridad.vista.Login;

/**
 *
 * @author PERSONAL
 */
public class Proceso_Factura extends javax.swing.JInternalFrame {

    ClienteDao Clientes = new ClienteDao();
    Cliente Buscar = new Cliente();
    ClienteDao Clientes1 = new ClienteDao();
    ClienteDao Clientes2 = new ClienteDao();
    Cliente Buscar1 = new Cliente();
    facturaDao factura = new facturaDao();
    Factura buscarf = new Factura();
    ProcesoProducto Existencia = new ProcesoProducto();
    ProcesoProductoDAO pro = new ProcesoProductoDAO();

    void habilitarAcciones() {

        int codigoAplicacion = 3006;
        var usuario = Login.usuarioComercial;

        btnAgregar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnBuscar.setEnabled(false);

        GenerarPermisos permisos = new GenerarPermisos();

        String[] permisosApp = new String[4];

        for (int i = 0; i < 4; i++) {
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
    float tot3;
    float tot2;
    float t3;
    float t2;
    float t1;
    float t7;
    float t8;
    float t9;

    DefaultTableModel modelo = new DefaultTableModel();
    int estadovalidacion;

    int variable1;

    /**
     * Creates new form Proceso_Factura
     */
//    
    public void llenadoDeCombos() {

        List<Cliente> Buscar = Clientes.select();
       
        for (int i = 0; i < Buscar.size(); i++) {
            cbox_empleado.addItem(Buscar.get(i).getCliente());
            String valor = cbox_empleado.getSelectedItem().toString();
          

        }
    }

    public void llenadoDeCombos2() {

        List<ProcesoProducto> Bus =pro.select();
        
        for (int i = 0; i < Bus.size(); i++) {
            cbox_empleado1.addItem(Bus.get(i).getNombre_producto());
            String valor = cbox_empleado1.getSelectedItem().toString();
//              producto.setNombre_producto(producto.getText());
            

        }
    }

    public void validar() {
        String valor = cbox_empleado.getSelectedItem().toString();
        

        Buscar.setCliente(valor);
     
        Buscar = Clientes.query2(Buscar);
       
        estadovalidacion = 1;
       cliente.setText(Buscar.getCliente());
    

        buscarVendedor();

        estadovalidacion = 0;
    }
    
     public void validar2() {
        String valor2 = cbox_empleado1.getSelectedItem().toString();
        

         Existencia.setNombre_producto(valor2);
     
     Existencia     = Clientes2.query3( Existencia);
       
        estadovalidacion = 1;
       producto.setText( Existencia.getNombre_bodega());
    

        buscarVendedorsi();

        estadovalidacion = 0;
    }
 
 

    public Proceso_Factura() {
        initComponents();
        llenadoDeCombos();
        tot.setText("0");
         E.setText("30");
        
        llenadoDeTablas2();
        llenadoDeCombos2();
////         
    }

    public void llenadoDeTablas2() {
        /**
         *
         * creaccion de la tabla de de titulos
         */
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Id_Cliente");
        modelo.addColumn("Cliente");
        modelo.addColumn("Nit");
        modelo.addColumn("Telefono");
        modelo.addColumn("Poducto");
        modelo.addColumn("cantidad");
        modelo.addColumn("Precio");
        modelo.addColumn("Monto");
        modelo.addColumn("Tipo de pago");
        modelo.addColumn("Numero de Factura");

        facturaDao venta = new facturaDao();
        List<Factura> ventas = venta.select();
        JtProductos1.setModel(modelo);
        String[] dato = new String[10];
        for (int i = 0; i < ventas.size(); i++) {
            dato[0] = ventas.get(i).getId_cliente();
            dato[1] = ventas.get(i).getCliente();
            dato[2] = (ventas.get(i).getNit());
            dato[3] = (ventas.get(i).getTelefono());
            dato[4] = (ventas.get(i).getProducto());
            dato[5] = (ventas.get(i).getPrecio_por_unidad());
            dato[6] = (ventas.get(i).getCantidad());
            dato[7] = (ventas.get(i).getMonto());
            dato[8] = (ventas.get(i).getTipo());
            dato[9] = (ventas.get(i).getNumero());

            System.out.println("vendedor:" + ventas);
            modelo.addRow(dato);
        }
    }

    private double redondear(double num) {
        return Math.rint(num * 100) / 100;
    }

    public void buscarVendedor() {

        Buscar1.setCliente(cliente.getText());
        Buscar1 = Clientes.query2(Buscar1);
     
        nit.setText(Buscar1.getNit());
//precio.setText(Buscar.);
        telefono.setText(Buscar1.getTelefono());
         id.setText(Buscar1.getId_cliente());

   
    }
         public void buscarVendedorsi() {

        Existencia.setNombre_producto(producto.getText());
        Existencia = Clientes2.query3(Existencia);
     producto.setText(Existencia.getNombre_producto());
        E.setText(Existencia.getNuevaExistencia());


   
    }
      public void buscarVendedor1() {
     
   
buscarf.setId_cliente(id.getText());
buscarf=factura.query(buscarf);
cliente.setText(buscarf.getCliente());
nit.setText(buscarf.getNit());
precio.setText(buscarf.getPrecio_por_unidad());
telefono.setText(buscarf.getTelefono());
producto.setText(buscarf.getProducto());
tot.setText(buscarf.getTotalmoNto());
monto.setText(buscarf.getMonto());
cantidad.setText(buscarf.getCantidad());
numero.setText(buscarf.getNumero());
tar.setText(buscarf.getTipo());


}
   

    public void limpiar() {
        id.setText("");
        cliente.setText("");
        nit.setText("");
        monto.setText("");
        precio.setText("");
        telefono.setText("");
        producto.setText("");
        monto.setText("");
        cantidad.setText("");
        tar.setText("");
        numero.setText("");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JtProductos1 = new javax.swing.JTable();
        cbox_empleado = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        cbox_empleado1 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        E = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        id = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cliente = new javax.swing.JTextField();
        nit = new javax.swing.JTextField();
        telefono = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        monto = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        tot = new javax.swing.JLabel();
        precio = new javax.swing.JTextField();
        cantidad = new javax.swing.JTextField();
        numero = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        Porducto = new javax.swing.JLabel();
        producto = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        RBEP1 = new javax.swing.JRadioButton();
        tar = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Procesos Factura");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("Ayuda");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnAgregar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnAgregar.setText("Guardar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Pedido de  Factura");

        JtProductos1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(JtProductos1);

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton7.setText("Reporte");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        cbox_empleado1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox_empleado1ActionPerformed(evt);
            }
        });

        jLabel11.setText("Existencias");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle"));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("ID");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Cliente");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Nit");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Telefono");

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton6.setText("Generar Consulta de Cliente");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(telefono)
                            .addComponent(nit)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton6)
                .addGap(0, 67, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(nit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle"));

        monto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Total");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("Monto");

        tot.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel19.setText("Numero de Factura");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("Precio por Unidad");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("Cantidad");

        jButton3.setText("Generar Consulta De Producto");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        Porducto.setText("Producto");

        jButton2.setText("Consulta Existencia");
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(monto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tot, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(77, 77, 77)
                        .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Porducto)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(precio, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                            .addComponent(producto))))
                .addGap(40, 40, 40))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel19)))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Porducto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(monto, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17)
                    .addComponent(tot, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3))
        );

        jLabel2.setText("Seleccione el Cliente");

        jLabel3.setText("Seleccione el  Producto");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle"));

        jRadioButton1.setText("Efectivo");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        RBEP1.setText("Tarjeta");
        RBEP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBEP1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(tar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RBEP1)
                            .addComponent(jRadioButton1))))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jRadioButton1)
                .addGap(18, 18, 18)
                .addComponent(RBEP1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(tar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(cbox_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(cbox_empleado1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(E, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(316, 316, 316)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(31, 31, 31))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(120, 120, 120)
                                .addComponent(btnAgregar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEliminar)
                                .addGap(18, 18, 18)
                                .addComponent(btnModificar)
                                .addGap(18, 18, 18)
                                .addComponent(jButton7)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbox_empleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(cbox_empleado1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(btnBuscar))
                    .addComponent(E, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 7, Short.MAX_VALUE)
                        .addComponent(btnAgregar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminar)
                            .addComponent(btnModificar)
                            .addComponent(jButton7))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.getAccessibleContext().setAccessibleName("Datos  de  Cliente");
        jPanel3.getAccessibleContext().setAccessibleName("Forma de  pago\n");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

//   
        float t3;
        float t2;
        float t1;

        t2 = Float.parseFloat(E.getText());
        t3 = Float.parseFloat(cantidad.getText());

        JtProductos1.getModel();

        t1 = t2 - t3;
        if (t3 < 0) {
            JOptionPane.showMessageDialog(null, " No se encuentra con suficientes insumos para realizar esta venta");
        }
        E.setText(String.valueOf(t1));
        String[] dato = new String[7];

        dato[0] = cliente.getText();
        dato[1] = nit.getText();
        dato[2] = telefono.getText();
        dato[3] = producto.getText();
        dato[4] = precio.getText();
        dato[5] = cantidad.getText();
        Double imp = Double.parseDouble(dato[4]) * Double.parseDouble(dato[5]);
        imp = redondear(imp);
        dato[6] = imp.toString();
        monto.setText(String.valueOf(imp));
        modelo.addRow(dato);
        facturaDao ClienteDAO = new facturaDao();
        Factura AInsertar = new Factura();
        AInsertar.setId_cliente(id.getText());
        AInsertar.setCliente(cliente.getText());
        AInsertar.setNit(nit.getText());
        AInsertar.setTelefono(telefono.getText());
        AInsertar.setProducto(producto.getText());
        AInsertar.setCantidad(cantidad.getText());
        AInsertar.setPrecio_por_unidad(precio.getText());
        AInsertar.setTotalmoNto(tot.getText());
        AInsertar.setMonto(monto.getText());
        AInsertar.setTipo(tar.getText());
        AInsertar.setNumero(numero.getText());

        ClienteDAO.insert(AInsertar);
        llenadoDeTablas2();

        t8 = Float.parseFloat(E.getText());
        t7 = Float.parseFloat(cantidad.getText());

        t9 = t8 - t9;
        E.setText(String.valueOf(t9));
        Proceso_Producto E2 = new Proceso_Producto();
        MDIComercial.jdpescritorio.add(E2);
        E2.toFront();
        E2.setVisible(true);
        Proceso_Producto.txtProductoNuevo.setText(producto.getText());
        Proceso_Producto.txtNuevaExistencia.setText(E.getText());

        tot2 = tot2 + tot3;

        float tot3;

        float tot2;
        String nodo1, nodo2, noda4;
        String r;
        tot2 = Float.parseFloat(tot.getText());
        tot3 = Float.parseFloat(monto.getText());

        JtProductos1.getModel();

        tot2 = tot2 + tot3;
        tot.setText(String.valueOf(tot2));
        BitacoraDao BitacoraDAO = new BitacoraDao();

        Bitacora Insertar = new Bitacora();
        Insertar.setId_Usuario(Login.usuarioComercial);
        Insertar.setAccion("Insertar");

        Insertar.setCodigoAplicacion("3005");
        Insertar.setModulo("3000");

        try {
            BitacoraDAO.insert(Insertar);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Aplicacion_Perfil.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, " La ejecunsion a  sido un exito");

        // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        validar();

        BitacoraDao BitacoraDAO = new BitacoraDao();

        Bitacora Insertar = new Bitacora();
        Insertar.setId_Usuario(Login.usuarioComercial);
        Insertar.setAccion("Buscar");

        Insertar.setCodigoAplicacion("3005");
        Insertar.setModulo("3000");

        try {
            BitacoraDAO.insert(Insertar);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Aplicacion_Perfil.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
buscarVendedor1();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        float tot3;
        float tot2;
        float tot4;
        float nodo1, nodo2, noda4;
        String r;
        tot2 = Float.parseFloat(tot.getText());
        tot3 = Float.parseFloat(monto.getText());

        JtProductos1.getModel();

        tot4 = tot2 + tot3;
        tot.setText(String.valueOf(tot4));
        facturaDao DAO = new facturaDao();
        Factura AEliminar = new Factura();
        AEliminar.setId_cliente(id.getText());
        AEliminar.setCliente(cliente.getText());
        AEliminar.setNit(nit.getText());
        AEliminar.setTelefono(telefono.getText());
        AEliminar.setProducto(producto.getText());
        AEliminar.setCantidad(cantidad.getText());
        AEliminar.setPrecio_por_unidad(precio.getText());
        AEliminar.setTotalmoNto(tot.getText());
        AEliminar.setMonto(monto.getText());
        AEliminar.setTipo(tar.getText());
        AEliminar.setNumero(numero.getText());
        DAO.delete(AEliminar);
        llenadoDeTablas2();
        nodo1 = Float.parseFloat(E.getText());
        nodo2 = Float.parseFloat(cantidad.getText());

        noda4 = nodo1 + nodo2;
        E.setText(String.valueOf(noda4));
        Proceso_Producto E2 = new Proceso_Producto();
        MDIComercial.jdpescritorio.add(E2);
        E2.toFront();
        E2.setVisible(true);
        Proceso_Producto.txtProductoNuevo.setText(producto.getText());
        Proceso_Producto.txtNuevaExistencia.setText(E.getText());

        BitacoraDao BitacoraDAO = new BitacoraDao();

        Bitacora Insertar = new Bitacora();
        Insertar.setId_Usuario(Login.usuarioComercial);
        Insertar.setAccion("Eliminar");

        Insertar.setCodigoAplicacion("3005");
        Insertar.setModulo("3000");

        try {
            BitacoraDAO.insert(Insertar);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Aplicacion_Perfil.class.getName()).log(Level.SEVERE, null, ex);
        }
        limpiar();
        JOptionPane.showMessageDialog(null, " A sido  un exito");
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        facturaDao Da1 = new facturaDao();

        Factura AModifica1 = new Factura();
        AModifica1.setId_cliente(id.getText());

        AModifica1.setCliente(cliente.getText());
        AModifica1.setNit(nit.getText());
        AModifica1.setTelefono(telefono.getText());
        AModifica1.setProducto(producto.getText());
        AModifica1.setPrecio_por_unidad(precio.getText());
        AModifica1.setCantidad(cantidad.getText());
        AModifica1.setMonto(monto.getText());
        AModifica1.setTotalmoNto(tot.getText());
        AModifica1.setTipo(tar.getText());
        AModifica1.setNumero(numero.getText());

        Da1.update(AModifica1);
        llenadoDeTablas2();
        t8 = Float.parseFloat(E.getText());
        t7 = Float.parseFloat(cantidad.getText());

        t9 = t9 + t8 - t9;
        E.setText(String.valueOf(t9));
        Proceso_Producto E2 = new Proceso_Producto();
        MDIComercial.jdpescritorio.add(E2);
        E2.toFront();
        E2.setVisible(true);
        Proceso_Producto.txtProductoNuevo.setText(producto.getText());
        Proceso_Producto.txtNuevaExistencia.setText(E.getText());

        BitacoraDao BitacoraDAO = new BitacoraDao();
        limpiar();
        Bitacora Insertar = new Bitacora();
        Insertar.setId_Usuario(Login.usuarioComercial);
        Insertar.setAccion("Modificar");

        Insertar.setCodigoAplicacion("3006");
        Insertar.setModulo("3000");

        try {
            BitacoraDAO.insert(Insertar);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Aplicacion_Perfil.class.getName()).log(Level.SEVERE, null, ex);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarActionPerformed
    private Connection connection = null;
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Map p = new HashMap();
        JasperReport report;
        JasperPrint print;

        try {
            connection = Conexion.getConnection();
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/main/java/Comercial/reportes/PFactura.jrxml");
            print = JasperFillManager.fillReport(report, p, connection);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("deudor ");
            view.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if ((new File("src\\main\\java\\Comercial\\reportes\\facturapedido.chm")).exists()) {
                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler src\\main\\java\\Comercial\\reportes\\facturapedido.chm");
                p.waitFor();
            } else {
                JOptionPane.showMessageDialog(null, "La ayuda no Fue encontrada");
            }
            //System.out.println("Correcto");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void RBEP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBEP1ActionPerformed
        prueba uno = new prueba();
        MDIComercial.jdpescritorio.add(uno);
        uno.toFront();
        uno.setVisible(true);
        tar.setText("tarjeta");

        // TODO add your handling code here:
    }//GEN-LAST:event_RBEP1ActionPerformed

    private void cbox_empleado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox_empleado1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbox_empleado1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        float t3;
        float t5;
        t3 = Float.parseFloat(cantidad.getText());
        t5 = Float.parseFloat(E.getText());
        if (t5 < t3) {
            JOptionPane.showMessageDialog(null, " No se encuentra con suficientes insumos para realizar esta venta");
        } else {
            JOptionPane.showMessageDialog(null, " tiene permiso de  vender");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        tar.setText("efectivo");
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed
 


   

       
      

   
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
validar2();

   
    

        // TODO add your handling cde here:
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel E;
    private javax.swing.JTable JtProductos1;
    private javax.swing.JLabel Porducto;
    private javax.swing.JRadioButton RBEP1;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JTextField cantidad;
    private javax.swing.JComboBox<String> cbox_empleado;
    private javax.swing.JComboBox<String> cbox_empleado1;
    private javax.swing.JTextField cliente;
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel monto;
    private javax.swing.JTextField nit;
    private javax.swing.JTextField numero;
    private javax.swing.JTextField precio;
    private javax.swing.JTextField producto;
    private javax.swing.JLabel tar;
    private javax.swing.JTextField telefono;
    private javax.swing.JLabel tot;
    // End of variables declaration//GEN-END:variables
}
