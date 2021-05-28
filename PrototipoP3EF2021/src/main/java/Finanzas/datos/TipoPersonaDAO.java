/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Finanzas.datos;

import Finanzas.dominio.TipoPersona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nay Ale
 */
public class TipoPersonaDAO extends Conexion {
    
    private static final String SQL_SELECT = "SELECT Codigo_TipoPersona, TipoPersona_Nombres FROM TipoPersona";
    private static final String SQL_INSERT = "INSERT INTO TipoPersona VALUES(?, ?)";
    private static final String SQL_DELETE = "DELETE FROM TipoPersona WHERE Codigo_TipoPersona=?";
    private static final String SQL_UPDATE = "UPDATE TipoPersona SET Codigo_TipoPersona=?, TipoPersona_Nombres=? WHERE Codigo_TipoPersona = ?";
    private static final String SQL_QUERY = "SELECT Codigo_TipoPersona, TipoPersona_Nombres FROM TipoPersona WHERE Codigo_TipoPersona = ?";
   
     public int insert(TipoPersona tipoPersona){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, tipoPersona.getCodigo_TipoPersona());
            stmt.setString(2, tipoPersona.getTipoPersona_Nombres());
            
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
 
 public int update(TipoPersona tipoPersona){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            //System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, tipoPersona.getCodigo_TipoPersona());
            stmt.setString(2, tipoPersona.getTipoPersona_Nombres());
            stmt.setString(3, tipoPersona.getCodigo_TipoPersona());
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
  public int delete(TipoPersona tipoPersona){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, tipoPersona.getCodigo_TipoPersona());
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
      public TipoPersona query(TipoPersona tipoPersona){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;        
        int rows = 0; 
        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, tipoPersona.getCodigo_TipoPersona());
            rs = stmt.executeQuery();
            while(rs.next()){
            String Codigo_TipoPersona = rs.getString("Codigo_TipoPersona");
            String TipoPersona_Nombres = rs.getString("TipoPersona_Nombres");
                    
                tipoPersona = new TipoPersona();
                tipoPersona.setCodigo_TipoPersona(Codigo_TipoPersona);
                tipoPersona.setTipoPersona_Nombres(TipoPersona_Nombres);        
            }
  
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }     
        return tipoPersona;
    }   

     public List<TipoPersona> listar() {
        List<TipoPersona> tipoPersona = new ArrayList <>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;   
        try {
            conn=Conexion.getConnection();
            stmt=conn.prepareStatement(SQL_SELECT);
            rs=stmt.executeQuery();
            while (rs.next()) {
            TipoPersona usr = new TipoPersona();
            usr.setCodigo_TipoPersona(rs.getString(1));
            usr.setTipoPersona_Nombres(rs.getString(2));
            tipoPersona.add(usr);
             }
         }catch (Exception e){
         }
         return tipoPersona;
     }
    
}
