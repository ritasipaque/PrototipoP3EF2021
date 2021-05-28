

package Finanzas.datos;

import Finanzas.dominio.CuentaBancaria;
import Finanzas.dominio.CuentaHabiente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Finanzas.dominio.Transaccion;
import java.sql.SQLException;



public class TransaccionesDAO {
private static final String SQL_SELECT = "SELECT Codigo_CuentaHabiente, Nombre_CuentaHabiente, ApellidoP_CuentaHabiente,Saldo_Habilitado FROM CuentaHabiente where Codigo_CuentaHabiente = Codigo_CuentaHabiente and Codigo_CuentaHabiente = ?";
 private static final String SQL_INSERT = "Insert into deposito(Codigo_CuentaHabiente,Balance,Transaccion,fecha)values(?,?,?,?)";
  private static final String SQL_INSERT2 = "Insert into deposito(Codigo_CuentaHabiente,Balance,Transaccion,fecha)values(?,?,?,?)";
private static final String SQL_UPDATE = "update CuentaHabiente set Saldo_Habilitado= Saldo_Habilitado + ? where Codigo_CuentaHabiente=?";
 private static final String SQL_UPDATE2 = "update CuentaHabiente set Saldo_Habilitado= Saldo_Habilitado - ? where Codigo_CuentaHabiente=?";
public static final String SQL_QUERY2 = "SELECT  Codigo_CuentaHabiente, Nombre_CuentaHabiente, ApellidoP_CuentaHabiente,Saldo_Habilitado FROM CuentaHabiente where Codigo_CuentaHabiente = Codigo_CuentaHabiente and Codigo_CuentaHabiente = ?";
private static final String sql_query = "SELECT  Codigo_CuentaHabiente, Nombre_CuentaHabiente, ApellidoP_CuentaHabiente,Saldo_Habilitado FROM CuentaHabiente where Codigo_CuentaHabiente = Codigo_CuentaHabiente and Codigo_CuentaHabiente = ?";
private static final String sql_query2 = "SELECT  Numero_CuentaBancaria, CuentaHabiente_Cuenta, Banco_Cuenta,Saldo_Cuenta FROM CuentaHabiente where Numero_CuentaBancaria = Numero_CuentaBancaria and Numero_CuentaBancaria = ?";
   public List<CuentaHabiente> listar()  {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        CuentaHabiente cuenta = null;
        List<CuentaHabiente> tipo1 = new ArrayList<>();

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String Codigo_CuentaHabiente = rs.getString("Codigo_CuentaHabiente");
                String Nombre_CuentaHabiente = rs.getString("Nombre_CuentaHabiente");
                String ApellidoP_CuentaHabiente = rs.getString("ApellidoP_CuentaHabiente");
                String Saldo_Habilitado = rs.getString("Saldo_Habilitado");
 
                cuenta = new CuentaHabiente();
               cuenta.setCodigo_CuentaHabiente(Codigo_CuentaHabiente);
                cuenta.setNombre_CuentaHabiente(Nombre_CuentaHabiente);
                cuenta.setApellidoP_CuentaHabiente(ApellidoP_CuentaHabiente);
                cuenta.setSaldo_Habilitado(Saldo_Habilitado);
             

                tipo1.add(cuenta);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);
            Conexion.close(rs);
        }
        return tipo1;
    }
   
   
  public int insert(Transaccion cuenta) {
        Connection con = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_INSERT);
            stmt.setString(1, cuenta.getCodigo_CuentaHabiente());
            stmt.setString(2, cuenta.getBalance());
            stmt.setString(3, cuenta.getTransaccion());
            stmt.setString(4, cuenta.getFecha());

            rows = stmt.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);
        }
        return rows;
    }

  public int update(Transaccion emisioncheque){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
 
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, emisioncheque.getTransaccion());
            stmt.setString(2, emisioncheque.getCodigo_CuentaHabiente());
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
  
   public int update2(Transaccion emisioncheque){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE2);
            stmt.setString(1, emisioncheque.getTransaccion());
            stmt.setString(2, emisioncheque.getCodigo_CuentaHabiente());
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
  
  

       public CuentaHabiente query(CuentaHabiente tipot) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
 
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(sql_query);
            stmt.setString(1, tipot.getCodigo_CuentaHabiente());
            rs = stmt.executeQuery();
            while (rs.next()) {
                 String Nombre_CuentaHabiente = rs.getString("Nombre_CuentaHabiente");
                String ApellidoP_CuentaHabiente = rs.getString("ApellidoP_CuentaHabiente");
                String Saldo_Habilitado = rs.getString("Saldo_Habilitado");

                tipot = new CuentaHabiente();
                tipot.setNombre_CuentaHabiente(Nombre_CuentaHabiente);
                tipot.setApellidoP_CuentaHabiente(ApellidoP_CuentaHabiente);
                tipot.setSaldo_Habilitado(Saldo_Habilitado);
                rows++;
            }

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return tipot;
    }

  

       public CuentaBancaria query(CuentaBancaria tipot) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
 
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(sql_query2);
            stmt.setString(1, tipot.getNumero_CuentaBancaria());
            rs = stmt.executeQuery();
            while (rs.next()) {
                String CuentaHabiente_Cuenta = rs.getString("CuentaHabiente_Cuenta");
                String Banco_Cuenta = rs.getString("Banco_Cuenta");
                String Saldo_Cuenta = rs.getString("Saldo_Cuenta");

                tipot = new CuentaBancaria();
                tipot.setCuentaHabiente_Cuenta(CuentaHabiente_Cuenta);
                tipot.setBanco_Cuenta(Banco_Cuenta);
                tipot.setSaldo_Cuenta(Saldo_Cuenta);
                rows++;
            }

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return tipot;
    }

  
}
