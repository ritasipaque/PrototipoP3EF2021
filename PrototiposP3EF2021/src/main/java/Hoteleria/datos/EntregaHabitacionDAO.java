package Hoteleria.datos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Hoteleria.dominio.EntregaHabitaciones;
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
public class EntregaHabitacionDAO {

    private static final String SQL_INSERT = "INSERT INTO tbl_entrega_habitacion values(?,?,?,?,?,?,?)";
    private static final String SQL_SELECT = "SELECT * FROM tbl_entrega_habitacion";
    private static final String SQL_QUERY = "SELECT * FROM tbl_entrega_habitacion WHERE PK_id_entrega = ?";
    private static final String SQL_UPDATE = "UPDATE tbl_entrega_habitacion SET  PK_id_reservacion=?, PK_id_habitacion=?,PK_no_identificacion=?, nombre=?,fecha=?,estado=? WHERE PK_id_entrega=?";
    
    public List<EntregaHabitaciones> select(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        EntregaHabitaciones entrega = null;
        List<EntregaHabitaciones> reservar = new ArrayList<EntregaHabitaciones>();
        
        try {
            conn = ConexionHoteleria.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                String id_entrega = rs.getString("PK_id_entrega");
                String id_reservacion = rs.getString("PK_id_reservacion");
                String id_habitacion = rs.getString("PK_id_habitacion");
                String no_identificacion=rs.getString("PK_no_identificacion");
                String Nombre = rs.getString("nombre");
                String fecha = rs.getString("fecha");
                String estado = rs.getString("estado");
                
                entrega = new EntregaHabitaciones();
                entrega.setPk_id_entrega(id_entrega);
                entrega.setPk_id_reservacion(id_reservacion);
                entrega.setPk_id_habitacion(id_habitacion);
                entrega.setPk_no_identificacion(no_identificacion);
                entrega.setNombre(Nombre);
                entrega.setFecha(fecha);
                entrega.setEstado(estado);
                
                reservar.add(entrega);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            ConexionHoteleria.close(rs);
            ConexionHoteleria.close(stmt);
            ConexionHoteleria.close(conn);
        }
        
        return reservar;
    }
    public int insert(EntregaHabitaciones entrega){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionHoteleria.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1,entrega.getPk_id_entrega());
            stmt.setString(2,entrega.getPk_id_reservacion());
            stmt.setString(3,entrega.getPk_id_habitacion());
            stmt.setString(4,entrega.getPk_no_identificacion());
            stmt.setString(5,entrega.getNombre());
            stmt.setString(6,entrega.getFecha());
            stmt.setString(7,entrega.getEstado());
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
    public EntregaHabitaciones query(EntregaHabitaciones habitaciones) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        int rows = 0;

        try {

            conn = ConexionHoteleria.getConnection();
            //System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, habitaciones.getPk_id_entrega());
            rs = stmt.executeQuery();

            while (rs.next()) {
                String id_entrega = rs.getString("PK_id_entrega");
                String id_reservacion = rs.getString("PK_id_reservacion");
                String id_habitacion = rs.getString("PK_id_habitacion");
                String no_identificacion=rs.getString("PK_no_identificacion");
                String Nombre = rs.getString("nombre");
                String fecha = rs.getString("fecha");
                String estado = rs.getString("estado");
                
                habitaciones = new EntregaHabitaciones();
                habitaciones.setPk_id_entrega(id_entrega);
                habitaciones.setPk_id_reservacion(id_reservacion);
                habitaciones.setPk_id_habitacion(id_habitacion);
                habitaciones.setPk_no_identificacion(no_identificacion);
                habitaciones.setNombre(Nombre);
                habitaciones.setFecha(fecha);
                habitaciones.setEstado(estado);
                
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
     public int update(EntregaHabitaciones habitacion){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = ConexionHoteleria.getConnection();
//          System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1, habitacion.getPk_id_reservacion());
            stmt.setString(2, habitacion.getPk_id_habitacion());
            stmt.setString(3, habitacion.getPk_no_identificacion());
            stmt.setString(4, habitacion.getNombre());
            stmt.setString(5, habitacion.getFecha());
            stmt.setString(6, habitacion.getEstado());
            stmt.setString(7, habitacion.getPk_id_entrega());

            
            rows = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            ConexionHoteleria.close(stmt);
            ConexionHoteleria.close(conn);
        }
        
        return rows;
    }
}
            

