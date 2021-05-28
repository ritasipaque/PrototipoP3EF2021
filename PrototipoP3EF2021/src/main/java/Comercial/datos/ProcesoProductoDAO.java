/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comercial.datos;

import Comercial.dominio.ProcesoProducto;
import Comercial.vista.Proceso_Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Diana
 */
public class ProcesoProductoDAO {

    private static final String SQL_SELECT = "SELECT PK_id_procesoproducto, nombre_producto, nombre_bodega, existencias_producto, fechaActualizacion, ProductoNuevo, NuevaExistencia, BodegasNuevaExistencia FROM tbl_proceso_producto";
    private static final String SQL_SELECT2 = "SELECT  nombre_producto, existencias_producto, fechaActualizacion, ProductoNuevo, NuevaExistencia, BodegasNuevaExistencia FROM tbl_proceso_producto";
    private static final String SQL_INSERT = "INSERT INTO tbl_proceso_producto (PK_id_procesoproducto, nombre_producto, nombre_bodega, existencias_producto, fechaActualizacion, ProductoNuevo, NuevaExistencia, BodegasNuevaExistencia) VALUES(?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE tbl_proceso_producto SET   nombre_producto= ?, nombre_bodega= ?, existencias_producto= ?, fechaActualizacion=?, ProductoNuevo=?, NuevaExistencia=?, BodegasNuevaExistencia=?  WHERE PK_id_procesoproducto= ?";
    private static final String SQL_QUERY = "SELECT PK_id_procesoproducto, nombre_producto, nombre_bodega, existencias_producto, fechaActualizacion, ProductoNuevo, NuevaExistencia, BodegasNuevaExistencia FROM tbl_proceso_producto WHERE PK_id_procesoproducto=?";
    private static final String SQL_DELETE = "DELETE FROM tbl_proceso_producto WHERE PK_id_procesoproducto=?";

     

  
  
    public List<ProcesoProducto> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ProcesoProducto procesoproducto = null;
        List<ProcesoProducto> procesoproductos = new ArrayList<ProcesoProducto>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int PK_id_procesoproducto = rs.getInt("PK_id_procesoproducto");
                String nombre_producto = rs.getString("nombre_producto");
                String nombre_bodega = rs.getString("nombre_bodega");
                String existencias_producto = rs.getString("existencias_producto");
                

                procesoproducto = new ProcesoProducto();
                procesoproducto.setPK_id_procesoproducto(PK_id_procesoproducto);
                procesoproducto.setNombre_producto(nombre_producto);
                procesoproducto.setNombre_bodega(nombre_bodega);
                procesoproducto.setExistencias_producto(existencias_producto);
                procesoproductos.add(procesoproducto);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return procesoproductos;
    }

    public List<ProcesoProducto> select2() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ProcesoProducto procesoproducto = null;
        List<ProcesoProducto> procesoproductos = new ArrayList<ProcesoProducto>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT2);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String nombre_producto = rs.getString("nombre_producto");
                String existencias_producto = rs.getString("existencias_producto");

                procesoproducto = new ProcesoProducto();
                procesoproducto.setNombre_producto(nombre_producto);
                procesoproducto.setExistencias_producto(existencias_producto);
                procesoproductos.add(procesoproducto);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return procesoproductos;
    }

    public int insert(ProcesoProducto procesoproducto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, procesoproducto.getPK_id_procesoproducto());
            stmt.setString(2, procesoproducto.getNombre_producto());
            stmt.setString(3, procesoproducto.getNombre_bodega());
            stmt.setString(4, procesoproducto.getExistencias_producto());

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

    public int update(ProcesoProducto procesoproducto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, procesoproducto.getNombre_producto());
            stmt.setString(2, procesoproducto.getNombre_bodega());
            stmt.setString(3, procesoproducto.getExistencias_producto());

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

    public ProcesoProducto query(ProcesoProducto moduloC) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
//            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, moduloC.getPK_id_procesoproducto());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int PK_id_procesoproducto = rs.getInt("PK_id_procesoproducto");
                String nombre_producto = rs.getString("nombre_producto");
                String nombre_bodega = rs.getString("nombre_bodega");
                String existencias_producto = rs.getString("existencias_producto");

                moduloC = new ProcesoProducto();
                moduloC.setPK_id_procesoproducto(PK_id_procesoproducto);
                moduloC.setNombre_producto(nombre_producto);
                moduloC.setNombre_bodega(nombre_bodega);
                moduloC.setExistencias_producto(existencias_producto);

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

    public int delete(ProcesoProducto procesoproducto) {

        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, procesoproducto.getPK_id_procesoproducto());
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

    public List<ProcesoProducto> listar() {
        List<ProcesoProducto> perfil = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                ProcesoProducto usr = new ProcesoProducto();
                usr.setPK_id_procesoproducto(rs.getInt(1));
                usr.setNombre_producto(rs.getString(2));
                usr.setNombre_bodega(rs.getString(3));
                usr.setExistencias_producto(rs.getString(4));

                perfil.add(usr);
            }
        } catch (Exception e) {
        }
        return perfil;
    }
}
