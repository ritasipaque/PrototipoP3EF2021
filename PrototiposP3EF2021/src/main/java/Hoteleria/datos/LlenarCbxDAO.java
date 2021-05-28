/**
 *
 * @author Jeff
 */
package Hoteleria.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;

public class LlenarCbxDAO {

    public void llenarInt(String consulta, String mostrar, JComboBox cbxModulo) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = ConexionHoteleria.getConnection();
            stmt = conn.prepareStatement(consulta);
            rs = stmt.executeQuery();

            cbxModulo.addItem("Seleccionar...");

            while (rs.next()) {
                cbxModulo.addItem(rs.getInt(mostrar));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionHoteleria.close(rs);
            ConexionHoteleria.close(stmt);
            ConexionHoteleria.close(conn);
        }
    }

    public void llenarString(String consulta, String mostrar, JComboBox cbxModulo) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = ConexionHoteleria.getConnection();
            stmt = conn.prepareStatement(consulta);
            rs = stmt.executeQuery();

            cbxModulo.addItem("Seleccionar...");

            while (rs.next()) {
                cbxModulo.addItem(rs.getString(mostrar));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ConexionHoteleria.close(rs);
            ConexionHoteleria.close(stmt);
            ConexionHoteleria.close(conn);
        }
    }
}
