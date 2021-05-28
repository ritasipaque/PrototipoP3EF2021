/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Finanzas.dominio;

/**
 *
 * @author OtakuGT
 */
public class EncabezadoAsiento {
    private String CodigoEA;
    private String Fecha;
    private String Moneda;
    private String Descripcion;

    public EncabezadoAsiento() {
    }
    

    public String getCodigoEA() {
        return CodigoEA;
    }

    public void setCodigoEA(String CodigoEA) {
        this.CodigoEA = CodigoEA;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getMoneda() {
        return Moneda;
    }

    public void setMoneda(String Moneda) {
        this.Moneda = Moneda;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
    

    public EncabezadoAsiento(String CodigoEA, String Fecha, String Moneda, String Descripcion) {
        this.CodigoEA = CodigoEA;
        this.Fecha = Fecha;
        this.Moneda = Moneda;
        this.Descripcion = Descripcion;
    }
        @Override
    public String toString() {
        return "EncabezadoAsiento{" + "CodigoEA=" + CodigoEA + ", Fecha=" + Fecha + ", Moneda=" + Moneda + ", Descripcion=" + Descripcion + '}';
    }
    
}
