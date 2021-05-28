/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comercial.datos;
import Comercial.dominio.Compra;
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
public class CompraDAO {
  private static final String SQL_SELECT = "SELECT Id_Factura, Id_proveedor, Nombre_proveedor,  Nit_proveedor, Id_producto, Nombre_producto, Precio_producto, Cantidad_producto, Subtotal_producto, Total_producto, Fecha_producto, Pago_producto FROM tbl_compra";
 private static final String SQL_INSERT = "INSERT INTO tbl_compra (Id_Factura, Id_proveedor, Nombre_proveedor,  Nit_proveedor, Id_producto, Nombre_producto, Precio_producto, Cantidad_producto, Subtotal_producto, Total_producto, Fecha_producto, Pago_producto) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
// private static final String SQL_UPDATE = "UPDATE tbl_proveedor SET   nombre_proveedor= ?, direccion_proveedor= ?, contacto_proveedor= ?, telefono_proveedor= ?, nit_proveedor= ?,  email_proveedor= ?, estatus_proveedor= ?    WHERE PK_id_proveedor= ?";
private static final String SQL_UPDATE = "UPDATE tbl_compra  SET     Id_Factura=?, Id_proveedor=?, Nombre_proveedor=?,  Nit_proveedor=?, Id_producto=?, Nombre_producto=?, Precio_producto=?, Cantidad_producto=?, Subtotal_producto=?, Total_producto=?, Fecha_producto=?, Pago_producto=?   WHERE Id_factura=?";
 private static final String SQL_QUERY =   "SELECT       Id_Factura, Id_proveedor, Nombre_proveedor,  Nit_proveedor, Id_producto, Nombre_producto, Precio_producto, Cantidad_producto, Subtotal_producto, Total_producto, Fecha_producto, Pago_producto FROM tbl_compra WHERE Id_factura=?";
 private static final String SQL_DELETE = "DELETE FROM tbl_compra WHERE Id_factura=?";


public List<Compra> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Compra proveedor = null;
        List<Compra> proveedores = new ArrayList<Compra>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int Id_factura = rs.getInt("Id_factura");
                String Id_proveedor = rs.getString("Id_proveedor");
                String  Nombre_proveedor = rs.getString("Nombre_proveedor");
                String Nit_proveedor = rs.getString("Nit_proveedor");
                String Id_producto = rs.getString("Id_producto");
                String  Nombre_producto = rs.getString("Nombre_producto");
                String  Precio_producto = rs.getString("Precio_producto");
                String  Cantidad_producto = rs.getString("Cantidad_producto");
                String  Subtotal_producto = rs.getString("Subtotal_producto");
                String  Total_producto = rs.getString("Total_producto");
                String  Fecha_producto = rs.getString("Fecha_producto");
                String  Pago_producto = rs.getString("Pago_producto");
                   
                 
                proveedor = new Compra();
                 proveedor.setId_factura(Id_factura);
                proveedor.setId_proveedor(Id_proveedor);
                proveedor.setNombre_proveedor(Nombre_proveedor);
                proveedor.setNit_proveedor(Nit_proveedor);
                proveedor.setId_producto(Id_producto);
                proveedor.setNombre_producto(Nombre_producto);
                proveedor.setPrecio_producto(Precio_producto);
                proveedor.setCantidad_producto(Cantidad_producto);
                proveedor.setSubtotal_producto(Subtotal_producto); 
                proveedor.setTotal_producto(Total_producto);
                proveedor.setFecha_producto(Fecha_producto);
                proveedor.setPago_producto(Pago_producto);
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

 public int insert(Compra aplicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, aplicacion.getId_factura());
            stmt.setString(2, aplicacion.getId_proveedor());
            stmt.setString(3, aplicacion.getNombre_proveedor());
            stmt.setString(4, aplicacion.getNit_proveedor());
            stmt.setString(5, aplicacion.getId_producto());
            stmt.setString(6, aplicacion.getNombre_producto());
            stmt.setString(7, aplicacion.getPrecio_producto());
            stmt.setString(8, aplicacion.getCantidad_producto());
            stmt.setString(9, aplicacion.getSubtotal_producto());
            stmt.setString(10, aplicacion.getTotal_producto());
            stmt.setString(11, aplicacion.getFecha_producto());
            stmt.setString(12, aplicacion.getPago_producto());
            
             
            //System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            //System.out.println("Registros afectados:" + rows);
//            JOptionPane.showMessageDialog(null, "Registro Exitoso");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }
 public Compra query(Compra moduloC) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
//            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, moduloC.getId_factura());
            rs = stmt.executeQuery();
            while (rs.next()) {
               int Id_factura = rs.getInt("Id_factura");
                String Id_proveedor = rs.getString("Id_proveedor");
                String  Nombre_proveedor = rs.getString("Nombre_proveedor");
                String Nit_proveedor = rs.getString("Nit_proveedor");
                String Id_producto = rs.getString("Id_producto");
                String  Nombre_producto = rs.getString("Nombre_producto");
                String  Precio_producto = rs.getString("Precio_producto");
                String  Cantidad_producto = rs.getString("Cantidad_producto");
                String  Subtotal_producto = rs.getString("Subtotal_producto");
                String  Total_producto = rs.getString("Total_producto");
                String  Fecha_producto = rs.getString("Fecha_producto");
                String  Pago_producto = rs.getString("Pago_producto");
                   
                 
                
                
               
                
                moduloC = new Compra();
                moduloC .setId_factura(Id_factura);
                moduloC .setId_proveedor(Id_proveedor);
                moduloC .setNombre_proveedor(Nombre_proveedor);
                moduloC .setNit_proveedor(Nit_proveedor);
                moduloC.setId_producto(Id_producto);
                 moduloC.setNombre_producto(Nombre_producto);
                 moduloC.setPrecio_producto(Precio_producto);
                 moduloC.setCantidad_producto(Cantidad_producto);
                 moduloC.setSubtotal_producto(Subtotal_producto); 
                 moduloC.setTotal_producto(Total_producto);
                 moduloC.setFecha_producto(Fecha_producto);
                 moduloC.setPago_producto(Pago_producto);
  
                
                
                
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
 public int update(Compra  aplicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, aplicacion.getId_factura());
            stmt.setString(2, aplicacion.getId_proveedor());
            stmt.setString(3, aplicacion.getNombre_proveedor());
            stmt.setString(4, aplicacion.getNit_proveedor());
            stmt.setString(5, aplicacion.getId_producto());
           stmt.setString(6, aplicacion.getNombre_producto());
         stmt.setString(7, aplicacion.getPrecio_producto());
         stmt.setString(8, aplicacion.getCantidad_producto());
         stmt.setString(9, aplicacion.getSubtotal_producto());
         stmt.setString(10, aplicacion.getTotal_producto());
         stmt.setString(11, aplicacion.getFecha_producto());
         stmt.setString(12, aplicacion.getPago_producto());
            
            
            
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
   
  public int delete(Compra aplicacion) {

        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, aplicacion.getId_factura());
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
 
}
