/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad.datos;
import seguridad.dominio.Asignacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 *
 * @author familia Sipaque
 */
public class AsignacionDAO {
    
  
    //Insertar en la BD
    private static final String SQL_INSERT = "INSERT INTO tbl_aplicacion_a_modulos(PK_id_modulo, PK_id_aplicacion) VALUES(?,?)";
    private static final String SQL_SELECT = "SELECT PK_id_modulo, PK_id_aplicacion  FROM tbl_aplicacion_a_modulos";
    private static final String SQL_UPDATE = "UPDATE tbl_aplicacion_a_modulos SET  PK_id_modulo=?, PK_id_aplicacion=? WHERE  PK_id_modulo = ?";
    private static final String SQL_DELETE = "DELETE FROM tbl_aplicacion_a_modulos WHERE PK_id_modulo=?";
    private static final String SQL_QUERY = "SELECT PK_id_modulo, PK_id_aplicacion FROM tbl_aplicacion_a_modulos WHERE PK_id_modulo = ?";

    public List<Asignacion> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Asignacion vendedor = null;
        List<Asignacion> vendedores = new ArrayList<Asignacion>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                
                String PK_id_modulo = rs.getString("PK_id_modulo");
                String PK_id_aplicacion = rs.getString("PK_id_aplicacion");
                
                vendedor = new Asignacion();
                vendedor.setPK_id_modulo(PK_id_modulo);
                vendedor.setPK_id_aplicacion(PK_id_aplicacion);
               
                
                vendedores.add(vendedor);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return vendedores;
    }

    public int insert(Asignacion vendedor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, vendedor.getPK_id_modulo());
            stmt.setString(2, vendedor.getPK_id_aplicacion());


            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    
}
