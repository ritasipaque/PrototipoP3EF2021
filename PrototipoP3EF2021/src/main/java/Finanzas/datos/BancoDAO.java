/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Finanzas.datos;

import Finanzas.dominio.Banco;
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
public class BancoDAO {
 private static final String SQL_SELECT = "SELECT Codigo_Banco, Nombre_Banco, Clave_Banco, Telefono_Banco FROM Banco";
 private static final String SQL_INSERT = "INSERT INTO Banco VALUES(?, ?, ?, ?)";
 private static final String SQL_DELETE = "DELETE FROM Banco WHERE Codigo_Banco=?";
 private static final String SQL_UPDATE = "UPDATE Banco SET Codigo_Banco=?, Nombre_Banco=?, Clave_Banco=?, Telefono_Banco=? WHERE Codigo_Banco = ?";
 private static final String SQL_QUERY = "SELECT Codigo_Banco, Nombre_Banco, Clave_Banco, Telefono_Banco FROM Banco WHERE Codigo_Banco = ?";
 
    public int insert(Banco banco){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, banco.getCodigo_Banco());
            stmt.setString(2, banco.getNombre_Banco());
            stmt.setString(3, banco.getClave_Banco());
            stmt.setString(4, banco.getTelefono_Banco());
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
 
 public int update(Banco banco){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            //System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, banco.getCodigo_Banco());
            stmt.setString(2, banco.getNombre_Banco());
            stmt.setString(3, banco.getClave_Banco());
            stmt.setString(4, banco.getTelefono_Banco());
            stmt.setString(5, banco.getCodigo_Banco());
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
  public int delete(Banco banco){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, banco.getCodigo_Banco());
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
      public Banco query(Banco banco){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;        
        int rows = 0; 
        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, banco.getCodigo_Banco());
            rs = stmt.executeQuery();
            while(rs.next()){
            String Codigo_Banco = rs.getString("Codigo_Banco");
            String Nombre_Banco = rs.getString("Nombre_Banco");
            String Clave_Banco = rs.getString("Clave_Banco");
            String Telefono_Banco = rs.getString("Telefono_Banco");
                
                banco = new Banco();
                banco.setCodigo_Banco(Codigo_Banco);
                banco.setNombre_Banco(Nombre_Banco);
                banco.setClave_Banco(Clave_Banco);
                banco.setTelefono_Banco(Telefono_Banco);            
            }
  
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }     
        return banco;
    }   

     public List<Banco> listar() {
        List<Banco> banco = new ArrayList <>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;   
        try {
            conn=Conexion.getConnection();
            stmt=conn.prepareStatement(SQL_SELECT);
            rs=stmt.executeQuery();
            while (rs.next()) {
            Banco usr = new Banco();
            usr.setCodigo_Banco(rs.getString(1));
            usr.setNombre_Banco(rs.getString(2));
            usr.setClave_Banco(rs.getString(3));
            usr.setTelefono_Banco(rs.getString(4));
            banco.add(usr);
             }
         }catch (Exception e){
         }
         return banco;
     }
}
