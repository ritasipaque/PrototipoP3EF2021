/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hoteleria.datos;

import Hoteleria.dominio.AmaDeLlaves;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import Hoteleria.datos.LlenarCbxDAO;

/**
 *
 * @author visitante
 */
public class AmaDeLlavesDAO {

    //Buscar en la BD
    private static final String SQL_SELECT = "SELECT PK_id_ama_de_llaves, nombre_ama_de_llaves, apellido_ama_de_llaves, piso_ama_de_llaves, entrada_ama_de_llaves, salida_ama_de_llaves, inicio_labores_ama_de_llaves, descripcion_ama_de_llaves, estado_ama_de_llaves FROM tbl_ama_de_llaves";
    //Insertar en la BD
    private static final String SQL_INSERT = "INSERT INTO tbl_ama_de_llaves(PK_id_ama_de_llaves, nombre_ama_de_llaves, apellido_ama_de_llaves, piso_ama_de_llaves, entrada_ama_de_llaves, salida_ama_de_llaves, inicio_labores_ama_de_llaves, descripcion_ama_de_llaves, estado_ama_de_llaves) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    //Modificar la BD
    private static final String SQL_UPDATE = "UPDATE tbl_ama_de_llaves SET nombre_ama_de_llaves=?, apellido_ama_de_llaves=?, piso_ama_de_llaves=?, entrada_ama_de_llaves=?, salida_ama_de_llaves=?, inicio_labores_ama_de_llaves=?, descripcion_ama_de_llaves=?, estado_ama_de_llaves=? WHERE PK_id_ama_de_llaves = ?";
    //Eliminar de la BD
    private static final String SQL_DELETE = "DELETE FROM tbl_ama_de_llaves WHERE PK_id_ama_de_llaves=?";
    //Buscar 2 en la BD
    public static final String SQL_QUERY = "SELECT PK_id_ama_de_llaves, nombre_ama_de_llaves, apellido_ama_de_llaves, piso_ama_de_llaves, entrada_ama_de_llaves, salida_ama_de_llaves, inicio_labores_ama_de_llaves, descripcion_ama_de_llaves, estado_ama_de_llaves FROM tbl_ama_de_llaves WHERE PK_id_ama_de_llaves = ?";

    public static final String SQL_QUERY2 = "SELECT PK_numero_piso FROM tbl_piso_hotel";
    public static final String SQL_PK = "PK_numero_piso";

    public List<AmaDeLlaves> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        AmaDeLlaves ama_De_Llave = null;
        List<AmaDeLlaves> ama_De_Llaves = new ArrayList<AmaDeLlaves>();

        try {
            conn = ConexionHoteleria.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_ama_de_llaves = rs.getInt("PK_id_ama_de_llaves");
                String nombre_ama_de_llaves = rs.getString("nombre_ama_de_llaves");
                String apellido_ama_de_llaves = rs.getString("apellido_ama_de_llaves");
                int piso_ama_de_llaves = rs.getInt("piso_ama_de_llaves");
                String entrada_ama_de_llaves = rs.getString("entrada_ama_de_llaves");
                String salida_ama_de_llaves = rs.getString("salida_ama_de_llaves");
                String inicio_labores_ama_de_llaves = rs.getString("inicio_labores_ama_de_llaves");
                String descripcion_ama_de_llaves = rs.getString("descripcion_ama_de_llaves");
                int estado_ama_de_llaves = rs.getInt("estado_ama_de_llaves");

                ama_De_Llave = new AmaDeLlaves();
                ama_De_Llave.setId_Ama_De_Llaves(id_ama_de_llaves);
                ama_De_Llave.setNombre_Ama_De_Llaves(nombre_ama_de_llaves);
                ama_De_Llave.setApellido_Ama_De_Llaves(apellido_ama_de_llaves);
                ama_De_Llave.setPiso_Ama_De_Llaves(piso_ama_de_llaves);
                ama_De_Llave.setEntrada_Ama_De_Llaves(entrada_ama_de_llaves);
                ama_De_Llave.setSalida_Ama_De_Llaves(salida_ama_de_llaves);
                ama_De_Llave.setInicio_Ama_De_Llaves(inicio_labores_ama_de_llaves);
                ama_De_Llave.setDescripcion_Ama_De_Llaves(descripcion_ama_de_llaves);
                ama_De_Llave.setEstado_Ama_De_Llaves(estado_ama_de_llaves);

                ama_De_Llaves.add(ama_De_Llave);
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

    public int insert(AmaDeLlaves ama_De_Llaves) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionHoteleria.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, ama_De_Llaves.getId_Ama_De_Llaves());
            stmt.setString(2, ama_De_Llaves.getNombre_Ama_De_Llaves());
            stmt.setString(3, ama_De_Llaves.getApellido_Ama_De_Llaves());
            stmt.setInt(4, ama_De_Llaves.getPiso_Ama_De_Llaves());
            stmt.setString(5, ama_De_Llaves.getEntrada_Ama_De_Llaves());
            stmt.setString(6, ama_De_Llaves.getSalida_Ama_De_Llaves());
            stmt.setString(7, ama_De_Llaves.getInicio_Ama_De_Llaves());
            stmt.setString(8, ama_De_Llaves.getDescripcion_Ama_De_Llaves());
            stmt.setInt(9, ama_De_Llaves.getEstado_Ama_De_Llaves());

