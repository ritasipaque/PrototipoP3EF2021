
package Finanzas.datos;

import Finanzas.dominio.EmisionCheque;
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
public class EmisionChequeDAO {
    
   private static final String SQL_SELECT = "SELECT Numero_Cheque, Fecha_Cheque, FK_Banco, FK_Cuenta, FK_Cuentahabiente, Monto_Cheque  FROM cheque";
   private static final String SQL_INSERT = "INSERT INTO  cheque VALUES(?,?,?,?,?,?)";
   private static final String SQL_DELETE = "DELETE FROM  cheque   WHERE Numero_Cheque =?";
   private static final String SQL_UPDATE = "UPDATE  cheque  SET  Numero_Cheque=?,  Fecha_Cheque=?, FK_Banco=?, FK_Cuenta=?, FK_Cuentahabiente=?, Monto_Cheque=?  WHERE    Numero_Cheque= ?";
   private static final String SQL_QUERY = "SELECT   Numero_Cheque,  Fecha_Cheque,  FK_Banco,  FK_Cuenta,  FK_Cuentahabiente,  Monto_Cheque  FROM cheque  WHERE    Numero_Cheque= ?";
   
   
     public int insert(EmisionCheque emisioncheque){
     Connection conn = null;
     PreparedStatement stmt = null;
     int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, emisioncheque.getNumero_Cheque());
            stmt.setString(2, emisioncheque.getFecha_Cheque());
            stmt.setString(3, emisioncheque.getFK_Banco());
            stmt.setString(4, emisioncheque.getFK_Cuenta());
            stmt.setString(5, emisioncheque.getFK_Cuentahabiente());
            stmt.setString(6, emisioncheque.getMonto_Cheque());
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
 
public int update(EmisionCheque emisioncheque){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            //System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, emisioncheque.getNumero_Cheque());
            stmt.setString(2, emisioncheque.getFecha_Cheque());
            stmt.setString(3, emisioncheque.getFK_Banco());
            stmt.setString(4, emisioncheque.getFK_Cuenta());
            stmt.setString(5, emisioncheque.getFK_Cuentahabiente());
            stmt.setString(6, emisioncheque.getMonto_Cheque());
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

  public int delete(EmisionCheque emisioncheque){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;       
        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, emisioncheque.getNumero_Cheque());
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
  
      public EmisionCheque query(EmisionCheque emisioncheque){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;        
        int rows = 0; 
        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, emisioncheque.getNumero_Cheque());
            rs = stmt.executeQuery();
            while(rs.next()){
            String Numero_Cheque = rs.getString("Numero_Cheque");
            String Fecha_Cheque = rs.getString("Fecha_Cheque");
            String FK_Banco = rs.getString("FK_Banco");
            String FK_Cuenta = rs.getString("FK_Cuenta");
            String FK_Cuentahabiente = rs.getString("FK_Cuentahabiente");
            String Monto_Cheque = rs.getString("Monto_Cheque");
                    
               emisioncheque = new EmisionCheque();
               emisioncheque.setNumero_Cheque(Numero_Cheque);
               emisioncheque.setFecha_Cheque(Fecha_Cheque);  
               emisioncheque.setFK_Banco(FK_Banco);
               emisioncheque.setFK_Cuenta(FK_Cuenta);
               emisioncheque.setFK_Cuentahabiente(FK_Cuentahabiente);
               emisioncheque.setMonto_Cheque(Monto_Cheque);
            }
  
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }     
        return emisioncheque;
    }   

     public List<EmisionCheque> listar() {
        List<EmisionCheque> emisioncheque = new ArrayList <EmisionCheque>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //EmisionCheque tipos = null;
        
        try {
            conn=Conexion.getConnection();
            stmt=conn.prepareStatement(SQL_SELECT);
            rs=stmt.executeQuery();
            while (rs.next()) {
            String Numero_Cheque = rs.getString("Numero_Cheque");
            String Fecha_Cheque = rs.getString("Fecha_Cheque");
            String FK_Banco = rs.getString("FK_Banco");
            String FK_Cuenta = rs.getString("FK_Cuenta");
           String FK_Cuentahabiente = rs.getString("FK_Cuentahabiente");
            String Monto_Cheque = rs.getString("Monto_Cheque");
            
            
            EmisionCheque usr = new EmisionCheque();
            usr.setNumero_Cheque(rs.getString(1));
            usr.setFecha_Cheque(rs.getString(2));
            usr.setFK_Banco(rs.getString(3));
            usr.setFK_Cuenta(rs.getString(4));
            usr.setFK_Cuentahabiente(rs.getString(5));
            usr.setMonto_Cheque(rs.getString(6));
            emisioncheque.add(usr);
             }
         }catch (Exception e){
            // System.out.println(e);
         } finally {
            Conexion.close(conn);
            Conexion.close(stmt);
            Conexion.close(rs);
        }
         return emisioncheque;
     }
     
    
    
}
