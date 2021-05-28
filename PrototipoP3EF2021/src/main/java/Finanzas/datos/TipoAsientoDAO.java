/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Finanzas.datos;

import Finanzas.dominio.TipoAsiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OtakuGT
 */
public class TipoAsientoDAO extends Conexion {
    private static final String SQL_SELECT = "SELECT * FROM Tipo_Asiento";
    private static final String SQL_INSERT = "INSERT INTO Tipo_Asiento(Codigo_TipoAsiento, Tipo_AsientoDesc) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE Tipo_Asiento SET Codigo_TipoAsiento=?, Tipo_AsientoDesc=? WHERE Codigo_TipoAsiento = ?";
    private static final String SQL_DELETE = "DELETE FROM Tipo_Asiento WHERE Codigo_TipoAsiento=?";
    private static final String SQL_QUERY = "SELECT Codigo_TipoAsiento, Tipo_AsientoDesc FROM Tipo_Asiento WHERE Codigo_TipoAsiento = ?";

    Finanzas.datos.Conexion conectar = new Finanzas.datos.Conexion();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    TipoAsiento PerFis = null;

    public List<TipoAsiento> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        TipoAsiento TA = null;
        List<TipoAsiento> TipoA = new ArrayList<TipoAsiento>();
        try {
            conn = Finanzas.datos.Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String id_TA = rs.getString("Codigo_TipoAsiento");
                String TAD = rs.getString("Tipo_AsientoDesc");



                TA = new TipoAsiento();
                TA.setIDTA(id_TA);
                TA.setTipo(TAD);


                TipoA.add(TA);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Finanzas.datos.Conexion.close(rs);
            Finanzas.datos.Conexion.close(stmt);
            Finanzas.datos.Conexion.close(conn);
        }

        return TipoA;
    }

    public TipoAsiento query(TipoAsiento TipoAsiento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;

        try {
            conn = Finanzas.datos.Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, TipoAsiento.getIDTA());
            rs = stmt.executeQuery();
            while (rs.next()) {
                String idTA = rs.getString("Codigo_TipoAsiento");
                String TAD = rs.getString("Tipo_AsientoDesc");


                TipoAsiento = new TipoAsiento();
                TipoAsiento.setIDTA(idTA);
                TipoAsiento.setTipo(TAD);

                rows++;
            }
            //System.out.println("Registros buscado:" + TipoAsiento);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Finanzas.datos.Conexion.close(rs);
            Finanzas.datos.Conexion.close(stmt);
            Finanzas.datos.Conexion.close(conn);
        }

        return TipoAsiento;
    }

    public int delete(TipoAsiento TipoAsiento) {

        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Finanzas.datos.Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, TipoAsiento.getIDTA());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Finanzas.datos.Conexion.close(stmt);
            Finanzas.datos.Conexion.close(conn);
        }

        return rows;
    }

    public int update(TipoAsiento TipoAsiento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Finanzas.datos.Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, TipoAsiento.getIDTA());
            stmt.setString(2, TipoAsiento.getTipo());
            stmt.setString(3, TipoAsiento.getIDTA());
            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Finanzas.datos.Conexion.close(stmt);
            Finanzas.datos.Conexion.close(conn);
        }

        return rows;
    }

    public int insert(TipoAsiento TipoAsiento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Finanzas.datos.Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, TipoAsiento.getIDTA());
            stmt.setString(2,TipoAsiento.getTipo());
            stmt.executeUpdate();
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            Finanzas.datos.Conexion.close(stmt);
            Finanzas.datos.Conexion.close(conn);
        }
        return 1;
    }
}


