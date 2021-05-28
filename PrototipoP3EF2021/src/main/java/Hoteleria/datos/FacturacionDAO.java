/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hoteleria.datos;

import Hoteleria.dominio.Facturacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Jeff
 */
public class FacturacionDAO {

    private static final String SQL_SELECT = "SELECT PK_id_factura, PK_id_reservacion, nombre_factura, formaPago_factura, totalReservacion_factura, totalServicios_factura, totalFacturacion_factura FROM tbl_facturacion";
    private static final String SQL_QUERY = "SELECT nombre_metodo FROM tbl_metodos_de_pago";
    private static final String SQL_PK = "nombre_metodo";
    private static final String SQL_QUERY2 = "SELECT PK_id_entrega, nombre FROM tbl_entrega_habitacion WHERE PK_id_entrega = ?";
    private static final String SQL_INSERT = "INSERT INTO tbl_facturacion(PK_id_factura, PK_id_reservacion, nombre_factura, fechaEntrada_factura, fechaSalida_factura, formaPago_factura, totalReservacion_factura, totalServicios_factura, totalFacturacion_factura, estado) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public List<Facturacion> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Facturacion factura = null;
        List<Facturacion> facturacion = new ArrayList<Facturacion>();

        try {
            conn = ConexionHoteleria.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_ama_de_llaves = rs.getInt("PK_id_factura");
                int nombre_ama_de_llaves = rs.getInt("PK_id_reservacion");
                String apellido_ama_de_llaves = rs.getString("nombre_factura");
                String piso_ama_de_llaves = rs.getString("formaPago_factura");
                //int entrada_ama_de_llaves = rs.getInt("noTarjeta_factura");
                int salida_ama_de_llaves = rs.getInt("totalReservacion_factura");
                int inicio_labores_ama_de_llaves = rs.getInt("totalServicios_factura");
                int descripcion_ama_de_llaves = rs.getInt("totalFacturacion_factura");

                factura = new Facturacion();
                factura.setId_Factura(id_ama_de_llaves);
                factura.setId_Reservacion(nombre_ama_de_llaves);
                factura.setNombre_Factura(apellido_ama_de_llaves);
                factura.setFormaPago_Factura(piso_ama_de_llaves);
                //factura.setNoTarjeta_Factura(entrada_ama_de_llaves);
                factura.setTotalReservacion_Factura(salida_ama_de_llaves);
                factura.setTotalServicios_Factura(inicio_labores_ama_de_llaves);
                factura.setTotalFactura_Factura(descripcion_ama_de_llaves);

                facturacion.add(factura);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionHoteleria.close(rs);
            ConexionHoteleria.close(stmt);
            ConexionHoteleria.close(conn);
        }
        return facturacion;
    }

//    public void llenarCbx(JComboBox cbxModulo) {
//        LlenarCbxDAO llenar = new LlenarCbxDAO();
//        llenar.llenarString(SQL_QUERY, SQL_PK, cbxModulo);
//    }

    public Facturacion query(Facturacion ama_De_Llaves) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;

        try {
            conn = ConexionHoteleria.getConnection();
            stmt = conn.prepareStatement(SQL_QUERY2);
            stmt.setInt(1, ama_De_Llaves.getId_Reservacion());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id_ama_de_llaves = rs.getInt("PK_id_entrega");
                String nombre_ama_de_llaves = rs.getString("nombre");

                ama_De_Llaves = new Facturacion();
                ama_De_Llaves.setId_Reservacion(id_ama_de_llaves);
                ama_De_Llaves.setNombre_Factura(nombre_ama_de_llaves);
                rows++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionHoteleria.close(rs);
            ConexionHoteleria.close(stmt);
            ConexionHoteleria.close(conn);
        }
        return ama_De_Llaves;
    }
    
    public int insert(Facturacion ama_De_Llaves) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionHoteleria.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, ama_De_Llaves.getId_Factura());
            stmt.setInt(2, ama_De_Llaves.getId_Reservacion());
            stmt.setString(3, ama_De_Llaves.getNombre_Factura());
            stmt.setString(4, ama_De_Llaves.getFechaEntrada_Factura());
            stmt.setString(5, ama_De_Llaves.getFechaSalida_Factura());
            stmt.setString(6, ama_De_Llaves.getFormaPago_Factura());
//            stmt.setInt(7, ama_De_Llaves.getNoTarjeta_Factura());
//            stmt.setInt(8, ama_De_Llaves.getCvv_Factura());
//            stmt.setString(9, ama_De_Llaves.getCaducidad_Factura());
            stmt.setInt(7, ama_De_Llaves.getTotalReservacion_Factura());
            stmt.setInt(8, ama_De_Llaves.getTotalServicios_Factura());
            stmt.setInt(9, ama_De_Llaves.getTotalFactura_Factura());
            stmt.setInt(10, ama_De_Llaves.getEstado_Factura());

            rows = stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Transferencia Exitosa");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionHoteleria.close(stmt);
            ConexionHoteleria.close(conn);
        }
        return rows;
    }
}
