

package Finanzas.datos;
import Finanzas.datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Finanzas.dominio.CuentaHabiente;
/**
 * 
 * @author Santiago Martinez Diaz
 */
public class CuentaHabienteDAO {
      private static final String sql_select = "SELECT Codigo_CuentaHabiente, Nombre_CuentaHabiente, ApellidoP_CuentaHabiente,ApellidoM_CuentaHabiente,TipoPersona_CuentaHabiente,Saldo_Habilitado FROM CuentaHabiente";
    private static final String sql_insert = "INSERT INTO CuentaHabiente(Codigo_CuentaHabiente, Nombre_CuentaHabiente, ApellidoP_CuentaHabiente,ApellidoM_CuentaHabiente,TipoPersona_CuentaHabiente,Saldo_Habilitado) VALUES(?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE CuentaHabiente SET Codigo_CuentaHabiente=?, Nombre_CuentaHabiente=?, ApellidoP_CuentaHabiente=?,ApellidoM_CuentaHabiente=?,Saldo_Habilitado=? WHERE Codigo_CuentaHabiente = ?";
    private static final String sql_delete = "DELETE FROM CuentaHabiente WHERE Codigo_CuentaHabiente=?";
    private static final String sql_query = "SELECT Codigo_CuentaHabiente, Nombre_CuentaHabiente, ApellidoP_CuentaHabiente,ApellidoM_CuentaHabiente,TipoPersona_CuentaHabiente,Saldo_Habilitado FROM CuentaHabiente WHERE Codigo_CuentaHabiente=?";

    public List<CuentaHabiente> listar() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        CuentaHabiente cuenta = null;
        List<CuentaHabiente> tipo1 = new ArrayList<>();

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(sql_select);
            rs = stmt.executeQuery();

            while (rs.next()) {
               String Codigo_CuentaHabiente = rs.getString("Codigo_CuentaHabiente");
                String Nombre_CuentaHabiente = rs.getString("Nombre_CuentaHabiente");
                String ApellidoP_CuentaHabiente = rs.getString("ApellidoP_CuentaHabiente");
                String ApellidoM_CuentaHabiente = rs.getString("ApellidoM_CuentaHabiente");
                String TipoPersona_CuentaHabiente = rs.getString("TipoPersona_CuentaHabiente");
                  String Saldo_Habilitado = rs.getString("Saldo_Habilitado");      
                cuenta = new CuentaHabiente();
               cuenta.setCodigo_CuentaHabiente(Codigo_CuentaHabiente);
                cuenta.setNombre_CuentaHabiente(Nombre_CuentaHabiente);
                cuenta.setApellidoP_CuentaHabiente(ApellidoP_CuentaHabiente);
                cuenta.setApellidoM_CuentaHabiente(ApellidoM_CuentaHabiente);
                cuenta.setTipoPersona_CuentaHabiente(TipoPersona_CuentaHabiente);
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

    public int insert(CuentaHabiente cuenta) {
        Connection con = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(sql_insert);
            stmt.setString(1, cuenta.getCodigo_CuentaHabiente());
            stmt.setString(2, cuenta.getNombre_CuentaHabiente());
            stmt.setString(3, cuenta.getApellidoP_CuentaHabiente());
            stmt.setString(4, cuenta.getApellidoM_CuentaHabiente());
            stmt.setString(5, cuenta.getTipoPersona_CuentaHabiente());
            stmt.setString(6, cuenta.getSaldo_Habilitado());
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

      public int update(CuentaHabiente cuenta){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            //System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
          stmt.setString(1, cuenta.getCodigo_CuentaHabiente());
          stmt.setString(2, cuenta.getNombre_CuentaHabiente());
          stmt.setString(3, cuenta.getApellidoP_CuentaHabiente());
          stmt.setString(4, cuenta.getApellidoM_CuentaHabiente());
          stmt.setString(6, cuenta.getSaldo_Habilitado());
          stmt.setString(7, cuenta.getCodigo_CuentaHabiente());
 
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

    public int delete(CuentaHabiente cuenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(sql_delete);
             stmt.setString(1, cuenta.getCodigo_CuentaHabiente());
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

    public CuentaHabiente query(CuentaHabiente cuenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<CuentaHabiente> tipotr = new ArrayList<CuentaHabiente>();
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(sql_query);
            stmt.setString(1, cuenta.getCodigo_CuentaHabiente());
            rs = stmt.executeQuery();
            while (rs.next()) {
               String Codigo_CuentaHabiente = rs.getString("Codigo_CuentaHabiente");
                String Nombre_CuentaHabiente = rs.getString("Nombre_CuentaHabiente");
                String ApellidoP_CuentaHabiente = rs.getString("ApellidoP_CuentaHabiente");
                String ApellidoM_CuentaHabiente = rs.getString("ApellidoM_CuentaHabiente");
                String TipoPersona_CuentaHabiente = rs.getString("TipoPersona_CuentaHabiente");
                String Saldo_Habilitado = rs.getString("Saldo_Habilitado");     

                cuenta = new CuentaHabiente();
                cuenta.setCodigo_CuentaHabiente(Codigo_CuentaHabiente);
                cuenta.setNombre_CuentaHabiente(Nombre_CuentaHabiente);
                cuenta.setApellidoP_CuentaHabiente(ApellidoP_CuentaHabiente);
                cuenta.setApellidoM_CuentaHabiente(ApellidoM_CuentaHabiente);
                cuenta.setTipoPersona_CuentaHabiente(TipoPersona_CuentaHabiente);
                cuenta.setSaldo_Habilitado(Saldo_Habilitado);
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
