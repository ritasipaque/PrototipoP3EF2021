/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad.dominio;

/**
 *
 * @author familia Sipaque
 */
public class Asignacion {
  private String PK_id_modulo;
   private String PK_id_aplicacion;

    public String getPK_id_modulo() {
        return PK_id_modulo;
    }

    public void setPK_id_modulo(String PK_id_modulo) {
        this.PK_id_modulo = PK_id_modulo;
    }

    public String getPK_id_aplicacion() {
        return PK_id_aplicacion;
    }

    public void setPK_id_aplicacion(String PK_id_aplicacion) {
        this.PK_id_aplicacion = PK_id_aplicacion;
    }

    @Override
    public String toString() {
        return "Asignacion{" + "PK_id_modulo=" + PK_id_modulo + ", PK_id_aplicacion=" + PK_id_aplicacion + '}';
    }
  
}
    

   