/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad.dominio;
import java.awt.event.ActionEvent;
import seguridad.vista.Mantenimiento_Modulos;
import java.awt.event.ActionListener;
/**
 *
 * @author Herbert Leonel Dominguez Chavez 15/02/2021
 */
public class Modulos{
    private int codigo_modulo;
    private String nombre_modulo;
    private String descripcion_modulo;
    private String estado_modulo;
    private Mantenimiento_Modulos frm;

    public int getCodigo_modulo() {
        return codigo_modulo;
    }
    public void setCodigo_modulo(int codigo_modulo) {
        this.codigo_modulo = codigo_modulo;
    }
    public String getNombre_modulo() {
        return nombre_modulo;
    }
    public void setNombre_modulo(String nombre_modulo) {
        this.nombre_modulo = nombre_modulo;
    }
    public String getDescripcion_modulo() {
        return descripcion_modulo;
    }
    public void setDescripcion_modulo(String descripcion_modulo) {
        this.descripcion_modulo = descripcion_modulo;
    }
    public String getEstado_modulo() {
        return estado_modulo;
    }
    public void setEstado_modulo(String estado_modulo) {
        this.estado_modulo = estado_modulo;
    }
    
    @Override
    public String toString() {
        return "Modulo{" + "PK_id_modulo=" + codigo_modulo + ", nombre_modulo=" + nombre_modulo + ", descripcion_modulo=" + descripcion_modulo + ", estado_modulo=" + estado_modulo +'}';
    }

    
    }
   
