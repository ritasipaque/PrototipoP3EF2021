package Finanzas.datos;

import Finanzas.dominio.AsientoContable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego Vásquez
 */
public class AsientoContableDAO extends Conexion {

    public String NroAsiento() {

        String NumeroAsientoContable = "";
        String SQL_Maximo = "SELECT MAX(Codigo_DetalleAsiento) FROM asientocontabledetalle";

        try {

            Connection conexion = Conexion.getConnection();
            PreparedStatement ps = conexion.prepareStatement(SQL_Maximo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                NumeroAsientoContable = rs.getString(1);
            }

        } catch (Exception ex) {
            System.out.println("¡ERROR!\n" + ex.toString());
        }

        return NumeroAsientoContable;
    }

    public int getCantidadEncabezados() {
        int cantidadRegistros = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = Conexion.getConnection();
            stmt = conn.prepareStatement("SELECT COUNT(codigo_encabezadoasiento) FROM encabezadoasientocontable");
            rs = stmt.executeQuery();

            while (rs.next()) {
                cantidadRegistros = rs.getInt("COUNT(codigo_encabezadoasiento)");
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

    public String[][] RegistrosEncabezado() {

        String[][] matrixClasificacion;
        int i = 0;
        i = getCantidadEncabezados();
        matrixClasificacion = new String[i][1];

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement("SELECT codigo_encabezadoasiento FROM encabezadoasientocontable");
            rs = stmt.executeQuery();
            int rowCount = 0;

            while (rs.next()) {
                matrixClasificacion[rowCount][0] = rs.getString(1);
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

    public int getCantidadPeriodos() {
        int cantidadRegistros = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = Conexion.getConnection();
            stmt = conn.prepareStatement("SELECT COUNT(codigo_periodofiscal) FROM periodofiscal");
            rs = stmt.executeQuery();

            while (rs.next()) {
                cantidadRegistros = rs.getInt("COUNT(codigo_periodofiscal)");
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

    public String[][] RegistrosPeriodos() {

        String[][] matrixClasificacion;
        int i = 0;
        i = getCantidadEncabezados();
        matrixClasificacion = new String[i][1];

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement("SELECT Codigo_PeriodoFiscal FROM periodofiscal");
            rs = stmt.executeQuery();
            int rowCount = 0;

            while (rs.next()) {
                matrixClasificacion[rowCount][0] = rs.getString(1);
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

    public int getCantidadTipoAsiento() {
        int cantidadRegistros = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = Conexion.getConnection();
            stmt = conn.prepareStatement("SELECT COUNT(codigo_tipoasiento) FROM tipo_asiento");
            rs = stmt.executeQuery();

            while (rs.next()) {
                cantidadRegistros = rs.getInt("COUNT(codigo_tipoasiento)");
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

    public String[] RegistrosTipoAsiento() {

        String[] matrixClasificacion;
        int i = 0;
        i = getCantidadTipoAsiento();
        matrixClasificacion = new String[i];

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement("SELECT Codigo_TipoAsiento FROM tipo_asiento");
            rs = stmt.executeQuery();
            int rowCount = 0;

            while (rs.next()) {
                matrixClasificacion[rowCount] = rs.getString(1);
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

    public int RegistrarAsientoDetalle(AsientoContable objAsientoContable) {

        int flagRegistro = 0;

        Connection con = null;
        PreparedStatement stmt = null;
        int row = 0;

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement("INSERT INTO asientocontabledetalle (Codigo_DetalleAsiento, CuentaContable_Asiento, Partida_Asiento, Encabezado_Asiento, Tipo_Asiento, Monto_Debe, Monto_Haber) VALUES (?,?,?,?,?,?,?)");
            stmt.setString(1, objAsientoContable.getCodigo_DetalleAsiento());
            stmt.setString(2, objAsientoContable.getCuentaContable_Asiento());
            stmt.setString(3, objAsientoContable.getPartida_Asiento());
            stmt.setString(4, objAsientoContable.getEncabezado_Asiento());
            stmt.setString(5, objAsientoContable.getTipo_Asiento());
            stmt.setString(6, objAsientoContable.getMonto_Debe());
            stmt.setString(7, objAsientoContable.getMonto_Haber());
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
}
