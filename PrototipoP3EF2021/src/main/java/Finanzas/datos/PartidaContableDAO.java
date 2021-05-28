package Finanzas.datos;

import Finanzas.dominio.PartidaContable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/* @author Diego Vásquez*/
public class PartidaContableDAO extends Conexion {

    public String NroPartida() {

        String NumeroAsientoContable = "";
        String SQL_Maximo = "SELECT MAX(Codigo_PartidaContable) FROM partidacontable";

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

    public int getCantidadRegistros() {
        int cantidadRegistros = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = Conexion.getConnection();
            stmt = conn.prepareStatement("SELECT COUNT(Codigo_PartidaContable) FROM partidacontable");
            rs = stmt.executeQuery();

            while (rs.next()) {
                cantidadRegistros = rs.getInt("COUNT(Codigo_PartidaContable)");
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
            stmt = conn.prepareStatement("SELECT * FROM partidacontable");
            rs = stmt.executeQuery();
            int rowCount = 0;

            while (rs.next()) {
                matrixClasificacion[rowCount][0] = rs.getString(1);
                matrixClasificacion[rowCount][1] = rs.getString(2);
                matrixClasificacion[rowCount][2] = rs.getString(3);
                matrixClasificacion[rowCount][3] = rs.getString(4);
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

    public int RegistrarPartidaContable(PartidaContable objpartidaContable) {

        int flagRegistro = 0;

        Connection con = null;
        PreparedStatement stmt = null;
        int row = 0;

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement("INSERT INTO partidacontable (codigo_partidacontable, fecha_partidacontable, periodo_fiscalpartida, glosa_partidacontable, monto_decuadre) VALUES (?,?,?,?,?)");
            stmt.setString(1, objpartidaContable.getCodigoPartidaContable());
            stmt.setString(2, objpartidaContable.getFechaPartidaContable());
            stmt.setString(3, objpartidaContable.getPeriodoFiscalPartida());
            stmt.setString(4, objpartidaContable.getGlosaPartidaContable());
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

    public int getDetalles(String codigo) {
        int registros = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = Conexion.getConnection();
            stmt = conn.prepareStatement("SELECT COUNT(Codigo_DetalleAsiento) FROM asientocontabledetalle WHERE partida_asiento = ?");
            stmt.setString(1, codigo);
            rs = stmt.executeQuery();

            while (rs.next()) {
                registros = rs.getInt("COUNT(Codigo_DetalleAsiento)");
            }

        } catch (Exception ex) {
            System.out.println(ex);

        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return registros;
    }

    public String[][] getDetallePartida(PartidaContable pc) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String[][] matrixPartida;
        int contadorData = 0;
        contadorData = getDetalles(pc.getCodigoPartidaContable());
        matrixPartida = new String[contadorData][3];

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement("SELECT cuentacontable_asiento, monto_debe, monto_haber FROM asientocontabledetalle \n"
                    + "INNER JOIN partidacontable ON partidacontable.Codigo_PartidaContable = asientocontabledetalle.Partida_Asiento\n"
                    + "WHERE Partida_Asiento = ?");

            stmt.setString(1, pc.getCodigoPartidaContable());
            rs = stmt.executeQuery();
            int rowCount = 0;

            while (rs.next()) {
                matrixPartida[rowCount][0] = rs.getString(1);
                matrixPartida[rowCount][1] = rs.getString(2);
                matrixPartida[rowCount][2] = rs.getString(3);
                rowCount++;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return matrixPartida;
    }

    public int cuadrarParida(String partidaContable, String monto) {

        int flagRegistro = 0;

        Connection con = null;
        PreparedStatement stmt = null;
        int row = 0;

        try {
            con = Conexion.getConnection();
            stmt = con.prepareStatement("UPDATE partidacontable SET monto_decuadre = ? WHERE codigo_partidacontable = ?");
            stmt.setString(1, monto);
            stmt.setString(2, partidaContable);
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
