/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad.dominio;

/**
 *
 * @author Nay Ale
 */
public class Perfil {
    private int Pk_id_perfil;
    private String nombre_perfil;
    private String descripcion_perfil;
    private int estado_perfil;  

    public Perfil() {
    }

    public Perfil(int Pk_id_perfil, String nombre_perfil, String descripcion_perfil, int estado_perfil) {
        this.Pk_id_perfil = Pk_id_perfil;
        this.nombre_perfil = nombre_perfil;
        this.descripcion_perfil = descripcion_perfil;
        this.estado_perfil = estado_perfil;
    }
    
    public int getPk_id_perfil() {
        return Pk_id_perfil;
    }

    public void setPk_id_perfil(int Pk_id_perfil) {
        this.Pk_id_perfil = Pk_id_perfil;
    }

    public String getNombre_perfil() {
        return nombre_perfil;
    }

    public void setNombre_perfil(String nombre_perfil) {
        this.nombre_perfil = nombre_perfil;
    }

    public String getDescripcion_perfil() {
        return descripcion_perfil;
    }

    public void setDescripcion_perfil(String descripcion_perfil) {
        this.descripcion_perfil = descripcion_perfil;
    }

    public int getEstado_perfil() {
        return estado_perfil;
    }

    public void setEstado_perfil(int estado_perfil) {
        this.estado_perfil = estado_perfil;
    }
    


    @Override
     public String toString() {
    return "Perfil{" + "Pk_id_perfil=" + Pk_id_perfil + ", nombre_perfil=" + nombre_perfil + ", descripcion_perfil=" + descripcion_perfil + ", estado_perfil=" + estado_perfil+'}';
    }
     
    public String toString2() {
         return String.valueOf(Pk_id_perfil);
    }

}
