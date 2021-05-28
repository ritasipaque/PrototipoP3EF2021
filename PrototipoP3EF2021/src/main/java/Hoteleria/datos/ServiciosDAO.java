/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hoteleria.datos;

import Hoteleria.dominio.Servicios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leone
 */
public class ServiciosDAO {
    private static final String SQL_INSERT = "insert into tbl_servicios values(?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE tbl_servicios SET nombre_servicio=?, descripcion_servicio=?, precio_servicio=?, tipo_servicio=?, estado_servicio=? WHERE PK_id_servicio=?";
    private static final String SQL_SELECT = "SELECT PK_id_servicio, nombre_servicio, descripcion_servicio, precio_servicio, tipo_servicio, estado_servicio FROM tbl_servicios";
    private static final String SQL_QUERY = "SELECT PK_id_servicio, nombre_servicio, descripcion_servicio, precio_servicio, tipo_servicio, estado_servicio FROM tbl_servicios WHERE PK_id_servicio = ?";
    private static final String SQL_DELETE = "delete from tbl_servicios where PK_id_servicio = ?";  
    
    public int insert(Servicios servicios) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionHoteleria.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, servicios.getId());
            stmt.setString(2, servicios.getNombre());
            stmt.setString(3, servicios.getDescripcion());
            stmt.setString(4, servicios.getPrecio());
            stmt.setString(5, servicios.getTipo());
            stmt.setString(6, servicios.getEstado());
           

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
    public int update(Servicios servicios){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = ConexionHoteleria.getConnection();
//          System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, servicios.getNombre());
            stmt.setString(2, servicios.getDescripcion());
            stmt.setString(3, servicios.getPrecio()); 
            stmt.setString(4, servicios.getTipo()); 
            stmt.setString(5, servicios.getEstado());
            stmt.setString(6, servicios.getId());
            
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
    public List<Servicios> select(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Servicios servicio = null;
        List<Servicios> servicios = new ArrayList<Servicios>();
        
        try {
            conn = ConexionHoteleria.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                String id = rs.getString("PK_id_servicio");
                String nombre = rs.getString("nombre_servicio");
                String descripcion = rs.getString("descripcion_servicio");
                String precio = rs.getString("precio_servicio");
                String tipo = rs.getString("tipo_servicio");
                String estado = rs.getString("estado_servicio");
                
                servicio = new Servicios();
                servicio.setId(id);
                servicio.setNombre(nombre);
                servicio.setDescripcion(descripcion);
                servicio.setPrecio(precio);
                servicio.setTipo(tipo);
                servicio.setEstado(estado);
                
                
                servicios.add(servicio);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            ConexionHoteleria.close(rs);
            ConexionHoteleria.close(stmt);
            ConexionHoteleria.close(conn);
        }
        
        return servicios;
    }
    public Servicios query(Servicios servicios) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;

        try {
            conn = ConexionHoteleria.getConnection();
            //System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, servicios.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString("PK_id_servicio");
                String nombre = rs.getString("nombre_servicio");
                String descripcion = rs.getString("descripcion_servicio");
                String precio = rs.getString("precio_servicio");
                String tipo = rs.getString("tipo_servicio");
                String estado = rs.getString("estado_servicio");

                servicios = new Servicios();
                servicios.setId(id);
                servicios.setNombre(nombre);
                servicios.setDescripcion(descripcion);
                servicios.setPrecio(precio);
                servicios.setTipo(tipo);
                servicios.setEstado(estado);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionHoteleria.close(rs);
            ConexionHoteleria.close(stmt);
            ConexionHoteleria.close(conn);
        }
        return servicios;
    }
    public int delete(Servicios servicios){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionHoteleria.getConnection();
            //System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, servicios.getId());
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
