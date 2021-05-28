/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hoteleria.datos;

import Hoteleria.dominio.Huespedes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leelu
 */
public class HuespedesDAO {
        private static final String SQL_INSERT = "insert into tbl_huespedes values(?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE tbl_huespedes SET nombre_huesped=?, apellido_huesped=?, nacionalidad_huesped=?, direccion_huesped=?, frecuencia_huesped=?, telefono_huesped=?, nit_huesped=? WHERE PK_no_identificacion=?";
    private static final String SQL_SELECT = "SELECT PK_no_identificacion, nombre_huesped, nombre_huesped, apellido_huesped, nacionalidad_huesped, direccion_huesped, frecuencia_huesped, telefono_huesped, nit_huesped FROM tbl_huespedes";
    private static final String SQL_QUERY = "SELECT PK_no_identificacion, nombre_huesped, nombre_huesped, apellido_huesped, nacionalidad_huesped, direccion_huesped, frecuencia_huesped, telefono_huesped, nit_huesped FROM tbl_huespedes WHERE PK_no_identificacion = ?";
     private static final String SQL_DELETE = "delete from tbl_huespedes where PK_no_identificacion = ?";
    
    public int insert(Huespedes huespedes) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionHoteleria.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, huespedes.getCodigo());
            stmt.setString(2, huespedes.getNombre());
            stmt.setString(3, huespedes.getApellido());
            stmt.setString(4, huespedes.getNacionalidad());
            stmt.setString(5, huespedes.getDireccion());
            stmt.setString(6, huespedes.getFrecuencia());
            stmt.setString(7, huespedes.getTelefono());
            stmt.setString(8, huespedes.getNit());
           

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
    public int update(Huespedes huespedes){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = ConexionHoteleria.getConnection();
//          System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(8, huespedes.getCodigo());
            stmt.setString(1, huespedes.getNombre());
            stmt.setString(2, huespedes.getApellido());
            stmt.setString(3, huespedes.getNacionalidad());
            stmt.setString(4, huespedes.getDireccion());
            stmt.setString(5, huespedes.getFrecuencia());
            stmt.setString(6, huespedes.getTelefono());
            stmt.setString(7, huespedes.getNit());

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
    public List<Huespedes> select(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Huespedes huespedes = null;
        List<Huespedes> metodo = new ArrayList<Huespedes>();
        
        try {
            conn = ConexionHoteleria.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                String codigo = rs.getString("PK_no_identificacion");
                String nombre = rs.getString("nombre_huesped");
                String apellido = rs.getString("apellido_huesped");
                String nacionalidad = rs.getString("nacionalidad_huesped");
                String direccion = rs.getString("direccion_huesped");
                String frecuencia = rs.getString("frecuencia_huesped");
                String telefono = rs.getString("telefono_huesped");
                String nit = rs.getString("nit_huesped");
                
                
                huespedes = new Huespedes();
                huespedes.setCodigo(codigo);
                huespedes.setNombre(nombre);
                huespedes.setApellido(apellido);
                huespedes.setNacionalidad(nacionalidad);
                huespedes.setDireccion(direccion);
                huespedes.setFrecuencia(frecuencia);
                huespedes.setTelefono(telefono);
                huespedes.setNit(nit);
                
                
                metodo.add(huespedes);
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
    public Huespedes query(Huespedes huespedes) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;

        try {
            conn = ConexionHoteleria.getConnection();
            //System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, huespedes.getCodigo());
            rs = stmt.executeQuery();
            while (rs.next()) {
                String codigo = rs.getString("PK_no_identificacion");
                String nombre = rs.getString("nombre_huesped");
                String apellido = rs.getString("apellido_huesped");
                String nacionalidad = rs.getString("nacionalidad_huesped");
                String direccion = rs.getString("direccion_huesped");
                String frecuencia = rs.getString("frecuencia_huesped");
                String telefono = rs.getString("telefono_huesped");
                String nit = rs.getString("nit_huesped");
                
                
                huespedes = new Huespedes();
                huespedes.setCodigo(codigo);
                huespedes.setNombre(nombre);
                huespedes.setApellido(apellido);
                huespedes.setNacionalidad(nacionalidad);
                huespedes.setDireccion(direccion);
                huespedes.setFrecuencia(frecuencia);
                huespedes.setTelefono(telefono);
                huespedes.setNit(nit);
                System.out.println(huespedes.getNombre());
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionHoteleria.close(rs);
            ConexionHoteleria.close(stmt);
            ConexionHoteleria.close(conn);
        }
        return huespedes;
    }
    public int delete(Huespedes modulos){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionHoteleria.getConnection();
            //System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, modulos.getCodigo());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionHoteleria.close(stmt);
            ConexionHoteleria.close(conn);
        }

        return rows;
    }
}
