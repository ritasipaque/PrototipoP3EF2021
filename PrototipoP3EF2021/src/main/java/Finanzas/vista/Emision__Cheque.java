
package Finanzas.vista;

import Finanzas.datos.BancoDAO;
import Finanzas.datos.Conexion;
import Finanzas.datos.CuentaBancariaDAO;
import Finanzas.datos.CuentaHabienteDAO;
import Finanzas.datos.EmisionChequeDAO;
import Finanzas.dominio.EmisionCheque;
import Finanzas.dominio.Banco;
import Finanzas.dominio.CuentaBancaria;
import Finanzas.dominio.CuentaHabiente;
import java.io.File;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
public class Emision__Cheque extends javax.swing.JInternalFrame {
int codigoAplicacion = 1105;

    public void llenadoDeCombos1() throws SQLException  {
       BancoDAO bancoDAO = new BancoDAO();
      List<Banco> banco = bancoDAO.listar();
      ComboBanco.addItem("Seleccione una opción");
      for (int i = 0; i < banco.size(); i++) {
           ComboBanco.addItem(banco.get(i).getCodigo_Banco());
       }
 }
     
      public void llenadoDeCombos2() throws SQLException  {
       CuentaBancariaDAO cuentabancariaDAO = new CuentaBancariaDAO();
      List<CuentaBancaria> cuentabancaria = cuentabancariaDAO.listar();
      ComboCuenta.addItem("Seleccione una opción");
      for (int i = 0; i < cuentabancaria.size(); i++) {
           ComboCuenta.addItem(cuentabancaria.get(i).getNumero_CuentaBancaria());
       }
      }
      
