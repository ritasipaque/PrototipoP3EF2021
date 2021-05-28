/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Finanzas.datos;

import Finanzas.dominio.CuentaContable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego Vásquez
 */
public class CuentaContableDAO {

    private String SQL_INSERT = "INSERT INTO CuentaContable (Codigo_CuentaContable, Nombre_CuentaContable, Clasificacion_CuentaContable, Estado_CuentaContable, Monto_CuentaContable) VALUES(?,?,?,?,?)";
    private String SQL_UPDATE = "UPDATE CuentaContable SET Nombre_CuentaContable = ?, Estado_CuentaContable = ? WHERE Codigo_CuentaContable = ?";
    private String SQL_QUERY = "SELECT * FROM CuentaContable WHERE Codigo_CuentaContable = ?";
    private String SQL_DELETE = "DELETE FROM CuentaContable WHERE Codigo_CuentaContable = ?";
    private String SQL_SELECT = "SELECT * FROM CuentaContable";

    public int Insertar(CuentaContable objCuenta) {

        int flagRegistro = 0;

        Connection con = null;
        PreparedStatement stmt = null;
        int row = 0;

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_INSERT);
            stmt.setString(1, objCuenta.getCodigoCuentaContable());
            stmt.setString(2, objCuenta.getNombreCuentaContable());
            stmt.setString(3, objCuenta.getClasificacionCuentaContable());
            stmt.setString(4, objCuenta.getEstadoCuentaContable());
            stmt.setString(5, "0");
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

    public int Actualizar(CuentaContable objCuenta) {
        int flagRegistro = 0;

        Connection con = null;
        PreparedStatement stmt = null;
        int row = 0;

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_UPDATE);
            stmt.setString(1, objCuenta.getNombreCuentaContable());
            stmt.setString(2, objCuenta.getEstadoCuentaContable());
            stmt.setString(3, objCuenta.getCodigoCuentaContable());
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

    public boolean Eliminar(String codigo) {
        boolean flagRegistro = false;

        Connection con = null;
        PreparedStatement stmt = null;

        boolean row = false;

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_DELETE);
            stmt.setString(1, codigo);
            row = stmt.execute();

            if (row == true) {
                flagRegistro = true;
            } else {
                flagRegistro = false;
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

    public CuentaContable Buscar(CuentaContable objCuenta) {

        String codigoCuenta = "";
        String nombreCuenta = "";
        String clasificacionCuenta = "";
        String estadoCuenta = "";

        CuentaContable objCuentaAux = null;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_QUERY);
            stmt.setString(1, objCuenta.getCodigoCuentaContable());
            rs = stmt.executeQuery();

            while (rs.next()) {
                codigoCuenta = rs.getString("Codigo_CuentaContable");
                nombreCuenta = rs.getString("Nombre_CuentaContable");
                clasificacionCuenta = rs.getString("Clasificacion_CuentaContable");
                estadoCuenta = rs.getString("Estado_CuentaContable");
            }

            objCuentaAux = new CuentaContable();

            objCuentaAux.setCodigoCuentaContable(codigoCuenta);
            objCuentaAux.setNombreCuentaContable(nombreCuenta);
            objCuentaAux.setClasificacionCuentaContable(clasificacionCuenta);
            objCuentaAux.setEstadoCuentaContable(estadoCuenta);

        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "¡ERROR INTERNO, CONSULTE CON EL ADMINISTRADOR!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexion.close(con);
            Conexion.close(stmt);
        }

        return objCuentaAux;
    }

    public int getCantidadRegistros() {
        int cantidadRegistros = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = Conexion.getConnection();
            stmt = conn.prepareStatement("SELECT COUNT(Codigo_CuentaContable) FROM CuentaContable");
            rs = stmt.executeQuery();

            while (rs.next()) {
                cantidadRegistros = rs.getInt("COUNT(Codigo_CuentaContable)");
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
        matrixClasificacion = new String[i][4];

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            int rowCount = 0;

            while (rs.next()) {
                matrixClasificacion[rowCount][0] = rs.getString("Codigo_CuentaContable");
                matrixClasificacion[rowCount][1] = rs.getString("Nombre_CuentaContable");
                matrixClasificacion[rowCount][2] = rs.getString("Clasificacion_CuentaContable");
                matrixClasificacion[rowCount][3] = rs.getString("Estado_CuentaContable");
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
