/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hoteleria.datos;

import Hoteleria.dominio.Pisos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author visitante
 */
public class PisosDAO {

    //Buscar en la BD
    private static final String SQL_SELECT = "SELECT PK_numero_piso, cantidad_habitaciones, descripcion_piso, estado_piso FROM tbl_piso_hotel";
    //Insertar en la BD
    private static final String SQL_INSERT = "INSERT INTO tbl_piso_hotel(PK_numero_piso, cantidad_habitaciones, descripcion_piso, estado_piso) VALUES(?, ?, ?, ?)";
    //Modificar la BD
    private static final String SQL_UPDATE = "UPDATE tbl_piso_hotel SET cantidad_habitaciones=?, descripcion_piso=?, estado_piso=? WHERE PK_numero_piso = ?";
    //Buscar 2 en la BD
    public static final String SQL_QUERY = "SELECT PK_numero_piso, cantidad_habitaciones, descripcion_piso, estado_piso FROM tbl_piso_hotel WHERE PK_numero_piso = ?";

    private static final String SQL_DELETE = "DELETE FROM tbl_piso_hotel WHERE PK_numero_piso=?";

    public List<Pisos> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Pisos piso = null;
        List<Pisos> pisos = new ArrayList<Pisos>();

        try {
            conn = ConexionHoteleria.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int numero_Piso = rs.getInt("PK_numero_piso");
                int cantidad_Habitaciones = rs.getInt("cantidad_habitaciones");
                String descripcion_Piso = rs.getString("descripcion_piso");
                int estado_Piso = rs.getInt("estado_piso");

                piso = new Pisos();
                piso.setId_Numero_De_Piso(numero_Piso);
                piso.setCantidad_De_Habitaciones(cantidad_Habitaciones);
                piso.setDescripcion_De_Piso(descripcion_Piso);
                piso.setEstado_De_Piso(estado_Piso);

                pisos.add(piso);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionHoteleria.close(rs);
            ConexionHoteleria.close(stmt);
            ConexionHoteleria.close(conn);
        }
        return pisos;
    }

    public int insert(Pisos pisos) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionHoteleria.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, pisos.getId_Numero_De_Piso());
            stmt.setInt(2, pisos.getCantidad_De_Habitaciones());
            stmt.setString(3, pisos.getDescripcion_De_Piso());
            stmt.setInt(4, pisos.getEstado_De_Piso());
            rows = stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro Exitoso");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionHoteleria.close(stmt);
            ConexionHoteleria.close(conn);
        }

        return rows;
    }

    public int update(Pisos pisos) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = ConexionHoteleria.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, pisos.getCantidad_De_Habitaciones());
            stmt.setString(2, pisos.getDescripcion_De_Piso());
            stmt.setInt(3, pisos.getEstado_De_Piso());
            stmt.setInt(4, pisos.getId_Numero_De_Piso());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionHoteleria.close(stmt);
            ConexionHoteleria.close(conn);
        }
        return rows;
    }

    public Pisos query(Pisos pisos) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;

        try {

            conn = ConexionHoteleria.getConnection();
            //System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, pisos.getId_Numero_De_Piso());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int numero_Piso = rs.getInt("PK_numero_piso");
                int cantidad_Habitaciones = rs.getInt("cantidad_habitaciones");
                String descripcion_Piso = rs.getString("descripcion_piso");
                int estado_Piso = rs.getInt("estado_piso");

                pisos = new Pisos();
                pisos.setId_Numero_De_Piso(numero_Piso);
                pisos.setCantidad_De_Habitaciones(cantidad_Habitaciones);
                pisos.setDescripcion_De_Piso(descripcion_Piso);
                pisos.setEstado_De_Piso(estado_Piso);
                rows++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionHoteleria.close(rs);
            ConexionHoteleria.close(stmt);
            ConexionHoteleria.close(conn);
        }
        return pisos;
    }
    
    public int delete(Pisos pisos) {

        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = ConexionHoteleria.getConnection();
            //System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, pisos.getId_Numero_De_Piso());
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

}
