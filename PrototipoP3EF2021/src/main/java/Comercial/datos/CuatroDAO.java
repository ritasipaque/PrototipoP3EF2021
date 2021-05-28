/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comercial.datos;
import Comercial.dominio.Cuatro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author SIPAQUE.RITA
 */
public class CuatroDAO {
    
    private static final String SQL_SELECT = "SELECT Id_numero, fecha_tarjeta, Nombre_tarjeta, password FROM tbl_pago";
   private static final String SQL_INSERT = "INSERT INTO tbl_pago(Id_numero, fecha_tarjeta, Nombre_tarjeta, password) VALUES(?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE tbl_pago SET Id_numero= ? ,fecha_tarjeta=?, Nombre_tarjeta=?, password=? WHERE Id_numero = ?";
    private static final String SQL_DELETE = "DELETE FROM tbl_pago WHERE Id_numero =?";
   private static final String SQL_QUERY = "SELECT Id_numero, fecha_tarjeta, Nombre_tarjeta, password FROM tbl_pago WHERE Id_numero = ?";
    
    
    
     public List<Cuatro> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cuatro vendedor = null;
        List<Cuatro> vendedores = new ArrayList<Cuatro>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id_numero");
                String cliente = rs.getString("fecha_tarjeta");
                String nit = rs.getString("Nombre_tarjeta");
                String estatus = rs.getString("password");
                
                vendedor = new Cuatro();
                vendedor.setId_numero(id);
                vendedor.setFecha_tarjeta(cliente);
                vendedor.setNombre_tarjeta(nit);
                vendedor.setPassword(estatus);
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
     
     public int insert(Cuatro aplicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, aplicacion.getId_numero());
            stmt.setString(2, aplicacion.getFecha_tarjeta());
            stmt.setString(3, aplicacion.getNombre_tarjeta());
            stmt.setString(4, aplicacion.getPassword());
           
            
             
            //System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            //System.out.println("Registros afectados:" + rows);            stmt.setString(2, aplicacion.getCliente());

            JOptionPane.showMessageDialog(null, "Registro Exitoso");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }
     
    public int update(Cuatro  aplicacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, aplicacion.getId_numero());
            stmt.setString(2, aplicacion.getFecha_tarjeta());
            stmt.setString(3, aplicacion.getNombre_tarjeta());
            stmt.setString(4, aplicacion.getPassword());
            
            
            
            
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
    public Cuatro query(Cuatro producto) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cuatro> productos = new ArrayList<Cuatro>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, producto.getId_numero());
            rs = stmt.executeQuery();
            while (rs.next()) {
               int ID = rs.getInt("Id_numero");
                String Cliente = rs.getString("fecha_tarjeta");
                String Nit = rs.getString("Nombre_tarjeta");
                String Estatus = rs.getString("password");
                
                 
                
                producto = new Cuatro();
                producto.setId_numero(ID);
                producto.setFecha_tarjeta(Cliente);
                producto.setNombre_tarjeta(Nit);
                producto.setPassword(Estatus);
                
                //empleados.add(empleado); // Si se utiliza un ArrayList
            }
            //System.out.println("Registros buscado:" + empleado);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        //return empleados;  // Si se utiliza un ArrayList
        return producto;
    }
    
    public int delete(Cuatro aplicacion) {

        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, aplicacion.getId_numero());
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
