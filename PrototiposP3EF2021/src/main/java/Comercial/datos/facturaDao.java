package Comercial.datos;


import Comercial.dominio.Factura;
import Comercial.dominio.ProcesoProducto;
import java.net.InetAddress;
import java.net.UnknownHostException;
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

/**
 *
 * @author PERSONAL
 */
public class facturaDao {
    
   private static final String SQL_INSERT = "INSERT INTO tbl_pedido_factura(ID,Cliente,Nit,telefono,producto , Cantidad ,Precio_por_unidad,Monto,Total,tipo,numero_cliente) VALUES(?, ?,?, ?,?, ?,?,?,?,?,?)";
    private static final String SQL_SELECT = "SELECT ID,Cliente,Nit,telefono,producto , Cantidad ,Precio_por_unidad,Monto,Total,tipo,numero_cliente FROM tbl_pedido_factura";
    private static final String SQL_QUERY = "SELECT ID,Cliente,Nit,telefono,producto , Cantidad ,Precio_por_unidad,Monto,Total,tipo,numero_cliente FROM tbl_pedido_factura WHERE ID=?";
    private static final String SQL_UPDATE = "UPDATE tbl_pedido_factura  SET ID=?, Cliente=? , Nit=? , telefono=? , producto=? , Cantidad =? , Precio_por_unidad=? , Monto=? , Total =?,tipo=?, numero_cliente=? WHERE ID";
    private static final String SQL_DELETE = "DELETE FROM tbl_pedido_factura WHERE ID= ?";
  
   private static final String SQL_UPDATE2 = "UPDATE tbl_proceso_producto SET   existencias_producto= ? WHERE existencias_producto= ?";
    /**
     *
     * seleccion de listas de la bitacora
     */
    public int update2(ProcesoProducto MOD) {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
             
            
            conn = Conexion.getConnection();
           stmt = conn.prepareStatement(SQL_UPDATE2);
          
              stmt.setString(1,  MOD.getNombre_producto());
       
           

            System.out.println("ejecutando query: " + SQL_UPDATE2);
           
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
   
   
   public List<Factura> select() {
         Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Factura venta = null;
        List<Factura> ventas = new ArrayList<Factura>();
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
                 String id_cliente  = rs.getString("ID");
                String cliente  = rs.getString("Cliente");
                 String nit = rs.getString("Nit");
                String telefono = rs.getString("telefono");
                  String producto = rs.getString("producto");
                  String cantidad = rs.getString("Cantidad");
                  String  precio = rs.getString("Precio_por_unidad");
                 String monto = rs.getString("monto");
                    String total = rs.getString("Total");
                         String numero_cliente = rs.getString("numero_cliente");
                          String tipo = rs.getString("tipo");

                /**
                 *
                 * concatenacionde de variables de de busqueda
                 */
                  venta = new Factura();
                  venta.setId_cliente(id_cliente);
                  venta.setCliente(cliente);    
                    venta.setNit(nit);
                    venta.setTelefono(telefono);
                    venta.setProducto(producto);                
                    venta.setCantidad(cantidad);
                    venta.setPrecio_por_unidad(precio);
                venta.setTotalmoNto(total);
                       venta.setMonto(monto);
                       venta.setNumero(numero_cliente);
                          venta.setTipo(tipo);

                    ventas.add(venta);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);

            Conexion.close(conn);
        }
      return ventas;

    }

    /**
     *
     * lista de la bitacora de seleccion de datos a usuario
     */
    public Factura query(Factura venta){
        /**
         *
         * conexion de base de datos
         */
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Factura> ventas = new ArrayList<Factura>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, venta.getId_cliente());
            rs = stmt.executeQuery();
            while (rs.next()) {
             /**
                 *
                 * busqueda de datos de la bitacocora en la de usuarios
                 */
                    String id_cliente  = rs.getString("ID");
                String cliente  = rs.getString("Cliente");
                 String nit = rs.getString("Nit");
                String telefono = rs.getString("telefono");
                  String producto = rs.getString("producto");
                  String cantidad = rs.getString("Cantidad");
                  String  precio = rs.getString("Precio_por_unidad");
                 String monto = rs.getString("monto");
                    String total = rs.getString("Total");
                         String numero_cliente = rs.getString("numero_cliente");
                          String tipo = rs.getString("tipo");

                /**
                 *
                 * concatenacionde de variables de de busqueda
                 */
                  venta = new Factura();
                  venta.setId_cliente(id_cliente);
                  venta.setCliente(cliente);    
                    venta.setNit(nit);
                    venta.setTelefono(telefono);
                    venta.setProducto(producto);                
                    venta.setCantidad(cantidad);
                    venta.setPrecio_por_unidad(precio);
                venta.setTotalmoNto(total);
                       venta.setMonto(monto);
                       venta.setNumero(numero_cliente);
                          venta.setTipo(tipo);

                    ventas.add(venta);

                  
            }
            System.out.println("Registros buscado:" + venta);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return venta;

    }

    public int insert(Factura insertar) {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
             
            
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
      
            stmt.setString(1,  insertar.getId_cliente());
            stmt.setString(2,  insertar.getCliente());
            stmt.setString(3,  insertar.getNit());
              stmt.setString(4,   insertar.getTelefono());
            stmt.setString(5,   insertar.getProducto());
                stmt.setString(6,  insertar.getCantidad());
            stmt.setString(7,  insertar.getPrecio_por_unidad());
           
                 stmt.setString(8,  insertar.getMonto());
                      stmt.setString(9,  insertar.getTotalmoNto());
                          stmt.setString(10,  insertar.getTipo());
                              stmt.setString(11,  insertar.getNumero());

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
     
    public int update(Factura  insertar) {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
             
            
            conn = Conexion.getConnection();
           stmt = conn.prepareStatement(SQL_UPDATE);
          
            stmt.setString(1,  insertar.getId_cliente());
            stmt.setString(2,  insertar.getCliente());
            stmt.setString(3,  insertar.getNit());
              stmt.setString(4,   insertar.getTelefono());
            stmt.setString(5,   insertar.getProducto());
                stmt.setString(6,  insertar.getCantidad());
            stmt.setString(7,  insertar.getPrecio_por_unidad());
           
                 stmt.setString(8,  insertar.getMonto());
                      stmt.setString(9,  insertar.getTotalmoNto());
                          stmt.setString(10,  insertar.getTipo());
                              stmt.setString(11,  insertar.getNumero());

            System.out.println("ejecutando query: " + SQL_UPDATE);
           
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
    
     public int delete(Factura insertar) {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
             
            
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
        
          
           stmt.setString(1,  insertar.getId_cliente());
//            stmt.setString(2,  insertar.getCliente());
//            stmt.setString(3,  insertar.getNit());
//              stmt.setString(4,   insertar.getTelefono());
//            stmt.setString(5,   insertar.getProducto());
//                stmt.setString(6,  insertar.getCantidad());
//            stmt.setString(7,  insertar.getPrecio_por_unidad());
//           
//                 stmt.setString(8,  insertar.getMonto());
//                      stmt.setString(9,  insertar.getTotalmoNto());

           System.out.println("Ejecutando query:" + SQL_DELETE);
           
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
