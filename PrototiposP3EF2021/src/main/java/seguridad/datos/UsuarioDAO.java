package seguridad.datos;

import seguridad.dominio.Usuario;
import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.hash;
import seguridad.dominio.Aplicacion;

/**
 *
 * @author OtakuGT
 */
public class UsuarioDAO extends Conexion {

    private static final String SQL_SELECT = "SELECT * FROM tbl_usuario";
    private static final String SQL_INSERT = "INSERT INTO tbl_usuario(PK_id_usuario, nombre_usuario, apellido_usuario, username_usuario, password_usuario, correo_usuario, cambio_password, estado_usuario, ultima_conexion) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE tbl_usuario SET PK_id_usuario=?, nombre_usuario=?, apellido_usuario=?, username_usuario=?, password_usuario=?, correo_usuario=?, cambio_password=?, estado_usuario=?, ultima_conexion=? WHERE PK_id_usuario = ?";
    private static final String SQL_DELETE = "DELETE FROM tbl_usuario WHERE PK_id_usuario=?";
    private static final String SQL_QUERY = "SELECT PK_id_usuario, nombre_usuario, apellido_usuario, username_usuario, password_usuario, correo_usuario, cambio_password, estado_usuario, ultima_conexion FROM tbl_usuario WHERE username_usuario = ?";
    private static final String SQL_QUERY2 = "SELECT PK_id_usuario, nombre_usuario, apellido_usuario, username_usuario, password_usuario, correo_usuario, cambio_password, estado_usuario, ultima_conexion FROM tbl_usuario WHERE PK_id_usuario = ?";

    Conexion conectar = new Conexion();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Usuario usuario = null;

    public List<Usuario> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_usuario = rs.getInt("PK_id_usuario");
                String nombre = rs.getString("nombre_usuario");
                String apellido = rs.getString("apellido_usuario");
                String username = rs.getString("username_usuario");
                String pass = rs.getString("password_usuario");
                var correo = rs.getString("correo_usuario");
                int cambio = rs.getInt("cambio_password");
                int estado = rs.getInt("estado_usuario");
                String ultima = rs.getString("ultima_conexion");

                usuario = new Usuario();
                usuario.setId_usuario(id_usuario);
                usuario.setNombre_usuario(nombre);
                usuario.setApellido_usuario(apellido);
                usuario.setUser_usuario(username);
                usuario.setPassword_usuario(pass);
                usuario.setCorreo_usuario(correo);
                usuario.setCambio_password(cambio);
                usuario.setEstado_usuario(estado);
                usuario.setUltima_conexion(ultima);

                usuarios.add(usuario);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return usuarios;
    }

    public Usuario query(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, usuario.getUser_usuario());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_usuario = rs.getInt("PK_id_usuario");
                String nombre_usuario = rs.getString("nombre_usuario");
                String apellido_usuario = rs.getString("apellido_usuario");
                String user_usuario = rs.getString("username_usuario");
                String password_usuario = rs.getString("password_usuario");
                String correo_usuario = rs.getString("correo_usuario");
                int cambio_contrasena = rs.getInt("cambio_password");
                int estado_usuario = rs.getInt("estado_usuario");
                String ultima_conexion = rs.getString("ultima_conexion");

                usuario = new Usuario();
                usuario.setId_usuario(id_usuario);
                usuario.setNombre_usuario(nombre_usuario);
                usuario.setApellido_usuario(apellido_usuario);
                usuario.setUser_usuario(user_usuario);
                usuario.setPassword_usuario(password_usuario);
                usuario.setCorreo_usuario(correo_usuario);
                usuario.setCambio_password(cambio_contrasena);
                usuario.setEstado_usuario(estado_usuario);
                usuario.setUltima_conexion(ultima_conexion);

                rows++;
            }
            //System.out.println("Registros buscado:" + usuario);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return usuario;
    }
    
    public Usuario query2(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY2);
            stmt.setInt(1, usuario.getId_usuario());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_usuario = rs.getInt("PK_id_usuario");
                String nombre_usuario = rs.getString("nombre_usuario");
                String apellido_usuario = rs.getString("apellido_usuario");
                String user_usuario = rs.getString("username_usuario");
                String password_usuario = rs.getString("password_usuario");
                String correo_usuario = rs.getString("correo_usuario");
                int cambio_contrasena = rs.getInt("cambio_password");
                int estado_usuario = rs.getInt("estado_usuario");
                String ultima_conexion = rs.getString("ultima_conexion");

                usuario = new Usuario();
                usuario.setId_usuario(id_usuario);
                usuario.setNombre_usuario(nombre_usuario);
                usuario.setApellido_usuario(apellido_usuario);
                usuario.setUser_usuario(user_usuario);
                usuario.setPassword_usuario(password_usuario);
                usuario.setCorreo_usuario(correo_usuario);
                usuario.setCambio_password(cambio_contrasena);
                usuario.setEstado_usuario(estado_usuario);
                usuario.setUltima_conexion(ultima_conexion);

                rows++;
            }
            //System.out.println("Registros buscado:" + usuario);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return usuario;
    }

    public int delete(Usuario usuario) {

        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, usuario.getId_usuario());
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

    public int update(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            //System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, usuario.getId_usuario());
            stmt.setString(2, usuario.getNombre_usuario());
            stmt.setString(3, usuario.getApellido_usuario());
            stmt.setString(4, usuario.getUser_usuario());
            stmt.setString(5, usuario.getPassword_usuario());
            stmt.setString(6, usuario.getCorreo_usuario());
            stmt.setInt(7, usuario.getCambio_password());
            stmt.setInt(8, usuario.getEstado_usuario());
            stmt.setString(9, usuario.getUltima_conexion());
            stmt.setInt(10, usuario.getId_usuario());
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

    public int insert(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, usuario.getId_usuario());
            stmt.setString(2, usuario.getNombre_usuario());
            stmt.setString(3, usuario.getApellido_usuario());
            stmt.setString(4, usuario.getUser_usuario());
            stmt.setString(5, usuario.getPassword_usuario());
            stmt.setString(6, usuario.getCorreo_usuario());
            stmt.setInt(7, usuario.getCambio_password());
            stmt.setInt(8, usuario.getEstado_usuario());
            stmt.setString(9, usuario.getUltima_conexion());
            stmt.executeUpdate();
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return 1;
    }

    /*
        Autor: Diego Vásquez [9959 - 19 - 19543]
        Fecha: sábado, 20 de marzo de 2021
     
        public boolean login
       
        Función <login>, de tipo booleano, esta función hará la validación y accceso del usuario al
        sistema. Será de utilidad para las funciones posteriores de seguridad y permisos.
     */
    public boolean login(Usuario user) throws SQLException {

        //========================================//
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConnection();
        //========================================//
        try {
            ps = conexion.prepareStatement("SELECT PK_id_usuario, username_usuario, password_usuario, nombre_usuario FROM tbl_usuario WHERE username_usuario = ?");
            ps.setString(1, user.getNombre_usuario());
            rs = ps.executeQuery();

            if (rs.next()) {
                if (user.getPassword_usuario().equals(rs.getString(3))) {
                    String sql_LastSession = "UPDATE tbl_usuario SET ultima_conexion = ? WHERE PK_id_usuario = ?";
                    ps = conexion.prepareStatement(sql_LastSession);
                    ps.setString(1, user.getUltima_conexion());
                    ps.setInt(2, rs.getInt(1));
                    ps.execute();
                    //========================================//
                    user.setId_usuario(rs.getInt(1));
                    user.setUser_usuario(rs.getString(2));
                    //=======================================//
                    return true;
                }
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(conexion);
        }
        return false;
    }
}