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
public class TipoAsiento {
   
    private String IDTA;
    private String Tipo;

    public TipoAsiento() {
    }

    
    public String getIDTA() {
        return IDTA;
    }

    public void setIDTA(String IDTA) {
        this.IDTA = IDTA;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    @Override
    public String toString() {
        return "TipoAsiento{" + "IDTA=" + IDTA + ", Tipo=" + Tipo + '}';
    }

    public TipoAsiento(String IDTA, String Tipo) {
        this.IDTA = IDTA;
        this.Tipo = Tipo;
    }
    
}
