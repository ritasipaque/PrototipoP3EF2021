/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comercial.dominio;

/**
 *
 * @author Diana
 */
public class Bodega {
    int PK_id_bodega;
    String nombre_bodega;
    String direccion;
    String codigo_almacen;

    public int getPK_id_bodega() {
        return PK_id_bodega;
    }

    public String getNombre_bodega() {
        return nombre_bodega;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCodigo_almacen() {
        return codigo_almacen;
    }

    public void setPK_id_bodega(int PK_id_bodega) {
        this.PK_id_bodega = PK_id_bodega;
    }

    public void setNombre_bodega(String nombre_bodega) {
        this.nombre_bodega = nombre_bodega;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCodigo_almacen(String codigo_almacen) {
        this.codigo_almacen = codigo_almacen;
    }
    
    
}
