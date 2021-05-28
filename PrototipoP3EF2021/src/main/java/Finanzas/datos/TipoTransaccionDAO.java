
package Finanzas.datos;

import Finanzas.datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Finanzas.dominio.TipoTransaccion;

/**
 * 
 * @author Santiago Martinez Diaz
 */
public class TipoTransaccionDAO {

     private static final String sql_select = "SELECT Codigo_TipoTransaccion, Transaccion_Tipo, Efecto_TipoTransaccion FROM TipoTransaccion";
    private static final String sql_insert = "INSERT INTO TipoTransaccion(Codigo_TipoTransaccion, Transaccion_Tipo, Efecto_TipoTransaccion) VALUES(?,?,?)";
    private static final String SQL_UPDATE = "UPDATE TipoTransaccion SET Codigo_TipoTransaccion=?, Transaccion_Tipo=?, Efecto_TipoTransaccion=? WHERE Codigo_TipoTransaccion = ?";
    private static final String sql_delete = "DELETE FROM TipoTransaccion WHERE Codigo_TipoTransaccion=?";
    private static final String sql_query = "SELECT Codigo_TipoTransaccion, Transaccion_Tipo, Efecto_TipoTransaccion FROM TipoTransaccion WHERE Codigo_TipoTransaccion=?";

    public List<TipoTransaccion> listar() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        TipoTransaccion tipos = null;
        List<TipoTransaccion> tipo1 = new ArrayList<TipoTransaccion>();

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(sql_select);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String Codigo_TipoTransaccion = rs.getString("Codigo_TipoTransaccion");
                String Transaccion_Tipo = rs.getString("Transaccion_Tipo");
                int Efecto_TipoTransaccion = rs.getInt("Efecto_TipoTransaccion");
 
                tipos = new TipoTransaccion();
               tipos.setCodigo_TipoTransaccion(Codigo_TipoTransaccion);
                tipos.setTransaccion_Tipo(Transaccion_Tipo);
                tipos.setEfecto_TipoTransaccion(Efecto_TipoTransaccion);

             

                tipo1.add(tipos);
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

    public int insert(TipoTransaccion tipo) {
        Connection con = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(sql_insert);
              stmt.setString(1, tipo.getCodigo_TipoTransaccion());
            stmt.setString(2, tipo.getTransaccion_Tipo());
            stmt.setInt(3, tipo.getEfecto_TipoTransaccion());

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

      public int update(TipoTransaccion tipo){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, tipo.getCodigo_TipoTransaccion());
            stmt.setString(2, tipo.getTransaccion_Tipo());
            stmt.setInt(3, tipo.getEfecto_TipoTransaccion());
          stmt.setString(4, tipo.getCodigo_TipoTransaccion());
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

    public int delete(TipoTransaccion tipo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(sql_delete);
             stmt.setString(1, tipo.getCodigo_TipoTransaccion());
            rows = stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, " Registro elminado con Exito");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public TipoTransaccion query(TipoTransaccion tipot) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<TipoTransaccion> tipotr = new ArrayList<TipoTransaccion>();
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(sql_query);
            stmt.setString(1, tipot.getCodigo_TipoTransaccion());
            rs = stmt.executeQuery();
            while (rs.next()) {
                String Codigo_TipoTransaccion = rs.getString("Codigo_TipoTransaccion");
                String Transaccion_Tipo = rs.getString("Transaccion_Tipo");
                int Efecto_TipoTransaccion = rs.getInt("Efecto_TipoTransaccion");


                tipot = new TipoTransaccion();
                tipot.setCodigo_TipoTransaccion(Codigo_TipoTransaccion);
                tipot.setTransaccion_Tipo(Transaccion_Tipo);
                tipot.setEfecto_TipoTransaccion(Efecto_TipoTransaccion);
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
