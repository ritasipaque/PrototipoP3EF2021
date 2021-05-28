package Finanzas.dominio;

import Finanzas.datos.PartidaContableDAO;
import javax.swing.JOptionPane;

/* @author Diego Vásquez*/
public class PartidaContable {

    /**
     * @return the sumaDebe
     */
    public double getSumaDebe() {
        return sumaDebe;
    }

    /**
     * @param sumaDebe the sumaDebe to set
     */
    public void setSumaDebe(double sumaDebe) {
        this.sumaDebe = sumaDebe;
    }

    /**
     * @return the sumaHaber
     */
    public double getSumaHaber() {
        return sumaHaber;
    }

    /**
     * @param sumaHaber the sumaHaber to set
     */
    public void setSumaHaber(double sumaHaber) {
        this.sumaHaber = sumaHaber;
    }

    /**
     * @return the diferencia
     */
    public double getDiferencia() {
        return diferencia;
    }

    /**
     * @param diferencia the diferencia to set
     */
    public void setDiferencia(double diferencia) {
        this.diferencia = diferencia;
    }

    /**
     * @return the cuadre
     */
    public double getCuadre() {
        return cuadre;
    }

    /**
     * @param cuadre the cuadre to set
     */
    public void setCuadre(double cuadre) {
        this.cuadre = cuadre;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the codigoPartidaContable
     */
    public String getCodigoPartidaContable() {
        return codigoPartidaContable;
    }

    /**
     * @param codigoPartidaContable the codigoPartidaContable to set
     */
    public void setCodigoPartidaContable(String codigoPartidaContable) {
        this.codigoPartidaContable = codigoPartidaContable;
    }

    /**
     * @return the fechaPartidaContable
     */
    public String getFechaPartidaContable() {
        return fechaPartidaContable;
    }

    /**
     * @param fechaPartidaContable the fechaPartidaContable to set
     */
    public void setFechaPartidaContable(String fechaPartidaContable) {
        this.fechaPartidaContable = fechaPartidaContable;
    }

    /**
     * @return the periodoFiscalPartida
     */
    public String getPeriodoFiscalPartida() {
        return periodoFiscalPartida;
    }

    /**
     * @param periodoFiscalPartida the periodoFiscalPartida to set
     */
    public void setPeriodoFiscalPartida(String periodoFiscalPartida) {
        this.periodoFiscalPartida = periodoFiscalPartida;
    }

    /**
     * @return the glosaPartidaContable
     */
    public String getGlosaPartidaContable() {
        return glosaPartidaContable;
    }

    /**
     * @param glosaPartidaContable the glosaPartidaContable to set
     */
    public void setGlosaPartidaContable(String glosaPartidaContable) {
        this.glosaPartidaContable = glosaPartidaContable;
    }

    /**
     * @return the montoCuadre
     */
    public String getMontoCuadre() {
        return montoCuadre;
    }

    /**
     * @param montoCuadre the montoCuadre to set
     */
    public void setMontoCuadre(String montoCuadre) {
        this.montoCuadre = montoCuadre;
    }

    private String codigoPartidaContable = "";
    private String fechaPartidaContable = "";
    private String periodoFiscalPartida = "";
    private String glosaPartidaContable = "";
    private String montoCuadre = "";

    PartidaContableDAO partidaContableDAO = new PartidaContableDAO();

    public String CodigoPartidaContable() {

        String CodigoRegistroAux = "";
        String CodigoRegistro = "";
        CodigoRegistroAux = partidaContableDAO.NroPartida();

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

    public String[][] getTablaRegistros() {
        String[][] matrixRegistros;

        partidaContableDAO.TablaDespliegue();

        matrixRegistros = new String[partidaContableDAO.TablaDespliegue().length][4];

        for (int i = 0; i < partidaContableDAO.TablaDespliegue().length; i++) {
            for (int j = 0; j < 4; j++) {
                matrixRegistros[i][j] = partidaContableDAO.TablaDespliegue()[i][j];
            }
        }
        return matrixRegistros;
    }

    public String[][] getTablaPartidas(PartidaContable pc) {
        String[][] matrixRegistros;

        partidaContableDAO.getDetallePartida(pc);

        matrixRegistros = new String[partidaContableDAO.getDetallePartida(pc).length][3];

        for (int i = 0; i < partidaContableDAO.getDetallePartida(pc).length; i++) {
            for (int j = 0; j < 3; j++) {
                matrixRegistros[i][j] = partidaContableDAO.getDetallePartida(pc)[i][j];
            }
        }
        return matrixRegistros;
    }

    public void RegistrarPartida(PartidaContable objPartida) {

        if (objPartida.equals(null)) {

            JOptionPane.showMessageDialog(null, "¡NO PUEDEN HABER CAMPOS VACÍOS!", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else {
            int respuesta = partidaContableDAO.RegistrarPartidaContable(objPartida);

            if (respuesta == 1) {
                JOptionPane.showMessageDialog(null, "¡REGISTRO EXITOSO!", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "¡REGISTRO ERRÓNEO!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void ejecutarCuadre(String partidaContable, String monto) {

        partidaContableDAO.cuadrarParida(partidaContable, monto);

        if (partidaContableDAO.cuadrarParida(partidaContable, monto) >= 1) {
            JOptionPane.showMessageDialog(null, "¡REGISTRO EXITOSO!", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "¡REGISTRO ERRÓNEO!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    //---------------------------------------------//
    private double sumaDebe = 0;
    private double sumaHaber = 0;
    private double diferencia = 0;
    private double cuadre = 0;
    private String mensaje = "";

}
