/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Finanzas.dominio;

import Finanzas.datos.CuentaContableDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego Vásquez
 */
public class CuentaContable {

    private String codigoCuentaContable;
    private String nombreCuentaContable;
    private String clasificacionCuentaContable;
    private String estadoCuentaContable;
    private String montoCuentaContable;

    /**
     * @return the codigoCuentaContable
     */
    public String getCodigoCuentaContable() {
        return codigoCuentaContable;
    }

    /**
     * @param codigoCuentaContable the codigoCuentaContable to set
     */
    public void setCodigoCuentaContable(String codigoCuentaContable) {
        this.codigoCuentaContable = codigoCuentaContable;
    }

    /**
     * @return the nombreCuentaContable
     */
    public String getNombreCuentaContable() {
        return nombreCuentaContable;
    }

    /**
     * @param nombreCuentaContable the nombreCuentaContable to set
     */
    public void setNombreCuentaContable(String nombreCuentaContable) {
        this.nombreCuentaContable = nombreCuentaContable;
    }

    /**
     * @return the clasificacionCuentaContable
     */
    public String getClasificacionCuentaContable() {
        return clasificacionCuentaContable;
    }

    /**
     * @param clasificacionCuentaContable the clasificacionCuentaContable to set
     */
    public void setClasificacionCuentaContable(String clasificacionCuentaContable) {
        this.clasificacionCuentaContable = clasificacionCuentaContable;
    }

    /**
     * @return the estadoCuentaContable
     */
    public String getEstadoCuentaContable() {
        return estadoCuentaContable;
    }

    /**
     * @param estadoCuentaContable the estadoCuentaContable to set
     */
    public void setEstadoCuentaContable(String estadoCuentaContable) {
        this.estadoCuentaContable = estadoCuentaContable;
    }

    /**
     * @return the montoCuentaContable
     */
    public String getMontoCuentaContable() {
        return montoCuentaContable;
    }

    /**
     * @param montoCuentaContable the montoCuentaContable to set
     */
    public void setMontoCuentaContable(String montoCuentaContable) {
        this.montoCuentaContable = montoCuentaContable;
    }

    CuentaContableDAO cuentaDAO = new CuentaContableDAO();

    public void Insertar(CuentaContable objCuenta) {
        if (objCuenta.getCodigoCuentaContable().equals("") || objCuenta.getNombreCuentaContable().equals("") || objCuenta.getClasificacionCuentaContable().equals("")) {

            JOptionPane.showMessageDialog(null, "¡NO PUEDEN HABER CAMPOS VACÍOS!", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else {
            int respuesta = cuentaDAO.Insertar(objCuenta);

            if (respuesta == 1) {
                JOptionPane.showMessageDialog(null, "¡REGISTRO EXITOSO!", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "¡REGISTRO ERRÓNEO!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void Actualizar(CuentaContable objCuenta) {
        if (objCuenta.getCodigoCuentaContable().equals("") || objCuenta.getNombreCuentaContable().equals("") || objCuenta.getClasificacionCuentaContable().equals("")) {

            JOptionPane.showMessageDialog(null, "¡NO PUEDEN HABER CAMPOS VACÍOS!", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else {
            int respuesta = cuentaDAO.Actualizar(objCuenta);

            if (respuesta == 1) {
                JOptionPane.showMessageDialog(null, "¡ACTUALIZACIÓN EXITOSA!", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "¡ACTUALIZACIÓN ERRÓNEA!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void Eliminar(String codigo) {
        if (codigo.equals("")) {

            JOptionPane.showMessageDialog(null, "¡CAMPO CÓDIGO NO PUEDE ESTAR VACÍO!", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else {
            boolean respuesta = cuentaDAO.Eliminar(codigo);

            if (respuesta == true) {
                JOptionPane.showMessageDialog(null, "¡ELIMINACIÓN EXITOSA!", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "¡ELIMINACIÓN ERRÓNEA!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public String[] Buscar(CuentaContable objCuenta) {

        String[] vectorObj = new String[4];

        if (objCuenta.getCodigoCuentaContable().equals("")) {

            JOptionPane.showMessageDialog(null, "¡CAMPO CÓDIGO NO PUEDE ESTAR VACÍO!", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else {

            cuentaDAO.Buscar(objCuenta);

            vectorObj[0] = cuentaDAO.Buscar(objCuenta).getCodigoCuentaContable();
            vectorObj[1] = cuentaDAO.Buscar(objCuenta).getNombreCuentaContable();
            vectorObj[2] = cuentaDAO.Buscar(objCuenta).getClasificacionCuentaContable();
            vectorObj[3] = cuentaDAO.Buscar(objCuenta).getEstadoCuentaContable();
        }

        return vectorObj;
    }

    public String[][] getTablaRegistros() {
        String[][] matrixRegistros;

        cuentaDAO.TablaDespliegue();

        matrixRegistros = new String[cuentaDAO.TablaDespliegue().length][4];

        for (int i = 0; i < cuentaDAO.TablaDespliegue().length; i++) {
            for (int j = 0; j < 4; j++) {
                matrixRegistros[i][j] = cuentaDAO.TablaDespliegue()[i][j];
            }
        }
        return matrixRegistros;
    }
}