      public void llenadoDeCombos3() throws SQLException  {
       CuentaHabienteDAO cuentadao = new CuentaHabienteDAO();
      List<CuentaHabiente> cuenta = cuentadao.listar();
      ComboPaguese.addItem("Seleccione una opción");
      for (int i = 0; i < cuenta.size(); i++) {
           ComboPaguese.addItem(cuenta.get(i).getCodigo_CuentaHabiente());
       }
      }
      public void llenadodetablas() { // metodo de llenado de tablas automaticamente aparecen los datos guardados en bd y se despliega en automatico en el jtable
    
          DefaultTableModel modelo = new DefaultTableModel();
          modelo.addColumn("Numero Cheque");
          modelo.addColumn("Fecha");
          modelo.addColumn("Banco");
          modelo.addColumn("Cuenta");
          modelo.addColumn("Paguese a"); 
          modelo.addColumn("Monto");
          
          EmisionChequeDAO emisionchequeDAO = new EmisionChequeDAO();
          List<EmisionCheque> echeque = emisionchequeDAO.listar();
          TablaECheque.setModel(modelo);
          String[] dato = new String[6];
          for (int i = 0; i < echeque.size(); i++) {
              dato[0] = echeque.get(i).getNumero_Cheque();
              dato[1] = echeque.get(i).getFecha_Cheque();
              dato[2] = echeque.get(i).getFK_Banco();
              dato[3] = echeque.get(i).getFK_Cuenta();
              dato[4] = echeque.get(i).getFK_Cuentahabiente();
              dato[5] = echeque.get(i).getMonto_Cheque();
              modelo.addRow(dato);
          }
    }
    public void buscarCheque(){    ///metodo para buscar un cheque ingresado y guardado a la base de datos
    EmisionCheque chequeconsultar = new EmisionCheque();
    EmisionChequeDAO emisionchequeDAO = new EmisionChequeDAO();
    chequeconsultar.setNumero_Cheque((txt_Buscar.getText()));
    //-------------------------------------------------------------------
    chequeconsultar = emisionchequeDAO.query(chequeconsultar);
    txtNoCheque.setText((chequeconsultar.getNumero_Cheque()));
    Fecha.setDateFormatString(chequeconsultar.getFecha_Cheque());
    ComboBanco.setSelectedItem(chequeconsultar.getFK_Banco());
    ComboCuenta.setSelectedItem(chequeconsultar.getFK_Cuenta());
    ComboPaguese.setSelectedItem(chequeconsultar.getFK_Cuentahabiente());
    txtMonto.setText(chequeconsultar.getMonto_Cheque());
  }
    /**
     * Creates new form Emision__Cheque
     */
    public void limpiar() {   //metodo de Limpiar automaticamente las cajas de textos 
        txtNoCheque.setText("");
        txtMonto.setText("");
        txt_Buscar.setText("");
        Fecha.setDateFormatString("");
        ComboBanco.setSelectedItem(null);
        ComboCuenta.setSelectedItem(null);
        ComboPaguese.setSelectedItem(null);
    }
    public Emision__Cheque() throws SQLException {
        initComponents();
        llenadodetablas();
        llenadoDeCombos1();
        llenadoDeCombos2();
        llenadoDeCombos3();
        int codigoAplicacion = 1105; // numero de asignacion para perfiles para bitacora.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    BitacoraDao BitacoraDAO = new BitacoraDao();
    Bitacora AInsertar = new Bitacora();
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Boton_Buscar = new javax.swing.JButton();
        txt_Buscar = new javax.swing.JTextField();
        BtnIng = new javax.swing.JButton();
        BtnMod = new javax.swing.JButton();
        BtnElim = new javax.swing.JButton();
        BtnAyu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNoCheque = new javax.swing.JTextField();
        ComboBanco = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        ComboCuenta = new javax.swing.JComboBox<>();
        ComboPaguese = new javax.swing.JComboBox<>();
        Fecha = new com.toedter.calendar.JDateChooser();
        btnImprimir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaECheque = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Emision__Cheque");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));

        Boton_Buscar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        Boton_Buscar.setText("Buscar");
        Boton_Buscar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Boton_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_BuscarActionPerformed(evt);
            }
        });

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

        BtnAyu.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        BtnAyu.setText("Ayuda");
        BtnAyu.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnAyu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAyuActionPerformed(evt);
            }
        });

        jLabel1.setText("Numero Cheque:");

        jLabel2.setText("Fecha:");

        jLabel3.setText("Banco:");

        jLabel4.setText("Paguese a :");

        ComboBanco.setMaximumRowCount(20);

        jLabel5.setText("Monto:");

        jLabel6.setText("Cuenta:");

        ComboCuenta.setMaximumRowCount(20);

        ComboPaguese.setMaximumRowCount(20);

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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Boton_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BtnIng, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BtnMod, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(BtnElim, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BtnAyu, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(58, 58, 58)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ComboBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNoCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ComboCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(ComboPaguese, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtNoCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ComboCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(ComboPaguese, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ComboBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_Buscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Boton_Buscar)
                        .addComponent(BtnIng)
                        .addComponent(BtnMod)
                        .addComponent(BtnElim)
                        .addComponent(BtnAyu)
                        .addComponent(btnImprimir))))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle:"));

        TablaECheque.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero Cheque", "Fecha ", "Banco", "Cuenta", "Paguese a", "Monto"
            }
        ));
        jScrollPane1.setViewportView(TablaECheque);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Boton_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_BuscarActionPerformed
        buscarCheque();
        txt_Buscar.setText("");
    }//GEN-LAST:event_Boton_BuscarActionPerformed

    private void BtnIngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnIngActionPerformed
        EmisionChequeDAO emisionchequeDAO = new EmisionChequeDAO();
        EmisionCheque insertar = new EmisionCheque();

        String Inicio = new SimpleDateFormat("dd/MM/yyyy").format(Fecha.getDate());

        insertar.setNumero_Cheque(txtNoCheque.getText());
        insertar.setFecha_Cheque(Inicio);
        insertar.setFK_Banco(ComboBanco.getSelectedItem().toString());
        insertar.setFK_Cuenta(ComboCuenta.getSelectedItem().toString());
        insertar.setFK_Cuentahabiente(ComboPaguese.getSelectedItem().toString());
        insertar.setMonto_Cheque(txtMonto.getText());

        JOptionPane.showMessageDialog(null, "Cheque registrado Exitosamente");
        //bitacora
        AInsertar.setId_Usuario(Login.usuarioFianzas);
        AInsertar.setAccion("Insertar");
        AInsertar.setCodigoAplicacion("1105");
        AInsertar.setModulo("1000");
        try {
            BitacoraDAO.insert(AInsertar);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Emision__Cheque.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        emisionchequeDAO.insert(insertar);
        llenadodetablas();
        limpiar();
    }//GEN-LAST:event_BtnIngActionPerformed

    private void BtnModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModActionPerformed
        EmisionChequeDAO emisionchequeDAO = new EmisionChequeDAO();
        EmisionCheque modificarcheque = new EmisionCheque();

        String Inicio = new SimpleDateFormat("dd/MM/yyyy").format(Fecha.getDate());

        modificarcheque.setNumero_Cheque(txtNoCheque.getText());
        modificarcheque.setFecha_Cheque(Inicio);
        modificarcheque.setFK_Banco(ComboBanco.getSelectedItem().toString());
        modificarcheque.setFK_Cuenta(ComboCuenta.getSelectedItem().toString());
        modificarcheque.setFK_Cuentahabiente(ComboPaguese.getSelectedItem().toString());
        modificarcheque.setMonto_Cheque(txtMonto.getText());
        JOptionPane.showMessageDialog(null, "Cheque Modificado Exitosamente");
        //bitacora
        AInsertar.setId_Usuario(Login.usuarioFianzas);
        AInsertar.setAccion("Modificar");
        AInsertar.setCodigoAplicacion("1105");
        AInsertar.setModulo("1000");

        try {
            BitacoraDAO.insert(AInsertar);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Emision__Cheque.class.getName()).log(Level.SEVERE, null, ex);
        }
        emisionchequeDAO.insert(modificarcheque);
        llenadodetablas();
        limpiar();
    }//GEN-LAST:event_BtnModActionPerformed

    private void BtnElimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnElimActionPerformed
        EmisionChequeDAO emisionchequeDAO = new EmisionChequeDAO();
        EmisionCheque chequeeliminar = new EmisionCheque();
        //Metodo Para Eliminar con el Numero de Cuenta Bancaria
        chequeeliminar.setNumero_Cheque((txt_Buscar.getText()));
        JOptionPane.showMessageDialog(null, "Cheque Eliminado Exitosamente");
        //bitacora
        AInsertar.setId_Usuario(Login.usuarioFianzas);
        AInsertar.setAccion("Eliminar");
        AInsertar.setCodigoAplicacion("1105");
        AInsertar.setModulo("1000");
        try {
            BitacoraDAO.insert(AInsertar);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Emision__Cheque.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        emisionchequeDAO.delete(chequeeliminar);
        llenadodetablas();
        limpiar();
    }//GEN-LAST:event_BtnElimActionPerformed

    private void BtnAyuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAyuActionPerformed
    /// metodo para ejecutar la ayuda que es una guia para Emision Cheque
        try {
            if ((new File("src\\main\\java\\Finanzas\\ayudas\\MantenimientoMoneda.chm")).exists()) {
                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler src\\main\\java\\Finanzas\\ayudas\\MantenimientoMoneda.chm");
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
                    + "/src/main/java/Finanzas/reportes/reporteCheque.jrxml");
            print = JasperFillManager.fillReport(report, p, connection);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte Emision Cheque");
            view.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnImprimirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Boton_Buscar;
    private javax.swing.JButton BtnAyu;
    private javax.swing.JButton BtnElim;
    private javax.swing.JButton BtnIng;
    private javax.swing.JButton BtnMod;
    private javax.swing.JComboBox<String> ComboBanco;
    private javax.swing.JComboBox<String> ComboCuenta;
    private javax.swing.JComboBox<String> ComboPaguese;
    private com.toedter.calendar.JDateChooser Fecha;
    private javax.swing.JTable TablaECheque;
    public javax.swing.JButton btnImprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtNoCheque;
    private javax.swing.JTextField txt_Buscar;
    // End of variables declaration//GEN-END:variables
}
