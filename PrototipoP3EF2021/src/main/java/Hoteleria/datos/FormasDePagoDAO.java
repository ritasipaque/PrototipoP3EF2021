/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hoteleria.datos;

import Hoteleria.dominio.FormasDePago;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author leone
 */
public class FormasDePagoDAO {
    private static final String SQL_INSERT = "insert into tbl_metodos_de_pago values(?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE tbl_metodos_de_pago SET nombre_metodo=?, descripcion_metodo=?, estado_metodo=? WHERE PK_id_metodo=?";
    private static final String SQL_SELECT = "SELECT PK_id_metodo, nombre_metodo, descripcion_metodo, estado_metodo FROM tbl_metodos_de_pago";
    private static final String SQL_QUERY = "SELECT PK_id_metodo, nombre_metodo, descripcion_metodo, estado_metodo FROM tbl_metodos_de_pago WHERE PK_id_metodo = ?";
    private static final String SQL_DELETE = "delete from tbl_metodos_de_pago where PK_id_metodo = ?";  
    
    public int insert(FormasDePago formasdepago) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionHoteleria.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, formasdepago.getId());
            stmt.setString(2, formasdepago.getNombre());
            stmt.setString(3, formasdepago.getDescripcion());
            stmt.setString(4, formasdepago.getEstado());
           

            //System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            //System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionHoteleria.close(stmt);
            ConexionHoteleria.close(conn);
        }

        return rows;
    }
    public int update(FormasDePago formasdepago){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = ConexionHoteleria.getConnection();
//          System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, formasdepago.getNombre());
            stmt.setString(2, formasdepago.getDescripcion());
            stmt.setString(3, formasdepago.getEstado());
            stmt.setString(4, formasdepago.getId());
            
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
    public List<FormasDePago> select(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        FormasDePago formasdepago = null;
        List<FormasDePago> metodo = new ArrayList<FormasDePago>();
        
        try {
            conn = ConexionHoteleria.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                String id_metodo = rs.getString("PK_id_metodo");
                String nombre_metodo = rs.getString("nombre_metodo");
                String descripcion_metodo = rs.getString("descripcion_metodo");
                String estado_metodo = rs.getString("estado_metodo");
                
                formasdepago = new FormasDePago();
                formasdepago.setId(id_metodo);
                formasdepago.setNombre(nombre_metodo);
                formasdepago.setDescripcion(descripcion_metodo);
                formasdepago.setEstado(estado_metodo);
                
                
                metodo.add(formasdepago);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            ConexionHoteleria.close(rs);
            ConexionHoteleria.close(stmt);
            ConexionHoteleria.close(conn);
        }
        
        return metodo;
    }
    public FormasDePago query(FormasDePago formasdepago) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;

        try {
            conn = ConexionHoteleria.getConnection();
            //System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, formasdepago.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                String id_metodo = rs.getString("PK_id_metodo");
                String nombre = rs.getString("nombre_metodo");
                String descripcio = rs.getString("descripcion_metodo");
                String estado = rs.getString("estado_metodo");

                formasdepago = new FormasDePago();
                formasdepago.setId(id_metodo);
                formasdepago.setNombre(nombre);
                formasdepago.setDescripcion(descripcio);
                formasdepago.setEstado(estado);
                System.out.println(formasdepago.getNombre());
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionHoteleria.close(rs);
            ConexionHoteleria.close(stmt);
            ConexionHoteleria.close(conn);
        }
        return formasdepago;
    }
    public int delete(FormasDePago formasdepago){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionHoteleria.getConnection();
            //System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, formasdepago.getId());
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
