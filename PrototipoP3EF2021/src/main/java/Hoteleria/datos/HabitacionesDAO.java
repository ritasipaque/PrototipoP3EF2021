/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hoteleria.datos;

import Hoteleria.dominio.Habitaciones;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author gegmo
 */
public class HabitacionesDAO {

    private static final String SQL_INSERT = "INSERT INTO tbl_mantenimiento_habitaciones(PK_id_habitacion, descripcion_habitacion,precio_habitacion, piso_habitaciones, estado_habitacion, tipo_de_habitacion,cantidad_maxima_pers) VALUES(?,?,?,?,?,?,?)";

    private static final String SQL_SELECT = "SELECT PK_id_habitacion, descripcion_habitacion, precio_habitacion, piso_habitaciones, estado_habitacion, tipo_de_habitacion,cantidad_maxima_pers FROM tbl_mantenimiento_habitaciones";

    private static final String SQL_UPDATE = "UPDATE tbl_mantenimiento_habitaciones SET descripcion_habitacion=?, precio_habitacion=?, piso_habitaciones=?, estado_habitacion=?, tipo_de_habitacion=?,cantidad_maxima_pers=? WHERE PK_id_habitacion = ?";

    public static final String SQL_QUERY = "SELECT PK_id_habitacion, descripcion_habitacion, precio_habitacion, piso_habitaciones, estado_habitacion, tipo_de_habitacion,cantidad_maxima_pers FROM tbl_mantenimiento_habitaciones WHERE PK_id_habitacion = ?";

    public static final String SQL_QUERY2 = "SELECT PK_numero_piso FROM tbl_piso_hotel";
    
     public static final String SQL_DELETE ="DELETE FROM tbl_mantenimiento_habitaciones WHERE PK_id_habitacion=?";

    public void query2(JComboBox cbxModulo) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = ConexionHoteleria.getConnection();
            //System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY2);
            //stmt.setInt(1, aplicacion.getId_ModuloCbx());
            rs = stmt.executeQuery();

            cbxModulo.addItem("Seleccionar...");
            
            while (rs.next()) {
                cbxModulo.addItem(rs.getInt("PK_numero_piso"));
            }
            //System.out.println("Registros buscado:" + aplicacion);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionHoteleria.close(rs);
            ConexionHoteleria.close(stmt);
            ConexionHoteleria.close(conn);
        }

    }
    public int insert(Habitaciones mantenimiento_habitaciones) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionHoteleria.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, mantenimiento_habitaciones.getId_Habitaciones());
            stmt.setString(2, mantenimiento_habitaciones.getDescripcion());
            stmt.setInt(3, mantenimiento_habitaciones.getPrecio());
            stmt.setInt(4, mantenimiento_habitaciones.getPiso());
            stmt.setInt(5, mantenimiento_habitaciones.getEstado_Habitacion());
            stmt.setString(6, mantenimiento_habitaciones.getTipo_Habitacion());
            stmt.setString (7, mantenimiento_habitaciones.getMax_personas());

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

    public int update(Habitaciones mantenimiento_habitaciones) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = ConexionHoteleria.getConnection();
            //System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, mantenimiento_habitaciones.getDescripcion());
            stmt.setInt(2, mantenimiento_habitaciones.getPrecio());
            stmt.setInt(3, mantenimiento_habitaciones.getPiso());
            stmt.setInt(4, mantenimiento_habitaciones.getEstado_Habitacion());
            stmt.setString(5, mantenimiento_habitaciones.getTipo_Habitacion());
            stmt.setInt(6, mantenimiento_habitaciones.getId_Habitaciones());
            stmt.setString(7, mantenimiento_habitaciones.getMax_personas());

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

    public Habitaciones query(Habitaciones habitaciones) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        int rows = 0;

        try {

            conn = ConexionHoteleria.getConnection();
            //System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, habitaciones.getId_Habitaciones());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id_habitacion = rs.getInt("PK_id_habitacion");
                String descripcion_hab = rs.getString("descripcion_habitacion");
                int precio = rs.getInt("precio_habitacion");
                int piso = rs.getInt("piso_habitaciones");
                int estado_hab = rs.getInt("estado_habitacion");
                String tipo_hab = rs.getString("tipo_de_habitacion");
                String max_per = rs.getString("cantidad_maxima_pers");

                habitaciones = new Habitaciones();
                habitaciones.setId_Habitaciones(id_habitacion);
                habitaciones.setDescripcion(descripcion_hab);
                habitaciones.setPrecio(precio);
                habitaciones.setPiso(piso);
                habitaciones.setEstado_Habitacion(estado_hab);
                habitaciones.setTipo_Habitacion(tipo_hab);
                habitaciones.setMax_personas(max_per);
                rows++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionHoteleria.close(rs);
            ConexionHoteleria.close(stmt);
            ConexionHoteleria.close(conn);

        }
        return (habitaciones);
    }

    public List<Habitaciones> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Habitaciones habitaciones = null;
        List<Habitaciones> habitacione = new ArrayList<Habitaciones>();

        try {
            conn = ConexionHoteleria.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_habitaciones = rs.getInt("PK_id_habitacion");
                String descripcion = rs.getString("descripcion_habitacion");
                int precio = rs.getInt("precio_habitacion");
                int piso = rs.getInt("piso_habitaciones");
                int estado = rs.getInt("estado_habitacion");
                String tipo = rs.getString("tipo_de_habitacion");
                String max_pers = rs.getString("cantidad_maxima_pers");

                habitaciones = new Habitaciones();
                habitaciones.setId_Habitaciones(id_habitaciones);
                habitaciones.setDescripcion(descripcion);
                habitaciones.setPrecio(precio);
                habitaciones.setPiso(piso);
                habitaciones.setEstado_Habitacion(estado);
                habitaciones.setTipo_Habitacion(tipo);
                habitaciones.setMax_personas(max_pers);

                habitacione.add(habitaciones);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionHoteleria.close(rs);
            ConexionHoteleria.close(stmt);
            ConexionHoteleria.close(conn);
        }
        return habitacione;
    }

     public int delete(Habitaciones habitaciones) {

        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = ConexionHoteleria.getConnection();
            //System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, habitaciones.getId_Habitaciones());
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
