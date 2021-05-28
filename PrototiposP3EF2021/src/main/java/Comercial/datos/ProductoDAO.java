/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comercial.datos;

import Comercial.dominio.Producto;
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
 * @author familia Sipaque - Diana Victores
 */
public class ProductoDAO {

    private static final String SQL_SELECT = "SELECT PK_id_producto, nombre_producto, precio_producto, descripcion_producto, estatus_producto, bodega, fechaIngreso FROM tbl_producto";
    private static final String SQL_INSERT = "INSERT INTO tbl_producto (PK_id_producto, nombre_producto, precio_producto, descripcion_producto, estatus_producto, bodega, fechaIngreso) VALUES(?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE tbl_producto SET   nombre_producto= ?, precio_producto= ?, descripcion_producto= ?, estatus_producto= ? bodega=?, fechaIngreso=?   WHERE PK_id_producto= ?";
    private static final String SQL_QUERY = "SELECT PK_id_producto, nombre_producto, precio_producto, descripcion_producto, estatus_producto, bodega, fechaIngreso FROM tbl_producto WHERE PK_id_producto=?";
    private static final String SQL_DELETE = "DELETE FROM tbl_producto WHERE PK_id_producto=?";
    public static final String SQL_QUERY2 = "SELECT  PK_id_producto  FROM tbl_producto";
    public static final String SQL_QUERY3 = "SELECT  PK_id_producto  FROM tbl_producto";

    public List<Producto> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Producto producto = null;
        List<Producto> productos = new ArrayList<Producto>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int PK_id_producto = rs.getInt("PK_id_producto");
                String nombre_producto = rs.getString("nombre_producto");
                String precio_producto = rs.getString("precio_producto");
                String descripcion_producto = rs.getString("descripcion_producto");
                String estatus_producto = rs.getString("estatus_producto");
                String bodega = rs.getString("bodega");
                String fechaIngreso = rs.getString("fechaIngreso");

                producto = new Producto();
                producto.setPK_id_producto(PK_id_producto);
                producto.setNombre_producto(nombre_producto);
                producto.setPrecio_producto(precio_producto);
                producto.setDescripcion_producto(descripcion_producto);
                producto.setEstatus_producto(estatus_producto);
                producto.setBodega(bodega);
                producto.setFechaIngreso(fechaIngreso);
                productos.add(producto);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return productos;
    }

    public int insert(Producto aplicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, aplicacion.getPK_id_producto());
            stmt.setString(2, aplicacion.getNombre_producto());
            stmt.setString(3, aplicacion.getPrecio_producto());
            stmt.setString(4, aplicacion.getDescripcion_producto());
            stmt.setString(5, aplicacion.getEstatus_producto());
            stmt.setString(6, aplicacion.getBodega());
            stmt.setString(7, aplicacion.getFechaIngreso());
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

    public int update(Producto aplicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, aplicacion.getNombre_producto());
            stmt.setString(2, aplicacion.getPrecio_producto());
            stmt.setString(3, aplicacion.getDescripcion_producto());
            stmt.setString(4, aplicacion.getEstatus_producto());
            stmt.setString(5, aplicacion.getBodega());
            stmt.setString(6, aplicacion.getFechaIngreso());
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

    public Producto query(Producto moduloC) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
//            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, moduloC.getPK_id_producto());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_proveedor = rs.getInt("PK_id_producto");
                String nombre = rs.getString("nombre_producto");
                String apellido = rs.getString("precio_producto");
                String contacto = rs.getString("descripcion_producto");
                String estatus = rs.getString("estatus_producto");
                String bodega = rs.getString("bodega");
                String fechaIngreso = rs.getString("fechaIngreso");

                moduloC = new Producto();
                moduloC.setPK_id_producto(id_proveedor);
                moduloC.setNombre_producto(nombre);
                moduloC.setPrecio_producto(apellido);
                moduloC.setDescripcion_producto(contacto);
                moduloC.setEstatus_producto(estatus);
                moduloC.setBodega(bodega);
                moduloC.setFechaIngreso(fechaIngreso);

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

    public int delete(Producto aplicacion) {

        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, aplicacion.getPK_id_producto());
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

    public void query2(JComboBox uno) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY2);
            //stmt.setInt(1, aplicacion.getId_ModuloCbx());
            rs = stmt.executeQuery();

            uno.addItem("Seleccionar...");

            while (rs.next()) {
                uno.addItem(rs.getInt("PK_id_producto"));

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

    public List<Producto> listar() {
        List<Producto> perfil = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Producto usr = new Producto();
                usr.setPK_id_producto(rs.getInt(1));
                usr.setNombre_producto(rs.getString(2));
                usr.setPrecio_producto(rs.getString(3));
                usr.setDescripcion_producto(rs.getString(4));
                usr.setEstatus_producto(rs.getString(5));
                usr.setBodega(rs.getString(6));
                usr.setFechaIngreso(rs.getString(7));

                perfil.add(usr);
            }
        } catch (Exception e) {
        }
        return perfil;
    }
    public void query3(JComboBox uno) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY2);
            //stmt.setInt(1, aplicacion.getId_ModuloCbx());
            rs = stmt.executeQuery();

            uno.addItem("Seleccionar...");

            while (rs.next()) {
                uno.addItem(rs.getInt("PK_id_producto"));

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
}
