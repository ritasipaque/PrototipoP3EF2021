/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Finanzas.dominio;

/**
 *
 * @author Nay Ale
 */
public class TipoPersona {
  private String Codigo_TipoPersona;
   private String TipoPersona_Nombres;
  
    
   public TipoPersona() {
    }
    
    public TipoPersona(String Codigo_TipoPersona, String TipoPersona_Nombres) {
        this.Codigo_TipoPersona = Codigo_TipoPersona;
        this.TipoPersona_Nombres = TipoPersona_Nombres;
    }
    
    public String getCodigo_TipoPersona() {
        return Codigo_TipoPersona;
    }

    public void setCodigo_TipoPersona(String Codigo_TipoPersona) {
        this.Codigo_TipoPersona = Codigo_TipoPersona;
    }

    public String getTipoPersona_Nombres() {
        return TipoPersona_Nombres;
    }

    public void setTipoPersona_Nombres(String TipoPersona_Nombres) {
        this.TipoPersona_Nombres = TipoPersona_Nombres;
    }
  @Override
  public String toString() {
    return "TipoPersona{" + "Codigo_TipoPersona=" + Codigo_TipoPersona + ", TipoPersona_Nombres=" + TipoPersona_Nombres +'}';
    }
}
