/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad.datos;

import seguridad.dominio.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import seguridad.datos.Conexion;
import seguridad.dominio.Asignacion_Aplicacion_Usuario;
import seguridad.dominio.Modulos;

/**
 *
 * @author gegmo
 */
public class Asignacion_Aplicacion_UsuarioDAO {

    private static final String SQL_SELECT = "SELECT PK_id_usuario, PK_id_aplicacion, ingresar, consulta, modificar, eliminar, imprimir FROM tbl_usuario_aplicacion";
    private static final String SQL_INSERT = "INSERT INTO tbl_usuario_aplicacion VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE tbl_usuario_aplicacion SET ingresar=?, consulta=?, modificar=?, eliminar=?, imprimir=? WHERE PK_id_usuario = ? and PK_id_aplicacion=?";
    private static final String SQL_DELETE = "DELETE FROM tbl_usuario_aplicacion WHERE PK_id_aplicacion=?";
    private static final String SQL_QUERY = "SELECT PK_id_usuario, nombre_usuario,apellido_usuario,password_usuario,cambio_password,estado_usuario  FROM tbl_usuario WHERE PK_id_usuario = ?";
    private static final String SQL_SELECT2 = "SELECT * FROM tbl_usuario";

    public List<Asignacion_Aplicacion_Usuario> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Asignacion_Aplicacion_Usuario usuarios = null;
        List<Asignacion_Aplicacion_Usuario> usuario = new ArrayList<Asignacion_Aplicacion_Usuario>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String id_usuario = rs.getString("PK_id_usuario");
                String id_app = rs.getString("PK_id_aplicacion");
                int ingresar = rs.getInt("ingresar");
                String consultar = rs.getString("consulta");
                String modificar = rs.getString("modificar");
                String eliminar = rs.getString("eliminar");
                String imprimir = rs.getString("imprimir");

                usuarios = new Asignacion_Aplicacion_Usuario();
                usuarios.setCodigo_Usuario(id_usuario);
                usuarios.setCodigo_Aplicacion(id_app);
                usuarios.setIngresar(ingresar);
                usuarios.setConsultar(consultar);
                usuarios.setModificar(modificar);
                usuarios.setEliminar(eliminar);
                usuarios.setImprimir(imprimir);
                usuario.add(usuarios);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuario;
    }

    public List<Usuario> select2() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuarios = null;
        List<Usuario> usuario = new ArrayList<Usuario>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT2);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_usuario = rs.getInt("PK_id_usuario");
                usuarios = new Usuario();
                usuarios.setId_usuario(id_usuario);
                usuario.add(usuarios);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuario;
    }

    public Usuario query(Usuario usuarioC) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
//            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setInt(1, (usuarioC.getId_usuario()));
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_persona = rs.getInt("PK_id_usuario");
                String nombre = rs.getString("nombre_usuario");
                String apellido = rs.getString("apellido_usuario");
                String password = rs.getString("password_usuario");
                String correo = rs.getString("cambio_password");
                int estado = rs.getInt("estado_usuario");

                usuarioC = new Usuario();
                usuarioC.setId_usuario(id_persona);
                usuarioC.setNombre_usuario(nombre);
                usuarioC.setApellido_usuario(apellido);
                usuarioC.setEstado_usuario(estado);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuarioC;
    }

    public int insert(Asignacion_Aplicacion_Usuario Asignacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, Asignacion.getCodigo_Usuario());
            stmt.setString(2, Asignacion.getCodigo_Aplicacion());
            stmt.setInt(3, Asignacion.getIngresar());
            stmt.setString(4, Asignacion.getConsultar());
            stmt.setString(5, Asignacion.getModificar());
            stmt.setString(6, Asignacion.getEliminar());
            stmt.setString(7, Asignacion.getImprimir());
//            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public int update(Asignacion_Aplicacion_Usuario Asignacion) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            //System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            //stmt.setInt(1, aplicacion.getId_Modulo());
            
            stmt.setInt(1, Asignacion.getIngresar());
            stmt.setString(2, Asignacion.getConsultar());
            stmt.setString(3, Asignacion.getModificar());
            stmt.setString(4, Asignacion.getEliminar());
            stmt.setString(5, Asignacion.getImprimir());
            stmt.setString(6, Asignacion.getCodigo_Usuario());
            stmt.setString(7, Asignacion.getCodigo_Aplicacion());
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
}
