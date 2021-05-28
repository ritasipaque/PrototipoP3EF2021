/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comercial.datos;
import Comercial.dominio.Proveedor;
import Comercial.datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author familia Sipaque
 */
public class ProveedorDAO1 {
 private static final String SQL_SELECT = "SELECT PK_id_proveedor, nombre_proveedor, direccion_proveedor, contacto_proveedor, telefono_proveedor, nit_proveedor, email_proveedor, estatus_proveedor FROM tbl_proveedor";
 private static final String SQL_INSERT = "INSERT INTO tbl_proveedor (PK_id_proveedor, nombre_proveedor, direccion_proveedor, contacto_proveedor, telefono_proveedor, nit_proveedor, email_proveedor, estatus_proveedor) VALUES(?,?,?,?,?,?,?,?)";
// private static final String SQL_UPDATE = "UPDATE tbl_proveedor SET   nombre_proveedor= ?, direccion_proveedor= ?, contacto_proveedor= ?, telefono_proveedor= ?, nit_proveedor= ?,  email_proveedor= ?, estatus_proveedor= ?    WHERE PK_id_proveedor= ?";
 private static final String SQL_UPDATE = "UPDATE tbl_proveedor SET  PK_id_proveedor= ?, nombre_proveedor= ?, direccion_proveedor= ?, contacto_proveedor= ?, telefono_proveedor= ?,  nit_proveedor= ?, email_proveedor= ?,  estatus_proveedor= ?     WHERE PK_id_proveedor= ?";
 private static final String SQL_QUERY = "SELECT PK_id_proveedor, nombre_proveedor, direccion_proveedor, contacto_proveedor, telefono_proveedor, nit_proveedor, email_proveedor, estatus_proveedor FROM tbl_proveedor WHERE PK_id_proveedor= ?";
 private static final String SQL_DELETE = "DELETE FROM tbl_proveedor WHERE PK_id_proveedor=?";
  public static final String SQL_QUERY2 = "SELECT PK_id_proveedor FROM tbl_proveedor"; 
  public List<Proveedor> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Proveedor proveedor = null;
        List<Proveedor> proveedores = new ArrayList<Proveedor>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int PK_id_proveedor = rs.getInt("PK_id_proveedor");
                String nombre_proveedor = rs.getString("nombre_proveedor");
                String direccion_proveedor = rs.getString("direccion_proveedor");
                String contacto_proveedor = rs.getString("contacto_proveedor");
                String telefono_proveedor = rs.getString("telefono_proveedor");
                String nit_proveedor = rs.getString("nit_proveedor");
                String email_proveedor = rs.getString("email_proveedor");
                String estatus_proveedor = rs.getString("estatus_proveedor");
                   
                 
                proveedor = new Proveedor();
                proveedor.setPK_id_proveedor(PK_id_proveedor);
                proveedor.setNombre_proveedor(nombre_proveedor);
                proveedor.setDireccion_proveedor(direccion_proveedor);
                proveedor.setContacto_proveedor(contacto_proveedor);
                proveedor.setTelefono_proveedor(telefono_proveedor);
                proveedor.setNit_proveedor(nit_proveedor);
                proveedor.setEmail_proveedor(email_proveedor);
                proveedor.setEstatus_proveedor(estatus_proveedor);
                proveedores.add(proveedor);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return proveedores;
    }
 
 
    public int insert(Proveedor aplicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, aplicacion.getPK_id_proveedor());
            stmt.setString(2, aplicacion.getNombre_proveedor());
            stmt.setString(3, aplicacion.getDireccion_proveedor());
            stmt.setString(4, aplicacion.getContacto_proveedor());
            stmt.setString(5, aplicacion.getTelefono_proveedor());
            stmt.setString(6, aplicacion.getNit_proveedor());
            stmt.setString(7, aplicacion.getEmail_proveedor());
            stmt.setString(8, aplicacion.getEstatus_proveedor());
             
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
    public Proveedor query(Proveedor moduloC) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
//            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, moduloC.getPK_id_proveedor());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_proveedor = rs.getInt("PK_id_proveedor");
                String nombre = rs.getString("nombre_proveedor");
                String apellido = rs.getString("direccion_proveedor");
                String contacto = rs.getString("contacto_proveedor");
                String telefono = rs.getString("telefono_proveedor");
                String nit = rs.getString("nit_proveedor");
                 String email = rs.getString("email_proveedor");
                String estatus = rs.getString("estatus_proveedor");

                
                moduloC = new Proveedor();
                moduloC.setPK_id_proveedor(id_proveedor);
                moduloC.setNombre_proveedor(nombre);
                moduloC.setDireccion_proveedor(apellido);
                moduloC.setContacto_proveedor(contacto);
                moduloC.setTelefono_proveedor(telefono);
                moduloC.setNit_proveedor(nit);
                moduloC.setEmail_proveedor(email);
                moduloC.setEstatus_proveedor(estatus);
                
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return moduloC;
    }
     
    
    public int delete(Proveedor aplicacion) {

        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, aplicacion.getPK_id_proveedor());
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
 public int update(Proveedor aplicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            
            
             stmt.setInt(1, aplicacion.getPK_id_proveedor());
            stmt.setString(2, aplicacion.getNombre_proveedor());
            stmt.setString(3, aplicacion.getDireccion_proveedor());
            stmt.setString(4, aplicacion.getContacto_proveedor());
            stmt.setString(5, aplicacion.getTelefono_proveedor());
            stmt.setString(6, aplicacion.getNit_proveedor());
            stmt.setString(7, aplicacion.getEmail_proveedor());
            stmt.setString(8, aplicacion.getEstatus_proveedor());
            
            
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado:" + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
}
 public void query2(JComboBox txt_combox) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY2);
            //stmt.setInt(1, aplicacion.getId_ModuloCbx());
            rs = stmt.executeQuery();

            txt_combox.addItem("Seleccionar...");
            
            while (rs.next()) {
                txt_combox.addItem(rs.getInt("PK_id_proveedor"));
                
            }
            //System.out.println("Registros buscado:" + aplicacion);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    
    
    
 }
    
 public List<Proveedor> listar() {
        List<Proveedor> perfil = new ArrayList <>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;   
        try {
            conn=Conexion.getConnection();
            stmt=conn.prepareStatement(SQL_SELECT);
            rs=stmt.executeQuery();
            while (rs.next()) {
            Proveedor usr = new Proveedor();
            usr.setPK_id_proveedor(rs.getInt(1));
            usr.setNombre_proveedor(rs.getString(2));
            usr.setDireccion_proveedor(rs.getString(3));
           usr.setContacto_proveedor(rs.getString(4));
           usr.setTelefono_proveedor(rs.getString(5));
           usr.setNit_proveedor(rs.getString(6));
           usr.setEmail_proveedor(rs.getString(7));
           usr.setEstatus_proveedor(rs.getString(8));
       
            
            
//            stmt.setInt(1, aplicacion.getPK_id_proveedor());
//            stmt.setString(2, aplicacion.getNombre_proveedor());
//            stmt.setString(3, aplicacion.getDireccion_proveedor());
//            stmt.setString(4, aplicacion.getContacto_proveedor());
//            stmt.setString(5, aplicacion.getTelefono_proveedor());
//            stmt.setString(6, aplicacion.getNit_proveedor());
//            stmt.setString(7, aplicacion.getEmail_proveedor());
//            stmt.setString(8, aplicacion.getEstatus_proveedor());
             
            
            
            
            perfil.add(usr);
             }
         }catch (Exception e){
         }
         return perfil;
     }
 
}