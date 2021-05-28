package seguridad.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PermisosDAO extends Conexion {

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    private String sqlConsultarUsuario = "SELECT PK_id_usuario FROM tbl_usuario WHERE username_usuario=?";
    private String sqlConsultarAplicacion = "SELECT PK_id_aplicacion FROM tbl_usuario_aplicacion WHERE PK_id_usuario = ?";
    private String sqlCantidaddeApps = "SELECT COUNT(PK_id_aplicacion) FROM tbl_usuario_aplicacion WHERE PK_id_usuario = ?";
    private String sqlConsultarPermisos = "SELECT ingresar, consulta, modificar, eliminar, imprimir FROM tbl_usuario_aplicacion WHERE (PK_id_usuario=?) AND (PK_id_aplicacion=?)";

    private String[] infoAplicaciones;
    private String[] permisosAplicaciones;
    
    //------------------------------//
    private String nombreUsuario = "";
    //-----------------------------//

    //-----------------------------
    public int getCodigoUsuario() {
        int codigoUsuario = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(sqlConsultarUsuario);
            stmt.setString(1, getNombreUsuario());
            rs = stmt.executeQuery();

            while (rs.next()) {
                codigoUsuario = rs.getInt("PK_id_usuario");
            }

        } catch (Exception ex) {
            System.out.println(ex);

        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return codigoUsuario;
    }

    //------------------------------------
    public int getCantidadApps() {
        int cantidadApps = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(sqlCantidaddeApps);
            stmt.setInt(1, getCodigoUsuario());
            rs = stmt.executeQuery();

            while (rs.next()) {
                cantidadApps = rs.getInt("COUNT(PK_id_aplicacion)");
            }

        } catch (Exception ex) {
            System.out.println(ex);

        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return cantidadApps;
    }
    //--------------------------------

    public String[] AppsdeUsuario() {

        infoAplicaciones = new String[getCantidadApps()];

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(sqlConsultarAplicacion);
            stmt.setInt(1, getCodigoUsuario());
            rs = stmt.executeQuery();
            int rowCount = 0;

            while(rs.next()){
                infoAplicaciones[rowCount] = String.valueOf(rs.getInt("PK_id_aplicacion"));
                rowCount++;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return infoAplicaciones;
    }
    
    public String[] getPermisosApp(int codigoApp){
        permisosAplicaciones = new String[5];
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(sqlConsultarPermisos);
            stmt.setInt(1, getCodigoUsuario());
            stmt.setInt(2, codigoApp);
            rs = stmt.executeQuery();

            while(rs.next()){
                permisosAplicaciones[0] = String.valueOf(rs.getInt("ingresar"));
                permisosAplicaciones[1] = String.valueOf(rs.getInt("consulta"));
                permisosAplicaciones[2] = String.valueOf(rs.getInt("modificar"));
                permisosAplicaciones[3] = String.valueOf(rs.getInt("eliminar"));
                permisosAplicaciones[4] = String.valueOf(rs.getInt("imprimir"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return permisosAplicaciones;
    }
}