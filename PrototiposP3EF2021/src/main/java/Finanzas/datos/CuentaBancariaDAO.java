

package Finanzas.datos;
import Finanzas.datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Finanzas.dominio.CuentaBancaria;


/**
 * 
 * @author Santiago Martinez Diaz
 * 
 */
public class CuentaBancariaDAO {
    private static final String sql_select = "SELECT Numero_CuentaBancaria, Moneda_Cuenta, CuentaHabiente_Cuenta,Banco_Cuenta,Saldo_Cuenta FROM CuentaBancaria";
    private static final String sql_insert = "INSERT INTO CuentaBancaria(Numero_CuentaBancaria, Moneda_Cuenta, CuentaHabiente_Cuenta,Banco_Cuenta,Saldo_Cuenta) VALUES(?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE CuentaBancaria SET Numero_CuentaBancaria=?, Moneda_Cuenta=?, CuentaHabiente_Cuenta=?,Banco_Cuenta=?,Saldo_Cuenta=?, WHERE Numero_CuentaBancaria = ?";
    private static final String sql_delete = "DELETE FROM CuentaBancaria WHERE Numero_CuentaBancaria=?";
    private static final String sql_query = "SELECT Numero_CuentaBancaria, Moneda_Cuenta, CuentaHabiente_Cuenta,Banco_Cuenta FROM TipoTransaccion WHERE CuentaBancaria=?";

    public List<CuentaBancaria> listar()  {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        CuentaBancaria cuenta = null;
        List<CuentaBancaria> tipo1 = new ArrayList<>();

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(sql_select);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String Numero_CuentaBancaria = rs.getString("Numero_CuentaBancaria");
                String Moneda_Cuenta = rs.getString("Moneda_Cuenta");
                String CuentaHabiente_Cuenta = rs.getString("CuentaHabiente_Cuenta");
                String Banco_Cuenta = rs.getString("Banco_Cuenta");
                String Saldo_Cuenta = rs.getString("Saldo_Cuenta");
 
                cuenta = new CuentaBancaria();
               cuenta.setNumero_CuentaBancaria(Numero_CuentaBancaria);
                cuenta.setMoneda_Cuenta(Moneda_Cuenta);
                cuenta.setCuentaHabiente_Cuenta(CuentaHabiente_Cuenta);
                cuenta.setBanco_Cuenta(Banco_Cuenta);
                cuenta.setSaldo_Cuenta(Saldo_Cuenta);
             

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

    public int insert(CuentaBancaria cuenta) {
        Connection con = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(sql_insert);
            stmt.setString(1, cuenta.getNumero_CuentaBancaria());
            stmt.setString(2, cuenta.getMoneda_Cuenta());
            stmt.setString(3, cuenta.getCuentaHabiente_Cuenta());
            stmt.setString(4, cuenta.getBanco_Cuenta());
            stmt.setString(5, cuenta.getSaldo_Cuenta());
            rows = stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Registro exitoso");
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);
        }
        return rows;
    }

      public int update(CuentaBancaria cuenta){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            //System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
          stmt.setString(1, cuenta.getNumero_CuentaBancaria());
          stmt.setString(2, cuenta.getMoneda_Cuenta());
          stmt.setString(3, cuenta.getCuentaHabiente_Cuenta());
          stmt.setString(4, cuenta.getBanco_Cuenta());
          stmt.setString(5, cuenta.getSaldo_Cuenta());
          stmt.setString(6, cuenta.getNumero_CuentaBancaria());
 
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

    public int delete(CuentaBancaria cuenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(sql_delete);
             stmt.setString(1, cuenta.getNumero_CuentaBancaria());
            rows = stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, " Eliminado Con Exito");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public CuentaBancaria query(CuentaBancaria cuenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<CuentaBancaria> tipotr = new ArrayList<CuentaBancaria>();
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(sql_query);
            stmt.setString(1, cuenta.getNumero_CuentaBancaria());
            rs = stmt.executeQuery();
            while (rs.next()) {
                String Numero_CuentaBancaria = rs.getString("Numero_CuentaBancaria");
                String Moneda_Cuenta = rs.getString("Moneda_Cuenta");
                String CuentaHabiente_Cuenta = rs.getString("CuentaHabiente_Cuenta");
                String Banco_Cuenta = rs.getString("Banco_Cuenta");
                String Saldo_Cuenta = rs.getString("Saldo_Cuenta");

                cuenta = new CuentaBancaria();
                cuenta.setNumero_CuentaBancaria(Numero_CuentaBancaria);
                cuenta.setMoneda_Cuenta(Moneda_Cuenta);
                cuenta.setCuentaHabiente_Cuenta(CuentaHabiente_Cuenta);
                cuenta.setBanco_Cuenta(Banco_Cuenta);
                cuenta.setSaldo_Cuenta(Saldo_Cuenta);
                rows++;
            }

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return cuenta;
    }





}
