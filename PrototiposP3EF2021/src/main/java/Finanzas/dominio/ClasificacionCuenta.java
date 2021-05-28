package Finanzas.dominio;

import Finanzas.datos.ClasificacionCuentaDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego Vásquez
 */
public class ClasificacionCuenta {

    private String codigoClasificacion = "";
    private String clasificacionCuenta = "";
    private String descripcionClasificacion = "";

    /**
     * @return the codigoClasificacion
     */
    public String getCodigoClasificacion() {
        return codigoClasificacion;
    }

    /**
     * @param codigoClasificacion the codigoClasificacion to set
     */
    public void setCodigoClasificacion(String codigoClasificacion) {
        this.codigoClasificacion = codigoClasificacion;
    }

    /**
     * @return the clasificacionCuenta
     */
    public String getClasificacionCuenta() {
        return clasificacionCuenta;
    }

    /**
     * @param clasificacionCuenta the clasificacionCuenta to set
     */
    public void setClasificacionCuenta(String clasificacionCuenta) {
        this.clasificacionCuenta = clasificacionCuenta;
    }

    /**
     * @return the descripcionClasificacion
     */
    public String getDescripcionClasificacion() {
        return descripcionClasificacion;
    }

    /**
     * @param descripcionClasificacion the descripcionClasificacion to set
     */
    public void setDescripcionClasificacion(String descripcionClasificacion) {
        this.descripcionClasificacion = descripcionClasificacion;
    }

    ClasificacionCuentaDAO clasificarDAO = new ClasificacionCuentaDAO();

    public void Insertar(ClasificacionCuenta objClasificacion) {
        if (objClasificacion.getCodigoClasificacion().equals("") || objClasificacion.getCodigoClasificacion().equals("") || objClasificacion.getDescripcionClasificacion().equals("")) {

            JOptionPane.showMessageDialog(null, "¡NO PUEDEN HABER CAMPOS VACÍOS!", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else {
            int respuesta = clasificarDAO.Insertar(objClasificacion);

            if (respuesta == 1) {
                JOptionPane.showMessageDialog(null, "¡REGISTRO EXITOSO!", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "¡REGISTRO ERRÓNEO!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void Actualizar(ClasificacionCuenta objClasificacion) {
        if (objClasificacion.getCodigoClasificacion().equals("") || objClasificacion.getCodigoClasificacion().equals("") || objClasificacion.getDescripcionClasificacion().equals("")) {

            JOptionPane.showMessageDialog(null, "¡NO PUEDEN HABER CAMPOS VACÍOS!", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else {
            int respuesta = clasificarDAO.Actualizar(objClasificacion);

            if (respuesta == 1) {
                JOptionPane.showMessageDialog(null, "¡ACTUALIZACIÓN EXITOSA!", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "¡ACTUALIZACIÓN ERRÓNEA!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void Eliminar(ClasificacionCuenta objClasificacion) {
        if (objClasificacion.getCodigoClasificacion().equals("")) {

            JOptionPane.showMessageDialog(null, "¡CAMPO CÓDIGO NO PUEDE ESTAR VACÍO!", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else {
            int respuesta = clasificarDAO.Eliminar(objClasificacion);

            if (respuesta == 1) {
                JOptionPane.showMessageDialog(null, "¡ELIMINACIÓN EXITOSA!", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "¡ELIMINACIÓN ERRÓNEA!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public String[] Buscar(ClasificacionCuenta objClasificacion) {

        String[] vectorObj = new String[3];

        if (objClasificacion.getCodigoClasificacion().equals("")) {

            JOptionPane.showMessageDialog(null, "¡CAMPO CÓDIGO NO PUEDE ESTAR VACÍO!", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else {

            clasificarDAO.Buscar(objClasificacion);

            vectorObj[0] = clasificarDAO.Buscar(objClasificacion).getCodigoClasificacion();
            vectorObj[1] = clasificarDAO.Buscar(objClasificacion).getClasificacionCuenta();
            vectorObj[2] = clasificarDAO.Buscar(objClasificacion).getDescripcionClasificacion();
        }

        return vectorObj;
    }

    public String[][] getTablaRegistros() {
        String[][] matrixRegistros;
        
        clasificarDAO.TablaDespliegue();
        
        matrixRegistros = new String[clasificarDAO.TablaDespliegue().length][3];

        for (int i = 0; i < clasificarDAO.TablaDespliegue().length; i++) {
            for (int j = 0; j < 3; j++) {
                matrixRegistros[i][j] = clasificarDAO.TablaDespliegue()[i][j];
            }
        }
        return matrixRegistros;
    }
}