            //System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            //System.out.println("Registros afectados:" + rows);
            JOptionPane.showMessageDialog(null, "Registro Exitoso");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionHoteleria.close(stmt);
            ConexionHoteleria.close(conn);
        }

        return rows;
    }

    public int update(AmaDeLlaves ama_De_Llaves) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = ConexionHoteleria.getConnection();
            //System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, ama_De_Llaves.getNombre_Ama_De_Llaves());
            stmt.setString(2, ama_De_Llaves.getApellido_Ama_De_Llaves());
            stmt.setInt(3, ama_De_Llaves.getPiso_Ama_De_Llaves());
            stmt.setString(4, ama_De_Llaves.getEntrada_Ama_De_Llaves());
            stmt.setString(5, ama_De_Llaves.getSalida_Ama_De_Llaves());
            stmt.setString(6, ama_De_Llaves.getInicio_Ama_De_Llaves());
            stmt.setString(7, ama_De_Llaves.getDescripcion_Ama_De_Llaves());
            stmt.setInt(8, ama_De_Llaves.getEstado_Ama_De_Llaves());
            stmt.setInt(9, ama_De_Llaves.getId_Ama_De_Llaves());
            rows = stmt.executeUpdate();
            //System.out.println("Registros actualizado:" + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionHoteleria.close(stmt);
            ConexionHoteleria.close(conn);
        }

        return rows;
    }

    public int delete(AmaDeLlaves ama_De_Llaves) {

        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = ConexionHoteleria.getConnection();
            //System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, ama_De_Llaves.getId_Ama_De_Llaves());
            rows = stmt.executeUpdate();
            //System.out.println("Registros eliminados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionHoteleria.close(stmt);
            ConexionHoteleria.close(conn);
        }

        return rows;
    }
    //    public List<Persona> query(Persona persona) { // Si se utiliza un ArrayList

    public AmaDeLlaves query(AmaDeLlaves ama_De_Llaves) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //List<Ama_De_Llaves> ama_De_Llave = new ArrayList<Ama_De_Llaves>();
        int rows = 0;

        try {

            conn = ConexionHoteleria.getConnection();
            //System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, ama_De_Llaves.getId_Ama_De_Llaves());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id_ama_de_llaves = rs.getInt("PK_id_ama_de_llaves");
                String nombre_ama_de_llaves = rs.getString("nombre_ama_de_llaves");
                String apellido_ama_de_llaves = rs.getString("apellido_ama_de_llaves");
                int piso_ama_de_llaves = rs.getInt("piso_ama_de_llaves");
                String entrada_ama_de_llaves = rs.getString("entrada_ama_de_llaves");
                String salida_ama_de_llaves = rs.getString("salida_ama_de_llaves");
                String inicio_labores_ama_de_llaves = rs.getString("inicio_labores_ama_de_llaves");
                String descripcion_ama_de_llaves = rs.getString("descripcion_ama_de_llaves");
                int estado_ama_de_llaves = rs.getInt("estado_ama_de_llaves");

                ama_De_Llaves = new AmaDeLlaves();
                ama_De_Llaves.setId_Ama_De_Llaves(id_ama_de_llaves);
                ama_De_Llaves.setNombre_Ama_De_Llaves(nombre_ama_de_llaves);
                ama_De_Llaves.setApellido_Ama_De_Llaves(apellido_ama_de_llaves);
                ama_De_Llaves.setPiso_Ama_De_Llaves(piso_ama_de_llaves);
                ama_De_Llaves.setEntrada_Ama_De_Llaves(entrada_ama_de_llaves);
                ama_De_Llaves.setSalida_Ama_De_Llaves(salida_ama_de_llaves);
                ama_De_Llaves.setInicio_Ama_De_Llaves(inicio_labores_ama_de_llaves);
                ama_De_Llaves.setDescripcion_Ama_De_Llaves(descripcion_ama_de_llaves);
                ama_De_Llaves.setEstado_Ama_De_Llaves(estado_ama_de_llaves);
                rows++;
            }
            //System.out.println("Registros buscado:" + aplicacion);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionHoteleria.close(rs);
            ConexionHoteleria.close(stmt);
            ConexionHoteleria.close(conn);
        }
        return ama_De_Llaves;

    }
    
//    public void query2(JComboBox cbxModulo) {
//
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//
//        try {
//
//            conn = ConexionHoteleria.getConnection();
//            //System.out.println("Ejecutando query:" + SQL_QUERY);
//            stmt = conn.prepareStatement(SQL_QUERY2);
//            //stmt.setInt(1, aplicacion.getId_ModuloCbx());
//            rs = stmt.executeQuery();
//
//            cbxModulo.addItem("Seleccionar...");
//            
//            while (rs.next()) {
//                cbxModulo.addItem(rs.getInt("PK_numero_piso"));
//            }
//            //System.out.println("Registros buscado:" + aplicacion);
//        } catch (SQLException ex) {
//            ex.printStackTrace(System.out);
//        } finally {
//            ConexionHoteleria.close(rs);
//            ConexionHoteleria.close(stmt);
//            ConexionHoteleria.close(conn);
//        }
//
//    }
    
    public void llenarCbx(JComboBox cbxModulo){
        LlenarCbxDAO llenar = new LlenarCbxDAO();
        llenar.llenarInt(SQL_QUERY2, SQL_PK, cbxModulo);
    }

}
