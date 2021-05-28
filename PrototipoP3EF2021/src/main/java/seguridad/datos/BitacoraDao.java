package seguridad.datos;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import seguridad.dominio.Bitacora;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seguridad.vista.Asignacion_modulos;

/**
 *
 * @author Carlos Alberto Flores De Paz
 */
public class BitacoraDao extends Conexion {

//
//            System.out.println("Host: " + hostname);
//
//            System.out.println("IP: " + addr.getHostAddress());
    public static String fechaActual() {

        java.util.Date fecha = new java.util.Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");

        return formatoFecha.format(fecha);

    }

    public static String horaActual() {

        java.util.Date fecha = new java.util.Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("hh:mm:ss");

        return formatoFecha.format(fecha);

    }

    public String gethost() throws UnknownHostException {

        InetAddress LocalHost = InetAddress.getLocalHost();
        return null;

    }

    public String getIp() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        InetAddress LocalHost = InetAddress.getLocalHost();
        return address.getHostAddress();
    }

    /**
     *
     * declaracion estatico de selec ,insert y query
     */
    private static final String SQL_INSERT = "INSERT INTO tbl_bitacora( PK_id_usuario, fecha,hora,ip,accion,tabla,host1,PK_id_Modulo) VALUES(?, ?,?, ?,?, ?,?,?)";
    private static final String SQL_SELECT = "SELECT PK_id_bitacora, PK_id_usuario, fecha,hora,ip,accion, tabla ,host1,PK_id_Modulo FROM tbl_bitacora";
    private static final String SQL_QUERY = "SELECT PK_id_bitacora, PK_id_usuario, fecha,hora,ip,accion, tabla,host1,PK_id_Modulo FROM tbl_bitacora WHERE PK_id_usuario = ?";

    /**
     *
     * seleccion de listas de la bitacora
     */
    public List<Bitacora> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Bitacora usuario = null;
        List<Bitacora> usuarios = new ArrayList<Bitacora>();
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
                String id_bitacora = rs.getString("PK_id_bitacora");
                String id_usuario = rs.getString("PK_id_usuario");
                String fecha = rs.getString("fecha");
                String hora = rs.getString("hora");
                String host = rs.getString("host1");

                /**
                 *
                 * concatenacionde de variables de de busqueda
                 */
                String ip = rs.getString("ip");
                String accion = rs.getString("accion");
                String CodigoAplicacion = rs.getString("tabla");
                String Modulo = rs.getString("PK_id_Modulo");

                usuario = new Bitacora();
                usuario.setId_Bitacora(id_bitacora);
                usuario.setId_Usuario(id_usuario);
                usuario.setFecha(fecha);
                usuario.setHora(hora);
                usuario.setHost(host);
                usuario.setIp(ip);
                usuario.setAccion(accion);
                usuario.setCodigoAplicacion(CodigoAplicacion);
                usuario.setModulo(Modulo);

                usuarios.add(usuario);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);

            Conexion.close(conn);
        }

        return usuarios;
    }

    /**
     *
     * lista de la bitacora de seleccion de datos a usuario
     */
    public Bitacora query(Bitacora usuario) throws UnknownHostException {
        /**
         *
         * conexion de base de datos
         */
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Bitacora> usuarios = new ArrayList<Bitacora>();
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, usuario.getId_Usuario());
            rs = stmt.executeQuery();
            while (rs.next()) {
                /**
                 *
                 * busqueda de datos de la bitacocora en la de usuarios
                 */
                String id_bitacora = rs.getString("PK_id_bitacora");
                String id_usuario = rs.getString("PK_id_usuario");
                String fecha = rs.getString("fecha");
                String hora = rs.getString("hora");
                String host = rs.getString("host1");
                String ip = rs.getString("ip");
                String accion = rs.getString("accion");
                String codigoAplicacion = rs.getString("tabla");
                String modulo = rs.getString("PK_id_Modulo");

                /**
                 *
                 * concatenacionde de variables de de busqueda
                 */
                usuario = new Bitacora();
                usuario.setId_Bitacora(id_bitacora);
                usuario.setId_Usuario(id_usuario);
                usuario.setFecha(fecha);
                usuario.setHora(hora);
                usuario.setHost(host);
//     

                usuario.setIp(ip);
                usuario.setAccion(accion);
                usuario.setCodigoAplicacion(codigoAplicacion);
                usuario.setModulo(modulo);

                usuarios.add(usuario);

            }
            //System.out.println("Registros buscado:" + vendedor);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuario;

    }

    public int insert(Bitacora insertar) throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        String hostname = addr.getHostName();
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            insertar.setHora(horaActual());
            insertar.setFecha(fechaActual());
            insertar.setHost(hostname);
            insertar.setIp(getIp());

            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, insertar.getId_Usuario());
            stmt.setString(2, insertar.getFecha());
            stmt.setString(3, insertar.getHora());
            stmt.setString(4, insertar.getHost());
            stmt.setString(5, insertar.getAccion());
            stmt.setString(6, insertar.getCodigoAplicacion());
            stmt.setString(7, insertar.getIp());
            stmt.setString(8, insertar.getModulo());

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
