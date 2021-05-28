package Comercial.datos;


import Comercial.dominio.Deudores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// revistar
/**
 *
 * @author Carlos Flores
 */
public class DeudoresDAO {
        private static final String SQL_INSERT = "INSERT INTO  tbl_acreedores(Id_acreedores ,acreedores,Nit,Monto,Estatus_Cliente ,telefono,producto) VALUES(?, ?,?, ?,?, ?,?)";
    private static final String SQL_SELECT = "SELECT Id_acreedores ,acreedores,Nit,Monto,Estatus_Cliente ,telefono,producto FROM  tbl_acreedores";
    private static final String SQL_QUERY = "SELECT Id_acreedores ,acreedores,Nit,Monto,Estatus_Cliente ,telefono,producto FROM  tbl_acreedores WHERE Id_acreedores  = ?";
  private static final String SQL_UPDATE = "UPDATE  tbl_acreedores SET  Id_acreedores =? ,acreedores=?,Nit=?,Monto=?,Estatus_Cliente=? ,telefono=?,producto = ? WHERE Id_acreedores ";
     private static final String SQL_DELETE = "DELETE FROM tbl_acreedores  WHERE Id_acreedores = ? ";
  
     public List<Deudores> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Deudores venta = null;
        List<Deudores> ventas = new ArrayList<Deudores>();
        try {
            /**
             *
             * conecion con sql de selecccion
             */
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                /**
                 *
                 * busqueda de datos de la bitacocora en la de usuarios
                 */
                String id_acreedores  = rs.getString("Id_acreedores");
                String acreedores  = rs.getString("acreedores");
                String nit =      rs.getString("Nit");
                String telefono = rs.getString("telefono");
                    String producto =      rs.getString("producto");
                  String monto = rs.getString("Monto");
                    String estatus_cliente  = rs.getString("Estatus_Cliente");

                /**
                 *
                 * concatenacionde de variables de de busqueda
                 */
              

                  venta = new Deudores();
                  venta.setAcreedores(acreedores);
                    venta.setId_Acreedores(id_acreedores);
                 venta.setNit(nit);
                 venta.setTelefono(telefono);
                 venta.setProducto(producto);
                    venta.setMonto(monto);
               venta.setEstatus_Cliente(estatus_cliente);
                    ventas.add(venta);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
          
    Conexion.close(stmt);
            Conexion.close(conn);
        }
  return ventas;

       
    }
  public  Deudores query(Deudores venta){
        /**
         *
         * conexion de base de datos
         */
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Deudores> ventas = new ArrayList<Deudores>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
             stmt.setString(1, venta.getId_Acreedores());
          
            rs = stmt.executeQuery();
            while (rs.next()) {
              
                String acreedores  = rs.getString("acreedores");
                String nit =      rs.getString("Nit");
                String telefono = rs.getString("telefono");
                    String producto =      rs.getString("producto");
                  String monto = rs.getString("Monto");
                    String estatus_cliente  = rs.getString("Estatus_Cliente");
                  String id = rs.getString("id_acreedores");
                /**
                 *
                 * concatenacionde de variables de de busqueda
                 */
              

                  venta = new Deudores();
                  venta.setAcreedores(acreedores);
              venta.setId_Acreedores(id);
                 venta.setNit(nit);
                 venta.setTelefono(telefono);
                 venta.setProducto(producto);
                    venta.setMonto(monto);
               venta.setEstatus_Cliente(estatus_cliente);
                    ventas.add(venta);
                /**
                 *
                 * busqueda de datos de la Acreedores en la de usuarios
                 */
                
               
            }
            //System.out.println("Registros buscado:" + vendedor);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
           
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return venta;


    
     }
//  insertat los carmpos  de  acreedores
     public int insert(Deudores insertar)  {
         ResultSet rs = null;
        Connection conn = null;
        PreparedStatement stmt = null;
      
        int rows = 0;
        try {
         
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1,  insertar.getId_Acreedores());
            stmt.setString(2,  insertar.getAcreedores());       
            stmt.setString(3,   insertar.getNit());
             stmt.setString(4,  insertar.getMonto());
                stmt.setString(5,  insertar.getEstatus_Cliente());
            stmt.setString(6,  insertar.getTelefono());
             stmt.setString(7,  insertar.getProducto());

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
//     Modificar las variables de  de acreedores
       public int update(Deudores mod) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1,   mod.getId_Acreedores());
            stmt.setString(2,   mod.getAcreedores());       
            stmt.setString(3,    mod.getNit());
             stmt.setString(4,   mod.getMonto());
             stmt.setString(5,   mod.getEstatus_Cliente());
            stmt.setString(6,   mod.getTelefono());
             stmt.setString(7,  mod.getProducto());
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

        public int delete(Deudores eliminar) {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
             
            
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
           
           System.out.println("Ejecutando query:" + SQL_DELETE);
               stmt.setString(1,  eliminar.getId_Acreedores());
         
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
