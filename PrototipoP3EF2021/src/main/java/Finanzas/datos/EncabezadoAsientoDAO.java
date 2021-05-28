/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Finanzas.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Finanzas.dominio.EncabezadoAsiento;

/**
 *
 * @author OtakuGT
 */
public class EncabezadoAsientoDAO {
    private static final String SQL_SELECT = "SELECT * FROM EncabezadoAsientoContable";
    private static final String SQL_INSERT = "INSERT INTO EncabezadoAsientoContable(Codigo_EncabezadoAsiento, Fecha_AsientoContable, Moneda_Asiento, Descripcion_Asiento) VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE EncabezadoAsientoContable SET Codigo_EncabezadoAsiento=?, Fecha_AsientoContable=?, Moneda_Asiento=?, Descripcion_Asiento=? WHERE Codigo_EncabezadoAsiento = ?";
    private static final String SQL_DELETE = "DELETE FROM EncabezadoAsientoContable WHERE Codigo_EncabezadoAsiento=?";
    private static final String SQL_QUERY = "SELECT Codigo_EncabezadoAsiento, Fecha_AsientoContable, Moneda_Asiento, Descripcion_Asiento FROM EncabezadoAsientoContable WHERE Codigo_EncabezadoAsiento = ?";

    
    Conexion conectar = new Conexion();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    EncabezadoAsiento EncA = null;

    public List<EncabezadoAsiento> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        EncabezadoAsiento EncA = null;
        List<EncabezadoAsiento> EncAs = new ArrayList<EncabezadoAsiento>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String CodigoEA = rs.getString("Codigo_EncabezadoAsiento");
                String Fecha = rs.getString("Fecha_AsientoContable");
                String Moneda = rs.getString("Moneda_Asiento");
                String Des = rs.getString("Descripcion_Asiento");

                EncA = new EncabezadoAsiento();
                EncA.setCodigoEA(CodigoEA);
                EncA.setFecha(Fecha);
                EncA.setMoneda(Moneda);
                EncA.setDescripcion(Des);


                EncAs.add(EncA);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return EncAs;
    }

    public EncabezadoAsiento query(EncabezadoAsiento EncA) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, EncA.getCodigoEA());
            rs = stmt.executeQuery();
            while (rs.next()) {
                String Codigo = rs.getString("Codigo_EncabezadoAsiento");
                String Fecha = rs.getString("Fecha_AsientoContable");
                String Moneda = rs.getString("Moneda_Asiento");
                String Des = rs.getString("Descripcion_Asiento");

                EncA = new EncabezadoAsiento();
                EncA.setCodigoEA(Codigo);
                EncA.setFecha(Fecha);
                EncA.setMoneda(Moneda);
                EncA.setDescripcion(Des);


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

        return EncA;
    }
    
    
    public int delete(EncabezadoAsiento EncA) {

        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            //System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, EncA.getCodigoEA());
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

    public int update(EncabezadoAsiento EncA) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            //System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, EncA.getCodigoEA());
            stmt.setString(2, EncA.getFecha());
            stmt.setString(3, EncA.getMoneda());
            stmt.setString(4, EncA.getDescripcion());
            stmt.setString(5, EncA.getCodigoEA());
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

    public int insert(EncabezadoAsiento EncA) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, EncA.getCodigoEA());
            stmt.setString(2, EncA.getFecha());
            stmt.setString(3, EncA.getMoneda());
            stmt.setString(4, EncA.getDescripcion());
            stmt.executeUpdate();
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return 1;
    }
}
