/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Finanzas.datos;

import Finanzas.dominio.Moneda;
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
public class MonedaDAO {
    
    private static final String SQL_SELECT = "SELECT Codigo_Moneda, Nombre_Moneda, Simbolo_Moneda FROM Moneda";
    private static final String SQL_INSERT = "INSERT INTO Moneda VALUES(?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM Moneda WHERE Codigo_Moneda=?";
    private static final String SQL_UPDATE = "UPDATE Moneda SET Codigo_Moneda=?, Nombre_Moneda=?, Simbolo_Moneda=? WHERE Codigo_Moneda = ?";
    private static final String SQL_QUERY = "SELECT Codigo_Moneda, Nombre_Moneda, Simbolo_Moneda FROM Moneda WHERE Codigo_Moneda = ?";
   
     public int insert(Moneda moneda){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, moneda.getCodigo_Moneda());
            stmt.setString(2, moneda.getNombre_Moneda());
            stmt.setString(3, moneda.getSimbolo_Moneda());
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
 
 public int update(Moneda moneda){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            //System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, moneda.getCodigo_Moneda());
            stmt.setString(2, moneda.getNombre_Moneda());
            stmt.setString(3, moneda.getSimbolo_Moneda());
            stmt.setString(4, moneda.getCodigo_Moneda());
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
  public int delete(Moneda moneda){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, moneda.getCodigo_Moneda());
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
      public Moneda query(Moneda moneda){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;        
        int rows = 0; 
        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, moneda.getCodigo_Moneda());
            rs = stmt.executeQuery();
            while(rs.next()){
            String Codigo_Moneda = rs.getString("Codigo_Moneda");
            String Nombre_Moneda = rs.getString("Nombre_Moneda");
            String Simbolo_Moneda = rs.getString("Simbolo_Moneda");
                    
                moneda = new Moneda();
                moneda.setCodigo_Moneda(Codigo_Moneda);
                moneda.setNombre_Moneda(Nombre_Moneda);
                moneda.setSimbolo_Moneda(Simbolo_Moneda);         
            }
  
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }     
        return moneda;
    }   

     public List<Moneda> listar() {
        List<Moneda> moneda = new ArrayList <>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;   
        try {
            conn=Conexion.getConnection();
            stmt=conn.prepareStatement(SQL_SELECT);
            rs=stmt.executeQuery();
            while (rs.next()) {
            Moneda usr = new Moneda();
            usr.setCodigo_Moneda(rs.getString(1));
            usr.setNombre_Moneda(rs.getString(2));
            usr.setSimbolo_Moneda(rs.getString(3));
            moneda.add(usr);
             }
         }catch (Exception e){
         }
         return moneda;
     }
    
    
    
    
    
}
