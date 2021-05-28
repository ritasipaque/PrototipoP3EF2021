package Finanzas.datos;

import Finanzas.dominio.ClasificacionCuenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

/**
 *
 * @author Diego Vásquez
 */
public class ClasificacionCuentaDAO extends Conexion {

    private String SQL_INSERT = "INSERT INTO ClasificacionCuenta (Codigo_clasificacion, Clasificacion_CuentaNombre, Descripcion_Clasificacion) VALUES(?,?,?)";
    private String SQL_UPDATE = "UPDATE ClasificacionCuenta SET Clasificacion_CuentaNombre = ?, Descripcion_Clasificacion = ? WHERE Codigo_clasificacion = ?";
    private String SQL_QUERY = "SELECT * FROM ClasificacionCuenta WHERE Codigo_clasificacion = ?";
    private String SQL_DELETE = "DELETE FROM ClasificacionCuenta WHERE Codigo_clasificacion = ?";
    private String SQL_SELECT = "SELECT * FROM ClasificacionCuenta";

    public int Insertar(ClasificacionCuenta objClasificacion) {

        int flagRegistro = 0;

        Connection con = null;
        PreparedStatement stmt = null;
        int row = 0;

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_INSERT);
            stmt.setString(1, objClasificacion.getCodigoClasificacion());
            stmt.setString(2, objClasificacion.getClasificacionCuenta());
            stmt.setString(3, objClasificacion.getDescripcionClasificacion());
            row = stmt.executeUpdate();

            if (row >= 1) {
                flagRegistro = 1;
            } else {
                flagRegistro = 0;
            }

        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "¡ERROR INTERNO, CONSULTE CON EL ADMINISTRADOR!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);
        }
        return flagRegistro;
    }

    public int Actualizar(ClasificacionCuenta objClasificacion) {
        int flagRegistro = 0;

        Connection con = null;
        PreparedStatement stmt = null;
        int row = 0;

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_UPDATE);
            stmt.setString(1, objClasificacion.getClasificacionCuenta());
            stmt.setString(2, objClasificacion.getDescripcionClasificacion());
            stmt.setString(3, objClasificacion.getCodigoClasificacion());
            row = stmt.executeUpdate();

            if (row >= 1) {
                flagRegistro = 1;
            } else {
                flagRegistro = 0;
            }

        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "¡ERROR INTERNO, CONSULTE CON EL ADMINISTRADOR!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);
        }
        return flagRegistro;
    }

    public int Eliminar(ClasificacionCuenta objClasificacion) {
        int flagRegistro = 0;

        Connection con = null;
        PreparedStatement stmt = null;

        int row = 0;

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_DELETE);
            stmt.setString(1, objClasificacion.getCodigoClasificacion());
            row = stmt.executeUpdate();

            if (row >= 1) {
                flagRegistro = 1;
            } else {
                flagRegistro = 0;
            }

        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "¡ERROR INTERNO, CONSULTE CON EL ADMINISTRADOR!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);
        }
        return flagRegistro;
    }

    public ClasificacionCuenta Buscar(ClasificacionCuenta objClasificacion) {

        String codigoClasificacion = "";
        String clasificacionCuenta = "";
        String descripcionClasificacion = "";

        ClasificacionCuenta objClas = null;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_QUERY);
            stmt.setString(1, objClasificacion.getCodigoClasificacion());
            rs = stmt.executeQuery();

            while (rs.next()) {
                codigoClasificacion = rs.getString("Codigo_clasificacion");
                clasificacionCuenta = rs.getString("Clasificacion_CuentaNombre");
                descripcionClasificacion = rs.getString("Descripcion_Clasificacion");
            }

            objClas = new ClasificacionCuenta();

            objClas.setCodigoClasificacion(codigoClasificacion);
            objClas.setClasificacionCuenta(clasificacionCuenta);
            objClas.setDescripcionClasificacion(descripcionClasificacion);

        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "¡ERROR INTERNO, CONSULTE CON EL ADMINISTRADOR!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);
        }

        return objClas;
    }

    public int getCantidadRegistros() {
        int cantidadRegistros = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = Conexion.getConnection();
            stmt = conn.prepareStatement("SELECT COUNT(Codigo_clasificacion) FROM ClasificacionCuenta");
            rs = stmt.executeQuery();

            while (rs.next()) {
                cantidadRegistros = rs.getInt("COUNT(Codigo_clasificacion)");
            }

        } catch (Exception ex) {
            System.out.println(ex);

        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return cantidadRegistros;
    }

    public String[][] TablaDespliegue() {

        String[][] matrixClasificacion;
        int i = 0;
        i = getCantidadRegistros();
        matrixClasificacion = new String[i][3];

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            int rowCount = 0;

            while (rs.next()) {
                matrixClasificacion[rowCount][0] = rs.getString("Codigo_clasificacion");
                matrixClasificacion[rowCount][1] = rs.getString("Clasificacion_CuentaNombre");
                matrixClasificacion[rowCount][2] = rs.getString("Descripcion_Clasificacion");
                rowCount++;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return matrixClasificacion;
    }
}
