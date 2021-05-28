/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad.datos;

import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import seguridad.dominio.Perfil;

/**
 *
 * @author Nay Ale
 */
public class PerfilDAO {
 private static final String SQL_SELECT = "SELECT Pk_id_perfil, nombre_perfil, descripcion_perfil, estado_perfil FROM tbl_perfil_encabezado";
 private static final String SQL_INSERT = "INSERT INTO tbl_perfil_encabezado VALUES(?, ?, ?, ?)";
 private static final String SQL_DELETE = "DELETE FROM tbl_perfil_encabezado WHERE Pk_id_perfil=?";
 private static final String SQL_UPDATE = "UPDATE tbl_perfil_encabezado SET Pk_id_perfil=?, nombre_perfil=?, descripcion_perfil=?, estado_perfil=? WHERE Pk_id_perfil = ?";
 private static final String SQL_QUERY = "SELECT Pk_id_perfil, nombre_perfil, descripcion_perfil, estado_perfil FROM tbl_perfil_encabezado WHERE Pk_id_perfil = ?";
    
    public int insert(Perfil perfil){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, perfil.getPk_id_perfil());
            stmt.setString(2, perfil.getNombre_perfil());
            stmt.setString(3, perfil.getDescripcion_perfil());
            stmt.setInt(4, perfil.getEstado_perfil());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }        
        return rows;
    }
    
    public int update(Perfil perfil){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            //System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, perfil.getPk_id_perfil());
            stmt.setString(2, perfil.getNombre_perfil());
            stmt.setString(3, perfil.getDescripcion_perfil());
            stmt.setInt(4, perfil.getEstado_perfil());
            stmt.setInt(5, perfil.getPk_id_perfil());
            rows = stmt.executeUpdate();
            //System.out.println("Registros actualizado:" + rows);
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return rows;
    }
    
    public int delete(Perfil perfil){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, perfil.getPk_id_perfil());
            rows = stmt.executeUpdate();
            //System.out.println("Registros eliminados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return rows;
    }
      public Perfil query(Perfil perfil){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;        
        int rows = 0; 
        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, perfil.getPk_id_perfil());
            rs = stmt.executeQuery();
            while(rs.next()){
            int Pk_id_perfil = rs.getInt("Pk_id_perfil");
            String nombre_perfil = rs.getString("nombre_perfil");
            String descripcion_perfil = rs.getString("descripcion_perfil");
            int estado_perfil = rs.getInt("estado_perfil");
                
                perfil = new Perfil();
                perfil.setPk_id_perfil(Pk_id_perfil);
                perfil.setNombre_perfil(nombre_perfil);
                perfil.setDescripcion_perfil(descripcion_perfil);
                perfil.setEstado_perfil(estado_perfil);            
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }     
        return perfil;
    }   

     public List<Perfil> listar() {
        List<Perfil> perfil = new ArrayList <>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;   
        try {
            conn=Conexion.getConnection();
            stmt=conn.prepareStatement(SQL_SELECT);
            rs=stmt.executeQuery();
            while (rs.next()) {
            Perfil usr = new Perfil();
            usr.setPk_id_perfil(rs.getInt(1));
            usr.setNombre_perfil(rs.getString(2));
            usr.setDescripcion_perfil(rs.getString(3));
            usr.setEstado_perfil(rs.getInt(4));
            perfil.add(usr);
             }
         }catch (Exception e){
         }
         return perfil;
     }
}
