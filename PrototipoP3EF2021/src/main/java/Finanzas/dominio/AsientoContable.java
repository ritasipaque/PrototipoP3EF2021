package Finanzas.dominio;

import Finanzas.datos.AsientoContableDAO;
import javax.swing.JOptionPane;

/**
 * @author Diego Vásquez
 */
public class AsientoContable {

    /**
     * @return the Codigo_DetalleAsiento
     */
    public String getCodigo_DetalleAsiento() {
        return Codigo_DetalleAsiento;
    }

    /**
     * @param Codigo_DetalleAsiento the Codigo_DetalleAsiento to set
     */
    public void setCodigo_DetalleAsiento(String Codigo_DetalleAsiento) {
        this.Codigo_DetalleAsiento = Codigo_DetalleAsiento;
    }

    /**
     * @return the CuentaContable_Asiento
     */
    public String getCuentaContable_Asiento() {
        return CuentaContable_Asiento;
    }

    /**
     * @param CuentaContable_Asiento the CuentaContable_Asiento to set
     */
    public void setCuentaContable_Asiento(String CuentaContable_Asiento) {
        this.CuentaContable_Asiento = CuentaContable_Asiento;
    }

    /**
     * @return the Partida_Asiento
     */
    public String getPartida_Asiento() {
        return Partida_Asiento;
    }

    /**
     * @param Partida_Asiento the Partida_Asiento to set
     */
    public void setPartida_Asiento(String Partida_Asiento) {
        this.Partida_Asiento = Partida_Asiento;
    }

    /**
     * @return the Encabezado_Asiento
     */
    public String getEncabezado_Asiento() {
        return Encabezado_Asiento;
    }

    /**
     * @param Encabezado_Asiento the Encabezado_Asiento to set
     */
    public void setEncabezado_Asiento(String Encabezado_Asiento) {
        this.Encabezado_Asiento = Encabezado_Asiento;
    }

    /**
     * @return the Tipo_Asiento
     */
    public String getTipo_Asiento() {
        return Tipo_Asiento;
    }

    /**
     * @param Tipo_Asiento the Tipo_Asiento to set
     */
    public void setTipo_Asiento(String Tipo_Asiento) {
        this.Tipo_Asiento = Tipo_Asiento;
    }

    /**
     * @return the Monto_Debe
     */
    public String getMonto_Debe() {
        return Monto_Debe;
    }

    /**
     * @param Monto_Debe the Monto_Debe to set
     */
    public void setMonto_Debe(String Monto_Debe) {
        this.Monto_Debe = Monto_Debe;
    }

    /**
     * @return the Monto_Haber
     */
    public String getMonto_Haber() {
        return Monto_Haber;
    }

    /**
     * @param Monto_Haber the Monto_Haber to set
     */
    public void setMonto_Haber(String Monto_Haber) {
        this.Monto_Haber = Monto_Haber;
    }

    AsientoContableDAO asientoContableDAO = new AsientoContableDAO();

    public String CodigoAsientoContable() {

        String CodigoRegistroAux = "";
        String CodigoRegistro = "";
        CodigoRegistroAux = asientoContableDAO.NroAsiento();

        if (CodigoRegistroAux == null) {
            CodigoRegistro = "0000001";
        } else {
            int incremento = Integer.parseInt(CodigoRegistroAux);
            incremento = incremento + 1;

            if (incremento >= 2 && incremento <= 9) {
                CodigoRegistro = "000000" + String.valueOf(incremento);
            }
            if (incremento >= 10 && incremento <= 99) {
                CodigoRegistro = "00000" + String.valueOf(incremento);
            }
            if (incremento >= 100 && incremento <= 999) {
                CodigoRegistro = "0000" + String.valueOf(incremento);
            }
            if (incremento >= 1000 && incremento <= 9999) {
                CodigoRegistro = "000" + String.valueOf(incremento);
            }
            if (incremento >= 10000 && incremento <= 99999) {
                CodigoRegistro = "00" + String.valueOf(incremento);
            }
            if (incremento >= 100000 && incremento <= 999999) {
                CodigoRegistro = "0" + String.valueOf(incremento);
            }
        }
        return CodigoRegistro;
    }

    public String[][] getRegistrosEncabezado() {
        String[][] matrixRegistros;

        asientoContableDAO.getCantidadEncabezados();

        matrixRegistros = new String[asientoContableDAO.RegistrosEncabezado().length][1];

        for (int i = 0; i < asientoContableDAO.RegistrosEncabezado().length; i++) {
            for (int j = 0; j < 1; j++) {
                matrixRegistros[i][j] = asientoContableDAO.RegistrosEncabezado()[i][j];
            }
        }
        return matrixRegistros;
    }

    public String[][] getRegistrosPeriodos() {
        String[][] matrixRegistros;

        asientoContableDAO.getCantidadPeriodos();

        matrixRegistros = new String[asientoContableDAO.RegistrosPeriodos().length][1];

        for (int i = 0; i < asientoContableDAO.RegistrosPeriodos().length; i++) {
            for (int j = 0; j < 1; j++) {
                matrixRegistros[i][j] = asientoContableDAO.RegistrosPeriodos()[i][j];
            }
        }
        return matrixRegistros;
    }

    public String[] getTipoAsientos() {
        String[] matrixRegistros;

        asientoContableDAO.getCantidadTipoAsiento();

        matrixRegistros = new String[asientoContableDAO.RegistrosTipoAsiento().length];

        for (int i = 0; i < asientoContableDAO.RegistrosTipoAsiento().length; i++) {

            matrixRegistros[i] = asientoContableDAO.RegistrosTipoAsiento()[i];

        }
        return matrixRegistros;
    }

    public void RegistrarDetalle(AsientoContable objAsiento) {
        
        if (objAsiento.equals(null)) {

            JOptionPane.showMessageDialog(null, "¡NO PUEDEN HABER CAMPOS VACÍOS!", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else {
            int respuesta = asientoContableDAO.RegistrarAsientoDetalle(objAsiento);

            if (respuesta == 1) {
                JOptionPane.showMessageDialog(null, "¡REGISTRO EXITOSO!", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "¡REGISTRO ERRÓNEO!", "ERROR", JOptionPane.ERROR_MESSAGE);
            } 
        }
    }

    private String Codigo_DetalleAsiento;
    private String CuentaContable_Asiento;
    private String Partida_Asiento;
    private String Encabezado_Asiento;
    private String Tipo_Asiento;
    private String Monto_Debe;
    private String Monto_Haber;
}
