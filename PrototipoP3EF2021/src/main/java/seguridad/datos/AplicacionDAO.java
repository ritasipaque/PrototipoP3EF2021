/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad.datos;

import seguridad.dominio.Aplicacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import seguridad.vista.Aplicacion_Perfil;

/**
 *
 * @author visitante
 */
public class AplicacionDAO {

    //Buscar en la BD
    private static final String SQL_SELECT = "SELECT PK_id_aplicacion, nombre_aplicacion, descripcion_aplicacion, no_reporteAsociado, estado_aplicacion FROM tbl_aplicacion";
    //Insertar en la BD
    private static final String SQL_INSERT = "INSERT INTO tbl_aplicacion(PK_id_aplicacion, nombre_aplicacion, descripcion_aplicacion, no_reporteAsociado, estado_aplicacion) VALUES(?, ?, ?, ?, ?)";
    //Modificar la BD
    private static final String SQL_UPDATE = "UPDATE tbl_aplicacion SET nombre_aplicacion=?, descripcion_aplicacion=?, no_reporteAsociado=?, estado_aplicacion=? WHERE PK_id_aplicacion = ?";
    //Eliminar de la BD
    private static final String SQL_DELETE = "DELETE FROM tbl_aplicacion WHERE PK_id_aplicacion=?";
    //Buscar 2 en la BD
    public static final String SQL_QUERY = "SELECT PK_id_aplicacion, nombre_aplicacion, descripcion_aplicacion, no_reporteAsociado, estado_aplicacion FROM tbl_aplicacion WHERE PK_id_aplicacion = ?";
    //Buscar 2 en la BD
    //public static final String SQL_QUERY2 = "SELECT PK_id_Modulo FROM tbl_modulo";
     private static final String SQL_SELECT2 = "SELECT PK_id_aplicacion, PK_id_modulo, nombre_aplicacion, descripcion_aplicacion, no_reporteAsociado, estado_aplicacion FROM tbl_aplicacion";

    public List<Aplicacion> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Aplicacion aplicacion = null;
        List<Aplicacion> aplicaciones = new ArrayList<Aplicacion>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_Aplicacion = rs.getInt("PK_id_aplicacion");
                //int id_Modulo = rs.getInt("PK_id_modulo");
                String nombre_Aplicacion = rs.getString("nombre_aplicacion");
                String Descripcion_Aplicacion = rs.getString("descripcion_aplicacion");
                int reporteAsociado_Aplicacion = rs.getInt("no_reporteAsociado");
                int estado_Aplicacion = rs.getInt("estado_aplicacion");

                aplicacion = new Aplicacion();
                aplicacion.setId_Aplicacion(id_Aplicacion);
                //aplicacion.setId_Modulo(id_Modulo);
                aplicacion.setNombre_Aplicacion(nombre_Aplicacion);
                aplicacion.setDescripcion_Aplicacion(Descripcion_Aplicacion);
                aplicacion.setReporteAsociado_Aplicacion(reporteAsociado_Aplicacion);
                aplicacion.setEstado_Aplicacion(estado_Aplicacion);

                aplicaciones.add(aplicacion);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return aplicaciones;
    }

    public int insert(Aplicacion aplicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, aplicacion.getId_Aplicacion());
            //stmt.setInt(2, aplicacion.getId_Modulo());
            stmt.setString(2, aplicacion.getNombre_Aplicacion());
            stmt.setString(3, aplicacion.getDescripcion_Aplicacion());
            stmt.setInt(4, aplicacion.getReporteAsociado_Aplicacion());
            stmt.setInt(5, aplicacion.getEstado_Aplicacion());

            //System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            //System.out.println("Registros afectados:" + rows);
            JOptionPane.showMessageDialog(null, "Registro Exitoso");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int update(Aplicacion aplicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            //System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            //stmt.setInt(1, aplicacion.getId_Modulo());
            stmt.setString(1, aplicacion.getNombre_Aplicacion());
            stmt.setString(2, aplicacion.getDescripcion_Aplicacion());
            stmt.setInt(3, aplicacion.getReporteAsociado_Aplicacion());
            stmt.setInt(4, aplicacion.getEstado_Aplicacion());
            stmt.setInt(5, aplicacion.getId_Aplicacion());
            rows = stmt.executeUpdate();
            //System.out.println("Registros actualizado:" + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int delete(Aplicacion aplicacion) {

        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, aplicacion.getId_Aplicacion());
            rows = stmt.executeUpdate();
            //System.out.println("Registros eliminados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }
    //    public List<Persona> query(Persona persona) { // Si se utiliza un ArrayList

    public Aplicacion query(Aplicacion aplicacion) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Aplicacion> aplicaciones = new ArrayList<Aplicacion>();
        int rows = 0;

        try {

            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, aplicacion.getId_Aplicacion());
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id_Aplicacion = rs.getInt("PK_id_aplicacion");
                //int id_Modulo = rs.getInt("PK_id_modulo");
                String nombre_Aplicacion = rs.getString("nombre_aplicacion");
                String Descripcion_Aplicacion = rs.getString("descripcion_aplicacion");
                int reporteAsociado_Aplicacion = rs.getInt("no_reporteAsociado");
                int estado_Aplicacion = rs.getInt("estado_aplicacion");

                aplicacion = new Aplicacion();
                aplicacion.setId_Aplicacion(id_Aplicacion);
                //aplicacion.setId_Modulo(id_Modulo);
                aplicacion.setNombre_Aplicacion(nombre_Aplicacion);
                aplicacion.setDescripcion_Aplicacion(Descripcion_Aplicacion);
                aplicacion.setReporteAsociado_Aplicacion(reporteAsociado_Aplicacion);
                aplicacion.setEstado_Aplicacion(estado_Aplicacion);
                rows++;
            }
            //System.out.println("Registros buscado:" + aplicacion);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return aplicacion;

    }

    /*
    public void query2(JComboBox cbxModulo) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY2);
            //stmt.setInt(1, aplicacion.getId_ModuloCbx());
            rs = stmt.executeQuery();

            cbxModulo.addItem("Seleccionar...");
            
            while (rs.next()) {
                cbxModulo.addItem(rs.getInt("PK_id_Modulo"));
            }
            //System.out.println("Registros buscado:" + aplicacion);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

    }*/
    
    public List<Aplicacion> select2(){
    Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Aplicacion a_app_usu = null;
        List<Aplicacion> personas = new ArrayList<Aplicacion>();
        
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int id_aplicacion = rs.getInt("PK_id_aplicacion");
                String nombre_aplicacion = rs.getString("nombre_aplicacion");
                
                
                a_app_usu = new Aplicacion();
                a_app_usu.setId_Aplicacion(id_aplicacion);
                a_app_usu.setNombre_Aplicacion(nombre_aplicacion);
                
                
                personas.add(a_app_usu);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return personas;
}

}
